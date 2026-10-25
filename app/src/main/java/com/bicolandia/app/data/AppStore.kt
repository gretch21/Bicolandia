package com.bicolandia.app.data

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File
import java.io.FileOutputStream

/**
 * Everything the user saves lives here and survives closing / reopening the
 * app: favorites, visited badges, saved travel guides, recent searches, the
 * profile (name, email, hometown, interests, settings) and the profile photo.
 *
 * Storage is the app's private SharedPreferences + a JPEG in the app's private
 * files folder, so nothing leaves the device. The lists are Compose snapshot
 * lists, so every screen that reads them updates instantly when they change.
 * Call AppStore.init(context) once from MainActivity.onCreate.
 */
object AppStore {
    private const val PREFS = "bicolandia_prefs"
    private const val SEP = "|"
    private const val PHOTO_FILE = "profile_photo.jpg"

    private var prefs: SharedPreferences? = null
    private var appContext: Context? = null
    private var initialized = false
    // Which account's data is loaded ("" = nobody logged in). Each account has its own prefs + photo.
    private var userId = ""

    val favorites = mutableStateListOf<String>()
    val visited = mutableStateListOf<String>()
    val savedGuides = mutableStateListOf<String>()
    val searchHistory = mutableStateListOf<String>()
    val interests = mutableStateListOf<String>()

    var profileName by mutableStateOf("Bicol Explorer")
    var profileEmail by mutableStateOf("")
    var profileHometown by mutableStateOf("")
    private val autoAdvanceTriviaState = mutableStateOf(true)
    val autoAdvanceTrivia: Boolean get() = autoAdvanceTriviaState.value

    private val rememberSearchesState = mutableStateOf(true)
    val rememberSearches: Boolean get() = rememberSearchesState.value

    // Bumped whenever the profile photo file changes so screens reload it
    var photoVersion by mutableStateOf(0)

    fun init(context: Context) {
        if (initialized) return
        initialized = true
        appContext = context.applicationContext
        // Load the data of the account that is currently logged in (if any)
        val auth = appContext!!.getSharedPreferences("bicolandia_auth", Context.MODE_PRIVATE)
        val email = if (auth.getBoolean("logged_in", false)) auth.getString("current_email", "") ?: "" else ""
        switchUser(email)
    }

    private fun userKey(email: String): String {
        val k = email.trim().lowercase()
        if (k.isEmpty()) return ""
        return java.security.MessageDigest.getInstance("SHA-256").digest(k.toByteArray())
            .joinToString("") { "%02x".format(it) }.take(16)
    }

    private fun resetInMemory() {
        favorites.clear(); visited.clear(); savedGuides.clear(); searchHistory.clear(); interests.clear()
        profileName = "Bicol Explorer"; profileEmail = ""; profileHometown = ""
        autoAdvanceTriviaState.value = true; rememberSearchesState.value = true
    }

    /**
     * Switches all saved data to the given account (empty email = logged out, nothing loaded).
     * Every account gets its own favorites, visited places, guides, searches, profile and photo,
     * so a new account never sees another account's data.
     */
    fun switchUser(email: String) {
        val ctx = appContext ?: return
        resetInMemory()
        val id = userKey(email)
        userId = id
        if (id.isEmpty()) { prefs = null; photoVersion++; return }
        val p = ctx.getSharedPreferences("${PREFS}_$id", Context.MODE_PRIVATE)
        prefs = p
        if (!p.getBoolean("initialized", false)) {
            // One-time: data saved before accounts were separated belongs to the account it was saved under
            val legacy = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            val legacyOwner = legacy.getString("profile_email", "")?.trim()?.lowercase() ?: ""
            val e = p.edit()
            if (legacyOwner.isNotEmpty() && legacyOwner == email.trim().lowercase()) {
                for ((k, v) in legacy.all) {
                    when (v) {
                        is String -> e.putString(k, v)
                        is Boolean -> e.putBoolean(k, v)
                    }
                }
                val oldPhoto = File(ctx.filesDir, PHOTO_FILE)
                val newPhoto = File(ctx.filesDir, "profile_photo_$id.jpg")
                if (oldPhoto.exists() && !newPhoto.exists()) { try { oldPhoto.copyTo(newPhoto) } catch (_: Exception) {} }
            }
            e.putBoolean("initialized", true).apply()
        }
        load(p, "favorites", favorites)
        load(p, "visited", visited)
        load(p, "saved_guides", savedGuides)
        load(p, "search_history", searchHistory)
        load(p, "interests", interests)
        profileName = p.getString("profile_name", "Bicol Explorer") ?: "Bicol Explorer"
        profileEmail = p.getString("profile_email", "") ?: ""
        profileHometown = p.getString("profile_hometown", "") ?: ""
        autoAdvanceTriviaState.value = p.getBoolean("auto_advance_trivia", true)
        rememberSearchesState.value = p.getBoolean("remember_searches", true)
        photoVersion++
    }

    private fun load(p: SharedPreferences, key: String, target: MutableList<String>) {
        target.clear()
        val raw = p.getString(key, "") ?: ""
        if (raw.isNotEmpty()) target.addAll(raw.split(SEP).filter { it.isNotBlank() })
    }

    private fun save(key: String, list: List<String>) {
        prefs?.edit()?.putString(key, list.joinToString(SEP))?.apply()
    }

    // ---------- favorites ----------
    fun isFavorite(id: String) = favorites.contains(id)
    fun toggleFavorite(id: String) {
        if (favorites.contains(id)) favorites.remove(id) else favorites.add(id)
        save("favorites", favorites)
    }
    fun clearFavorites() { favorites.clear(); save("favorites", favorites) }

    // ---------- visited ----------
    fun isVisited(id: String) = visited.contains(id)
    fun toggleVisited(id: String) {
        if (visited.contains(id)) visited.remove(id) else visited.add(id)
        save("visited", visited)
    }
    fun clearVisited() { visited.clear(); save("visited", visited) }

    // ---------- saved travel guides ----------
    fun isGuideSaved(id: String) = savedGuides.contains(id)
    fun toggleGuide(id: String) {
        if (savedGuides.contains(id)) savedGuides.remove(id) else savedGuides.add(id)
        save("saved_guides", savedGuides)
    }
    fun clearSavedGuides() { savedGuides.clear(); save("saved_guides", savedGuides) }

    // ---------- recent searches ----------
    fun addSearch(query: String) {
        val q = query.trim()
        if (q.isEmpty() || !rememberSearches) return
        searchHistory.removeAll { it.equals(q, ignoreCase = true) }
        searchHistory.add(0, q)
        while (searchHistory.size > 8) searchHistory.removeAt(searchHistory.size - 1)
        save("search_history", searchHistory)
    }
    fun clearSearchHistory() { searchHistory.clear(); save("search_history", searchHistory) }
    fun setRememberSearches(value: Boolean) {
        rememberSearchesState.value = value
        prefs?.edit()?.putBoolean("remember_searches", value)?.apply()
        if (!value) clearSearchHistory()
    }

    // ---------- profile ----------
    fun saveProfile(name: String, email: String, hometown: String, chosenInterests: List<String>) {
        profileName = name.trim().ifEmpty { "Bicol Explorer" }
        profileEmail = email.trim()
        profileHometown = hometown.trim()
        interests.clear()
        interests.addAll(chosenInterests)
        prefs?.edit()
            ?.putString("profile_name", profileName)
            ?.putString("profile_email", profileEmail)
            ?.putString("profile_hometown", profileHometown)
            ?.apply()
        save("interests", interests)
    }
    fun setAutoAdvanceTrivia(value: Boolean) {
        autoAdvanceTriviaState.value = value
        prefs?.edit()?.putBoolean("auto_advance_trivia", value)?.apply()
    }

    // ---------- profile photo ----------
    private fun photoFile(context: Context) =
        File(context.filesDir, if (userId.isEmpty()) PHOTO_FILE else "profile_photo_$userId.jpg")

    fun hasPhoto(context: Context) = photoFile(context).exists()

    fun loadPhoto(context: Context): ImageBitmap? {
        val f = photoFile(context)
        if (!f.exists()) return null
        return try { BitmapFactory.decodeFile(f.absolutePath)?.asImageBitmap() } catch (e: Exception) { null }
    }

    /** Copies the picked image into the app's private storage (scaled down, upright) so it persists. */
    fun savePhoto(context: Context, uri: Uri): Boolean {
        return try {
            val resolver = context.contentResolver
            // 1) read the size only, to pick a sample size that keeps memory small
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            resolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, bounds) }
            var sample = 1
            val longest = maxOf(bounds.outWidth, bounds.outHeight)
            while (longest / sample > 1400) sample *= 2
            // 2) decode
            val opts = BitmapFactory.Options().apply { inSampleSize = sample }
            var bmp = resolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, opts) } ?: return false
            // 3) fix orientation from EXIF
            val degrees = resolver.openInputStream(uri)?.use {
                when (ExifInterface(it).getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)) {
                    ExifInterface.ORIENTATION_ROTATE_90 -> 90f
                    ExifInterface.ORIENTATION_ROTATE_180 -> 180f
                    ExifInterface.ORIENTATION_ROTATE_270 -> 270f
                    else -> 0f
                }
            } ?: 0f
            if (degrees != 0f) {
                val m = Matrix().apply { postRotate(degrees) }
                bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, m, true)
            }
            // 4) center-crop to a square so the round avatar always looks right
            val side = minOf(bmp.width, bmp.height)
            val square = Bitmap.createBitmap(bmp, (bmp.width - side) / 2, (bmp.height - side) / 2, side, side)
            FileOutputStream(photoFile(context)).use { square.compress(Bitmap.CompressFormat.JPEG, 90, it) }
            photoVersion++
            true
        } catch (e: Exception) {
            false
        }
    }

    fun removePhoto(context: Context) {
        photoFile(context).delete()
        photoVersion++
    }

    /** Wipes everything the app has stored for this user. */
    fun eraseAll(context: Context) {
        favorites.clear(); visited.clear(); savedGuides.clear(); searchHistory.clear(); interests.clear()
        profileName = "Bicol Explorer"; profileEmail = ""; profileHometown = ""
        autoAdvanceTriviaState.value = true; rememberSearchesState.value = true
        prefs?.edit()?.clear()?.putBoolean("initialized", true)?.apply()
        removePhoto(context)
    }
}
