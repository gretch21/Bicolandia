package com.bicolandia.app.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.*
import com.bicolandia.app.ui.theme.*

@Composable
fun SavedGuidesScreen(onBack: () -> Unit, onOpenGuide: (String) -> Unit, onBrowseGuides: () -> Unit) {
  val subjects = AppStore.savedGuides.mapNotNull { resolveTravelSubject(it) }.reversed()
  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar("Saved Guides", "Your travel guides, ready offline", onBack, trailingIcon = null) }
      if (subjects.isEmpty()) {
        item {
          Column(
            Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Box(Modifier.size(84.dp).clip(CircleShape).background(Color(0xFF0288D1).copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
              Icon(Icons.Filled.BookmarkBorder, contentDescription = null, tint = Color(0xFF0288D1), modifier = Modifier.size(40.dp))
            }
            Spacer(Modifier.height(12.dp))
            Text("No saved guides yet", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(4.dp))
            Text(
              "Open the Travel Guide, find a place and tap the bookmark to keep its directions here.",
              color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 17.sp,
              textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(Modifier.height(16.dp))
            Box(
              Modifier.clip(RoundedCornerShape(20.dp)).background(BicolDeepGreen).clickable(onClick = onBrowseGuides).padding(horizontal = 20.dp, vertical = 11.dp)
            ) { Text("Browse Travel Guides", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp) }
          }
        }
      } else {
        item {
          Text(
            "${subjects.size} saved ${if (subjects.size == 1) "guide" else "guides"}",
            color = BicolTextSecondary, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
          )
        }
        items(subjects, key = { it.id }) { s ->
          Card(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp).clickable { onOpenGuide(s.id) },
            shape = RoundedCornerShape(18.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              PlaceholderImage(s.name, s.colorHex, 92.dp, modifier = Modifier.width(96.dp), icon = iconFor(s.category), shape = RoundedCornerShape(topStart = 18.dp, bottomStart = 18.dp), imageId = s.id)
              Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text(s.name, fontWeight = FontWeight.Bold, fontSize = 14.5.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("${s.category} · ${s.location}", color = BicolTextSecondary, fontSize = 11.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(Icons.Filled.DirectionsBus, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(13.dp))
                  Text(" View directions", color = BicolDeepGreen, fontWeight = FontWeight.SemiBold, fontSize = 11.5.sp)
                }
              }
              Box(
                Modifier.padding(end = 10.dp).size(36.dp).clip(CircleShape).background(Color(0xFFF0F1F3)).clickable { AppStore.toggleGuide(s.id) },
                contentAlignment = Alignment.Center
              ) { Icon(Icons.Filled.Bookmark, contentDescription = "Remove saved guide", tint = BicolCoral, modifier = Modifier.size(18.dp)) }
            }
          }
        }
      }
      item { Spacer(Modifier.height(120.dp)) }
    }
  }
}

private val interestOptions = listOf("Nature & Adventure", "Culture & Heritage", "Food & Culinary", "Beaches & Islands", "Festivals", "Photography")

@Composable
fun AccountSettingsScreen(onBack: () -> Unit) {
  val context = LocalContext.current
  var name by remember { mutableStateOf(AppStore.profileName) }
  var email by remember { mutableStateOf(AppStore.profileEmail) }
  var hometown by remember { mutableStateOf(AppStore.profileHometown) }
  val chosen = remember { mutableStateListOf<String>().apply { addAll(AppStore.interests) } }
  val photo = rememberProfilePhoto()
  val picker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
    if (uri != null) {
      val ok = AppStore.savePhoto(context, uri)
      Toast.makeText(context, if (ok) "Profile photo updated" else "Couldn't use that picture, please try another", Toast.LENGTH_SHORT).show()
    }
  }
  val fieldColors = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = BicolDeepGreen, focusedLabelColor = BicolDeepGreen, cursorColor = BicolDeepGreen,
    unfocusedContainerColor = Color.White, focusedContainerColor = Color.White
  )

  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar("Account Settings", "Make Bicolandia yours", onBack, trailingIcon = null) }
      item {
        Card(
          Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp),
          shape = RoundedCornerShape(18.dp), elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
          Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(70.dp).clip(CircleShape).background(BicolDeepGreen), contentAlignment = Alignment.Center) {
              if (photo != null) {
                Image(bitmap = photo, contentDescription = "Profile photo", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
              } else {
                Text(name.trim().take(1).uppercase().ifEmpty { "B" }, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 28.sp)
              }
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
              Text("Profile photo", fontWeight = FontWeight.Bold, fontSize = 14.sp)
              Text("Saved on this device only", color = BicolTextSecondary, fontSize = 11.sp)
              Spacer(Modifier.height(8.dp))
              Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(Modifier.clip(RoundedCornerShape(16.dp)).background(BicolDeepGreen).clickable { picker.launch("image/*") }.padding(horizontal = 14.dp, vertical = 7.dp)) {
                  Text(if (photo == null) "Upload" else "Change", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                }
                if (photo != null) {
                  Box(Modifier.clip(RoundedCornerShape(16.dp)).background(Color(0xFFF0F1F3)).clickable { AppStore.removePhoto(context) }.padding(horizontal = 14.dp, vertical = 7.dp)) {
                    Text("Remove", color = BicolTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                  }
                }
              }
            }
          }
        }
      }
      item {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text("Your details", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(top = 6.dp))
          OutlinedTextField(
            value = name, onValueChange = { name = it.take(40) }, label = { Text("Display name") },
            singleLine = true, shape = RoundedCornerShape(14.dp), colors = fieldColors, modifier = Modifier.fillMaxWidth()
          )
          OutlinedTextField(
            value = email, onValueChange = { email = it.take(80) }, label = { Text("Email (optional)") },
            singleLine = true, shape = RoundedCornerShape(14.dp), colors = fieldColors, modifier = Modifier.fillMaxWidth()
          )
          OutlinedTextField(
            value = hometown, onValueChange = { hometown = it.take(50) }, label = { Text("Hometown (e.g. Legazpi City, Albay)") },
            singleLine = true, shape = RoundedCornerShape(14.dp), colors = fieldColors, modifier = Modifier.fillMaxWidth()
          )
        }
      }
      item {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
          Text("What do you love to explore?", fontWeight = FontWeight.Bold, fontSize = 15.sp)
          Text("Pick as many as you like", color = BicolTextSecondary, fontSize = 11.5.sp)
          Spacer(Modifier.height(8.dp))
          // simple wrapping rows of chips (2 per row keeps them readable on small phones)
          interestOptions.chunked(2).forEach { row ->
            Row(Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
              row.forEach { opt ->
                val on = chosen.contains(opt)
                Box(
                  Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (on) BicolCoral else Color(0xFFF0F1F3))
                    .clickable { if (on) chosen.remove(opt) else chosen.add(opt) }
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                  contentAlignment = Alignment.Center
                ) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    if (on) { Icon(Icons.Filled.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp)); Spacer(Modifier.width(4.dp)) }
                    Text(opt, color = if (on) Color.White else BicolTextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                  }
                }
              }
              if (row.size == 1) Spacer(Modifier.weight(1f))
            }
          }
        }
      }
      item {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
          Text("App preferences", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(vertical = 6.dp))
          SettingSwitchRow(
            Icons.Filled.Lightbulb, "Auto-advance Did You Know",
            "Home trivia cards slide on their own every few seconds",
            AppStore.autoAdvanceTrivia
          ) { AppStore.setAutoAdvanceTrivia(it) }
        }
      }
      item {
        Box(
          Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(BicolCoral)
            .clickable {
              AppStore.saveProfile(name, email, hometown, chosen.toList())
              Toast.makeText(context, "Settings saved", Toast.LENGTH_SHORT).show()
              onBack()
            }
            .padding(vertical = 14.dp),
          contentAlignment = Alignment.Center
        ) { Text("Save changes", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp) }
      }
      item { Spacer(Modifier.height(120.dp)) }
    }
  }
}

@Composable
private fun SettingSwitchRow(icon: ImageVector, title: String, subtitle: String, checked: Boolean, onChange: (Boolean) -> Unit) {
  Row(
    Modifier.fillMaxWidth().padding(bottom = 8.dp).clip(RoundedCornerShape(14.dp)).background(Color.White).padding(14.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(Modifier.size(36.dp).clip(RoundedCornerShape(10.dp)).background(BicolDeepGreen.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
      Icon(icon, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(18.dp))
    }
    Spacer(Modifier.width(12.dp))
    Column(Modifier.weight(1f)) {
      Text(title, fontWeight = FontWeight.SemiBold, fontSize = 13.5.sp)
      Text(subtitle, color = BicolTextSecondary, fontSize = 11.sp, lineHeight = 14.sp)
    }
    Spacer(Modifier.width(8.dp))
    Switch(
      checked = checked, onCheckedChange = onChange,
      colors = SwitchDefaults.colors(checkedTrackColor = BicolDeepGreen, checkedThumbColor = Color.White)
    )
  }
}

@Composable
fun PrivacySecurityScreen(onBack: () -> Unit) {
  val context = LocalContext.current
  var pending by remember { mutableStateOf<Pair<String, () -> Unit>?>(null) }

  pending?.let { (message, action) ->
    AlertDialog(
      onDismissRequest = { pending = null },
      title = { Text("Are you sure?") },
      text = { Text(message) },
      confirmButton = { TextButton(onClick = { action(); pending = null; Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show() }) { Text("Yes, continue", color = BicolCoral) } },
      dismissButton = { TextButton(onClick = { pending = null }) { Text("Cancel") } }
    )
  }

  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar("Privacy & Security", "You are in control of your data", onBack, trailingIcon = null) }
      item {
        Card(
          Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp),
          shape = RoundedCornerShape(18.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F1E8)),
          elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
          Row(Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            Icon(Icons.Filled.Shield, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(26.dp))
            Spacer(Modifier.width(12.dp))
            Column {
              Text("Your data stays on your phone", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = BicolDeepGreen)
              Spacer(Modifier.height(4.dp))
              Text(
                "Bicolandia keeps your favorites, visited places, saved guides, profile details and photo in the app's private storage on this device. There is no account server and nothing is uploaded.",
                color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 17.sp
              )
            }
          }
        }
      }
      item { PrivacySection("What the app can access") }
      item {
        InfoBullets(
          listOf(
            "No special permissions are requested. Picking a profile photo uses Android's own photo picker, and only the picture you choose is copied into the app.",
            "Call and email buttons only open your phone's dialer or email app. Nothing is dialed or sent until you confirm it there.",
            "Places, photos and guides are built into the app, so browsing does not need a connection."
          )
        )
      }
      item { PrivacySection("Controls") }
      item {
        SettingSwitchRow(
          Icons.Filled.History, "Remember recent searches",
          "Shows your latest searches on the search screen. Turning it off also clears them.",
          AppStore.rememberSearches
        ) { AppStore.setRememberSearches(it) }
      }
      item {
        Column(Modifier.padding(horizontal = 16.dp)) {
          DataActionRow(Icons.Filled.ManageSearch, "Clear recent searches", "${AppStore.searchHistory.size} saved") {
            pending = "Remove your recent searches?" to { AppStore.clearSearchHistory() }
          }
          DataActionRow(Icons.Filled.Favorite, "Clear favorites", "${AppStore.favorites.size} saved") {
            pending = "Remove every place from your favorites?" to { AppStore.clearFavorites() }
          }
          DataActionRow(Icons.Filled.PinDrop, "Reset visited places and badges", "${AppStore.visited.size} visited") {
            pending = "Reset your Bicol Map progress and badges?" to { AppStore.clearVisited() }
          }
          DataActionRow(Icons.Filled.Bookmark, "Clear saved guides", "${AppStore.savedGuides.size} saved") {
            pending = "Remove all saved travel guides?" to { AppStore.clearSavedGuides() }
          }
          DataActionRow(Icons.Filled.Delete, "Erase all my data", "Favorites, trips, guides, profile and photo", danger = true) {
            pending = "This permanently erases everything Bicolandia has saved on this phone, including your profile photo." to { AppStore.eraseAll(context) }
          }
        }
      }
      item { PrivacySection("Stay safe while traveling") }
      item {
        InfoBullets(
          listOf(
            "Confirm rates, schedules and availability directly with providers. Bicolandia is an information guide, not a booking service.",
            "For emergencies, dial 911. The Tourist Essentials screen lists official hotlines.",
            "Keep valuables secure in crowded terminals and markets, and share your itinerary with someone you trust."
          )
        )
      }
      item { Spacer(Modifier.height(120.dp)) }
    }
  }
}

@Composable
private fun PrivacySection(text: String) {
  Text(text, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = BicolTextPrimary, modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 6.dp))
}

@Composable
private fun InfoBullets(lines: List<String>) {
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(14.dp)) {
      lines.forEach {
        Row(Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
          Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.padding(top = 1.dp).size(15.dp))
          Spacer(Modifier.width(8.dp))
          Text(it, color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 17.sp)
        }
      }
    }
  }
}

@Composable
private fun DataActionRow(icon: ImageVector, title: String, subtitle: String, danger: Boolean = false, onClick: () -> Unit) {
  val tint = if (danger) BicolCoral else BicolTextPrimary
  Row(
    Modifier
      .fillMaxWidth()
      .padding(bottom = 8.dp)
      .clip(RoundedCornerShape(14.dp))
      .background(if (danger) Color(0xFFFDE7E5) else Color.White)
      .clickable(onClick = onClick)
      .padding(14.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(20.dp))
    Spacer(Modifier.width(12.dp))
    Column(Modifier.weight(1f)) {
      Text(title, fontWeight = FontWeight.SemiBold, fontSize = 13.5.sp, color = tint)
      Text(subtitle, color = BicolTextSecondary, fontSize = 11.sp)
    }
    Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = BicolTextSecondary)
  }
}

// ============================================================
// HELP & SUPPORT - real how-tos, official numbers, share/feedback
// ============================================================
private data class Faq(val question: String, val answer: String)

private val faqs = listOf(
  Faq("How do I save a favorite?", "Tap the heart on any card or on a detail page. Favorites are saved on your device and appear in the Favorites tab, even after you close the app."),
  Faq("How does the Bicol Map work?", "Open a province, destination, festival, activity, restaurant or stay and tap Mark as Visited. Each province on your Bicol Map earns up to 3 stars as you visit more places, and you unlock badges along the way. Find it in Profile > My Trips."),
  Faq("How do I save a Travel Guide?", "Open the Travel Guide, pick a place and tap the bookmark. Your saved guides are listed in Profile > Saved Guides."),
  Faq("How do I change my profile photo?", "Tap your photo on the Profile screen (or use Account Settings), then choose a picture from your phone. It is stored only on your device."),
  Faq("Can I book hotels or tours in the app?", "No. Bicolandia is an information guide. Rates, schedules and availability change, so please confirm directly with hotels, boat operators and guides."),
  Faq("Where can I find emergency numbers?", "Open Tourist Essentials from the menu or the Home screen. It lists 911, the Philippine Red Cross and the Department of Tourism assistance lines."),
  Faq("Do I need internet to use the app?", "Places, photos and travel guides are built into the app, so you can browse without a connection. Calling and emailing use your phone's normal service.")
)

@Composable
fun HelpSupportScreen(onBack: () -> Unit, onOpenEssentials: () -> Unit) {
  val context = LocalContext.current
  val dot = touristAssistanceContacts.firstOrNull()
  val version = remember {
    try { context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "1.0" } catch (e: Exception) { "1.0" }
  }

  fun safeStart(intent: Intent) {
    try { context.startActivity(intent) } catch (e: ActivityNotFoundException) {
      Toast.makeText(context, "No app available for this action", Toast.LENGTH_SHORT).show()
    }
  }

  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar("Help & Support", "We're here to help", onBack, trailingIcon = null) }

      item { HelpHeading("Urgent help") }
      item {
        Card(
          Modifier.fillMaxWidth().padding(horizontal = 16.dp),
          shape = RoundedCornerShape(18.dp), elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
          Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            HelpAction(Icons.Filled.Call, BicolCoral, "Emergency", "911 · Unified national emergency line") {
              safeStart(Intent(Intent.ACTION_DIAL, Uri.parse("tel:911")))
            }
            if (dot != null) {
              HelpAction(Icons.Filled.SupportAgent, BicolDeepGreen, "DOT Tourist Assistance", "${dot.phone} · ${dot.hours}") {
                val number = Regex("\\(([^)]+)\\)").find(dot.phone)?.groupValues?.get(1) ?: dot.phone
                safeStart(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number")))
              }
            }
            HelpAction(Icons.Filled.HealthAndSafety, Color(0xFF0288D1), "Tourist Essentials", "All hotlines and travel checklists", onOpenEssentials)
          }
        }
      }

      item { HelpHeading("Frequently asked questions") }
      items(faqs) { FaqCard(it) }

      item { HelpHeading("Get in touch") }
      item {
        Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
          HelpAction(Icons.Filled.Email, Color(0xFF6A1B9A), "Send feedback", "Tell us what to improve") {
            safeStart(
              Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")).apply {
                putExtra(Intent.EXTRA_SUBJECT, "Bicolandia feedback")
                putExtra(Intent.EXTRA_TEXT, "Hi Bicolandia team,\n\n")
              }
            )
          }
          HelpAction(Icons.Filled.Share, Color(0xFFEF6C00), "Share Bicolandia", "Invite a friend to explore Bicol") {
            shareText(context, "Bicolandia", "Explore Bicol with me: volcanoes, whale sharks, festivals, food and where to stay. Get the Bicolandia app!")
          }
        }
      }

      item {
        Text(
          "Bicolandia · version $version",
          color = BicolTextSecondary, fontSize = 11.sp,
          modifier = Modifier.fillMaxWidth().padding(20.dp),
          textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
      }
      item { Spacer(Modifier.height(100.dp)) }
    }
  }
}

@Composable
private fun HelpHeading(text: String) {
  Text(text, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = BicolTextPrimary, modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 8.dp))
}

@Composable
private fun HelpAction(icon: ImageVector, tint: Color, title: String, subtitle: String, onClick: () -> Unit) {
  Row(
    Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(Color(0xFFF7F8FA)).clickable(onClick = onClick).padding(12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(Modifier.size(38.dp).clip(RoundedCornerShape(11.dp)).background(tint.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) {
      Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(19.dp))
    }
    Spacer(Modifier.width(12.dp))
    Column(Modifier.weight(1f)) {
      Text(title, fontWeight = FontWeight.SemiBold, fontSize = 13.5.sp, color = BicolTextPrimary)
      Text(subtitle, color = BicolTextSecondary, fontSize = 11.sp, lineHeight = 14.sp)
    }
    Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = BicolTextSecondary)
  }
}

@Composable
private fun FaqCard(faq: Faq) {
  var open by remember { mutableStateOf(false) }
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.fillMaxWidth().clickable { open = !open }.animateContentSize().padding(14.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(faq.question, fontWeight = FontWeight.SemiBold, fontSize = 13.5.sp, modifier = Modifier.weight(1f))
        Icon(if (open) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, contentDescription = null, tint = BicolTextSecondary)
      }
      if (open) {
        Spacer(Modifier.height(8.dp))
        Text(faq.answer, color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 17.sp)
      }
    }
  }
}
