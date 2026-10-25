package com.bicolandia.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bicolandia.app.R
import com.bicolandia.app.ui.theme.BicolBg
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onDone: () -> Unit) {
  LaunchedEffect(Unit) {
    delay(1500)
    onDone()
  }
  Box(Modifier.fillMaxSize().background(BicolBg), contentAlignment = Alignment.Center) {
    Image(
      painter = painterResource(id = R.drawable.logo_full),
      contentDescription = "Bicolandia",
      contentScale = ContentScale.Fit,
      modifier = Modifier.height(130.dp)
    )
  }
}
