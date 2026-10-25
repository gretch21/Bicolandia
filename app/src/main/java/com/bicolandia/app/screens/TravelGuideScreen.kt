package com.bicolandia.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.*
import com.bicolandia.app.ui.theme.*

@Composable
fun MapScreen(initialPlaceId: String? = null) {
  var selected by remember { mutableStateOf(initialPlaceId?.let { resolveTravelSubject(it) }) }
  var query by remember { mutableStateOf("") }
  var searchActive by remember { mutableStateOf(false) }
  var activeBucket by remember { mutableStateOf<TravelBucket?>(null) }

  if (selected != null) {
    DestinationTravelGuide(selected!!, onBack = { selected = null })
    return
  }

  val allSubjects = remember { allTravelSubjects() }
  val filtered = remember(query, activeBucket) {
    allSubjects.filter { s ->
      (activeBucket == null || bucketOf(s) == activeBucket) &&
        (query.isBlank() || s.name.contains(query, ignoreCase = true) || s.location.contains(query, ignoreCase = true))
    }
  }

  fun exitSearch() { searchActive = false; query = ""; activeBucket = null }

  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar("Travel Guide", "Plan how to get to your Bicol destination.", null, trailingIcon = null) }

      item {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
          Text("WHERE ARE YOU GOING?", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
          Spacer(Modifier.height(8.dp))
          Card(
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = if (searchActive) 3.dp else 1.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              Modifier.fillMaxWidth().height(50.dp).padding(horizontal = 16.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(Icons.Filled.Search, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(18.dp))
              Spacer(Modifier.width(8.dp))
              Box(Modifier.weight(1f)) {
                if (query.isEmpty()) {
                  Text("Search a province, destination, activity, restaurant...", color = BicolTextSecondary, fontSize = 12.5.sp)
                }
                BasicTextField(
                  value = query,
                  onValueChange = { query = it; searchActive = true },
                  singleLine = true,
                  textStyle = TextStyle(fontSize = 13.sp, color = BicolTextPrimary),
                  cursorBrush = androidx.compose.ui.graphics.SolidColor(BicolCoral),
                  modifier = Modifier.fillMaxWidth().onFocusChanged { if (it.isFocused) searchActive = true }
                )
              }
              if (searchActive) {
                Text(
                  "Cancel", color = BicolCoral, fontSize = 12.5.sp, fontWeight = FontWeight.SemiBold,
                  modifier = Modifier.clickable { exitSearch() }
                )
              }
            }
          }
        }
      }

      if (searchActive) {
        item {
          LazyRow(
            Modifier.fillMaxWidth().padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
          ) {
            item { FilterChipPill("All", activeBucket == null) { activeBucket = null } }
            items(TravelBucket.values().toList()) { b ->
              FilterChipPill(b.label, activeBucket == b) { activeBucket = if (activeBucket == b) null else b }
            }
          }
        }
        if (filtered.isEmpty()) {
          item {
            Column(
              Modifier.fillMaxWidth().padding(vertical = 40.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Icon(Icons.Filled.SearchOff, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(30.dp))
              Spacer(Modifier.height(8.dp))
              Text("No matches. Try a different search or filter.", color = BicolTextSecondary, fontSize = 12.sp)
            }
          }
        } else {
          items(filtered.take(80)) { s -> SearchResultRow(s) { selected = s } }
        }
        item { Spacer(Modifier.height(120.dp)) }
      } else {
        item {
          Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            Spacer(Modifier.height(18.dp))
            Text("QUICK START", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            Spacer(Modifier.height(8.dp))
          }
        }
        item {
          LazyRow(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(quickStartCards) { card ->
              QuickStartCard(card) {
                activeBucket = card.bucket
                query = card.presetQuery
                searchActive = true
              }
            }
          }
        }

        item {
          Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            Spacer(Modifier.height(20.dp))
            Text("BROWSE BY PROVINCE", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            Spacer(Modifier.height(8.dp))
          }
        }
        items(provinces) { p ->
          Card(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 5.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
          ) {
            Row(
              Modifier
                .fillMaxWidth()
                .clickable { selected = TravelSubject(p.id, p.name, p.name, "Province", p.colorHex) }
                .padding(14.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(Modifier.size(38.dp).clip(CircleShape).background(Color.fromHex(p.colorHex).copy(alpha = 0.15f)), contentAlignment = Alignment.Center) {
                Icon(Icons.Filled.Place, contentDescription = null, tint = Color.fromHex(p.colorHex), modifier = Modifier.size(18.dp))
              }
              Spacer(Modifier.width(12.dp))
              Column(Modifier.weight(1f)) {
                Text(p.name, fontWeight = FontWeight.SemiBold, fontSize = 13.5.sp)
                Text("How to get around ${p.name}", color = BicolTextSecondary, fontSize = 11.sp)
              }
              SaveGuideButton(p.id)
              Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(18.dp))
            }
          }
        }

        item {
          Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            Spacer(Modifier.height(22.dp))
            Text("GETTING AROUND BICOL", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            Spacer(Modifier.height(2.dp))
            Text(
              "A general overview - for step-by-step directions, search a specific destination above.",
              color = BicolTextSecondary, fontSize = 11.sp
            )
          }
        }
        items(generalTransportModes) { GeneralTransportRow(it) }
        item { Spacer(Modifier.height(120.dp)) }
      }
    }
  }
}

// ---------------- Quick Start ----------------
private data class QuickStartInfo(
  val icon: ImageVector, val title: String, val subtitle: String, val colorHex: String,
  val bucket: TravelBucket?, val presetQuery: String = ""
)

private val quickStartCards = listOf(
  QuickStartInfo(Icons.Filled.Flight, "From Manila", "Air / Bus", "#1565C0", TravelBucket.PROVINCE),
  QuickStartInfo(Icons.Filled.DirectionsBus, "Around Bicol", "Bus / Van / Jeepney", "#EF6C00", TravelBucket.ACTIVITY),
  QuickStartInfo(Icons.Filled.DirectionsBoat, "Island Travel", "Boat / Ferry", "#00796B", null, "Island"),
  QuickStartInfo(Icons.Filled.Storefront, "Local Eats", "Restaurants", "#37474F", TravelBucket.RESTAURANT)
)

@Composable
private fun QuickStartCard(card: QuickStartInfo, onClick: () -> Unit) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    modifier = Modifier.width(130.dp).clickable(onClick = onClick)
  ) {
    Column(Modifier.padding(14.dp)) {
      Box(Modifier.size(34.dp).clip(RoundedCornerShape(10.dp)).background(Color.fromHex(card.colorHex).copy(alpha = 0.15f)), contentAlignment = Alignment.Center) {
        Icon(card.icon, contentDescription = null, tint = Color.fromHex(card.colorHex), modifier = Modifier.size(17.dp))
      }
      Spacer(Modifier.height(8.dp))
      Text(card.title, fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
      Text(card.subtitle, color = BicolTextSecondary, fontSize = 10.5.sp)
    }
  }
}

// ---------------- Unified search result row + filter chip ----------------
@Composable
private fun FilterChipPill(label: String, selected: Boolean, onClick: () -> Unit) {
  Box(
    Modifier
      .clip(RoundedCornerShape(20.dp))
      .background(if (selected) BicolDeepGreen else Color.White)
      .clickable(onClick = onClick)
      .padding(horizontal = 14.dp, vertical = 8.dp)
  ) {
    Text(label, color = if (selected) Color.White else BicolTextSecondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
  }
}

@Composable
private fun SearchResultRow(s: TravelSubject, onClick: () -> Unit) {
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(
      Modifier.fillMaxWidth().clickable(onClick = onClick).padding(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(Modifier.size(36.dp).clip(CircleShape).background(Color.fromHex(s.colorHex).copy(alpha = 0.15f)), contentAlignment = Alignment.Center) {
        Icon(iconFor(s.category), contentDescription = null, tint = Color.fromHex(s.colorHex), modifier = Modifier.size(16.dp))
      }
      Spacer(Modifier.width(12.dp))
      Column(Modifier.weight(1f)) {
        Text(s.name, fontWeight = FontWeight.SemiBold, fontSize = 13.5.sp)
        Text("${s.category} · ${s.location}", color = BicolTextSecondary, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
      }
      SaveGuideButton(s.id)
      Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(18.dp))
    }
  }
}

// ---------------- General "Getting Around Bicol" (kept, shortened, secondary) ----------------
private data class GeneralMode(val icon: ImageVector, val title: String, val desc: String, val colorHex: String)

private val generalTransportModes = listOf(
  GeneralMode(Icons.Filled.Flight, "Air", "Four airports serve the region - Legazpi (DRP), Naga (WNP), Virac (VRC), and Masbate (MBT) - each about an hour from Manila by plane.", "#1565C0"),
  GeneralMode(Icons.Filled.DirectionsBus, "Bus", "Provincial buses leave Manila's Cubao, Pasay, and Alabang terminals every day, rolling into Legazpi, Naga, or Sorsogon after roughly 8 to 11 hours on the road.", "#EF6C00"),
  GeneralMode(Icons.Filled.AirportShuttle, "Van (V-hire)", "For hopping between towns once you're in Bicol, a V-hire van along the Maharlika Highway beats the bus for speed - they leave often and don't wait for a full seat count.", "#5E35B1"),
  GeneralMode(Icons.Filled.DirectionsBus, "Jeepney", "The everyday ride for getting around town - just flag one down anywhere along its route and hop off wherever you need.", "#2E7D32"),
  GeneralMode(Icons.Filled.TwoWheeler, "Tricycle / Habal-habal", "Tricycles handle short in-town trips; once the road gets rougher, a habal-habal (motorbike taxi) is what actually gets you to the barangay or trailhead.", "#C62828"),
  GeneralMode(Icons.Filled.DirectionsBoat, "Boat / Ferry", "Island-hopping territory - RORO ferries and outrigger bancas link Catanduanes, Masbate, and Ticao to the mainland, plus island groups like Caramoan and Calaguas.", "#0288D1")
)

@Composable
private fun GeneralTransportRow(mode: GeneralMode) {
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 5.dp),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
      Box(Modifier.size(34.dp).clip(RoundedCornerShape(10.dp)).background(Color.fromHex(mode.colorHex).copy(alpha = 0.15f)), contentAlignment = Alignment.Center) {
        Icon(mode.icon, contentDescription = null, tint = Color.fromHex(mode.colorHex), modifier = Modifier.size(17.dp))
      }
      Spacer(Modifier.width(12.dp))
      Column {
        Text(mode.title, fontWeight = FontWeight.Bold, fontSize = 13.5.sp)
        Spacer(Modifier.height(2.dp))
        Text(mode.desc, color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 16.5.sp)
      }
    }
  }
}

// ---------------- Specific destination guide ----------------
@Composable
private fun DestinationTravelGuide(subject: TravelSubject, onBack: () -> Unit) {
  val guide = remember(subject.id) { buildTravelGuide(subject.name, subject.location, subject.category) }
  val brand = Color.fromHex(subject.colorHex)
  val context = LocalContext.current

  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item {
        Box(Modifier.fillMaxWidth()) {
          // The place's own photo (same picture used in its category), with a brand-colored scrim for legible text
          PlaceholderImage(
            subject.name, subject.colorHex, 210.dp, icon = iconFor(subject.category),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(bottomStart = 26.dp, bottomEnd = 26.dp),
            imageId = subject.id
          )
          Box(
            Modifier.matchParentSize().clip(RoundedCornerShape(bottomStart = 26.dp, bottomEnd = 26.dp)).background(
              androidx.compose.ui.graphics.Brush.verticalGradient(listOf(Color.Black.copy(alpha = 0.30f), Color.Transparent, brand.copy(alpha = 0.72f)))
            )
          )
          Row(
            Modifier.fillMaxWidth().padding(16.dp).align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            RoundIconButton(Icons.Filled.ArrowBack, tint = Color.White, bg = Color.Black.copy(alpha = 0.35f), onClick = onBack)
            val saved = AppStore.savedGuides.contains(subject.id)
            RoundIconButton(
              if (saved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
              tint = if (saved) BicolOrange else Color.White,
              bg = Color.Black.copy(alpha = 0.35f),
              onClick = {
                AppStore.toggleGuide(subject.id)
                Toast.makeText(context, if (AppStore.isGuideSaved(subject.id)) "Guide saved to Profile > Saved Guides" else "Guide removed from Saved Guides", Toast.LENGTH_SHORT).show()
              }
            )
          }
          Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
            Text(subject.name, fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
            Text("${subject.category} · ${guide.province}".uppercase(), fontWeight = FontWeight.SemiBold, fontSize = 11.sp, color = Color.White.copy(alpha = 0.9f))
          }
        }
      }

      item { GuideSectionLabel("HOW TO GET THERE") }
      item { GuideLegGroup("From Manila", guide.fromManila) }
      item { GuideLegGroup("From ${guide.province}'s Hub City", guide.fromHub) }

      item { GuideSectionLabel("ROUTE") }
      item { RouteTimeline(guide) }

      item { GuideSectionLabel("LOCAL TRANSPORT") }
      item { GuideLegGroup(null, guide.localTransport) }

      item { GuideSectionLabel("TIME") }
      item {
        InfoCard(Icons.Filled.Schedule, BicolDeepGreen) {
          Text("Approx. total travel time", color = BicolTextSecondary, fontSize = 11.sp)
          Text(guide.totalTime, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
      }

      item { GuideSectionLabel("BEFORE YOU GO") }
      item { ChecklistCard(guide.beforeYouGo) }

      item { GuideSectionLabel("BEST TIME TO GO") }
      item {
        InfoCard(Icons.Filled.WbSunny, BicolOrange) {
          Text(guide.bestTimeToGo, color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 17.sp)
        }
      }

      item { GuideSectionLabel("TRAVEL TIPS") }
      item { TipsCard(guide.travelTips) }

      item { GuideSectionLabel("GOOD TO KNOW") }
      item { GoodToKnowCard(guide.goodToKnow) }

      item { Spacer(Modifier.height(120.dp)) }
    }
  }
}

@Composable
private fun GuideSectionLabel(text: String) {
  Text(
    text, color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp,
    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
  )
}

@Composable
private fun GuideLegGroup(subtitle: String?, legs: List<TravelLeg>) {
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(14.dp)) {
      if (subtitle != null) {
        Text(subtitle, fontWeight = FontWeight.Bold, fontSize = 12.5.sp, color = BicolTextPrimary)
        Spacer(Modifier.height(8.dp))
      }
      legs.forEachIndexed { i, leg ->
        Row(Modifier.fillMaxWidth().padding(vertical = 6.dp), verticalAlignment = Alignment.Top) {
          Box(Modifier.size(28.dp).clip(RoundedCornerShape(9.dp)).background(BicolDeepGreen.copy(alpha = 0.1f)), contentAlignment = Alignment.Center) {
            Icon(leg.icon, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(15.dp))
          }
          Spacer(Modifier.width(10.dp))
          Column(Modifier.weight(1f)) {
            Text(leg.mode, fontWeight = FontWeight.SemiBold, fontSize = 12.5.sp)
            Text(leg.detail, color = BicolTextSecondary, fontSize = 11.5.sp, lineHeight = 15.sp)
          }
          Spacer(Modifier.width(8.dp))
          Text(leg.duration, color = BicolDeepGreen, fontWeight = FontWeight.Bold, fontSize = 10.5.sp, textAlign = TextAlign.End, modifier = Modifier.width(90.dp))
        }
        if (i < legs.size - 1) HorizontalDivider(color = Color(0xFFF0F1F3))
      }
    }
  }
}

@Composable
private fun RouteTimeline(guide: TravelGuide) {
  val steps = buildList<Pair<String, ImageVector>> {
    add("Manila" to Icons.Filled.LocationCity)
    (guide.fromManila.firstOrNull())?.let { add(it.mode to it.icon) }
    add(guide.province + "'s Hub" to Icons.Filled.Place)
    (guide.fromHub.firstOrNull())?.let { add(it.mode to it.icon) }
    (guide.localTransport.firstOrNull())?.let { add(it.mode to it.icon) }
    add(guide.placeName to Icons.Filled.Flag)
  }
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(16.dp)) {
      steps.forEachIndexed { i, (label, icon) ->
        val isStop = i % 2 == 0
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            icon, contentDescription = null,
            tint = if (isStop) BicolDeepGreen else BicolTextSecondary,
            modifier = Modifier.size(if (isStop) 17.dp else 14.dp).width(26.dp)
          )
          Text(
            label, fontSize = if (isStop) 13.sp else 11.5.sp,
            fontWeight = if (isStop) FontWeight.Bold else FontWeight.Normal,
            color = if (isStop) BicolTextPrimary else BicolTextSecondary
          )
        }
        if (i < steps.size - 1) {
          Box(Modifier.padding(start = 12.dp)) {
            Icon(Icons.Filled.ArrowDownward, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(13.dp))
          }
        }
      }
    }
  }
}

@Composable
private fun InfoCard(icon: androidx.compose.ui.graphics.vector.ImageVector, tint: Color, content: @Composable ColumnScope.() -> Unit) {
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
      Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp))
      Spacer(Modifier.width(10.dp))
      Column(content = content)
    }
  }
}

@Composable
private fun ChecklistCard(items: List<String>) {
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(14.dp)) {
      items.forEach { tip ->
        Row(Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
          Icon(Icons.Filled.CheckBox, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(15.dp))
          Spacer(Modifier.width(8.dp))
          Text(tip, color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 16.sp)
        }
      }
    }
  }
}

@Composable
private fun TipsCard(tips: List<String>) {
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(14.dp)) {
      tips.forEach { tip ->
        Row(Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
          Box(Modifier.size(18.dp).padding(top = 1.dp), contentAlignment = Alignment.Center) {
            Icon(Icons.Filled.Check, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(14.dp))
          }
          Spacer(Modifier.width(6.dp))
          Text(tip, color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 16.sp)
        }
      }
    }
  }
}

@Composable
private fun GoodToKnowCard(items: List<String>) {
  Card(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
  ) {
    Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
      Icon(Icons.Filled.Info, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(16.dp))
      Spacer(Modifier.width(10.dp))
      Column {
        items.forEach { note ->
          Text("• $note", color = BicolTextSecondary, fontSize = 11.5.sp, lineHeight = 16.sp, modifier = Modifier.padding(vertical = 2.dp))
        }
      }
    }
  }
}

@Composable
private fun SaveGuideButton(id: String) {
  val context = LocalContext.current
  val saved = AppStore.savedGuides.contains(id)
  Icon(
    if (saved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
    contentDescription = if (saved) "Remove saved guide" else "Save guide",
    tint = if (saved) BicolCoral else BicolTextSecondary,
    modifier = Modifier
      .padding(end = 4.dp)
      .size(34.dp)
      .clip(CircleShape)
      .clickable {
        AppStore.toggleGuide(id)
        Toast.makeText(context, if (AppStore.isGuideSaved(id)) "Guide saved" else "Guide removed", Toast.LENGTH_SHORT).show()
      }
      .padding(6.dp)
  )
}
