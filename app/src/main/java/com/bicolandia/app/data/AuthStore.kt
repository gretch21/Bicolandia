package com.bicolandia.app.data

import android.content.Context
import android.content.SharedPreferences
import java.security.MessageDigest

/**
 * Local accounts + "stay logged in".
 *
 * - Accounts created on the Sign Up screen are saved on this device (private
 *   SharedPreferences; the password is stored as a SHA-256 hash, never as plain text).
 * - Log In checks the email + password against a saved account.
 * - The logged-in state and "onboarding already seen" are remembered, so reopening
 *   the app goes logo -> dashboard. The user only returns to Login by tapping Logout.
 *
 * Kept in its own preferences file so "Erase my data" in Privacy does not delete
 * the account or log the user out. Call AuthStore.init(context) once at startup.
 */
object AuthStore {
    private const val PREFS = "bicolandia_auth"
    private var prefs: SharedPreferences? = null

    fun init(context: Context) {
        if (prefs != null) return
        prefs = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

    val isLoggedIn: Boolean get() = prefs?.getBoolean("logged_in", false) ?: false
    val onboardingDone: Boolean get() = prefs?.getBoolean("onboarding_done", false) ?: false

    fun markOnboardingDone() { prefs?.edit()?.putBoolean("onboarding_done", true)?.apply() }

    private fun key(email: String) = email.trim().lowercase()

    private fun hash(password: String): String =
        MessageDigest.getInstance("SHA-256").digest(password.toByteArray()).joinToString("") { "%02x".format(it) }

    /** Saves a new account. Returns false if that email is already registered. */
    fun register(name: String, email: String, password: String): Boolean {
        val p = prefs ?: return false
        val k = key(email)
        if (p.contains("hash_$k")) return false
        p.edit()
            .putString("name_$k", name.trim())
            .putString("hash_$k", hash(password))
            .apply()
        return true
    }

    /** Checks the credentials; on success the user stays logged in until they log out. */
    fun login(email: String, password: String): Boolean {
        val p = prefs ?: return false
        val k = key(email)
        val saved = p.getString("hash_$k", null) ?: return false
        if (saved != hash(password)) return false
        p.edit().putBoolean("logged_in", true).putString("current_email", k).apply()
        // Load this account's own saved data (favorites, visited, profile, photo...)
        AppStore.switchUser(k)
        // Show this account's name and email on the Profile screen
        val name = p.getString("name_$k", "") ?: ""
        if (AppStore.profileEmail != k || (name.isNotBlank() && AppStore.profileName != name)) {
            AppStore.saveProfile(name.ifBlank { AppStore.profileName }, k, AppStore.profileHometown, AppStore.interests.toList())
        }
        return true
    }

    fun logout() {
        prefs?.edit()?.putBoolean("logged_in", false)?.apply()
        AppStore.switchUser("")
    }
}
