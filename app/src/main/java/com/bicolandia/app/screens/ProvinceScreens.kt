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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.data.*
import com.bicolandia.app.ui.theme.*

@Composable
fun ProvinceListScreen(onBack: () -> Unit, onProvinceClick: (Province) -> Unit) {
    Scaffold(containerColor = BicolBg) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            item { DetailTopBar("Bicol Provinces", null, onBack, trailingIcon = Icons.Filled.Search) }
            item {
                Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text("Where to next?", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = BicolTextPrimary)
                    Text("Discover the ${provinces.size} gems of the Bicol Region", color = BicolTextSecondary, fontSize = 13.sp)
                }
            }
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height(((provinces.size + 1) / 2 * 180).dp).padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    userScrollEnabled = false
                ) {
                    items(provinces) { prov ->
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(168.dp)
                                .clip(RoundedCornerShape(18.dp))
                                .clickable { onProvinceClick(prov) }
                        ) {
                            PlaceholderImage(prov.name, prov.colorHex, 168.dp, icon = Icons.Filled.Terrain, shape = RoundedCornerShape(18.dp), imageId = prov.id)
                            Column(Modifier.align(Alignment.BottomStart).padding(12.dp)) {
                                Box(
                                    Modifier.clip(RoundedCornerShape(20.dp)).background(Color.Black.copy(alpha = 0.4f)).padding(horizontal = 10.dp, vertical = 4.dp)
                                ) { Text("${prov.destinationCount} DESTINATIONS", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold) }
                                Spacer(Modifier.height(4.dp))
                                Text(prov.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                                Text("EXPLORE NOW →", color = BicolOrange, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFFE3F1E8))
                        .padding(18.dp)
                ) {
                    Text("Explore Bicol express!", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = BicolDeepGreen)
                    Spacer(Modifier.height(4.dp))
                    Text("Save 15% on group tours when you book across multiple provinces this season.", color = BicolTextSecondary, fontSize = 12.sp)
                }
            }
            item { Spacer(Modifier.height(120.dp)) }
        }
    }
}

@Composable
fun ProvinceDetailScreen(province: Province, onBack: () -> Unit, onTravelGuide: (Province) -> Unit = {}) {
    val context = LocalContext.current
    Scaffold(containerColor = BicolBg) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            item {
                Box(Modifier.fillMaxWidth()) {
                    PlaceholderImage(province.name, province.colorHex, 320.dp, icon = Icons.Filled.Terrain, shape = RoundedCornerShape(0.dp), imageId = province.id)
                    Row(
                        Modifier.fillMaxWidth().padding(16.dp).align(Alignment.TopCenter),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RoundIconButton(Icons.Filled.ArrowBack, tint = Color.White, bg = Color.Black.copy(alpha = 0.35f), onClick = onBack)
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            val isFav = FavoritesStore.favoriteIds.contains(province.id)
                            RoundIconButton(
                                if (isFav) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                tint = if (isFav) BicolCoral else Color.White,
                                bg = Color.Black.copy(alpha = 0.35f),
                                onClick = { FavoritesStore.toggle(province.id) }
                            )
                            RoundIconButton(
                                Icons.Filled.Share, tint = Color.White, bg = Color.Black.copy(alpha = 0.35f),
                                onClick = { sharePlaceText(context, province.name, "Bicol Region, Philippines", province.about) }
                            )
                        }
                    }
                    Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            StatusBadge("BICOL REGION", "#F5A623")
                            Spacer(Modifier.width(8.dp))
                            Icon(Icons.Filled.Star, contentDescription = null, tint = BicolOrange, modifier = Modifier.size(14.dp))
                            Text(" ${province.rating}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(province.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                            Text(" Bicol Region, Philippines", color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
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
                                .clickable { onTravelGuide(province) }
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.DirectionsBus, contentDescription = null, tint = Color.White, modifier = Modifier.size(15.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("How to Get There", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                        }
                        VisitedPill(province.id)
                    }
                    Spacer(Modifier.height(16.dp))
                    Text("About ${province.name}", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary)
                    Spacer(Modifier.height(6.dp))
                    Text(province.about, fontSize = 13.sp, color = BicolTextSecondary, lineHeight = 19.sp)
                    if (province.more.isNotBlank()) {
                        Spacer(Modifier.height(8.dp))
                        Text(province.more, fontSize = 13.sp, color = BicolTextSecondary, lineHeight = 19.sp)
                    }

                    QuickFactsGrid(
                        listOf(
                            QuickFact("Capital", province.capital),
                            QuickFact("Languages", province.languages),
                            QuickFact("Climate", province.climate),
                            QuickFact("Best Time", province.bestTimeToVisit)
                        )
                    )

                    AttractionsSection(provinceAttractions(province), province.colorHex)
                    HighlightsSection("Must-Do Activities", provinceActivities(province).map { QuickFact(it.name, it.desc) })

                    Text("Savor Local Flavors", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary, modifier = Modifier.padding(top = 8.dp))
                    Spacer(Modifier.height(10.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        items(provinceFoods(province)) { food ->
                            RoundPhotoItem(food.name, food.imageId, province.colorHex, Icons.Filled.Restaurant)
                        }
                    }

                    val festivals = provinceFestivals(province)
                    if (festivals.isNotEmpty()) {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(festivals) { fest ->
                                Box(
                                    Modifier
                                        .width(300.dp)
                                        .height(130.dp)
                                        .clip(RoundedCornerShape(18.dp))
                                ) {
                                    // festivals without their own photo fall back to the province photo
                                    val ownPhoto = fest.imageId != null || AppImages.forName(fest.name) != null
                                    PlaceholderImage(
                                        fest.name, "#C24444", 130.dp, icon = Icons.Filled.Celebration, shape = RoundedCornerShape(18.dp),
                                        imageId = fest.imageId ?: if (ownPhoto) null else province.id
                                    )
                                    Column(Modifier.align(Alignment.BottomStart).padding(14.dp)) {
                                        Text("EVERY YEAR", color = BicolOrange, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                        Text(fest.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                        Text(fest.desc, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                    }
                                }
                            }
                        }
                    }

                    val steps = provinceGettingThereSteps(province)
                    if (steps.isNotEmpty()) {
                        ExtraHeading("Getting Around ${province.name}")
                        GettingThereSteps(steps)
                    } else if (province.gettingAround.isNotBlank()) {
                        ExtraHeading("Getting Around ${province.name}")
                        ExtraInfoCard(Icons.Filled.DirectionsBus, BicolDeepGreen, province.gettingAround)
                    }
                    if (province.tips.isNotEmpty()) {
                        ExtraHeading("Travel Tips")
                        ExtraTipsCard(province.tips)
                        Spacer(Modifier.height(16.dp))
                    }

                    val dining = provinceDining(province)
                    if (dining.isNotEmpty()) {
                        Text("Best Dining", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BicolTextPrimary, modifier = Modifier.padding(top = 8.dp))
                        Text("Top-rated restaurants in ${province.capital}", color = BicolTextSecondary, fontSize = 12.sp)
                        Spacer(Modifier.height(10.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp), modifier = Modifier.padding(bottom = 24.dp)) {
                            itemsIndexed(dining) { i, d ->
                                // restaurants without their own photo use one of the existing restaurant photos
                                val own = d.imageId?.takeIf { AppImages.forId(it) != null }
                                val photo = own ?: if (AppImages.forName(d.name) != null) null else "food_resto_${i % 5}"
                                RoundPhotoItem(d.name, photo, province.colorHex, Icons.Filled.Restaurant)
                            }
                        }
                    }
                    Spacer(Modifier.height(70.dp))
                }
            }
        }
    }
}

@Composable
private fun RoundPhotoItem(name: String, imageId: String?, colorHex: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(84.dp)) {
        Box(Modifier.size(76.dp).clip(CircleShape).background(Color.fromHex(colorHex).copy(alpha = 0.8f)), contentAlignment = Alignment.Center) {
            val resId = AppImages.resolve(imageId, name)
            if (resId != null) {
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Icon(icon, contentDescription = null, tint = Color.White)
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(name, fontSize = 11.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun AttractionsSection(items: List<ProvinceAttraction>, colorHex: String) {
    if (items.isEmpty()) return
    Column(Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
        Text("Top Attractions", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BicolTextPrimary)
        Spacer(Modifier.height(10.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(items) { a ->
                Card(Modifier.width(190.dp), shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
                    Column {
                        PlaceholderImage(a.name, colorHex, 112.dp, icon = iconFor(a.category), shape = RoundedCornerShape(0.dp), imageId = a.imageId)
                        Column(Modifier.padding(10.dp)) {
                            Text(a.name, fontWeight = FontWeight.Bold, fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text(a.category, color = BicolDeepGreen, fontWeight = FontWeight.SemiBold, fontSize = 10.sp)
                            Spacer(Modifier.height(2.dp))
                            Text(a.blurb, color = BicolTextSecondary, fontSize = 11.sp, lineHeight = 14.sp, maxLines = 3, overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GettingThereSteps(steps: List<ProvinceStep>) {
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
        Column(Modifier.padding(14.dp)) {
            steps.forEachIndexed { i, step ->
                Row(Modifier.fillMaxWidth().padding(vertical = 6.dp), verticalAlignment = Alignment.Top) {
                    Box(Modifier.size(26.dp).clip(CircleShape).background(BicolDeepGreen), contentAlignment = Alignment.Center) {
                        Text("${i + 1}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(step.title, fontWeight = FontWeight.SemiBold, fontSize = 13.5.sp, color = BicolTextPrimary)
                        Spacer(Modifier.height(2.dp))
                        Text(step.detail, color = BicolTextSecondary, fontSize = 12.5.sp, lineHeight = 18.sp)
                    }
                }
            }
        }
    }
}
