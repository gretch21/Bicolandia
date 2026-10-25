package com.bicolandia.app.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.AppStore
import com.bicolandia.app.ui.theme.*

fun shareText(context: Context, title: String, body: String) {
    val send = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    context.startActivity(Intent.createChooser(send, "Share $title"))
}

fun sharePlaceText(context: Context, name: String, location: String, blurb: String) {
    shareText(context, name, "$name ($location)\n$blurb\n\nDiscovered in the Bicolandia app.")
}


@Composable
fun VisitedPill(id: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val visited = AppStore.visited.contains(id)
    val bg = animateColorAsState(if (visited) BicolDeepGreen else Color.White, label = "visitedBg").value
    val fg = if (visited) Color.White else BicolDeepGreen
    Row(
        modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bg)
            .border(1.5.dp, BicolDeepGreen, RoundedCornerShape(20.dp))
            .clickable {
                AppStore.toggleVisited(id)
                Toast.makeText(
                    context,
                    if (AppStore.isVisited(id)) "Added to your Bicol Map" else "Removed from your Bicol Map",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            if (visited) Icons.Filled.CheckCircle else Icons.Filled.AddLocationAlt,
            contentDescription = null, tint = fg, modifier = Modifier.size(15.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(if (visited) "Visited" else "Mark as Visited", color = fg, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
    }
}


@Composable
fun rememberProfilePhoto(): ImageBitmap? {
    val context = LocalContext.current
    val version = AppStore.photoVersion
    return remember(version) { AppStore.loadPhoto(context) }
}
