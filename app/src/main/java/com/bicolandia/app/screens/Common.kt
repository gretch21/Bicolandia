package com.bicolandia.app.screens

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.AppImages
import com.bicolandia.app.data.PlaceDetail
import com.bicolandia.app.data.Province
import com.bicolandia.app.data.QuickFact
import com.bicolandia.app.data.SimpleItem
import com.bicolandia.app.data.isVisitable
import com.bicolandia.app.ui.theme.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

fun iconFor(category: String): ImageVector = when {
  category.contains("Volcano", true) -> Icons.Filled.Terrain
  category.contains("Beach", true) -> Icons.Filled.BeachAccess
  category.contains("Waterfall", true) -> Icons.Filled.Water
  category.contains("Hopping", true) -> Icons.Filled.DirectionsBoat
  category.contains("Island", true) -> Icons.Filled.Landscape
  category.contains("Park", true) -> Icons.Filled.Park
  category.contains("Historical", true) -> Icons.Filled.AccountBalance
  category.contains("Church", true) -> Icons.Filled.AccountBalance
  category.contains("Museum", true) -> Icons.Filled.Museum
  category.contains("Cultural", true) -> Icons.Filled.TheaterComedy
  category.contains("Religious", true) -> Icons.Filled.AccountBalance
  category.contains("Agricultural", true) -> Icons.Filled.Grass
  category.contains("Eco", true) -> Icons.Filled.Eco
  category.contains("ATV", true) -> Icons.Filled.TwoWheeler
  category.contains("Hiking", true) -> Icons.Filled.Hiking
  category.contains("Surfing", true) -> Icons.Filled.Surfing
  category.contains("Diving", true) -> Icons.Filled.Pool
  category.contains("Whale", true) -> Icons.Filled.Waves
  category.contains("Traditional", true) -> Icons.Filled.Restaurant
  category.contains("Delicac", true) -> Icons.Filled.Cake
  category.contains("Restaurant", true) -> Icons.Filled.Storefront
  category.contains("Festival", true) -> Icons.Filled.Celebration
  // Stay & Rest type tiles (exact matches so no other category is affected)
  category.equals("Hotels", true) -> Icons.Filled.Hotel
  category.equals("Resorts", true) -> Icons.Filled.Villa
  category.equals("Homestays", true) -> Icons.Filled.House
  category.equals("Inns", true) -> Icons.Filled.Cottage
  category.equals("Mountain Accommodations", true) -> Icons.Filled.Terrain
  else -> Icons.Filled.Image
}

@Composable
fun PlaceholderImage(
  label: String,
  colorHex: String,
  height: Dp = 120.dp,
  modifier: Modifier = Modifier,
  icon: ImageVector = Icons.Filled.Image,
  shape: Shape = RoundedCornerShape(18.dp),
  imageId: String? = null
) {
  val base = Color.fromHex(colorHex)
  val resId = AppImages.resolve(imageId, label)
  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(height)
      .clip(shape)
      .background(base)
  ) {
    if (resId != null) {
      Image(
        painter = painterResource(id = resId),
        contentDescription = label,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
    } else {
      // No local photo for this one yet - fall back to a colored tile with an icon
      Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Icon(icon, contentDescription = label, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(32.dp))
      }
    }
    // Bottom scrim so any overlaid title/badge text stays legible on the photo
    Box(
      Modifier
        .fillMaxSize()
        .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.02f), Color.Black.copy(alpha = 0.45f))))
    )
  }
}

// ============================================================
// TOP BAR - back circle, bold title + small colored uppercase
// subtitle, trailing action circle (filter/search)
// ============================================================
@Composable
fun DetailTopBar(title: String, subtitle: String? = null, onBack: (() -> Unit)? = null, trailingIcon: ImageVector? = Icons.Filled.Tune) {
  Row(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    if (onBack != null) {
      RoundIconButton(Icons.Filled.ArrowBack, onClick = onBack)
      Spacer(Modifier.width(12.dp))
    }
    Column(Modifier.weight(1f)) {
      Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = BicolTextPrimary)
      if (subtitle != null) {
        Text(subtitle.uppercase(), fontWeight = FontWeight.SemiBold, fontSize = 11.sp, color = BicolDeepGreen)
      }
    }
    if (trailingIcon != null) RoundIconButton(trailingIcon, onClick = {})
  }
}

@Composable
fun RoundIconButton(icon: ImageVector, tint: Color = BicolTextPrimary, bg: Color = Color(0xFFF0F1F3), onClick: () -> Unit) {
  Box(
    Modifier.size(40.dp).clip(CircleShape).background(bg).clickable(onClick = onClick),
    contentAlignment = Alignment.Center
  ) { Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(20.dp)) }
}

// ============================================================
// SEARCH BAR
// ============================================================
@Composable
fun SearchBarRow(placeholder: String, onSearchClick: () -> Unit = {}) {
  Row(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      Modifier
        .weight(1f)
        .height(48.dp)
        .clip(RoundedCornerShape(24.dp))
        .background(Color(0xFFF0F1F3))
        .padding(horizontal = 16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(Icons.Filled.Search, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(18.dp))
      Spacer(Modifier.width(8.dp))
      Text(placeholder, color = BicolTextSecondary, fontSize = 14.sp)
    }
  }
}

@Composable
fun SearchBarWithButton(placeholder: String, onSearch: (String) -> Unit = {}) {
  var query by remember { mutableStateOf("") }
  Row(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      Modifier
        .weight(1f)
        .height(52.dp)
        .clip(RoundedCornerShape(26.dp))
        .background(Color(0xFFF0F1F3))
        .padding(horizontal = 16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(Icons.Filled.Search, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(18.dp))
      Spacer(Modifier.width(8.dp))
      Box(Modifier.weight(1f)) {
        if (query.isEmpty()) Text(placeholder, color = BicolTextSecondary, fontSize = 13.sp)
        BasicTextField(
          value = query,
          onValueChange = { query = it },
          singleLine = true,
          textStyle = TextStyle(fontSize = 13.sp, color = BicolTextPrimary),
          cursorBrush = SolidColor(BicolCoral),
          keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
          keyboardActions = KeyboardActions(onSearch = { onSearch(query.trim()) }),
          modifier = Modifier.fillMaxWidth()
        )
      }
      if (query.isNotEmpty()) {
        Icon(
          Icons.Filled.Close, contentDescription = "Clear search", tint = BicolTextSecondary,
          modifier = Modifier.size(18.dp).clip(CircleShape).clickable { query = "" }
        )
      }
    }
    Spacer(Modifier.width(10.dp))
    Box(
      Modifier.height(52.dp).clip(RoundedCornerShape(26.dp)).background(BicolCoral).clickable { onSearch(query.trim()) }.padding(horizontal = 22.dp),
      contentAlignment = Alignment.Center
    ) { Text("Search", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp) }
  }
}

// ============================================================
// FILTER CHIPS (Explore / Favorites)
// ============================================================
@Composable
fun FilterChipsRow(options: List<String>, selected: String, onSelect: (String) -> Unit) {
  LazyRow(Modifier.padding(horizontal = 16.dp, vertical = 4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    items(options) { opt ->
      val isSelected = opt == selected
      Box(
        Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(if (isSelected) BicolCoral else Color(0xFFF0F1F3))
          .clickable { onSelect(opt) }
          .padding(horizontal = 18.dp, vertical = 10.dp)
      ) {
        Text(opt, color = if (isSelected) Color.White else BicolTextPrimary, fontWeight = FontWeight.Medium, fontSize = 13.sp)
      }
    }
  }
}

// ============================================================
// STATUS / TAG PILL BADGE
// ============================================================
@Composable
fun StatusBadge(text: String, colorHex: String) {
  Box(
    Modifier.clip(RoundedCornerShape(20.dp)).background(Color.fromHex(colorHex)).padding(horizontal = 12.dp, vertical = 5.dp)
  ) { Text(text, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 10.sp) }
}

@Composable
fun RatingPill(rating: Float) {
  Row(
    Modifier.clip(RoundedCornerShape(20.dp)).background(Color.Black.copy(alpha = 0.55f)).padding(horizontal = 10.dp, vertical = 5.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(Icons.Filled.Star, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(13.dp))
    Spacer(Modifier.width(3.dp))
    Text("$rating", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
  }
}

// ============================================================
// BIG VERTICAL LIST CARD (Volcanoes/Dishes/Festivals list screens)
// image + rating pill, status badge, title+location, desc,
// engagement count, circular arrow button
// ============================================================
@Composable
fun PlaceListCard(place: PlaceDetail, onClick: () -> Unit) {
  Card(
    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).clickable(onClick = onClick),
    shape = RoundedCornerShape(20.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column {
      Box(Modifier.fillMaxWidth()) {
        PlaceholderImage(place.name, place.colorHex, 170.dp, icon = iconFor(place.category), shape = RoundedCornerShape(0.dp), imageId = place.id)
        Box(Modifier.align(Alignment.TopEnd).padding(10.dp)) { RatingPill(place.rating) }
      }
      Column(Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(place.name, fontWeight = FontWeight.Bold, fontSize = 17.sp, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
          StatusBadge(place.status, place.statusColorHex)
        }
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(Icons.Filled.LocationOn, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(13.dp))
          Spacer(Modifier.width(2.dp))
          Text(place.location, color = BicolTextSecondary, fontSize = 12.sp)
        }
        Spacer(Modifier.height(6.dp))
        Text(place.shortDescription, color = BicolTextSecondary, fontSize = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(Icons.Filled.People, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(15.dp))
          Spacer(Modifier.width(4.dp))
          Text(place.engagement, color = BicolTextSecondary, fontSize = 12.sp, modifier = Modifier.weight(1f))
          RoundIconButton(Icons.Filled.ArrowForward, tint = Color.White, bg = BicolDeepGreen, onClick = onClick)
        }
      }
    }
  }
}

// ============================================================
// QUICK FACTS 2x2 GRID
// ============================================================
private val factIcons = listOf(Icons.Filled.Business, Icons.Filled.Translate, Icons.Filled.WbSunny, Icons.Filled.CheckCircle)
private val factIconBg = listOf(Color(0xFFDCE9FA), Color(0xFFFCE3D6), Color(0xFFFDF3D0), Color(0xFFD9F0DE))
private val factIconTint = listOf(Color(0xFF3E7CB1), Color(0xFFE8734A), Color(0xFFD1A526), Color(0xFF2E7D32))

@Composable
fun QuickFactsGrid(facts: List<QuickFact>) {
  Column(Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
    Text("Quick Facts", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary)
    Spacer(Modifier.height(10.dp))
    val rows = facts.chunked(2)
    rows.forEach { row ->
      Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        row.forEachIndexed { idx, fact ->
          val globalIdx = facts.indexOf(fact)
          Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
          ) {
            Column(Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                Modifier.size(34.dp).clip(CircleShape).background(factIconBg[globalIdx % 4]),
                contentAlignment = Alignment.Center
              ) { Icon(factIcons[globalIdx % 4], contentDescription = null, tint = factIconTint[globalIdx % 4], modifier = Modifier.size(16.dp)) }
              Spacer(Modifier.height(6.dp))
              Text(fact.label.uppercase(), color = BicolTextSecondary, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
              Spacer(Modifier.height(2.dp))
              Text(fact.value, fontWeight = FontWeight.Bold, fontSize = 13.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
          }
        }
        if (row.size == 1) Spacer(Modifier.weight(1f))
      }
      Spacer(Modifier.height(12.dp))
    }
  }
}

// ============================================================
// HIGHLIGHT / ACTIVITY LIST ROWS
// ============================================================
private val highlightIcons = listOf(Icons.Filled.DirectionsWalk, Icons.Filled.PhotoCamera, Icons.Filled.TwoWheeler, Icons.Filled.Star, Icons.Filled.Groups)
private val highlightBgs = listOf(Color(0xFFE3F1E8), Color(0xFFF0F1F3), Color(0xFFF0F1F3), Color(0xFFE3F1E8), Color(0xFFF0F1F3))

@Composable
fun HighlightsSection(title: String, items: List<QuickFact>) {
  if (items.isEmpty()) return
  Column(Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
    Text(title, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary)
    Spacer(Modifier.height(10.dp))
    items.forEachIndexed { i, item ->
      Row(
        Modifier
          .fillMaxWidth()
          .padding(bottom = 10.dp)
          .clip(RoundedCornerShape(14.dp))
          .background(highlightBgs[i % highlightBgs.size])
          .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(Modifier.size(38.dp).clip(RoundedCornerShape(10.dp)).background(Color.White), contentAlignment = Alignment.Center) {
          Icon(highlightIcons[i % highlightIcons.size], contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column {
          Text(item.label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
          Text(item.value, color = BicolTextSecondary, fontSize = 12.sp)
        }
      }
    }
  }
}

// ============================================================
// NEARBY / HORIZONTAL SMALL CARDS
// ============================================================
@Composable
fun NearbyRow(title: String, names: List<String>, colorHex: String, category: String = "", onViewMap: (() -> Unit)? = null) {
  if (names.isEmpty()) return
  Column(Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      Text(title, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary)
      if (onViewMap != null) Text("View Map", color = BicolDeepGreen, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, modifier = Modifier.clickable(onClick = onViewMap))
    }
    Spacer(Modifier.height(10.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
      items(names) { name ->
        Column(Modifier.width(140.dp)) {
          PlaceholderImage(name, colorHex, 90.dp, icon = iconFor(category))
          Spacer(Modifier.height(6.dp))
          Text(name, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
      }
    }
  }
}

// ============================================================
// GENERIC HERO DETAIL SCREEN (used for destinations, festivals,
// activities, cuisines - matches Mayon/Bicol Express/Magayon/
// Cagsawa ATV detail screens exactly)
// ============================================================
@Composable
fun PlaceDetailScreen(place: PlaceDetail, onBack: () -> Unit, onTravelGuide: (PlaceDetail) -> Unit = {}) {
  val context = LocalContext.current
  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item {
        Box(Modifier.fillMaxWidth()) {
          // Softened bottom corners so the hero photo blends smoothly into the content below
          PlaceholderImage(
            place.name, place.colorHex, 340.dp, icon = iconFor(place.category),
            shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp),
            imageId = place.id
          )
          Row(
            Modifier.fillMaxWidth().padding(16.dp).align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            RoundIconButton(Icons.Filled.ArrowBack, tint = Color.White, bg = Color.Black.copy(alpha = 0.35f), onClick = onBack)
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
              val isFav = FavoritesStore.favoriteIds.contains(place.id)
              RoundIconButton(
                if (isFav) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                tint = if (isFav) BicolCoral else Color.White,
                bg = Color.Black.copy(alpha = 0.35f),
                onClick = { FavoritesStore.toggle(place.id) }
              )
              RoundIconButton(
                Icons.Filled.Share, tint = Color.White, bg = Color.Black.copy(alpha = 0.35f),
                onClick = { sharePlaceText(context, place.name, place.location, place.shortDescription) }
              )
            }
          }
          Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              StatusBadge(place.status, place.statusColorHex)
              Spacer(Modifier.width(8.dp))
              Icon(Icons.Filled.Star, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(14.dp))
              Text(" ${place.rating}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
              Text(" (${place.engagement} Reviews)", color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
            }
            Spacer(Modifier.height(4.dp))
            Text(place.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 26.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Filled.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
              Text(" ${place.location}", color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
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
                .clickable { onTravelGuide(place) }
                .padding(horizontal = 16.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(Icons.Filled.DirectionsBus, contentDescription = null, tint = Color.White, modifier = Modifier.size(15.dp))
              Spacer(Modifier.width(8.dp))
              Text("How to Get There", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            }
            if (isVisitable(place)) VisitedPill(place.id)
          }
          Spacer(Modifier.height(16.dp))
          Text("About", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary)
          Spacer(Modifier.height(6.dp))
          Text(place.about, fontSize = 13.sp, color = BicolTextSecondary, lineHeight = 19.sp)
          QuickFactsGrid(place.quickFacts)
          HighlightsSection("Highlights", place.highlights)
          PlaceExtraSections(place)
          NearbyRow("Nearby Attractions", place.nearbyAttractions, place.colorHex, place.category, onViewMap = {})
          NearbyRow("Nearby Restaurants", place.nearbyRestaurants, "#8D6E63", "Restaurants")
          Spacer(Modifier.height(120.dp))
        }
      }
    }
  }
}

// ============================================================
// CATEGORY GRIDS
// ============================================================
@Composable
fun CategoryTileGrid(items: List<SimpleItem>, columns: Int = 2, onClick: (SimpleItem) -> Unit) {
  // Nested inside an outer LazyColumn item { } - that gives this composable an
  // INFINITE height constraint. A LazyVerticalGrid with fillMaxSize() under an
  // infinite constraint throws "Vertically scrollable component was measured
  // with an infinity maximum height constraints" the instant this screen renders,
  // which is exactly the crash-on-tap bug. Fix: give it an explicit, computed
  // height (like ProvinceListScreen already does) and disable its own scrolling
  // so the outer LazyColumn is the only scroll container.
  val rows = (items.size + columns - 1) / columns
  val tileHeight = 150.dp
  val spacing = 12.dp
  val contentPadding = 16.dp
  val gridHeight = contentPadding * 2 + tileHeight * rows + spacing * (rows - 1).coerceAtLeast(0)
  LazyVerticalGrid(
    columns = GridCells.Fixed(columns),
    contentPadding = PaddingValues(16.dp),
    horizontalArrangement = Arrangement.spacedBy(12.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp),
    modifier = Modifier.fillMaxWidth().height(gridHeight),
    userScrollEnabled = false
  ) {
    items(items) { item ->
      Box(
        Modifier
          .fillMaxWidth()
          .height(150.dp)
          .clip(RoundedCornerShape(18.dp))
          .clickable { onClick(item) }
      ) {
        PlaceholderImage(item.title, item.colorHex, 150.dp, icon = iconFor(item.title), shape = RoundedCornerShape(18.dp), imageId = item.id)
        Box(
          Modifier.align(Alignment.TopStart).padding(10.dp).size(28.dp).clip(CircleShape).background(Color.Black.copy(alpha = 0.35f)),
          contentAlignment = Alignment.Center
        ) { Icon(iconFor(item.title), contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp)) }
        Column(Modifier.align(Alignment.BottomStart).padding(10.dp)) {
          Text(item.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
          if (item.subtitle.isNotEmpty()) Text(item.subtitle, color = Color.White.copy(alpha = 0.85f), fontSize = 11.sp)
        }
      }
    }
  }
}

// Single-column banner cards (Festivals categories)
@Composable
fun CategoryBannerList(items: List<SimpleItem>, onClick: (SimpleItem) -> Unit) {
  Column(Modifier.padding(horizontal = 16.dp)) {
    items.forEach { item ->
      Box(
        Modifier
          .fillMaxWidth()
          .height(150.dp)
          .padding(bottom = 14.dp)
          .clip(RoundedCornerShape(20.dp))
          .clickable { onClick(item) }
      ) {
        PlaceholderImage(item.title, item.colorHex, 150.dp, icon = iconFor(item.title), shape = RoundedCornerShape(20.dp), imageId = item.id)
        Box(
          Modifier.align(Alignment.TopStart).padding(12.dp).size(30.dp).clip(CircleShape).background(Color.Black.copy(alpha = 0.35f)),
          contentAlignment = Alignment.Center
        ) { Icon(iconFor(item.title), contentDescription = null, tint = Color.White, modifier = Modifier.size(15.dp)) }
        Box(
          Modifier.align(Alignment.CenterEnd).padding(14.dp).size(34.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.3f)),
          contentAlignment = Alignment.Center
        ) { Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = Color.White) }
        Column(Modifier.align(Alignment.BottomStart).padding(14.dp)) {
          Text(item.title.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp, lineHeight = 20.sp)
          if (item.subtitle.isNotEmpty()) Text(item.subtitle, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
        }
      }
    }
  }
}

// Single-column food cards (Cuisines categories) - badge top-left, icon circle bottom-right
@Composable
fun CategoryFoodList(items: List<SimpleItem>, onClick: (SimpleItem) -> Unit) {
  Column(Modifier.padding(horizontal = 16.dp)) {
    items.forEach { item ->
      Box(
        Modifier
          .fillMaxWidth()
          .height(160.dp)
          .padding(bottom = 14.dp)
          .clip(RoundedCornerShape(20.dp))
          .clickable { onClick(item) }
      ) {
        PlaceholderImage(item.title, item.colorHex, 160.dp, icon = iconFor(item.title), shape = RoundedCornerShape(20.dp), imageId = item.id)
        if (item.badge.isNotEmpty()) {
          Box(Modifier.align(Alignment.TopStart).padding(12.dp)) { StatusBadge(item.badge, "#D9483A") }
        }
        Box(
          Modifier.align(Alignment.BottomEnd).padding(14.dp).size(36.dp).clip(CircleShape).background(Color.Black.copy(alpha = 0.4f)),
          contentAlignment = Alignment.Center
        ) { Icon(iconFor(item.title), contentDescription = null, tint = Color.White, modifier = Modifier.size(17.dp)) }
        Column(Modifier.align(Alignment.BottomStart).padding(14.dp)) {
          Text(item.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
          if (item.subtitle.isNotEmpty()) Text(item.subtitle, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
        }
      }
    }
  }
}

@Composable
fun NotFoundScreen(onBack: () -> Unit) {
  Scaffold(containerColor = BicolBg) { padding ->
    Column(
      Modifier.padding(padding).fillMaxSize().padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Icon(Icons.Filled.SearchOff, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(48.dp))
      Spacer(Modifier.height(12.dp))
      Text("We couldn't find that page", fontWeight = FontWeight.Bold, fontSize = 16.sp)
      Spacer(Modifier.height(6.dp))
      Text("Let's head back and try another spot.", color = BicolTextSecondary, fontSize = 13.sp)
      Spacer(Modifier.height(20.dp))
      Box(
        Modifier.clip(RoundedCornerShape(24.dp)).background(BicolCoral).clickable(onClick = onBack).padding(horizontal = 24.dp, vertical = 12.dp)
      ) { Text("Go Back", color = Color.White, fontWeight = FontWeight.SemiBold) }
    }
  }
}
