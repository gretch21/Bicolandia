package com.bicolandia.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.*
import com.bicolandia.app.ui.theme.*

@Composable
fun ExploreHubScreen(
  onProvinceClick: (String) -> Unit,
  onDestinationCategory: () -> Unit,
  onFestivalCategory: () -> Unit,
  onActivityCategory: () -> Unit,
  onCuisineCategory: () -> Unit,
  onCultureCategory: () -> Unit = {},
  onStayCategory: () -> Unit = {}
) {
  data class ExploreTile(val title: String, val subtitle: String, val colorHex: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val big: Boolean, val onClick: () -> Unit)

  val tiles = listOf(
    ExploreTile("Provinces", "${provinces.size} provinces", "#1E8A6E", Icons.Filled.Map, true) { onProvinceClick("") },
    ExploreTile("Destinations", "${destinationClassifications.size} categories", "#C1440E", Icons.Filled.Terrain, false, onDestinationCategory),
    ExploreTile("Festivals", "${festivalClassifications.size} categories", "#7a5a2f", Icons.Filled.Celebration, false, onFestivalCategory),
    ExploreTile("Activities", "${activityClassifications.size} categories", "#2a7a86", Icons.Filled.Surfing, false, onActivityCategory),
    ExploreTile("Cuisines", "${cuisineClassifications.size} categories", "#E3A857", Icons.Filled.Restaurant, false, onCuisineCategory),
    ExploreTile("Culture & Heritage", "${cultureClassifications.size} categories", "#6A1B9A", Icons.Filled.HistoryEdu, false, onCultureCategory),
    ExploreTile("Stay & Rest", "${stayClassifications.size} categories", "#00695C", Icons.Filled.Hotel, false, onStayCategory)
  )

  Scaffold(containerColor = BicolBg) { padding ->
    // Card heights follow the phone's real screen height so the grid fills the
    // page (big Provinces card + 3 rows) instead of leaving a large empty gap
    // above the bottom bar. Smaller phones simply scroll.
    BoxWithConstraints(Modifier.padding(padding).fillMaxSize()) {
      val available = (maxHeight - 108.dp - 96.dp - 32.dp - 48.dp).coerceAtLeast(380.dp)
      val bigHeight = (available * 0.22f).coerceIn(112.dp, 200.dp)
      val rowHeight = ((available - bigHeight) / 3f).coerceIn(126.dp, 240.dp)
      LazyColumn {
        item { DetailTopBar("Explore Bicol", "Discover the whole region", null, trailingIcon = null) }
        item {
          Text(
            "BROWSE BY CATEGORY",
            color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
          )
        }
        item {
          Column(Modifier.padding(16.dp)) {
            // Provinces: big full-width tile
            val big = tiles.first { it.big }
            ExploreCategoryCard(big.title, big.subtitle, big.colorHex, big.icon, Modifier.fillMaxWidth().height(bigHeight), big.onClick)
            Spacer(Modifier.height(12.dp))
            // Remaining tiles: 2-column grid
            tiles.filter { !it.big }.chunked(2).forEach { row ->
              Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                row.forEach { t ->
                  ExploreCategoryCard(t.title, t.subtitle, t.colorHex, t.icon, Modifier.weight(1f).height(rowHeight), t.onClick)
                }
              }
              Spacer(Modifier.height(12.dp))
            }
          }
        }
        item { Spacer(Modifier.height(120.dp)) }
      }
    }
  }
}

@Composable
private fun ExploreCategoryCard(
  title: String,
  subtitle: String,
  colorHex: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  modifier: Modifier,
  onClick: () -> Unit
) {
  Box(
    modifier
      .clip(RoundedCornerShape(18.dp))
      .clickable(onClick = onClick)
  ) {
    PlaceholderImage(title, colorHex, 999.dp, modifier = Modifier.fillMaxSize(), icon = icon, shape = RoundedCornerShape(18.dp), imageId = title)
    Column(Modifier.align(Alignment.BottomStart).padding(14.dp)) {
      Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
      Spacer(Modifier.height(6.dp))
      Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
      Text(subtitle, color = Color.White.copy(alpha = 0.85f), fontSize = 11.sp)
    }
  }
}

@Composable
private fun SectionHeader(title: String, trailing: String?) {
  Row(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary)
    if (trailing != null) Text(trailing, color = BicolCoral, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
  }
}

// ============================================================
// CLASSIFICATION SCREEN - the "8 destination cards" / "4 festival
// cards" / "6 activity cards" / "3 cuisine cards" grid/list views
// ============================================================
@Composable
fun ClassificationScreen(categoryId: String, onBack: () -> Unit, onClassificationClick: (String, String) -> Unit) {
  val (title, subtitle, heading, desc, classifications, style) = when (categoryId) {
    "destinations" -> DestinationsCopy
    "festivals" -> FestivalsCopy
    "activities" -> ActivitiesCopy
    "cuisines" -> CuisinesCopy
    "culture" -> CultureCopy
    "stay" -> StayCopy
    else -> DestinationsCopy
  }
  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar(title, subtitle, onBack) }
      item {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
          Text(heading, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = BicolTextPrimary)
          Spacer(Modifier.height(4.dp))
          Text(desc, color = BicolTextSecondary, fontSize = 13.sp)
          Spacer(Modifier.height(12.dp))
        }
      }
      when (style) {
        "grid" -> item { CategoryTileGrid(classifications) { onClassificationClick(categoryId, it.id) } }
        "banner" -> item { CategoryBannerList(classifications) { onClassificationClick(categoryId, it.id) } }
        "food" -> item { CategoryFoodList(classifications) { onClassificationClick(categoryId, it.id) } }
      }
      item { Spacer(Modifier.height(120.dp)) }
    }
  }
}

// Kotlin auto-generates component1..component6 for this data class - no manual operators needed.
private data class CopySet(
  val title: String, val subtitle: String, val heading: String, val desc: String,
  val items: List<SimpleItem>, val style: String
)

private val DestinationsCopy = CopySet(
  "Destinations", "Bicolandia Experience", "Explore by Category", "Find your perfect adventure in the heart of Bicol.",
  destinationClassifications, "grid"
)
private val FestivalsCopy = CopySet(
  "Festivals", "Cultural Celebrations", "Choose a Category", "Discover the vibrant traditions and soul of the Bicol region.",
  festivalClassifications, "banner"
)
private val ActivitiesCopy = CopySet(
  "Activities", "Adventure Awaits", "What's your vibe?", "From volcanic trails to crystal clear waters, choose your next thrill in Bicol.",
  activityClassifications, "grid"
)
private val CuisinesCopy = CopySet(
  "Cuisines", "Taste of Bicol", "Spicy, Creamy, & Savory", "Discover the unique flavors of the Bicol region, famous for coconut milk and siling labuyo.",
  cuisineClassifications, "food"
)
private val CultureCopy = CopySet(
  "Culture & Heritage", "Soul of Bicolandia", "Choose a Category", "From the Ibalong Epic to living crafts and folk traditions - the heritage that shapes Bicolandia.",
  cultureClassifications, "banner"
)
private val StayCopy = CopySet(
  "Stay & Rest", "Where to Stay", "Choose a Category", "A tourism information guide to lodging types across Bicol - not a booking service.",
  stayClassifications, "grid"
)

// ============================================================
// PLACE LIST SCREEN - the "5 examples" list under a classification
// ============================================================
@Composable
fun PlaceListScreen(categoryId: String, classificationId: String, onBack: () -> Unit, onPlaceClick: (PlaceDetail) -> Unit) {
  val dataMap = when (categoryId) {
    "destinations" -> destinationsData
    "festivals" -> festivalsData
    "activities" -> activitiesData
    "cuisines" -> cuisinesData
    "culture" -> cultureData
    else -> emptyMap()
  }
  val places = dataMap[classificationId] ?: emptyList()
  val label = places.firstOrNull()?.category ?: ""
  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar(label, "Bicolandia Flavors", onBack, trailingIcon = Icons.Filled.List) }
      item { SearchBarRow("Search ${label.lowercase()}...") }
      item { Spacer(Modifier.height(8.dp)) }
      items(places) { p -> PlaceListCard(p) { onPlaceClick(p) } }
      item { Spacer(Modifier.height(120.dp)) }
    }
  }
}
