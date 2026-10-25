package com.bicolandia.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.*
import com.bicolandia.app.ui.theme.*
import java.text.Normalizer

private data class SearchHit(
    val id: String,
    val name: String,
    val group: String,
    val sub: String,
    val colorHex: String,
    val icon: ImageVector,
    val route: String,
    val haystack: String
)

private fun fold(s: String): String =
    Normalizer.normalize(s, Normalizer.Form.NFD).replace(Regex("\\p{M}+"), "").lowercase()

private fun groupOf(category: String): String = when {
    destinationClassifications.any { it.title == category } -> "Destinations"
    activityClassifications.any { it.title == category } -> "Activities"
    festivalClassifications.any { it.title == category } -> "Festivals"
    cuisineClassifications.any { it.title == category } -> "Food"
    else -> "Culture"
}

// Built once: provinces, every place (destinations, activities, food, festivals, culture) and every stay
private val searchIndex: List<SearchHit> by lazy {
    val out = mutableListOf<SearchHit>()
    provinces.forEach {
        out.add(SearchHit(it.id, it.name, "Provinces", "Province · Capital: ${it.capital}", it.colorHex, Icons.Filled.Terrain, "province_detail/${it.id}",
            fold("${it.name} province ${it.capital} ${it.about} ${it.topAttractions.joinToString(" ")}")))
    }
    val places = destinationsData.values.flatten() + activitiesData.values.flatten() + festivalsData.values.flatten() +
        cuisinesData.values.flatten() + cultureData.values.flatten()
    places.forEach {
        out.add(SearchHit(it.id, it.name, groupOf(it.category), "${it.category} · ${it.location}", it.colorHex, iconFor(it.category), "place_detail/${it.id}",
            fold("${it.name} ${it.category} ${it.location} ${it.shortDescription}")))
    }
    stayData.values.flatten().forEach {
        out.add(SearchHit(it.id, it.name, "Stays", "${it.type} · ${it.location}, ${it.province}", it.colorHex, Icons.Filled.Hotel, "stay_detail/${it.id}",
            fold("${it.name} ${it.type} ${it.location} ${it.province} ${it.shortDescription} stay hotel resort inn homestay accommodation")))
    }
    out
}

private val searchGroups = listOf("All", "Provinces", "Destinations", "Activities", "Food", "Festivals", "Culture", "Stays")
private val suggestions = listOf("Mayon", "Whale shark", "Bicol Express", "Beach", "Festival", "Waterfall", "Resort", "Surfing")

private fun runSearch(query: String, group: String): List<SearchHit> {
    val tokens = fold(query).split(" ").filter { it.isNotBlank() }
    if (tokens.isEmpty()) return emptyList()
    return searchIndex
        .filter { (group == "All" || it.group == group) && tokens.all { t -> it.haystack.contains(t) } }
        .sortedBy { hit ->
            val n = fold(hit.name)
            when {
                n == tokens.joinToString(" ") -> 0
                n.startsWith(tokens.first()) -> 1
                tokens.all { n.contains(it) } -> 2
                else -> 3
            }
        }
}

@Composable
fun SearchScreen(initialQuery: String, onBack: () -> Unit, onOpen: (String) -> Unit) {
    var query by remember { mutableStateOf(initialQuery) }
    var group by remember { mutableStateOf("All") }
    val focus = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    val results = remember(query, group) { runSearch(query, group) }

    LaunchedEffect(Unit) {
        if (initialQuery.isNotBlank()) AppStore.addSearch(initialQuery) else focus.requestFocus()
    }

    Scaffold(containerColor = BicolBg) { padding ->
        Column(Modifier.padding(padding)) {
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                RoundIconButton(Icons.Filled.ArrowBack, onClick = onBack)
                Spacer(Modifier.width(10.dp))
                Row(
                    Modifier.weight(1f).height(48.dp).clip(RoundedCornerShape(24.dp)).background(Color.White).padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Search, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Box(Modifier.weight(1f)) {
                        if (query.isEmpty()) Text("Search destinations, food, stays...", color = BicolTextSecondary, fontSize = 13.sp)
                        BasicTextField(
                            value = query,
                            onValueChange = { query = it },
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 14.sp, color = BicolTextPrimary),
                            cursorBrush = SolidColor(BicolCoral),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = { AppStore.addSearch(query); keyboard?.hide() }),
                            modifier = Modifier.fillMaxWidth().focusRequester(focus)
                        )
                    }
                    if (query.isNotEmpty()) {
                        Icon(
                            Icons.Filled.Close, contentDescription = "Clear", tint = BicolTextSecondary,
                            modifier = Modifier.size(18.dp).clip(CircleShape).clickable { query = "" }
                        )
                    }
                }
            }

            if (query.isBlank()) {
                LazyColumn {
                    if (AppStore.searchHistory.isNotEmpty()) {
                        item {
                            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text("Recent searches", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.weight(1f))
                                Text("Clear", color = BicolCoral, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, modifier = Modifier.clickable { AppStore.clearSearchHistory() })
                            }
                        }
                        item { ChipFlow(AppStore.searchHistory.toList(), Icons.Filled.History) { query = it } }
                    }
                    item {
                        Text("Try searching for", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 6.dp))
                    }
                    item { ChipFlow(suggestions, Icons.Filled.TrendingUp) { query = it } }
                    item { Spacer(Modifier.height(120.dp)) }
                }
            } else {
                FilterChipsRow(searchGroups, group) { group = it }
                Spacer(Modifier.height(4.dp))
                Text(
                    "${results.size} ${if (results.size == 1) "result" else "results"} for \"${query.trim()}\"",
                    color = BicolTextSecondary, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
                if (results.isEmpty()) {
                    Column(Modifier.fillMaxWidth().padding(40.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.SearchOff, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(44.dp))
                        Spacer(Modifier.height(8.dp))
                        Text("No matches found", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Try a different word, a place name, a province, or a food like \"laing\".", color = BicolTextSecondary, fontSize = 12.sp, textAlign = TextAlign.Center)
                    }
                } else {
                    LazyColumn {
                        items(results.take(60), key = { it.id }) { hit ->
                            Card(
                                Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 5.dp).clickable {
                                    AppStore.addSearch(query)
                                    keyboard?.hide()
                                    onOpen(hit.route)
                                },
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    PlaceholderImage(hit.name, hit.colorHex, 74.dp, modifier = Modifier.width(80.dp), icon = hit.icon, shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp), imageId = hit.id)
                                    Column(Modifier.weight(1f).padding(horizontal = 12.dp, vertical = 8.dp)) {
                                        Text(hit.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                        Text(hit.sub, color = BicolTextSecondary, fontSize = 11.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                    }
                                    Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.padding(end = 10.dp))
                                }
                            }
                        }
                        item { Spacer(Modifier.height(120.dp)) }
                    }
                }
            }
        }
    }
}

// Wrapping chips (2 to 3 per row depending on text length) that put the tapped text into the search box
@Composable
private fun ChipFlow(labels: List<String>, icon: ImageVector, onClick: (String) -> Unit) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        labels.chunked(2).forEach { row ->
            Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { label ->
                    Row(
                        Modifier.weight(1f).clip(RoundedCornerShape(20.dp)).background(Color.White).clickable { onClick(label) }.padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(icon, contentDescription = null, tint = BicolTextSecondary, modifier = Modifier.size(15.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(label, fontSize = 12.5.sp, color = BicolTextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}
