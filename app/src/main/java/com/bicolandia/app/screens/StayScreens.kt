package com.bicolandia.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.Accommodation
import com.bicolandia.app.data.QuickFact
import com.bicolandia.app.data.stayClassifications
import com.bicolandia.app.data.stayData
import com.bicolandia.app.ui.theme.*

private fun stayIconFor(type: String): ImageVector = when {
  type.contains("Resort", true) -> Icons.Filled.Villa
  type.contains("Homestay", true) -> Icons.Filled.House
  type.contains("Inn", true) -> Icons.Filled.Cottage
  type.contains("Beach", true) -> Icons.Filled.BeachAccess
  type.contains("Mountain", true) -> Icons.Filled.Terrain
  type.contains("Hotel", true) -> Icons.Filled.Hotel
  else -> Icons.Filled.Home
}

@Composable
fun StayListScreen(typeId: String, onBack: () -> Unit, onClick: (Accommodation) -> Unit) {
  val accommodations = stayData[typeId] ?: emptyList()
  val label = stayClassifications.firstOrNull { it.id == typeId }?.title
    ?: accommodations.firstOrNull()?.type ?: "Stay & Rest"
  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar(label, "Stay & Rest · Bicolandia", onBack, trailingIcon = null) }
      item {
        Text(
          "A tourism information guide - not a booking service. Details are compiled from public listings and tourism sources; confirm rooms, transfers and operating status with each property before you travel.",
          color = BicolTextSecondary, fontSize = 11.5.sp, lineHeight = 15.sp,
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
      }
      item { Spacer(Modifier.height(6.dp)) }
      items(accommodations) { acc -> StayListCard(acc) { onClick(acc) } }
      item { Spacer(Modifier.height(120.dp)) }
    }
  }
}

@Composable
private fun StayProvincePill(province: String) {
  Row(
    Modifier.clip(RoundedCornerShape(20.dp)).background(Color.Black.copy(alpha = 0.55f)).padding(horizontal = 10.dp, vertical = 5.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(Icons.Filled.LocationOn, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(13.dp))
    Spacer(Modifier.width(3.dp))
    Text(province, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
  }
}

@Composable
private fun StayListCard(acc: Accommodation, onClick: () -> Unit) {
  Card(
    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).clickable(onClick = onClick),
    shape = RoundedCornerShape(20.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column {
      Box(Modifier.fillMaxWidth()) {
        PlaceholderImage(acc.name, acc.colorHex, 170.dp, icon = stayIconFor(acc.type), shape = RoundedCornerShape(0.dp), imageId = acc.imageId)
        Box(Modifier.align(Alignment.TopEnd).padding(10.dp)) { StayProvincePill(acc.province) }
      }
      Column(Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(acc.name, fontWeight = FontWeight.Bold, fontSize = 17.sp, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
          Spacer(Modifier.width(8.dp))
          StatusBadge(acc.type.uppercase(), acc.colorHex)
        }
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(Icons.Filled.LocationOn, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(13.dp))
          Spacer(Modifier.width(2.dp))
          Text(acc.location, color = BicolTextSecondary, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        Spacer(Modifier.height(6.dp))
        Text(acc.shortDescription, color = BicolTextSecondary, fontSize = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(Icons.Filled.Explore, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(15.dp))
          Spacer(Modifier.width(4.dp))
          Text(
            "Near: " + acc.nearbyAttractions.take(2).joinToString(" · ") { it.substringBefore(" (") },
            color = BicolTextSecondary, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
          )
          Spacer(Modifier.width(8.dp))
          RoundIconButton(Icons.Filled.ArrowForward, tint = Color.White, bg = BicolDeepGreen, onClick = onClick)
        }
      }
    }
  }
}

// ============================================================
// DETAIL SCREEN - same structure as PlaceDetailScreen:
// hero → "How to Get There" pill → About → Quick Facts →
// Highlights → Nearby cards ... with the Stay & Rest extras
// (area, route steps, best time, considerations) as matching cards.
// ============================================================
@Composable
fun StayDetailScreen(acc: Accommodation, onBack: () -> Unit, onTravelGuide: () -> Unit = {}) {
  val context = LocalContext.current
  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item {
        Box(Modifier.fillMaxWidth()) {
          // Softened bottom corners so the hero photo blends smoothly into the content below
          PlaceholderImage(
            acc.name, acc.colorHex, 340.dp, icon = stayIconFor(acc.type),
            shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp),
            imageId = acc.imageId
          )
          Row(
            Modifier.fillMaxWidth().padding(16.dp).align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            RoundIconButton(Icons.Filled.ArrowBack, tint = Color.White, bg = Color.Black.copy(alpha = 0.35f), onClick = onBack)
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
              val isFav = FavoritesStore.favoriteIds.contains(acc.id)
              RoundIconButton(
                if (isFav) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                tint = if (isFav) BicolCoral else Color.White,
                bg = Color.Black.copy(alpha = 0.35f),
                onClick = { FavoritesStore.toggle(acc.id) }
              )
              RoundIconButton(Icons.Filled.Share, tint = Color.White, bg = Color.Black.copy(alpha = 0.35f), onClick = {
                sharePlaceText(context, acc.name, "${acc.location}, ${acc.province}", acc.shortDescription)
              })
            }
          }
          Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              StatusBadge(acc.type.uppercase(), acc.colorHex)
              Spacer(Modifier.width(8.dp))
              Icon(Icons.Filled.LocationOn, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(14.dp))
              Text(" ${acc.province}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
            Spacer(Modifier.height(4.dp))
            Text(acc.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 26.sp, lineHeight = 30.sp)
            Row(verticalAlignment = Alignment.Top) {
              Icon(Icons.Filled.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.padding(top = 2.dp).size(14.dp))
              Text(" ${acc.location}", color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp, lineHeight = 17.sp)
            }
          }
        }
      }
      item {
        Column(Modifier.padding(horizontal = 16.dp)) {
          Spacer(Modifier.height(16.dp))
          Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Row(
              Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(BicolDeepGreen)
                .clickable(onClick = onTravelGuide)
                .padding(horizontal = 16.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(Icons.Filled.DirectionsBus, contentDescription = null, tint = Color.White, modifier = Modifier.size(15.dp))
              Spacer(Modifier.width(8.dp))
              Text("How to Get There", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            }
            VisitedPill(acc.id)
          }
          Spacer(Modifier.height(16.dp))
          Text("About", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary)
          Spacer(Modifier.height(6.dp))
          Text(acc.overview.ifBlank { acc.shortDescription }, fontSize = 13.sp, color = BicolTextSecondary, lineHeight = 19.sp)

          StayQuickFacts(acc.facts)
          StayHighlights("Highlights & Facilities", acc.highlights)

          if (acc.areaNote.isNotBlank()) {
            StayHeading("The Area")
            StayInfoCard(Icons.Filled.Explore, BicolDeepGreen, acc.areaNote)
          }

          StayHeading("Getting There")
          StayInfoCard(Icons.Filled.DirectionsBus, BicolDeepGreen, acc.howToGetThere)
          if (acc.routeSteps.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            StayStepList(acc.routeSteps)
          }

          StayNearbyRow("Nearby Attractions", acc.nearbyAttractions, acc.colorHex, stayIconFor(acc.type))
          StayHighlights("Nearby Activities", acc.nearbyActivities)

          if (acc.bestTime.isNotBlank()) {
            StayHeading("Best Time to Visit")
            StayInfoCard(Icons.Filled.WbSunny, BicolCoral, acc.bestTime)
          }

          StayHeading("Travel Considerations")
          StayChecklistCard(acc.travelConsiderations, Icons.Filled.CheckCircle, BicolDeepGreen, Color.White)

          StayHeading("Good to Know")
          StayChecklistCard(acc.prepInfo, Icons.Filled.Lightbulb, Color(0xFFE08A00), Color(0xFFFFF3E0))

          Text(
            "Stay & Rest is a tourism information guide, not a booking service. Details are compiled from public listings and tourism sources and can change - please confirm rooms, transfers and operating status directly with the property before you travel.",
            color = BicolTextSecondary, fontSize = 11.sp, lineHeight = 15.sp,
            modifier = Modifier.padding(vertical = 16.dp)
          )
          Spacer(Modifier.height(100.dp))
        }
      }
    }
  }
}

@Composable
private fun StayHeading(text: String) {
  Text(text, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary, modifier = Modifier.padding(top = 14.dp, bottom = 8.dp))
}


private val stayFactBg = listOf(Color(0xFFDCE9FA), Color(0xFFFCE3D6), Color(0xFFFDF3D0), Color(0xFFD9F0DE))
private val stayFactTint = listOf(Color(0xFF3E7CB1), Color(0xFFE8734A), Color(0xFFD1A526), Color(0xFF2E7D32))

private fun stayFactIcon(label: String): ImageVector = when {
  label.contains("Setting", true) -> Icons.Filled.Place
  label.contains("Size", true) || label.contains("Unit", true) || label.contains("Capacity", true) || label.contains("Rooms", true) -> Icons.Filled.MeetingRoom
  label.contains("Facilit", true) || label.contains("Feature", true) || label.contains("Perks", true) -> Icons.Filled.Pool
  label.contains("hub", true) || label.contains("Getting", true) || label.contains("Access", true) || label.contains("Transfer", true) ||
    label.contains("Distance", true) || label.contains("Walk", true) || label.contains("Interaction", true) -> Icons.Filled.DirectionsBus
  label.contains("season", true) || label.contains("Best", true) -> Icons.Filled.WbSunny
  label.contains("Good for", true) -> Icons.Filled.Groups
  label.contains("Check-in", true) || label.contains("Front desk", true) -> Icons.Filled.Schedule
  label.contains("Style", true) || label.contains("Type", true) -> Icons.Filled.Hotel
  label.contains("Breakfast", true) -> Icons.Filled.Restaurant
  label.contains("Tours", true) -> Icons.Filled.Explore
  else -> Icons.Filled.Info
}

@Composable
private fun StayQuickFacts(facts: List<QuickFact>) {
  if (facts.isEmpty()) return
  Column(Modifier.fillMaxWidth().padding(top = 12.dp)) {
    Text("Quick Facts", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary)
    Spacer(Modifier.height(10.dp))
    facts.chunked(2).forEachIndexed { rowIndex, row ->
      Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        row.forEachIndexed { colIndex, fact ->
          val i = rowIndex * 2 + colIndex
          Card(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
          ) {
            Column(Modifier.fillMaxSize().padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                Modifier.size(34.dp).clip(CircleShape).background(stayFactBg[i % 4]),
                contentAlignment = Alignment.Center
              ) { Icon(stayFactIcon(fact.label), contentDescription = null, tint = stayFactTint[i % 4], modifier = Modifier.size(16.dp)) }
              Spacer(Modifier.height(6.dp))
              Text(fact.label.uppercase(), color = BicolTextSecondary, fontSize = 10.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
              Spacer(Modifier.height(2.dp))
              Text(fact.value, fontWeight = FontWeight.Bold, fontSize = 12.5.sp, lineHeight = 16.sp, textAlign = TextAlign.Center)
            }
          }
        }
      }
      Spacer(Modifier.height(12.dp))
    }
  }
}


private val stayRowBgs = listOf(Color(0xFFE3F1E8), Color(0xFFF0F1F3), Color(0xFFF0F1F3), Color(0xFFE3F1E8), Color(0xFFF0F1F3))


private fun String.hasWord(word: String): Boolean =
  Regex("\\b" + Regex.escape(word) + "\\b", RegexOption.IGNORE_CASE).containsMatchIn(this)

private fun stayFeatureIcon(text: String): ImageVector = when {
  text.contains("pool", true) -> Icons.Filled.Pool
  text.hasWord("spa") || text.contains("massage", true) || text.contains("wellness", true) || text.contains("soak", true) -> Icons.Filled.Spa
  text.contains("wi-fi", true) || text.contains("wifi", true) -> Icons.Filled.Wifi
  text.contains("parking", true) -> Icons.Filled.LocalParking
  text.contains("shuttle", true) || text.contains("airport", true) || text.contains("transfer", true) -> Icons.Filled.AirportShuttle
  text.contains("gym", true) || text.contains("fitness", true) || text.contains("climbing", true) || text.contains("zipline", true) -> Icons.Filled.FitnessCenter
  text.contains("restaurant", true) || text.contains("dining", true) || text.contains("breakfast", true) || text.contains("café", true) ||
    text.contains("food", true) || text.contains("seafood", true) || text.contains("tasting", true) || text.contains("grill", true) -> Icons.Filled.Restaurant
  text.contains("surf", true) -> Icons.Filled.Surfing
  text.contains("hiking", true) || text.hasWord("hike") || text.contains("trek", true) || text.contains("trail", true) || text.hasWord("walk") || text.hasWord("walking") -> Icons.Filled.Hiking
  text.contains("boat", true) || text.contains("island", true) || text.contains("hopping", true) || text.contains("kayak", true) || text.contains("cruise", true) -> Icons.Filled.DirectionsBoat
  text.contains("beach", true) || text.contains("swim", true) || text.contains("snorkel", true) || text.contains("diving", true) -> Icons.Filled.BeachAccess
  text.hasWord("fall") || text.hasWord("falls") || text.contains("waterfall", true) -> Icons.Filled.Water
  text.contains("museum", true) || text.contains("heritage", true) || text.contains("festival", true) || text.contains("devotion", true) -> Icons.Filled.Museum
  text.contains("view", true) || text.contains("sunrise", true) || text.contains("sunset", true) || text.contains("photo", true) -> Icons.Filled.PhotoCamera
  text.contains("front desk", true) || text.contains("24-hour", true) || text.contains("check-in", true) -> Icons.Filled.Schedule
  text.hasWord("pet") || text.hasWord("pets") -> Icons.Filled.Pets
  text.contains("family", true) || text.contains("children", true) || text.contains("kids", true) || text.contains("groups", true) -> Icons.Filled.Groups
  else -> Icons.Filled.CheckCircle
}

@Composable
private fun StayHighlights(title: String, entries: List<String>) {
  if (entries.isEmpty()) return
  Column(Modifier.fillMaxWidth().padding(top = 14.dp)) {
    Text(title, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary)
    Spacer(Modifier.height(10.dp))
    entries.forEachIndexed { i, text ->
      Row(
        Modifier
          .fillMaxWidth()
          .padding(bottom = 10.dp)
          .clip(RoundedCornerShape(14.dp))
          .background(stayRowBgs[i % stayRowBgs.size])
          .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(Modifier.size(38.dp).clip(RoundedCornerShape(10.dp)).background(Color.White), contentAlignment = Alignment.Center) {
          Icon(stayFeatureIcon(text), contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(12.dp))
        Text(text, fontWeight = FontWeight.Medium, fontSize = 13.sp, lineHeight = 18.sp, color = BicolTextPrimary, modifier = Modifier.weight(1f))
      }
    }
  }
}

@Composable
private fun StayNearbyRow(title: String, entries: List<String>, colorHex: String, icon: ImageVector) {
  if (entries.isEmpty()) return
  Column(Modifier.fillMaxWidth().padding(top = 14.dp)) {
    Text(title, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary)
    Spacer(Modifier.height(10.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
      items(entries) { entry ->
        val name = entry.substringBefore(" (")
        val note = entry.substringAfter(" (", "").removeSuffix(")")
        Column(Modifier.width(150.dp)) {
          PlaceholderImage(name, colorHex, 96.dp, icon = icon)
          Spacer(Modifier.height(6.dp))
          Text(name, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, lineHeight = 16.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
          if (note.isNotBlank()) {
            Text(note, color = BicolTextSecondary, fontSize = 11.sp, lineHeight = 14.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
          }
        }
      }
    }
  }
}

@Composable
private fun StayInfoCard(icon: ImageVector, tint: Color, text: String) {
  Card(
    Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
      Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.padding(top = 1.dp).size(18.dp))
      Spacer(Modifier.width(10.dp))
      Text(text, color = BicolTextSecondary, fontSize = 12.5.sp, lineHeight = 18.sp)
    }
  }
}

@Composable
private fun StayStepList(steps: List<String>) {
  Card(
    Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(14.dp)) {
      steps.forEachIndexed { index, step ->
        Row(Modifier.padding(vertical = 5.dp), verticalAlignment = Alignment.Top) {
          Box(
            Modifier.size(22.dp).clip(CircleShape).background(BicolDeepGreen),
            contentAlignment = Alignment.Center
          ) {
            Text("${index + 1}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
          }
          Spacer(Modifier.width(10.dp))
          Text(step, color = BicolTextSecondary, fontSize = 12.5.sp, lineHeight = 17.sp, modifier = Modifier.weight(1f))
        }
      }
    }
  }
}

@Composable
private fun StayChecklistCard(entries: List<String>, icon: ImageVector, tint: Color, container: Color) {
  Card(
    Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = container),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(14.dp)) {
      entries.forEach { tip ->
        Row(Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
          Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.padding(top = 1.dp).size(15.dp))
          Spacer(Modifier.width(8.dp))
          Text(tip, color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 16.sp)
        }
      }
    }
  }
}
