package com.bicolandia.app.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.*
import com.bicolandia.app.ui.theme.*

private val nodeXFractions = listOf(0.26f, 0.74f, 0.28f, 0.72f, 0.26f, 0.70f)
private val decorIcons = listOf(Icons.Filled.Waves, Icons.Filled.Park, Icons.Filled.Terrain, Icons.Filled.Celebration, Icons.Filled.DirectionsBoat, Icons.Filled.BeachAccess)

private fun badgeIcon(key: String): ImageVector = when (key) {
  "flag" -> Icons.Filled.Flag
  "volcano" -> Icons.Filled.Terrain
  "beach" -> Icons.Filled.BeachAccess
  "food" -> Icons.Filled.Restaurant
  "festival" -> Icons.Filled.Celebration
  "adventure" -> Icons.Filled.Hiking
  "stay" -> Icons.Filled.Hotel
  "pin" -> Icons.Filled.Place
  "route" -> Icons.Filled.Route
  else -> Icons.Filled.EmojiEvents
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BicolMapScreen(onBack: () -> Unit, onOpenRoute: (String) -> Unit) {
  val level = TripProgress.level()
  val visited = TripProgress.visitedCount()
  val total = TripProgress.totalPlaces()
  val progress = TripProgress.allProvinceProgress()
  val badges = TripProgress.badges()
  val earned = badges.count { it.earned }
  val starsEarned = progress.sumOf { it.stars }
  var selectedId by remember { mutableStateOf<String?>(null) }
  val selected = selectedId?.let { id -> progress.find { it.province.id == id } }

  if (selected != null) {
    ModalBottomSheet(onDismissRequest = { selectedId = null }, containerColor = Color.White) {
      ProvinceSheet(selected, onExplore = { selectedId = null; onOpenRoute("province_detail/${selected.province.id}") }) {
        selectedId = null
        onOpenRoute(it)
      }
    }
  }

  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar("My Trips", "Your Bicol Map", onBack, trailingIcon = null) }

      // ---------- summary ----------
      item {
        Box(
          Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Brush.linearGradient(listOf(BicolDeepGreen, Color(0xFF2E7D32))))
            .padding(20.dp)
        ) {
          Icon(Icons.Filled.EmojiEvents, contentDescription = null, tint = Color.White.copy(alpha = 0.14f), modifier = Modifier.align(Alignment.TopEnd).size(88.dp))
          Column {
            Text("YOUR LEVEL", color = BicolOrange, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            Text(level.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Spacer(Modifier.height(4.dp))
            Text("$visited of $total places visited", color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
            Spacer(Modifier.height(10.dp))
            val frac = if (total == 0) 0f else visited.toFloat() / total
            Box(Modifier.fillMaxWidth().height(9.dp).clip(RoundedCornerShape(5.dp)).background(Color.White.copy(alpha = 0.25f))) {
              Box(Modifier.fillMaxWidth(frac.coerceIn(0.02f, 1f)).fillMaxHeight().clip(RoundedCornerShape(5.dp)).background(Brush.horizontalGradient(listOf(BicolCoral, BicolOrange))))
            }
            Spacer(Modifier.height(8.dp))
            Text(
              if (level.nextAt != null) "${level.nextAt - level.current} more to reach ${level.nextName}" else "Top level reached. Salamat, Bicolano!",
              color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp
            )
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
              SummaryChip(Icons.Filled.Star, "$starsEarned/18 stars")
              SummaryChip(Icons.Filled.EmojiEvents, "$earned/${badges.size} badges")
            }
          }
        }
      }

      // ---------- the map ----------
      item {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
          Text("Bicol Trail", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary)
          Text("Tap a province to see its places. Visit more to earn stars!", color = BicolTextSecondary, fontSize = 12.sp)
        }
      }
      item { BicolTrail(progress) { selectedId = it } }

      // ---------- badges ----------
      item {
        Row(Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
          Text("Badges", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary, modifier = Modifier.weight(1f))
          Text("$earned of ${badges.size} earned", color = BicolCoral, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
        }
      }
      items(badges.chunked(3)) { row ->
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 5.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          row.forEach { b -> BadgeTile(b, Modifier.weight(1f)) }
          repeat(3 - row.size) { Spacer(Modifier.weight(1f)) }
        }
      }

      item {
        Card(
          Modifier.fillMaxWidth().padding(16.dp),
          shape = RoundedCornerShape(18.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
          elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
          Row(Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = Color(0xFFE08A00), modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(10.dp))
            Column {
              Text("How it works", fontWeight = FontWeight.Bold, fontSize = 14.sp)
              Spacer(Modifier.height(4.dp))
              Text(
                "Open any province, destination, festival, activity, restaurant or stay and tap Mark as Visited. Your trail lights up, provinces earn up to 3 stars, and new badges unlock as you explore Bicol.",
                color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 17.sp
              )
            }
          }
        }
      }
      item { Spacer(Modifier.height(110.dp)) }
    }
  }
}

@Composable
private fun SummaryChip(icon: ImageVector, text: String) {
  Row(
    Modifier.clip(RoundedCornerShape(16.dp)).background(Color.White.copy(alpha = 0.18f)).padding(horizontal = 12.dp, vertical = 6.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(icon, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(14.dp))
    Spacer(Modifier.width(5.dp))
    Text(text, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
  }
}

// ============================================================
// THE TRAIL - winding road with one node per province
// ============================================================
@Composable
private fun BicolTrail(progress: List<ProvinceProgress>, onSelect: (String) -> Unit) {
  val density = LocalDensity.current
  val nodeSize = 68.dp
  val stepY = 132.dp
  val topPad = 34.dp
  val blockHalfWidth = 62.dp
  val starsHeight = 22.dp
  val totalHeight = topPad + stepY * (progress.size - 1) + nodeSize + 78.dp

  // the province the traveler is standing on / should discover next
  val lastReached = progress.indexOfLast { it.stars >= 1 }
  val nextTarget = progress.indexOfFirst { it.stars == 0 }
  val pulseTransition = rememberInfiniteTransition(label = "trail")
  val pulse by pulseTransition.animateFloat(
    initialValue = 1f, targetValue = 1.16f,
    animationSpec = infiniteRepeatable(tween(850, easing = FastOutSlowInEasing), RepeatMode.Reverse), label = "pulse"
  )

  BoxWithConstraints(
    Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
      .height(totalHeight)
      .clip(RoundedCornerShape(26.dp))
      .background(Brush.verticalGradient(listOf(Color(0xFFE4F3E8), Color(0xFFE3F0FA), Color(0xFFFFF1DC))))
  ) {
    val width = maxWidth
    val centers = progress.indices.map { i ->
      Offset(
        with(density) { (width * nodeXFractions[i % nodeXFractions.size]).toPx() },
        with(density) { (topPad + starsHeight + stepY * i + nodeSize / 2).toPx() }
      )
    }

    Canvas(Modifier.matchParentSize()) {
      for (i in 0 until centers.size - 1) {
        val a = centers[i]
        val b = centers[i + 1]
        val dy = (b.y - a.y) * 0.55f
        val seg = Path().apply {
          moveTo(a.x, a.y)
          cubicTo(a.x, a.y + dy, b.x, b.y - dy, b.x, b.y)
        }
        val done = progress[i].stars >= 1
        // road edge
        drawPath(seg, Color.White.copy(alpha = 0.95f), style = Stroke(width = 30f, cap = StrokeCap.Round))
        if (done) {
          drawPath(seg, BicolCoral, style = Stroke(width = 14f, cap = StrokeCap.Round))
        } else {
          drawPath(seg, Color(0xFFCBD2D9), style = Stroke(width = 12f, cap = StrokeCap.Round, pathEffect = PathEffect.dashPathEffect(floatArrayOf(22f, 18f))))
        }
      }
    }

    // soft decoration on the opposite side of each node
    progress.indices.forEach { i ->
      val onLeft = nodeXFractions[i % nodeXFractions.size] < 0.5f
      val cy = topPad + starsHeight + stepY * i + nodeSize / 2
      Icon(
        decorIcons[i % decorIcons.size], contentDescription = null,
        tint = BicolDeepGreen.copy(alpha = 0.16f),
        modifier = Modifier
          .size(46.dp)
          .offset(x = if (onLeft) width - 74.dp else 28.dp, y = cy - 23.dp + 22.dp)
      )
    }

    progress.forEachIndexed { i, p ->
      val cx = with(density) { centers[i].x.toDp() }
      val cy = with(density) { centers[i].y.toDp() }
      val isNext = i == nextTarget
      ProvinceNode(
        index = i + 1, p = p, pulsing = isNext, pulse = pulse, traveler = i == lastReached,
        nodeSize = nodeSize, starsHeight = starsHeight,
        modifier = Modifier.offset(x = cx - blockHalfWidth, y = cy - nodeSize / 2 - starsHeight).width(blockHalfWidth * 2),
        onClick = { onSelect(p.province.id) }
      )
    }
  }
}

@Composable
private fun ProvinceNode(
  index: Int, p: ProvinceProgress, pulsing: Boolean, pulse: Float, traveler: Boolean,
  nodeSize: androidx.compose.ui.unit.Dp, starsHeight: androidx.compose.ui.unit.Dp,
  modifier: Modifier, onClick: () -> Unit
) {
  val brand = Color.fromHex(p.province.colorHex)
  val started = p.stars >= 1
  val gold = Color(0xFFFFC107)
  Column(modifier.clickable(onClick = onClick), horizontalAlignment = Alignment.CenterHorizontally) {
    Row(Modifier.height(starsHeight), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
      repeat(3) { s ->
        Icon(
          Icons.Filled.Star, contentDescription = null,
          tint = if (s < p.stars) gold else Color(0xFFCBD2D9),
          modifier = Modifier.size(if (s == 1) 20.dp else 16.dp)
        )
      }
    }
    Box(contentAlignment = Alignment.Center) {
      Box(
        Modifier
          .size(nodeSize)
          .scale(if (pulsing) pulse else 1f)
          .clip(CircleShape)
          .background(Color.White)
          .border(if (p.stars >= 3) 5.dp else 4.dp, if (p.stars >= 3) gold else if (started) brand else Color(0xFFCBD2D9), CircleShape)
          .padding(6.dp)
          .clip(CircleShape)
          .background(if (started) brand else Color(0xFFF0F1F3)),
        contentAlignment = Alignment.Center
      ) {
        if (p.stars >= 3) {
          Icon(Icons.Filled.EmojiEvents, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
        } else {
          Text("$index", color = if (started) Color.White else BicolTextSecondary, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        }
      }
      if (traveler) {
        Box(
          Modifier.align(Alignment.TopEnd).offset(x = 6.dp, y = (-4).dp).size(26.dp).clip(CircleShape).background(BicolCoral).border(2.dp, Color.White, CircleShape),
          contentAlignment = Alignment.Center
        ) { Icon(Icons.Filled.Luggage, contentDescription = "You are here", tint = Color.White, modifier = Modifier.size(14.dp)) }
      }
    }
    Spacer(Modifier.height(4.dp))
    Text(p.province.name, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = BicolTextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
    Text("${p.visited}/${p.total} visited", color = BicolTextSecondary, fontSize = 10.sp)
  }
}

// ============================================================
// PROVINCE SHEET - every place in a province, tap to toggle visited
// ============================================================
@Composable
private fun ProvinceSheet(p: ProvinceProgress, onExplore: () -> Unit, onOpen: (String) -> Unit) {
  val current = TripProgress.provinceProgress(p.province.id) ?: p   // live values (updates as items are toggled)
  val places = TripProgress.placesIn(p.province.id)
  val nextStar = when {
    current.stars >= 3 -> "Maximum stars earned. You've mastered ${current.province.name}!"
    current.stars == 2 -> "Visit ${current.starThreeAt - current.visited} more for the 3rd star"
    current.stars == 1 -> "Visit ${current.starTwoAt - current.visited} more for the 2nd star"
    else -> "Visit your first place to earn a star"
  }
  Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 24.dp)) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Column(Modifier.weight(1f)) {
        Text(current.province.name, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Text("${current.visited} of ${current.total} visited", color = BicolTextSecondary, fontSize = 12.sp)
      }
      Row {
        repeat(3) { s -> Icon(Icons.Filled.Star, contentDescription = null, tint = if (s < current.stars) Color(0xFFFFC107) else Color(0xFFCBD2D9), modifier = Modifier.size(24.dp)) }
      }
    }
    Spacer(Modifier.height(6.dp))
    Text(nextStar, color = BicolDeepGreen, fontWeight = FontWeight.SemiBold, fontSize = 12.5.sp)
    Spacer(Modifier.height(10.dp))
    Box(
      Modifier.clip(RoundedCornerShape(20.dp)).background(BicolDeepGreen).clickable(onClick = onExplore).padding(horizontal = 18.dp, vertical = 10.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Filled.Explore, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(8.dp))
        Text("Explore ${current.province.name}", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
      }
    }
    Spacer(Modifier.height(12.dp))
    Text("Tap the circle to mark a place visited", color = BicolTextSecondary, fontSize = 11.sp)
    Spacer(Modifier.height(6.dp))
    LazyColumn(Modifier.heightIn(max = 380.dp)) {
      items(places, key = { it.id }) { item ->
        val done = AppStore.visited.contains(item.id)
        Row(
          Modifier.fillMaxWidth().padding(vertical = 3.dp).clip(RoundedCornerShape(12.dp)).background(if (done) Color(0xFFE3F1E8) else Color(0xFFF7F8FA)).padding(horizontal = 10.dp, vertical = 8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            Modifier.size(28.dp).clip(CircleShape).background(if (done) BicolDeepGreen else Color(0xFFE5E7EB)).clickable { AppStore.toggleVisited(item.id) },
            contentAlignment = Alignment.Center
          ) { if (done) Icon(Icons.Filled.Check, contentDescription = "Visited", tint = Color.White, modifier = Modifier.size(16.dp)) }
          Spacer(Modifier.width(10.dp))
          Column(Modifier.weight(1f).clickable { onOpen(item.route) }) {
            Text(item.name, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(item.kind, color = BicolTextSecondary, fontSize = 10.5.sp)
          }
          Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.clickable { onOpen(item.route) })
        }
      }
    }
  }
}

@Composable
private fun BadgeTile(b: TripBadge, modifier: Modifier) {
  Card(
    modifier,
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = if (b.earned) 2.dp else 0.dp),
    colors = CardDefaults.cardColors(containerColor = if (b.earned) Color.White else Color(0xFFF0F1F3))
  ) {
    Column(Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
      Box(
        Modifier.size(46.dp).clip(CircleShape).background(if (b.earned) Brush.linearGradient(listOf(BicolCoral, BicolOrange)) else Brush.linearGradient(listOf(Color(0xFFD5DBE1), Color(0xFFD5DBE1)))),
        contentAlignment = Alignment.Center
      ) {
        Icon(if (b.earned) badgeIcon(b.iconKey) else Icons.Filled.Lock, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
      }
      Spacer(Modifier.height(6.dp))
      Text(b.title, fontWeight = FontWeight.Bold, fontSize = 11.sp, textAlign = TextAlign.Center, color = if (b.earned) BicolTextPrimary else BicolTextSecondary, maxLines = 2, lineHeight = 13.sp)
      Text(b.hint, fontSize = 9.sp, color = BicolTextSecondary, textAlign = TextAlign.Center, maxLines = 3, lineHeight = 11.sp, modifier = Modifier.padding(top = 2.dp))
    }
  }
}
