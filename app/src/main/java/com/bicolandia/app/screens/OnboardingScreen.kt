package com.bicolandia.app.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.ui.theme.*
import kotlinx.coroutines.launch


private data class OnboardPage(
  val icon: ImageVector,
  val eyebrow: String,
  val title: String,
  val body: String,
  val gradient: List<Color>
)

private val onboardPages = listOf(
  OnboardPage(
    icon = Icons.Filled.Terrain,
    eyebrow = "EXPLORE",
    title = "Discover Bicol's\nNatural Wonders",
    body = "From Mayon's perfect cone to hidden waterfalls and quiet islands - the region's most breathtaking places, all in one guide.",
    gradient = listOf(Color(0xFF1E5631), Color(0xFF2E7D4F))
  ),
  OnboardPage(
    icon = Icons.Filled.Route,
    eyebrow = "GET THERE",
    title = "Plan Every Step\nof the Trip",
    body = "Real, practical travel guides for every destination - how to get there, what to ride next, and how long it actually takes.",
    gradient = listOf(Color(0xFFE8483A), Color(0xFFF07A5A))
  ),
  OnboardPage(
    icon = Icons.Filled.Celebration,
    eyebrow = "EXPERIENCE",
    title = "Taste & Live\nBicolano Culture",
    body = "Fiery Bicol Express, centuries-old festivals, and living traditions - dive into what makes Bicol unlike anywhere else.",
    gradient = listOf(Color(0xFF1565C0), Color(0xFF42A5D0))
  )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
  val pagerState = rememberPagerState(pageCount = { onboardPages.size })
  val scope = rememberCoroutineScope()
  val page = onboardPages[pagerState.currentPage]

  val bgTop by animateColorAsStateCompat(page.gradient[0])
  val bgBottom by animateColorAsStateCompat(page.gradient[1])

  Box(
    Modifier
      .fillMaxSize()
      .background(Brush.verticalGradient(listOf(bgTop, bgBottom)))
  ) {
    Column(Modifier.fillMaxSize()) {
      Row(Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.End) {
        Text(
          "Skip", color = Color.White.copy(alpha = 0.85f), fontWeight = FontWeight.SemiBold, fontSize = 14.sp,
          modifier = Modifier.clickable(onClick = onFinish).padding(8.dp)
        )
      }

      HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { index ->
        val p = onboardPages[index]
        Column(
          Modifier.fillMaxSize().padding(horizontal = 32.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Box(
            Modifier.size(120.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.16f)),
            contentAlignment = Alignment.Center
          ) {
            Box(Modifier.size(90.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.18f)), contentAlignment = Alignment.Center) {
              Icon(p.icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(42.dp))
            }
          }
          Spacer(Modifier.height(36.dp))
          Text(p.eyebrow, color = Color.White.copy(alpha = 0.75f), fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 2.sp)
          Spacer(Modifier.height(10.dp))
          Text(
            p.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp,
            lineHeight = 36.sp, textAlign = TextAlign.Center
          )
          Spacer(Modifier.height(16.dp))
          Text(
            p.body, color = Color.White.copy(alpha = 0.88f), fontSize = 14.5.sp,
            lineHeight = 21.sp, textAlign = TextAlign.Center
          )
        }
      }

      Row(
        Modifier.fillMaxWidth().padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.Center
      ) {
        onboardPages.indices.forEach { i ->
          val active = i == pagerState.currentPage
          val width by animateDpAsState(if (active) 22.dp else 7.dp, animationSpec = tween(250), label = "dot")
          Box(
            Modifier
              .padding(horizontal = 3.dp)
              .height(7.dp)
              .width(width)
              .clip(RoundedCornerShape(4.dp))
              .background(Color.White.copy(alpha = if (active) 1f else 0.4f))
          )
        }
      }

      Row(
        Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.End
      ) {
        val isLast = pagerState.currentPage == onboardPages.lastIndex
        Row(
          Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(Color.White)
            .clickable {
              if (isLast) onFinish()
              else scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            }
            .padding(horizontal = if (isLast) 26.dp else 18.dp, vertical = 14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            if (isLast) "Get Started" else "Next",
            color = page.gradient[0], fontWeight = FontWeight.Bold, fontSize = 14.sp
          )
          Spacer(Modifier.width(6.dp))
          Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = page.gradient[0], modifier = Modifier.size(16.dp))
        }
      }
    }
  }
}

@Composable
private fun animateColorAsStateCompat(target: Color) =
  androidx.compose.animation.animateColorAsState(target, animationSpec = tween(400), label = "bg")
