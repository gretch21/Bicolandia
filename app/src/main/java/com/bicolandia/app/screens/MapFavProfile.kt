package com.bicolandia.app.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import com.bicolandia.app.data.*
import com.bicolandia.app.ui.theme.*

object FavoritesStore {
  val favoriteIds: MutableList<String> get() = AppStore.favorites
  fun toggle(id: String) = AppStore.toggleFavorite(id)
}

private data class FavItem(
  val id: String,
  val name: String,
  val group: String,    // filter chip group
  val label: String,    // small pill on the card
  val description: String,
  val colorHex: String,
  val icon: ImageVector,
  val route: String
)

private val favoriteFilters = listOf("All", "Provinces", "Destinations", "Activities", "Food", "Festivals", "Culture", "Stays")

private fun resolveFavorite(id: String): FavItem? {
  provinces.find { it.id == id }?.let {
    return FavItem(it.id, it.name, "Provinces", "PROVINCE", it.about, it.colorHex, Icons.Filled.Terrain, "province_detail/${it.id}")
  }
  findPlaceById(id)?.let { p ->
    val group = when {
      destinationClassifications.any { it.title == p.category } -> "Destinations"
      activityClassifications.any { it.title == p.category } -> "Activities"
      festivalClassifications.any { it.title == p.category } -> "Festivals"
      cuisineClassifications.any { it.title == p.category } -> "Food"
      else -> "Culture"
    }
    return FavItem(p.id, p.name, group, p.category.uppercase(), p.shortDescription, p.colorHex, iconFor(p.category), "place_detail/${p.id}")
  }
  findAccommodationById(id)?.let {
    return FavItem(it.id, it.name, "Stays", it.type.uppercase(), it.shortDescription, it.colorHex, Icons.Filled.Hotel, "stay_detail/${it.id}")
  }
  return null
}

@Composable
fun FavoritesScreen(onOpen: (String) -> Unit = {}) {
  var filter by remember { mutableStateOf("All") }
  var confirmClear by remember { mutableStateOf(false) }
  val all = AppStore.favorites.mapNotNull { resolveFavorite(it) }.reversed()  // newest first
  val shown = if (filter == "All") all else all.filter { it.group == filter }

  if (confirmClear) {
    AlertDialog(
      onDismissRequest = { confirmClear = false },
      title = { Text("Remove all favorites?") },
      text = { Text("This clears every place you saved. You can always save them again.") },
      confirmButton = { TextButton(onClick = { AppStore.clearFavorites(); confirmClear = false }) { Text("Remove all", color = BicolCoral) } },
      dismissButton = { TextButton(onClick = { confirmClear = false }) { Text("Cancel") } }
    )
  }

  Scaffold(containerColor = BicolBg) { padding ->
    Column(Modifier.padding(padding)) {
      Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
          Text("Favorites", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = BicolTextPrimary)
          Text(
            if (all.isEmpty()) "Your curated Bicol collection" else "${all.size} saved · your curated Bicol collection",
            color = BicolTextSecondary, fontSize = 13.sp
          )
        }
        if (all.isNotEmpty()) RoundIconButton(Icons.Filled.DeleteSweep, onClick = { confirmClear = true })
      }
      FilterChipsRow(favoriteFilters, filter) { filter = it }
      Spacer(Modifier.height(8.dp))

      if (shown.isEmpty()) {
        Box(Modifier.fillMaxSize().padding(bottom = 100.dp), contentAlignment = Alignment.Center) {
          Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 32.dp)) {
            Box(Modifier.size(84.dp).clip(CircleShape).background(BicolCoral.copy(alpha = 0.10f)), contentAlignment = Alignment.Center) {
              Icon(Icons.Filled.FavoriteBorder, contentDescription = null, modifier = Modifier.size(40.dp), tint = BicolCoral)
            }
            Spacer(Modifier.height(12.dp))
            Text(if (all.isEmpty()) "No favorites yet" else "Nothing saved in $filter", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(4.dp))
            Text(
              "Tap the heart on any province, place, festival, dish or stay to save it here. Your favorites stay on your device.",
              color = BicolTextSecondary, fontSize = 12.sp, textAlign = TextAlign.Center, lineHeight = 17.sp
            )
          }
        }
      } else {
        LazyColumn {
          items(shown, key = { it.id }) { f ->
            Card(
              Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).clickable { onOpen(f.route) },
              shape = RoundedCornerShape(20.dp),
              elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
              Column {
                Box(Modifier.fillMaxWidth()) {
                  PlaceholderImage(f.name, f.colorHex, 150.dp, icon = f.icon, shape = RoundedCornerShape(0.dp), imageId = f.id)
                  Box(Modifier.align(Alignment.TopStart).padding(10.dp)) {
                    Box(Modifier.clip(RoundedCornerShape(6.dp)).background(Color.Black.copy(alpha = 0.5f)).padding(horizontal = 8.dp, vertical = 4.dp)) {
                      Text(f.label, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                  }
                  Box(
                    Modifier.align(Alignment.TopEnd).padding(10.dp).size(32.dp).clip(CircleShape).background(Color.White).clickable { AppStore.toggleFavorite(f.id) },
                    contentAlignment = Alignment.Center
                  ) { Icon(Icons.Filled.Favorite, contentDescription = "Remove from favorites", tint = BicolCoral, modifier = Modifier.size(16.dp)) }
                }
                Column(Modifier.padding(14.dp)) {
                  Text(f.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                  Spacer(Modifier.height(2.dp))
                  Text(f.description, color = BicolTextSecondary, fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                  Spacer(Modifier.height(8.dp))
                  Text("View Details →", color = BicolCoral, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                }
              }
            }
          }
          item { Spacer(Modifier.height(120.dp)) }
        }
      }
    }
  }
}

@Composable
fun ProfileScreen(
  onMyTrips: () -> Unit = {},
  onSavedGuides: () -> Unit = {},
  onAccount: () -> Unit = {},
  onPrivacy: () -> Unit = {},
  onHelp: () -> Unit = {},
  onFavorites: () -> Unit = {},
  onLogout: () -> Unit = {}
) {
  val context = LocalContext.current
  val photo = rememberProfilePhoto()
  val picker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
    if (uri != null) {
      val ok = AppStore.savePhoto(context, uri)
      Toast.makeText(context, if (ok) "Profile photo updated" else "Couldn't use that picture, please try another", Toast.LENGTH_SHORT).show()
    }
  }
  val level = TripProgress.level()
  val visited = TripProgress.visitedCount()
  val badgesEarned = TripProgress.earnedBadgeCount()
  val provincesTouched = TripProgress.provincesTouched()
  val nextText = if (level.nextAt != null) {
    val left = level.nextAt - level.current
    "$left more ${if (left == 1) "place" else "places"} to reach ${level.nextName}"
  } else "You've reached the top level. Salamat, Bicolano!"
  val fraction = if (level.nextAt == null) 1f else {
    val base = listOf(0, 1, 5, 15, 30).last { it <= level.current }
    ((level.current - base).toFloat() / (level.nextAt - base)).coerceIn(0f, 1f)
  }

  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      // ---------- header ----------
      item {
        Box(
          Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(Brush.verticalGradient(listOf(BicolDeepGreen, Color(0xFF2E7D32))))
            .padding(bottom = 46.dp)
        ) {
          Icon(
            Icons.Filled.Terrain, contentDescription = null, tint = Color.White.copy(alpha = 0.10f),
            modifier = Modifier.align(Alignment.TopEnd).size(150.dp).offset(x = 30.dp, y = 10.dp)
          )
          Column(Modifier.fillMaxWidth().padding(top = 18.dp, start = 20.dp, end = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
              Text("Profile", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp, modifier = Modifier.weight(1f))
              RoundIconButton(Icons.Filled.Edit, bg = Color.White.copy(alpha = 0.2f), tint = Color.White, onClick = onAccount)
            }
            Spacer(Modifier.height(14.dp))
            Box(contentAlignment = Alignment.BottomEnd) {
              Box(
                Modifier
                  .size(112.dp)
                  .border(3.dp, Brush.linearGradient(listOf(BicolCoral, BicolOrange)), CircleShape)
                  .padding(5.dp)
                  .clip(CircleShape)
                  .background(Color.White.copy(alpha = 0.25f))
                  .clickable { picker.launch("image/*") },
                contentAlignment = Alignment.Center
              ) {
                if (photo != null) {
                  Image(bitmap = photo, contentDescription = "Profile photo", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                } else {
                  Text(
                    AppStore.profileName.trim().take(1).uppercase().ifEmpty { "B" },
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 42.sp
                  )
                }
              }
              Box(
                Modifier.size(34.dp).clip(CircleShape).background(BicolCoral).border(2.dp, Color.White, CircleShape).clickable { picker.launch("image/*") },
                contentAlignment = Alignment.Center
              ) { Icon(Icons.Filled.CameraAlt, contentDescription = "Change profile photo", tint = Color.White, modifier = Modifier.size(17.dp)) }
            }
            Spacer(Modifier.height(12.dp))
            Text(AppStore.profileName, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            if (AppStore.profileHometown.isNotBlank()) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.LocationOn, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(13.dp))
                Text(" ${AppStore.profileHometown}", color = Color.White.copy(alpha = 0.9f), fontSize = 12.5.sp)
              }
            }
            Spacer(Modifier.height(10.dp))
            Row(
              Modifier.clip(RoundedCornerShape(20.dp)).background(Color.White.copy(alpha = 0.18f)).padding(horizontal = 14.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(Icons.Filled.EmojiEvents, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(15.dp))
              Spacer(Modifier.width(6.dp))
              Text(level.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
            }
            Spacer(Modifier.height(6.dp))
            Text("Tap your photo to change it", color = Color.White.copy(alpha = 0.7f), fontSize = 10.5.sp)
          }
        }
      }

      // ---------- live stats (overlap the header) ----------
      item {
        Card(
          Modifier.fillMaxWidth().padding(horizontal = 20.dp).offset(y = (-34).dp),
          shape = RoundedCornerShape(20.dp),
          elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
          Row(Modifier.fillMaxWidth().padding(vertical = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            StatBlock(Icons.Filled.Terrain, "$provincesTouched/6", "PROVINCES")
            StatBlock(Icons.Filled.PinDrop, "$visited", "VISITED")
            StatBlock(Icons.Filled.EmojiEvents, "$badgesEarned", "BADGES")
            StatBlock(Icons.Filled.Favorite, "${AppStore.favorites.size}", "FAVORITES")
          }
        }
      }

      item {
        Column(Modifier.offset(y = (-18).dp).padding(horizontal = 20.dp)) {
          // ---------- level progress ----------
          Card(
            Modifier.fillMaxWidth().clickable(onClick = onMyTrips),
            shape = RoundedCornerShape(18.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
          ) {
            Column(Modifier.padding(16.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                  Text("YOUR BICOL JOURNEY", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                  Text(level.name, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary)
                }
                Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = BicolTextSecondary)
              }
              Spacer(Modifier.height(10.dp))
              Box(Modifier.fillMaxWidth().height(9.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xFFE5E7EB))) {
                Box(Modifier.fillMaxWidth(fraction).fillMaxHeight().clip(RoundedCornerShape(5.dp)).background(Brush.horizontalGradient(listOf(BicolCoral, BicolOrange))))
              }
              Spacer(Modifier.height(8.dp))
              Text(nextText, color = BicolTextSecondary, fontSize = 12.sp)
            }
          }

          Spacer(Modifier.height(16.dp))
          SectionLabel("Adventure Details")
          SettingsRow(Icons.Filled.Luggage, Color(0xFF2E7D32), "My Trips", "Bicol Map · $visited of ${TripProgress.totalPlaces()} places", onMyTrips)
          SettingsRow(Icons.Filled.Bookmark, Color(0xFF0288D1), "Saved Guides", "${AppStore.savedGuides.size} saved", onSavedGuides)
          SettingsRow(Icons.Filled.Favorite, BicolCoral, "Favorites", "${AppStore.favorites.size} saved", onFavorites)

          Spacer(Modifier.height(10.dp))
          SectionLabel("Settings & Privacy")
          SettingsRow(Icons.Filled.ManageAccounts, Color(0xFF6A1B9A), "Account Settings", "Name, hometown, interests", onAccount)
          SettingsRow(Icons.Filled.Shield, Color(0xFF00695C), "Privacy & Security", "Your data and controls", onPrivacy)
          SettingsRow(Icons.Filled.Help, Color(0xFFEF6C00), "Help & Support", "FAQs and contacts", onHelp)

          Spacer(Modifier.height(14.dp))
          Box(
            Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(14.dp))
              .background(Color(0xFFFDE7E5))
              .clickable(onClick = onLogout)
              .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Filled.Logout, contentDescription = null, tint = BicolCoral, modifier = Modifier.size(16.dp))
              Text(" Logout", color = BicolCoral, fontWeight = FontWeight.SemiBold)
            }
          }
          Spacer(Modifier.height(120.dp))
        }
      }
    }
  }
}

@Composable
private fun StatBlock(icon: ImageVector, value: String, label: String) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Box(Modifier.size(30.dp).clip(CircleShape).background(Color(0xFFE3F1E8)), contentAlignment = Alignment.Center) {
      Icon(icon, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(16.dp))
    }
    Spacer(Modifier.height(4.dp))
    Text(value, fontWeight = FontWeight.Bold, fontSize = 17.sp)
    Text(label, color = BicolTextSecondary, fontSize = 9.sp, fontWeight = FontWeight.SemiBold)
  }
}

@Composable
private fun SectionLabel(text: String) {
  Text(text, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = BicolTextPrimary, modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
private fun SettingsRow(icon: ImageVector, tint: Color, label: String, subtitle: String, onClick: () -> Unit) {
  Row(
    Modifier
      .fillMaxWidth()
      .padding(bottom = 8.dp)
      .clip(RoundedCornerShape(14.dp))
      .background(Color.White)
      .clickable(onClick = onClick)
      .padding(14.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(Modifier.size(38.dp).clip(RoundedCornerShape(11.dp)).background(tint.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) {
      Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(19.dp))
    }
    Spacer(Modifier.width(12.dp))
    Column(Modifier.weight(1f)) {
      Text(label, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = BicolTextPrimary)
      Text(subtitle, fontSize = 11.sp, color = BicolTextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
    Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = BicolTextSecondary)
  }
}
