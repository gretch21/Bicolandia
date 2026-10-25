package com.bicolandia.app.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.*
import com.bicolandia.app.ui.theme.*

private val essentialsFilters = listOf("All", "Emergency", "Assistance", "Checklist", "Good to Know")

@Composable
fun TouristEssentialsScreen(onBack: (() -> Unit)? = null, onOpenTravelGuide: () -> Unit = {}) {
  val context = LocalContext.current
  var filter by remember { mutableStateOf("All") }
  // Tap-to-check state for the trip-ready checklist (kept in memory for this visit)
  val checked = remember { mutableStateMapOf<String, Boolean>() }

  fun show(section: String) = filter == "All" || filter == section

  Scaffold(containerColor = BicolBg) { padding ->
    LazyColumn(Modifier.padding(padding)) {
      item { DetailTopBar("Tourist Essentials", "Before & during your trip", onBack, trailingIcon = null) }
      item { EssentialsHero(onCall911 = { openDialer(context, "911") }) }
      item { Spacer(Modifier.height(10.dp)) }
      item { FilterChipsRow(essentialsFilters, filter) { filter = it } }
      item { Spacer(Modifier.height(6.dp)) }

      if (show("Emergency")) {
        item {
          Column(Modifier.padding(horizontal = 16.dp)) {
            EssentialsHeader(Icons.Filled.Emergency, BicolCoral, "Emergency Contacts", "Tap a card for details · tap Call to open your dialer")
            emergencyContacts.forEach { c -> EmergencyCard(c) { openDialer(context, c.number) } }
          }
        }
      }

      if (show("Assistance")) {
        item {
          Column(Modifier.padding(horizontal = 16.dp)) {
            EssentialsHeader(Icons.Filled.SupportAgent, BicolDeepGreen, "Tourist Assistance", "Official help desks for visitors")
            touristAssistanceContacts.forEach { a ->
              AssistanceCard(
                a,
                onCall = { number -> openDialer(context, number) },
                onEmail = { address -> sendEmail(context, address) }
              )
            }
          }
        }
      }

      if (show("Checklist")) {
        item {
          Column(Modifier.padding(horizontal = 16.dp)) {
            EssentialsHeader(Icons.Filled.TaskAlt, Color(0xFF0288D1), "Trip-Ready Checklist", "Tap each reminder as you get ready")
            EssentialsProgressCard(checked, transportationReminders.size + travelPrecautions.size)
            EssentialsChecklistCard("Transportation Reminders", Icons.Filled.DirectionsBus, transportationReminders, checked) {
              Spacer(Modifier.height(6.dp))
              Row(
                Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(16.dp))
                  .background(BicolCoral.copy(alpha = 0.1f))
                  .clickable(onClick = onOpenTravelGuide)
                  .padding(horizontal = 14.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text("Open Travel Guide", color = BicolCoral, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = BicolCoral, modifier = Modifier.size(16.dp))
              }
            }
            EssentialsChecklistCard("Travel Precautions", Icons.Filled.Shield, travelPrecautions, checked)
          }
        }
      }

      if (show("Good to Know")) {
        item {
          Column(Modifier.padding(horizontal = 16.dp)) {
            EssentialsHeader(Icons.Filled.Lightbulb, Color(0xFFE08A00), "Good to Know", "Local realities worth keeping in mind")
            Card(
              Modifier.fillMaxWidth().padding(vertical = 4.dp),
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
              elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
              Column(Modifier.padding(14.dp)) {
                goodToKnowLocalInfo.forEach { note ->
                  Row(Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Filled.Info, contentDescription = null, tint = Color(0xFFE08A00), modifier = Modifier.padding(top = 1.dp).size(15.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(note, color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 17.sp)
                  }
                }
              }
            }
          }
        }
      }

      item {
        Text(
          essentialsSourceNote,
          color = BicolTextSecondary, fontSize = 10.5.sp, lineHeight = 14.sp,
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)
        )
      }
      item { Spacer(Modifier.height(120.dp)) }
    }
  }
}

@Composable
private fun EssentialsHero(onCall911: () -> Unit) {
  Box(
    Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp)
      .clip(RoundedCornerShape(24.dp))
      .background(Brush.linearGradient(listOf(BicolDeepGreen, Color(0xFF2E7D32))))
      .padding(20.dp)
  ) {
    Icon(
      Icons.Filled.HealthAndSafety, contentDescription = null,
      tint = Color.White.copy(alpha = 0.16f),
      modifier = Modifier.align(Alignment.TopEnd).size(96.dp)
    )
    Column {
      Text("STAY SAFE · TRAVEL SMART", color = BicolOrange, fontWeight = FontWeight.Bold, fontSize = 11.sp)
      Spacer(Modifier.height(6.dp))
      Text(
        "What do I need to know before/during my trip?",
        color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, lineHeight = 25.sp,
        modifier = Modifier.fillMaxWidth(0.82f)
      )
      Spacer(Modifier.height(6.dp))
      Text(
        "Emergency numbers, help desks and quick checklists - all in one place.",
        color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp, lineHeight = 16.sp,
        modifier = Modifier.fillMaxWidth(0.85f)
      )
      Spacer(Modifier.height(14.dp))
      Row(verticalAlignment = Alignment.CenterVertically) {
        Row(
          Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(BicolCoral)
            .clickable(onClick = onCall911)
            .padding(horizontal = 18.dp, vertical = 11.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(Icons.Filled.Call, contentDescription = "Call 911", tint = Color.White, modifier = Modifier.size(16.dp))
          Spacer(Modifier.width(8.dp))
          Text("Call 911", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Spacer(Modifier.width(12.dp))
        Text("Unified national\nemergency line", color = Color.White.copy(alpha = 0.85f), fontSize = 11.sp, lineHeight = 14.sp)
      }
    }
  }
}

@Composable
private fun EssentialsHeader(icon: ImageVector, tint: Color, title: String, subtitle: String) {
  Row(Modifier.fillMaxWidth().padding(top = 14.dp, bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
    Box(Modifier.size(36.dp).clip(CircleShape).background(tint.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) {
      Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp))
    }
    Spacer(Modifier.width(10.dp))
    Column {
      Text(title, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary)
      Text(subtitle, color = BicolTextSecondary, fontSize = 11.5.sp)
    }
  }
}

private fun emergencyIcon(key: String): ImageVector = when (key) {
  "police" -> Icons.Filled.LocalPolice
  "fire" -> Icons.Filled.LocalFireDepartment
  "medical" -> Icons.Filled.MedicalServices
  "coastguard" -> Icons.Filled.DirectionsBoat
  "redcross" -> Icons.Filled.Favorite
  else -> Icons.Filled.Emergency
}

private fun emergencyTint(key: String): Color = when (key) {
  "police" -> Color(0xFF3E7CB1)
  "fire" -> Color(0xFFE8734A)
  "medical" -> BicolCoral
  "coastguard" -> Color(0xFF0288D1)
  "redcross" -> Color(0xFFD9483A)
  else -> BicolCoral
}

@Composable
private fun EmergencyCard(c: EmergencyContact, onCall: () -> Unit) {
  var expanded by remember { mutableStateOf(false) }
  val tint = emergencyTint(c.iconKey)
  Card(
    Modifier.fillMaxWidth().padding(vertical = 5.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(
      Modifier
        .fillMaxWidth()
        .clickable { expanded = !expanded }
        .animateContentSize()
        .padding(14.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(42.dp).clip(CircleShape).background(tint.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) {
          Icon(emergencyIcon(c.iconKey), contentDescription = null, tint = tint, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
          Text(c.label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = BicolTextPrimary)
          Text(if (expanded) "Tap to hide details" else "Tap for details", color = BicolTextSecondary, fontSize = 11.sp)
        }
        Spacer(Modifier.width(8.dp))
        Row(
          Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(BicolDeepGreen)
            .clickable(onClick = onCall)
            .padding(horizontal = 14.dp, vertical = 9.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(Icons.Filled.Call, contentDescription = "Call ${c.label}", tint = Color.White, modifier = Modifier.size(13.dp))
          Spacer(Modifier.width(6.dp))
          Text(c.number, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
      }
      if (expanded) {
        Spacer(Modifier.height(10.dp))
        Text(c.note, color = BicolTextSecondary, fontSize = 12.sp, lineHeight = 17.sp)
      }
    }
  }
}

private fun dialableNumber(display: String): String {
  val paren = Regex("\\(([^)]+)\\)").find(display)?.groupValues?.get(1)
  return paren ?: display
}

@Composable
private fun AssistanceCard(a: TouristAssistanceContact, onCall: (String) -> Unit, onEmail: (String) -> Unit) {
  val is247 = a.hours.contains("24/7")
  Card(
    Modifier.fillMaxWidth().padding(vertical = 5.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(14.dp)) {
      Row(verticalAlignment = Alignment.Top) {
        Text(a.name, fontWeight = FontWeight.Bold, fontSize = 14.5.sp, color = BicolTextPrimary, lineHeight = 19.sp, modifier = Modifier.weight(1f))
        Spacer(Modifier.width(8.dp))
        Row(
          Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (is247) Color(0xFFD9F0DE) else Color(0xFFF0F1F3))
            .padding(horizontal = 10.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(Icons.Filled.Schedule, contentDescription = null, tint = if (is247) BicolDeepGreen else BicolTextSecondary, modifier = Modifier.size(12.dp))
          Spacer(Modifier.width(4.dp))
          Text(
            if (is247) "24/7" else "Office hours",
            color = if (is247) BicolDeepGreen else BicolTextSecondary, fontWeight = FontWeight.Bold, fontSize = 10.5.sp
          )
        }
      }
      Spacer(Modifier.height(8.dp))
      AssistanceInfoLine(Icons.Filled.LocationOn, a.location)
      AssistanceInfoLine(Icons.Filled.Info, a.purpose)
      Spacer(Modifier.height(12.dp))
      Row(
        Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(20.dp))
          .background(BicolDeepGreen)
          .clickable { onCall(dialableNumber(a.phone)) }
          .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(Icons.Filled.Call, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
        Spacer(Modifier.width(8.dp))
        Text(a.phone, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.5.sp, textAlign = TextAlign.Center)
      }
      if (a.altPhone != null || a.email != null) {
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          if (a.altPhone != null) {
            SecondaryActionPill(Icons.Filled.Call, a.altPhone, Modifier.weight(1f)) { onCall(a.altPhone) }
          }
          if (a.email != null) {
            SecondaryActionPill(Icons.Filled.Email, "Email", Modifier.weight(1f)) { onEmail(a.email) }
          }
        }
      }
    }
  }
}

@Composable
private fun SecondaryActionPill(icon: ImageVector, label: String, modifier: Modifier, onClick: () -> Unit) {
  Row(
    modifier
      .clip(RoundedCornerShape(20.dp))
      .background(Color(0xFFF0F1F3))
      .clickable(onClick = onClick)
      .padding(horizontal = 12.dp, vertical = 9.dp),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(icon, contentDescription = null, tint = BicolTextPrimary, modifier = Modifier.size(13.dp))
    Spacer(Modifier.width(6.dp))
    Text(label, color = BicolTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, maxLines = 1)
  }
}

@Composable
private fun AssistanceInfoLine(icon: ImageVector, text: String) {
  Row(Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
    Icon(icon, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.padding(top = 1.dp).size(13.dp))
    Spacer(Modifier.width(6.dp))
    Text(text, color = BicolTextSecondary, fontSize = 11.5.sp, lineHeight = 15.sp)
  }
}

@Composable
private fun EssentialsProgressCard(checked: SnapshotStateMap<String, Boolean>, total: Int) {
  val done = checked.values.count { it }
  val fraction = if (total == 0) 0f else done.toFloat() / total
  val animated by animateFloatAsState(targetValue = fraction, label = "checklistProgress")
  Card(
    Modifier.fillMaxWidth().padding(vertical = 4.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(14.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
          Text(if (done == total && total > 0) "You're trip-ready!" else "$done of $total reminders checked", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = BicolTextPrimary)
          Text("A quick self-check before and during your trip", color = BicolTextSecondary, fontSize = 11.sp)
        }
        if (done > 0) {
          Text(
            "Reset", color = BicolCoral, fontWeight = FontWeight.SemiBold, fontSize = 12.sp,
            modifier = Modifier.clip(RoundedCornerShape(12.dp)).clickable { checked.clear() }.padding(horizontal = 10.dp, vertical = 6.dp)
          )
        }
      }
      Spacer(Modifier.height(10.dp))
      Box(Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)).background(Color(0xFFE5E7EB))) {
        Box(Modifier.fillMaxWidth(animated).fillMaxHeight().clip(RoundedCornerShape(4.dp)).background(BicolDeepGreen))
      }
    }
  }
}

@Composable
private fun EssentialsChecklistCard(
  title: String,
  icon: ImageVector,
  entries: List<String>,
  checked: SnapshotStateMap<String, Boolean>,
  footer: @Composable ColumnScope.() -> Unit = {}
) {
  Card(
    Modifier.fillMaxWidth().padding(vertical = 5.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(Modifier.padding(14.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = BicolDeepGreen, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(8.dp))
        Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = BicolTextPrimary)
      }
      Spacer(Modifier.height(6.dp))
      entries.forEach { text ->
        val isChecked = checked[text] == true
        Row(
          Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { checked[text] = !isChecked }
            .padding(vertical = 7.dp),
          verticalAlignment = Alignment.Top
        ) {
          Box(
            Modifier
              .padding(top = 1.dp)
              .size(20.dp)
              .clip(CircleShape)
              .background(if (isChecked) BicolDeepGreen else Color(0xFFE5E7EB)),
            contentAlignment = Alignment.Center
          ) {
            if (isChecked) Icon(Icons.Filled.Check, contentDescription = "Checked", tint = Color.White, modifier = Modifier.size(13.dp))
          }
          Spacer(Modifier.width(10.dp))
          Text(
            text,
            color = if (isChecked) BicolTextSecondary.copy(alpha = 0.7f) else BicolTextSecondary,
            textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None,
            fontSize = 12.5.sp, lineHeight = 17.sp,
            modifier = Modifier.weight(1f)
          )
        }
      }
      footer()
    }
  }
}

private fun openDialer(context: Context, number: String) {
  try {
    context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number")))
  } catch (e: ActivityNotFoundException) {
    // No dialer available on this device - nothing else to do
  }
}

private fun sendEmail(context: Context, address: String) {
  try {
    context.startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$address")))
  } catch (e: ActivityNotFoundException) {
    // No email app available on this device - nothing else to do
  }
}
