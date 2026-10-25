package com.bicolandia.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.PlaceDetail
import com.bicolandia.app.data.cultureClassifications
import com.bicolandia.app.data.cuisineClassifications
import com.bicolandia.app.data.festivalClassifications
import com.bicolandia.app.ui.theme.*

@Composable
fun PlaceExtraSections(place: PlaceDetail) {
    val isCulture = cultureClassifications.any { it.title == place.category }
    val isFood = cuisineClassifications.any { it.title == place.category } && place.category != "Restaurants"
    val isFestival = festivalClassifications.any { it.title == place.category }

    if (place.bestTime.isNotBlank()) {
        ExtraHeading(if (isCulture) "When to Experience It" else if (isFood) "When to Enjoy It" else if (isFestival) "When It Happens" else "Best Time to Visit")
        ExtraInfoCard(Icons.Filled.WbSunny, BicolOrange, place.bestTime)
    }
    if (place.gettingThere.isNotBlank()) {
        ExtraHeading(if (isCulture || isFood) "Where to Experience It" else "Getting There")
        ExtraInfoCard(if (isCulture || isFood) Icons.Filled.Explore else Icons.Filled.DirectionsBus, BicolDeepGreen, place.gettingThere)
    }
    if (place.tips.isNotEmpty()) {
        ExtraHeading(if (isCulture) "Good to Know" else "Visitor Tips")
        ExtraTipsCard(place.tips)
    }
    if (place.funFact.isNotBlank()) {
        Spacer(Modifier.height(12.dp))
        Card(
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
                Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = Color(0xFFE08A00), modifier = Modifier.padding(top = 1.dp).size(20.dp))
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("Did you know?", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = BicolTextPrimary)
                    Spacer(Modifier.height(2.dp))
                    Text(place.funFact, color = BicolTextSecondary, fontSize = 12.5.sp, lineHeight = 17.sp)
                }
            }
        }
    }
}

@Composable
fun ExtraHeading(text: String) {
    Text(text, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
}

@Composable
fun ExtraInfoCard(icon: ImageVector, tint: Color, text: String) {
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
fun ExtraTipsCard(tips: List<String>) {
    Card(
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.padding(14.dp)) {
            tips.forEach { tip ->
                Row(Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.padding(top = 1.dp).size(15.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(tip, color = BicolTextSecondary, fontSize = 12.5.sp, lineHeight = 17.sp)
                }
            }
        }
    }
}
