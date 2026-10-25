package com.bicolandia.app.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.*
import com.bicolandia.app.ui.theme.*
import kotlinx.coroutines.delay

val trivias = listOf(
  Triple("Mayon's Perfect Cone", "Renowned as the \"Perfect Cone\" because of its almost symmetric conical shape.", "#2E88D8"),
  Triple("Whale Shark Capital", "Donsol, Sorsogon is globally known as the whale shark capital of the world.", "#1E8A6E"),
  Triple("Asia's Biggest Marian Devotion", "The Peñafrancia Festival draws over a million devotees to Naga City every September.", "#5E35B1"),
  Triple("Bicol's Fiery Cuisine", "Bicolanos are famous for cooking with siling labuyo and rich coconut milk (gata).", "#C24444"),
  Triple("Ruins of an Eruption", "The Cagsawa Ruins are the remains of a church buried by Mayon's 1814 eruption.", "#6D4C41"),
  Triple("The Philippines' Pili Country", "Bicol is the country's top grower of pili, a native nut used in candy and pastries.", "#8D6E63"),
  Triple("A Reality TV Backdrop", "Caramoan's lagoons and limestone cliffs have hosted international Survivor editions.", "#00897B"),
  Triple("Gentle Giants of the Pass", "Ticao Pass and San Miguel Bay are feeding grounds for migratory manta rays.", "#0288D1"),
  Triple("A River Runs Through It", "The Bicol River is one of the country's major rivers, long central to regional trade.", "#2E7D32"),
  Triple("Many Tongues, One Region", "Bicolano has several distinct dialects that can vary from province to province.", "#EF6C00")
)


private const val TRIVIA_INTERVAL_MS = 6000L

@Composable
fun HomeScreen(
  onMenuClick: () -> Unit = {},
  onSeeAllDestinations: () -> Unit = {},
  onSeeAllActivities: () -> Unit = {},
  onSeeAllCuisines: () -> Unit = {},
  onSeeAllFestivals: () -> Unit = {},
  onPlaceClick: (PlaceDetail) -> Unit = {},
  onOpenTravelGuide: () -> Unit = {},
  onOpenEssentials: () -> Unit = {},
  onSearch: (String) -> Unit = {}
) {

  val topDestinations = destinationsData.values.flatten().sortedByDescending { it.rating }.take(6)
  val topActivities = (atvRides + whaleShark + hiking).take(6)
  val topCuisines = (traditionalDishes + delicacies).take(6)
  val upcomingFestivals = culturalFestivals.take(6)

  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item {
        Row(
          Modifier.fillMaxWidth().padding(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          RoundIconButton(Icons.Filled.Menu, onClick = onMenuClick)
          Spacer(Modifier.width(12.dp))
          Column(Modifier.weight(1f)) {
            Text("Bicolandia", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = BicolTextPrimary)
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Filled.LocationOn, contentDescription = null, tint = BicolCoral, modifier = Modifier.size(13.dp))
              Text(" Philippines", color = BicolTextSecondary, fontSize = 12.sp)
            }
          }
        }
      }
      item { SearchBarWithButton("Search destinations, food...", onSearch) }
      item { HeroBanner() }
      item { DidYouKnowPager() }
      item { HomeDestinationSection("Region 5", "Top Destinations", topDestinations, onSeeAllDestinations, onPlaceClick) }
      item { HomeCarouselSection("Adventure", "Top Activities", topActivities, onSeeAllActivities, onPlaceClick) }
      item { HomeCuisineStorySection("Taste of Bicol", "Top Cuisines", topCuisines, onSeeAllCuisines, onPlaceClick) }
      item { HomeFestivalSection("Mark your calendar", "Upcoming Festivals", upcomingFestivals, onSeeAllFestivals, onPlaceClick) }
      item { Spacer(Modifier.height(120.dp)) }
    }
  }
}

@Composable
private fun HeroBanner() {
  val resId = AppImages.forId("volcano_mayon")
  Box(
    Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .height(190.dp)
      .clip(RoundedCornerShape(28.dp))
  ) {
    if (resId != null) {
      Image(
        painter = painterResource(id = resId),
        contentDescription = "Bicolandia",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
    } else {
      Box(Modifier.fillMaxSize().background(Color(0xFF3E2723)))
    }

    Box(
      Modifier
        .fillMaxSize()
        .background(
          Brush.verticalGradient(
            listOf(
              Color.Black.copy(alpha = 0.15f),
              Color.Black.copy(alpha = 0.35f),
              Color.Black.copy(alpha = 0.75f)
            )
          )
        )
    )
    Column(Modifier.align(Alignment.BottomStart).padding(20.dp)) {
      Text("REGION 5 · PHILIPPINES", color = BicolOrange, fontWeight = FontWeight.Bold, fontSize = 11.sp)
      Spacer(Modifier.height(4.dp))
      Row {
        Text("Bicol", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 26.sp)
        Text("andia", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 26.sp)
      }
      Spacer(Modifier.height(4.dp))
      Text(
        "Discover volcanoes, whale sharks, and the spiciest food in the Philippines - all in one region.",
        color = Color.White.copy(alpha = 0.9f),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        modifier = Modifier.fillMaxWidth(0.85f)
      )
    }
  }
}

@Composable
private fun HomeTripToolsSection(onOpenTravelGuide: () -> Unit, onOpenEssentials: () -> Unit) {
  Column(Modifier.padding(top = 10.dp)) {
    Column(Modifier.padding(horizontal = 16.dp)) {
      Text("PLAN YOUR TRIP", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 10.sp)
      Text("Before You Go", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary)
    }
    Spacer(Modifier.height(10.dp))
    Row(
      Modifier.fillMaxWidth().padding(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      TripToolCard(
        "Travel Guide", "How do I get there?", Icons.Filled.DirectionsBus,
        listOf(BicolCoral, Color(0xFFF2735F)), Modifier.weight(1f), onOpenTravelGuide
      )
      TripToolCard(
        "Tourist Essentials", "What do I need to know before/during my trip?", Icons.Filled.HealthAndSafety,
        listOf(BicolDeepGreen, Color(0xFF2E7D32)), Modifier.weight(1f), onOpenEssentials
      )
    }
  }
}

@Composable
private fun TripToolCard(
  title: String,
  question: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  colors: List<Color>,
  modifier: Modifier,
  onClick: () -> Unit
) {
  Box(
    modifier
      .height(168.dp)
      .clip(RoundedCornerShape(20.dp))
      .background(Brush.linearGradient(colors))
      .clickable(onClick = onClick)
      .padding(14.dp)
  ) {
    Icon(
      icon, contentDescription = null,
      tint = Color.White.copy(alpha = 0.18f),
      modifier = Modifier.align(Alignment.BottomEnd).size(76.dp)
    )
    Icon(
      Icons.Filled.ArrowForward, contentDescription = null,
      tint = Color.White.copy(alpha = 0.9f),
      modifier = Modifier.align(Alignment.TopEnd).size(18.dp)
    )
    Column {
      Box(Modifier.size(36.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.22f)), contentAlignment = Alignment.Center) {
        Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(19.dp))
      }
      Spacer(Modifier.height(10.dp))
      Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp, lineHeight = 18.sp)
      Spacer(Modifier.height(6.dp))
      Row(Modifier.height(IntrinsicSize.Min)) {
        Box(Modifier.width(2.dp).fillMaxHeight().background(Color.White.copy(alpha = 0.6f)))
        Spacer(Modifier.width(7.dp))
        Text("\"$question\"", color = Color.White.copy(alpha = 0.92f), fontSize = 11.5.sp, lineHeight = 15.sp)
      }
    }
  }
}

// ============================================================
// DID YOU KNOW - auto-advancing horizontal pager (10 cards),
// shifts to the next card automatically every few seconds.
// ============================================================
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DidYouKnowPager() {
  val pagerState = rememberPagerState(pageCount = { trivias.size })

  // Follows the "Auto-advance Did You Know" switch in Profile > Account Settings
  LaunchedEffect(pagerState, AppStore.autoAdvanceTrivia) {
    if (AppStore.autoAdvanceTrivia) {
      while (true) {
        delay(TRIVIA_INTERVAL_MS)
        val next = (pagerState.currentPage + 1) % trivias.size
        pagerState.animateScrollToPage(next)
      }
    }
  }

  Column(Modifier.padding(vertical = 4.dp)) {
    HorizontalPager(
      state = pagerState,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .height(150.dp)
    ) { page ->
      val trivia = trivias[page]
      Box(
        Modifier
          .fillMaxSize()
          .clip(RoundedCornerShape(20.dp))
          .background(Brush.horizontalGradient(listOf(Color.fromHex(trivia.third), Color.fromHex(trivia.third).copy(alpha = 0.75f))))
          .padding(20.dp)
      ) {
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
            Text(" DID YOU KNOW?", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
          }
          Spacer(Modifier.height(8.dp))
          Text(trivia.first, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
          Spacer(Modifier.height(4.dp))
          Text(trivia.second, color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp, lineHeight = 16.sp, modifier = Modifier.fillMaxWidth(0.75f))
        }
        Icon(
          Icons.Filled.Terrain, contentDescription = null,
          tint = Color.White.copy(alpha = 0.25f),
          modifier = Modifier.align(Alignment.BottomEnd).size(90.dp)
        )
      }
    }
    Row(
      Modifier.fillMaxWidth().padding(top = 8.dp),
      horizontalArrangement = Arrangement.Center
    ) {
      repeat(trivias.size) { i ->
        val active = i == pagerState.currentPage
        Box(
          Modifier
            .padding(horizontal = 2.dp)
            .size(if (active) 7.dp else 5.dp)
            .clip(CircleShape)
            .background(if (active) BicolCoral else Color(0xFFD9DBDF))
        )
      }
    }
  }
}

@Composable
private fun SectionHeader(eyebrow: String, title: String, onSeeAll: () -> Unit) {
  Row(
    Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column {
      Text(eyebrow.uppercase(), color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 10.sp)
      Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary)
    }
    Text(
      "See all",
      color = Color(0xFF2E88D8),
      fontWeight = FontWeight.SemiBold,
      fontSize = 13.sp,
      modifier = Modifier.clickable(onClick = onSeeAll)
    )
  }
}

@Composable
private fun HomeDestinationSection(
  eyebrow: String,
  title: String,
  places: List<PlaceDetail>,
  onSeeAll: () -> Unit,
  onPlaceClick: (PlaceDetail) -> Unit
) {
  Column(Modifier.padding(top = 14.dp)) {
    SectionHeader(eyebrow, title, onSeeAll)
    Spacer(Modifier.height(10.dp))
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      // Only the first 5 are shown here; the 6th stays in the list, unseen, for "See all"
      items(places.take(5)) { p ->
        Card(
          modifier = Modifier.width(230.dp).clickable { onPlaceClick(p) },
          shape = RoundedCornerShape(20.dp),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column {
            Box {
              PlaceholderImage(p.name, p.colorHex, 170.dp, icon = iconFor(p.category), shape = RoundedCornerShape(0.dp), imageId = p.id)
              val isFav = FavoritesStore.favoriteIds.contains(p.id)
              Box(
                Modifier
                  .align(Alignment.TopEnd).padding(10.dp).size(30.dp).clip(CircleShape)
                  .background(Color.White.copy(alpha = 0.9f))
                  .clickable { FavoritesStore.toggle(p.id) },
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  if (isFav) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                  contentDescription = null, tint = BicolCoral, modifier = Modifier.size(15.dp)
                )
              }
            }
            Column(Modifier.padding(14.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(p.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                Icon(Icons.Filled.Star, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(15.dp))
                Text(" ${p.rating}", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = BicolTextPrimary)
              }
              Spacer(Modifier.height(4.dp))
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.LocationOn, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(12.dp))
                Text(" ${p.location}", color = BicolTextSecondary, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun HomeCarouselSection(
  eyebrow: String,
  title: String,
  places: List<PlaceDetail>,
  onSeeAll: () -> Unit,
  onPlaceClick: (PlaceDetail) -> Unit
) {
  Column(Modifier.padding(top = 14.dp)) {
    SectionHeader(eyebrow, title, onSeeAll)
    Spacer(Modifier.height(10.dp))
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      // Only the first 5 are shown here; the 6th stays in the list, unseen, for "See all"
      items(places.take(5)) { p ->
        Box(Modifier.width(160.dp).clickable { onPlaceClick(p) }) {
          Box {
            PlaceholderImage(p.name, p.colorHex, 150.dp, icon = iconFor(p.category), imageId = p.id)
            val isFav = FavoritesStore.favoriteIds.contains(p.id)
            Box(
              Modifier
                .align(Alignment.TopEnd).padding(8.dp).size(28.dp).clip(CircleShape)
                .background(Color.White.copy(alpha = 0.85f))
                .clickable { FavoritesStore.toggle(p.id) },
              contentAlignment = Alignment.Center
            ) {
              Icon(
                if (isFav) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null, tint = BicolCoral, modifier = Modifier.size(14.dp)
              )
            }
            Column(Modifier.align(Alignment.BottomStart).padding(10.dp)) {
              Text(p.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(10.dp))
                Text(" ${p.location}", color = Color.White.copy(alpha = 0.85f), fontSize = 10.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.weight(1f))
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun HomeCuisineStorySection(
  eyebrow: String,
  title: String,
  places: List<PlaceDetail>,
  onSeeAll: () -> Unit,
  onPlaceClick: (PlaceDetail) -> Unit
) {
  Column(Modifier.padding(top = 14.dp)) {
    SectionHeader(eyebrow, title, onSeeAll)
    Spacer(Modifier.height(12.dp))
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // Only the first 5 are shown here; the 6th stays in the list, unseen, for "See all"
      items(places.take(5)) { p ->
        Column(
          Modifier.width(84.dp).clickable { onPlaceClick(p) },
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Box(
            Modifier
              .size(84.dp)
              .clip(CircleShape)
              .border(
                width = 2.5.dp,
                brush = Brush.linearGradient(listOf(BicolCoral, BicolOrange)),
                shape = CircleShape
              )
              .padding(3.dp)
          ) {
            PlaceholderImage(
              p.name, p.colorHex, 78.dp, icon = iconFor(p.category),
              shape = CircleShape,
              modifier = Modifier.clip(CircleShape),
              imageId = p.id
            )
          }
          Spacer(Modifier.height(6.dp))
          Text(
            p.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            color = BicolTextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
          )
        }
      }
    }
  }
}

@Composable
private fun HomeFestivalSection(
  eyebrow: String,
  title: String,
  places: List<PlaceDetail>,
  onSeeAll: () -> Unit,
  onPlaceClick: (PlaceDetail) -> Unit
) {
  Column(Modifier.padding(top = 14.dp)) {
    SectionHeader(eyebrow, title, onSeeAll)
    Spacer(Modifier.height(10.dp))
    Column(Modifier.padding(horizontal = 16.dp)) {
      places.forEach { p ->
        Box(
          Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(bottom = 14.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { onPlaceClick(p) }
        ) {
          PlaceholderImage(p.name, p.colorHex, 150.dp, icon = iconFor(p.category), shape = RoundedCornerShape(24.dp), imageId = p.id)
          Box(
            Modifier
              .fillMaxSize()
              .background(
                Brush.horizontalGradient(
                  listOf(Color.fromHex(p.colorHex).copy(alpha = 0.85f), Color.Black.copy(alpha = 0.25f), Color.Transparent)
                )
              )
          )
          Column(Modifier.align(Alignment.CenterStart).padding(20.dp).fillMaxWidth(0.72f)) {
            Text("EVERY ${p.status.uppercase()}", color = BicolOrange, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            Spacer(Modifier.height(4.dp))
            Text(p.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, lineHeight = 24.sp)
            Spacer(Modifier.height(4.dp))
            Text(p.shortDescription, color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp, lineHeight = 16.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
          }
        }
      }
    }
  }
}
