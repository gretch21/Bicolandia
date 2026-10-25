package com.bicolandia.app.data

private fun qf(vararg pairs: Pair<String, String>) = pairs.map { QuickFact(it.first, it.second) }
private fun hl(vararg pairs: Pair<String, String>) = pairs.map { QuickFact(it.first, it.second) }

// ---------- VOLCANOES (matches "Volcanoes List Screen.png") ----------
val volcanoes = listOf(
  PlaceDetail(
    "volcano_mayon", "Mayon Volcano", "Volcanoes", "Albay, Bicol", "ACTIVE", "#2E7D32", 4.9f, "+12k",
    "The world's most perfect volcanic cone and iconic landmark of Albay.",
    "Renowned as the \"Perfect Cone\" because of its symmetric conical shape, Mayon is the most active volcano in the Philippines. It rises 2,462 meters from the shores of the Albay Gulf and is the center of the Mayon Volcano Natural Park.",
    qf("Elevation" to "2,463 m", "Type" to "Stratovolcano", "Last Eruption" to "2023", "Status" to "Alert Level 1"),
    hl("Lava Trail Hiking" to "Navigate the 2006 lava wall", "Landscape Photography" to "Capture the perfect golden hour cone", "ATV Adventure" to "Adrenaline rush through rock trails"),
    listOf("Cagsawa Ruins", "Daraga Church"), listOf("1st Colonial Grill", "Small Talk Cafe"), "#3E7CB1"
  ),
  PlaceDetail(
    "volcano_bulusan", "Mount Bulusan", "Volcanoes", "Sorsogon, Bicol", "ACTIVE", "#2E7D32", 4.7f, "+8k",
    "The southernmost volcano on Luzon Island, home to the serene Bulusan Lake.",
    "Mount Bulusan anchors the Bulusan Volcano Natural Park, a biodiversity hotspot ringed by hot springs, waterfalls, and the tranquil Bulusan Lake at its base.",
    qf("Elevation" to "1,565 m", "Type" to "Stratovolcano", "Last Eruption" to "2016", "Status" to "Alert Level 0"),
    hl("Lake Trekking" to "Walk the trail around Bulusan Lake", "Birdwatching" to "Spot native Bicol forest birds", "Camping" to "Overnight stays near the ranger station"),
    listOf("Bulusan Volcano Natural Park", "Rizal Beach"), listOf("Fitzgerald's"), "#1177A8"
  ),
  PlaceDetail(
    "volcano_isarog", "Mount Isarog", "Volcanoes", "Camarines Sur, Bicol", "POTENTIALLY ACTIVE", "#EF9A00", 4.8f, "+5k",
    "A majestic stratovolcano known for its rich biodiversity and hidden waterfalls.",
    "Mount Isarog is protected as a natural park, its rainforest slopes hiding dozens of waterfalls and endemic wildlife found nowhere else in the Philippines.",
    qf("Elevation" to "1,966 m", "Type" to "Stratovolcano", "Last Eruption" to "Unknown", "Status" to "Dormant"),
    hl("Waterfall Trekking" to "Visit Malabsay and Nabuntolan Falls", "Wildlife Spotting" to "Isarog is a key biodiversity area", "Hot Spring Bathing" to "Relax in Panicuason after the trail"),
    listOf("Panicuason Hot Springs", "Naga Cathedral"), listOf("Bigg's Diner", "Bob Marlin"), "#1E8A6E"
  ),
  PlaceDetail(
    "volcano_iriga", "Mount Iriga", "Volcanoes", "Camarines Sur, Bicol", "POTENTIALLY ACTIVE", "#EF9A00", 4.5f, "+3k",
    "Also known as Mount Asog, offering breathtaking panoramic views of Lake Buhi.",
    "Mount Iriga's crater lake and jagged ridgeline make it a favorite day-hike, with sweeping views over Lake Buhi - home of the world's smallest commercial fish, the sinarapan.",
    qf("Elevation" to "1,196 m", "Type" to "Stratovolcano", "Last Eruption" to "1642", "Status" to "Dormant"),
    hl("Ridge Hiking" to "Panoramic views of Lake Buhi", "Lake Tour" to "See the sinarapan fishing grounds", "Photography" to "Sunrise views over the crater rim"),
    listOf("Lake Buhi", "Iriga City Public Plaza"), listOf("Geewan Restaurant"), "#2E6FA8"
  ),
  PlaceDetail(
    "volcano_masaraga", "Mount Masaraga", "Volcanoes", "Albay, Bicol", "EXTINCT", "#6B7280", 4.6f, "+2k",
    "A sharp-peaked mountain that stands as a silent sentinel neighbor to Mayon.",
    "Overshadowed by Mayon but rewarding for serious hikers, Masaraga offers a quieter, more technical climb with excellent views back toward the perfect cone.",
    qf("Elevation" to "1,328 m", "Type" to "Stratovolcano", "Last Eruption" to "None recorded", "Status" to "Extinct"),
    hl("Technical Hiking" to "Steeper, less-traveled trail", "Ridge Photography" to "Unobstructed views of Mayon", "Forest Trekking" to "Dense mossy forest cover"),
    listOf("Mayon Volcano", "Lignon Hill"), listOf("Small Talk Cafe"), "#3E7CB1"
  )
).map { enrich(it) }

// ---------- CULTURAL FESTIVALS (matches "Cultural Festivals List Screen.png") ----------
val culturalFestivals = listOf(
  PlaceDetail(
    "fest_magayon", "Magayon Festival", "Cultural Festivals", "Albay, Bicol", "MAY", "#2E7D32", 4.9f, "+25k",
    "A month-long celebration honoring the legend of Daragang Magayon and Albay's heritage.",
    "The festival celebrates the origin of Mayon Volcano, derived from the Bicolano word 'Magayon' meaning beautiful. It honors the tragic love story of Daragang Magayon (Beautiful Maiden) and Panganoron, whose tomb is said to have grown into the volcano we see today.",
    qf("Month" to "May", "Duration" to "Month-long", "Type" to "Cultural & Religious", "Main Venue" to "Albay Astrodome"),
    hl("Street Dancing" to "Vibrant costumes and Bicolano beats", "Beauty Pageants" to "Search for Daragang Magayon", "Trade Fairs" to "Best of Albay's local crafts and food"),
    listOf("Cagsawa Ruins", "Lignon Hill"), listOf("1st Colonial Grill", "Small Talk Cafe"), "#C24444"
  ),
  PlaceDetail(
    "fest_penafrancia", "Peñafrancia Festival", "Cultural Festivals", "Camarines Sur, Bicol", "SEPT", "#5E35B1", 5.0f, "+1M",
    "The biggest Marian devotion in Asia, featuring the spectacular fluvial procession of 'Ina.'",
    "Held annually in Naga City, the Peñafrancia Festival draws over a million devotees for the fluvial procession of Our Lady of Peñafrancia along the Bicol River - one of the largest religious gatherings in the country.",
    qf("Month" to "September", "Duration" to "9 days", "Type" to "Religious", "Main Venue" to "Naga Metropolitan Cathedral"),
    hl("Fluvial Procession" to "Image of Ina carried down the Bicol River", "Novena Masses" to "Nine days of devotion in Naga", "Traslacion" to "Solemn image transfer procession"),
    listOf("Naga Cathedral", "Peñafrancia Basilica"), listOf("Bigg's Diner", "Bob Marlin"), "#8D6E63"
  ),
  PlaceDetail(
    "fest_ibalong", "Ibalong Festival", "Cultural Festivals", "Albay, Bicol", "AUGUST", "#2E7D32", 4.8f, "+18k",
    "A non-religious festival celebrating the Bicolano epic 'Ibalong' with masks and heroes.",
    "Ibalong Festival re-enacts the pre-colonial epic of Bicol's founding heroes - Baltog, Handyong, and Bantong - through street performances, mask parades, and cultural showcases across Legazpi City.",
    qf("Month" to "August", "Duration" to "1 week", "Type" to "Cultural", "Main Venue" to "Legazpi City"),
    hl("Mask Parade" to "Epic heroes and mythical creatures", "Street Performances" to "Dramatized scenes from the Ibalong epic", "Cultural Exhibits" to "Bicolano pre-colonial heritage"),
    listOf("Embarcadero de Legazpi", "Lignon Hill"), listOf("Waft Coffee"), "#3E7CB1"
  ),
  PlaceDetail(
    "fest_rodeo", "Rodeo Masbateño", "Cultural Festivals", "Masbate, Bicol", "APRIL", "#EF6C00", 4.7f, "+10k",
    "The only cattle-themed rodeo festival in Southeast Asia, showcasing local ranch skills.",
    "Masbate's ranching heritage takes center stage with bull riding, calf roping, and horse racing competitions drawing cowboys from across the region.",
    qf("Month" to "April", "Duration" to "1 week", "Type" to "Cultural", "Main Venue" to "Masbate City Rodeo Grounds"),
    hl("Bull Riding" to "Main rodeo arena event", "Calf Roping" to "Timed ranching skill competition", "Horse Parade" to "Ranch culture on full display"),
    listOf("Buntod Sandbar", "Masbate City Plaza"), emptyList(), "#2E9B8F"
  ),
  PlaceDetail(
    "fest_kasanggayahan", "Kasanggayahan Festival", "Cultural Festivals", "Sorsogon, Bicol", "OCTOBER", "#00796B", 4.6f, "+12k",
    "Celebrating the foundation of Sorsogon, featuring the 'Pantomina sa Tinampo' folk dance.",
    "Kasanggayahan marks Sorsogon's founding anniversary with a province-wide showcase of folk dances, agri-trade fairs, and street parades culminating in the beloved Pantomina sa Tinampo.",
    qf("Month" to "October", "Duration" to "2 weeks", "Type" to "Cultural", "Main Venue" to "Sorsogon City"),
    hl("Pantomina sa Tinampo" to "Iconic Sorsogon wedding folk dance", "Agri-Trade Fair" to "Local produce and products", "Street Parade" to "Province-wide festival procession"),
    listOf("Rizal Beach", "Paguriran Island"), listOf("Fitzgerald's"), "#1177A8"
  )
).map { enrich(it) }

// ---------- ATV RIDES (matches "ATV Rides List Screen.png") ----------
val atvRides = listOf(
  PlaceDetail(
    "atv_cagsawa", "Cagsawa ATV Trail", "ATV Rides", "Daraga, Albay", "POPULAR", "#EF9A00", 4.8f, "+12k",
    "The classic ride through rocky streams and ruins with the best front-row view of Mount Mayon.",
    "The Cagsawa ATV Trail offers an adrenaline-pumping journey through the rugged terrain of Daraga. Navigate through rocky riverbeds, lush pine forests, and volcanic ash trails as you head toward the base of the iconic Mayon Volcano. It's the ultimate Bicol adventure for thrill-seekers.",
    qf("Duration" to "1 - 3 Hours", "Difficulty" to "Moderate", "Best Time" to "Sunrise", "Guide" to "Required"),
    hl("Rocky River Crossings" to "Splash through shallow streams", "Lava Wall Views" to "Up-close look at cooled volcanic flows", "Forest Trails" to "Explore lush local pine forests"),
    listOf("Cagsawa Ruins", "Daraga Church"), listOf("1st Colonial Grill", "Small Talk Cafe"), "#3E7CB1"
  ),
  PlaceDetail(
    "atv_blacklava", "Black Lava Trail", "ATV Rides", "Sto. Domingo, Albay", "EXPERT", "#C62828", 5.0f, "+8k",
    "Navigate the rugged 2006 lava wall. A challenging terrain for thrill-seekers and adventure lovers.",
    "This is the most technical of Albay's ATV trails, crossing the hardened lava flow from the 2006 eruption. Loose volcanic rock and steep grades make it a favorite among experienced riders.",
    qf("Duration" to "2 - 4 Hours", "Difficulty" to "Expert", "Best Time" to "Morning", "Guide" to "Required"),
    hl("Lava Field Crossing" to "Ride across the 2006 eruption flow", "Steep Ascents" to "Technical, high-grade terrain", "Summit Viewpoint" to "Closest legal approach to the crater"),
    listOf("Mayon Volcano", "Sto. Domingo Beach"), listOf("Small Talk Cafe"), "#C24444"
  ),
  PlaceDetail(
    "atv_greenlava", "Green Lava Trail", "ATV Rides", "Malilipot, Albay", "SCENIC", "#2E7D32", 4.8f, "+5k",
    "A scenic route through lush vegetation and rolling hills, perfect for photography and nature fans.",
    "Green Lava Trail winds through vegetation that has reclaimed an older lava flow, offering a gentler ride with sweeping hillside views - ideal for riders who want scenery over speed.",
    qf("Duration" to "1 - 2 Hours", "Difficulty" to "Easy", "Best Time" to "Afternoon", "Guide" to "Optional"),
    hl("Rolling Hills" to "Gentle terrain, great photo stops", "Vegetation Cover" to "Reclaimed lava field greenery", "Sunset Views" to "Popular late-afternoon ride"),
    listOf("Quitinday Hills", "Malilipot Falls"), listOf("Waft Coffee"), "#1E8A6E"
  ),
  PlaceDetail(
    "atv_forest", "Forest Trail", "ATV Rides", "Camalig, Albay", "WILD", "#5E35B1", 4.7f, "+3k",
    "Venture deep into the wooded outskirts of the volcano. Expect tight turns and diverse terrain.",
    "A shaded, winding route through Camalig's forest belt, mixing tight switchbacks with the occasional stream crossing. Cooler and more sheltered than the open lava trails.",
    qf("Duration" to "1 - 2 Hours", "Difficulty" to "Moderate", "Best Time" to "Midday", "Guide" to "Required"),
    hl("Tight Switchbacks" to "Technical forest riding", "Stream Crossings" to "Small creek fords along the way", "Shaded Canopy" to "Cooler ride under forest cover"),
    listOf("Camalig Church", "Hoyop-Hoyopan Cave"), listOf("Geewan Restaurant"), "#8D6E63"
  ),
  PlaceDetail(
    "atv_riverbed", "River Bed Trail", "ATV Rides", "Daraga, Albay", "SPLASH", "#0288D1", 4.8f, "+6k",
    "Experience the thrill of splashing through shallow rivers and navigating rocky riverbeds.",
    "River Bed Trail follows a dry-season creek bed strewn with volcanic rock, alternating between shallow water crossings and rocky flats - a wetter, splashier alternative to the classic trail.",
    qf("Duration" to "1 - 3 Hours", "Difficulty" to "Moderate", "Best Time" to "Dry Season", "Guide" to "Required"),
    hl("Water Crossings" to "Multiple shallow river fords", "Rocky Flats" to "Volcanic rock riverbed terrain", "Cooling Splashes" to "A wetter ride than the other trails"),
    listOf("Cagsawa Ruins", "Daraga Church"), listOf("1st Colonial Grill"), "#3E7CB1"
  )
).map { enrich(it) }

// ---------- TRADITIONAL DISHES (matches "Traditional Dishes List Screen.png") ----------
val traditionalDishes = listOf(
  PlaceDetail(
    "food_bicolexpress", "Bicol Express", "Traditional Dishes", "Malate-style Bicolano Original", "ICONIC", "#EF9A00", 4.9f, "+25k",
    "The ultimate spicy stew made with long chilies, coconut milk, shrimp paste, and succulent pork chunks.",
    "Named after the passenger train service from Manila to Legazpi, Bicol Express is a spicy stew made with long chilies, coconut milk, shrimp paste, and pork. While its name was coined in Malate, Manila, its heart and fiery soul belong to the Bicolano culinary tradition of using heavy cream and spice.",
    qf("Base" to "Coconut Milk", "Spice" to "Birds Eye Chilies", "Protein" to "Pork Belly", "Salty" to "Shrimp Paste"),
    hl("Slow Simmering" to "Cooked until the coconut milk renders oil", "Flavor Infusion" to "Sautéing bagoong with aromatics first"),
    listOf("Legazpi Boulevard"), listOf("1st Colonial Grill", "Small Talk Cafe"), "#8D4A2B"
  ),
  PlaceDetail(
    "food_laing", "Laing", "Traditional Dishes", "Camalig, Albay", "MUST-TRY", "#2E7D32", 4.8f, "+18k",
    "Creamy and spicy dried taro leaves slowly simmered in rich gata (coconut milk) until tender and flavorful.",
    "Laing showcases the Bicolano love of coconut milk and heat - dried taro (gabi) leaves simmered low and slow until the leaves turn silky and the sauce reduces into a thick, spicy cream.",
    qf("Base" to "Coconut Milk", "Spice" to "Siling Labuyo", "Protein" to "Dried Shrimp (opt.)", "Salty" to "Bagoong"),
    hl("Low Simmering" to "Leaves cooked whole, unstirred at first", "Coconut Reduction" to "Sauce thickens as it slow-cooks"),
    listOf("Camalig Church"), listOf("1st Colonial Grill"), "#4E7C4A"
  ),
  PlaceDetail(
    "food_pinangat", "Pinangat", "Traditional Dishes", "Camalig, Albay", "DELICACY", "#5E35B1", 5.0f, "+15k",
    "A specialized parcel of shredded taro leaves and meat wrapped in whole leaves and steamed in coconut milk.",
    "Pinangat is Laing's more refined cousin - taro leaves wrapped into neat parcels around a savory filling, then simmered in coconut cream until the whole bundle is tender.",
    qf("Base" to "Coconut Milk", "Spice" to "Mild to Medium", "Protein" to "Pork or Fish", "Salty" to "Bagoong"),
    hl("Leaf Wrapping" to "Taro leaves bundled by hand", "Long Steaming" to "Cooked whole in coconut cream"),
    listOf("Camalig Public Market"), listOf("Small Talk Cafe"), "#7A5AA8"
  ),
  PlaceDetail(
    "food_kinunot", "Kinunot", "Traditional Dishes", "Coastal Bicol Towns", "UNIQUE", "#0288D1", 4.7f, "+8k",
    "Savory flaked stingray or shark meat cooked with malunggay leaves in creamy spiced coconut milk.",
    "A coastal Bicolano specialty, Kinunot flakes stingray or shark meat until shredded, then simmers it with malunggay (moringa) leaves in coconut milk spiced with chili.",
    qf("Base" to "Coconut Milk", "Spice" to "Siling Labuyo", "Protein" to "Stingray/Shark", "Salty" to "Patis"),
    hl("Meat Flaking" to "Boiled then shredded by hand", "Malunggay Simmer" to "Leaves added in the final minutes"),
    listOf("Coastal fish markets"), listOf("Fitzgerald's"), "#1177A8"
  ),
  PlaceDetail(
    "food_kinalas", "Kinalas", "Traditional Dishes", "Naga City, Camarines Sur", "NAGA'S BEST", "#C62828", 4.8f, "+12k",
    "A hearty noodle soup with tender meat scraped from the bone, topped with a signature rich brown sauce.",
    "Kinalas is Naga City's beloved noodle soup - miki noodles in a deeply savory broth, topped with meat scraped straight from bones simmered for hours, finished with a thick brown gravy.",
    qf("Base" to "Bone Broth", "Spice" to "Mild", "Protein" to "Beef/Pork Scrapings", "Salty" to "Soy-based Gravy"),
    hl("Bone Simmering" to "Broth built over hours", "Gravy Finish" to "Thick brown sauce ladled on top"),
    listOf("Naga City Public Market"), listOf("Bigg's Diner", "Bob Marlin"), "#C24444"
  )
).map { enrich(it) }

// ---------- Generic generator for categories without exact reference screens ----------
private fun genPlaces(
  prefix: String, categoryLabel: String, entries: List<Triple<String, String, String>>, // name, location, shortDesc
  quickFactLabels: List<String>, colorHex: String
): List<PlaceDetail> = entries.mapIndexed { i, (name, location, desc) ->
  PlaceDetail(
    "${prefix}_$i", name, categoryLabel, location, listOf("POPULAR", "SCENIC", "MUST-SEE", "TOP-RATED")[i % 4],
    listOf("#EF9A00", "#2E7D32", "#0288D1", "#5E35B1")[i % 4], 4.5f + (i % 5) * 0.1f, "+${(i + 3)}k",
    desc, "$name is a highlight of ${categoryLabel.lowercase()} in Bicol. $desc",
    quickFactLabels.mapIndexed { j, label -> QuickFact(label, listOf("Half-day", "Moderate", "Year-round", "Guide optional")[j % 4]) },
    hl("Guided Tour" to "Local guides available on-site", "Photo Stops" to "Scenic viewpoints along the way", "Local Snacks" to "Vendors nearby for refreshments"),
    listOf("Nearby town center"), listOf("Local eatery"), colorHex
  )
}

val beaches = genPlaces("beach", "Beaches", listOf(
  Triple("Bagasbas Beach", "Daet, Camarines Norte", "A long stretch of gray-sand beach popular with surfers and sunset watchers."),
  Triple("Calaguas Islands", "Camarines Norte", "Powdery white sand often compared to Boracay, reachable by boat."),
  Triple("Subic Beach", "Sorsogon", "A quiet cove favored for its calm, clear waters."),
  Triple("Rizal Beach", "Sorsogon City", "A popular local beach with a lively boardwalk at sunset."),
  Triple("Puraran Beach", "Baras, Catanduanes", "Home to the famous Majestic Puraran surf break.")
), listOf("Sand Type", "Best Activity", "Best Time", "Access"), "#0288D1").map { enrich(it) }

val waterfalls = genPlaces("falls", "Waterfalls", listOf(
  Triple("Malabsay Falls", "Naga City, Camarines Sur", "A short trek from the city into Isarog's forest to a cool plunge pool."),
  Triple("Nabuntolan Falls", "Camarines Sur", "A multi-tier falls tucked deep within Mount Isarog Natural Park."),
  Triple("Bulusan Twin Falls", "Sorsogon", "Twin cascades within Bulusan Volcano Natural Park."),
  Triple("Maribina Falls", "Catanduanes", "A wide, powerful falls popular for swimming."),
  Triple("Colasi Falls", "Camarines Norte", "A series of cascades set in dense rainforest.")
), listOf("Height", "Swim Spot", "Best Time", "Access"), "#00838F").map { enrich(it) }

val islands = genPlaces("island", "Islands", listOf(
  Triple("Caramoan Islands", "Camarines Sur", "Limestone cliffs and hidden lagoons - a Survivor filming location."),
  Triple("Ticao Island", "Masbate", "Gateway to Manta Bowl, one of the world's top manta ray dive sites."),
  Triple("Cagraray Island", "Albay", "Home to Misibis Bay resort and quiet fishing villages."),
  Triple("Batan Island", "Albay", "A small, laid-back island with mangrove tours."),
  Triple("San Miguel Island", "Camarines Sur", "A quiet island in San Miguel Bay known for its lighthouse.")
), listOf("Boat Ride", "Best Activity", "Best Time", "Access"), "#00897B").map { enrich(it) }

val parks = genPlaces("park", "Parks", listOf(
  Triple("Mayon Volcano Natural Park", "Albay", "A protected area ringing the volcano with trails and viewpoints."),
  Triple("Bulusan Volcano Natural Park", "Sorsogon", "Rainforest, lake, and hot springs in one protected park."),
  Triple("Quitinday Hills", "Albay", "Rolling green mounds often called Bicol's 'Chocolate Hills.'"),
  Triple("Panicuason Hot Spring Park", "Camarines Sur", "Natural hot spring pools at the foot of Mount Isarog."),
  Triple("Paguriran Island Park", "Sorsogon", "A scenic island park with rock formations and tide pools.")
), listOf("Size", "Main Draw", "Best Time", "Entrance Fee"), "#388E3C").map { enrich(it) }

val historicalSites = genPlaces("hist", "Historical Sites", listOf(
  Triple("Cagsawa Ruins", "Daraga, Albay", "The bell tower of a 1814 church buried by Mayon's eruption."),
  Triple("Casa Real Sorsogon", "Sorsogon City", "A restored Spanish-era provincial hall."),
  Triple("Ticao Heritage Houses", "Masbate", "Ancestral houses reflecting old trade-route wealth."),
  Triple("Camarines Sur Provincial Capitol", "Pili", "A landmark colonial-influenced government building."),
  Triple("Daet Freedom Park", "Camarines Norte", "Site of the first Rizal monument in the Philippines.")
), listOf("Era", "Significance", "Best Time", "Entrance Fee"), "#6D4C41").map { enrich(it) }

val churches = genPlaces("church", "Churches", listOf(
  Triple("Daraga Church", "Daraga, Albay", "A baroque hilltop church with sweeping Mayon views."),
  Triple("Peñafrancia Basilica", "Naga City", "The shrine of the region's most revered Marian devotion."),
  Triple("Naga Metropolitan Cathedral", "Naga City", "The seat of the Archdiocese of Caceres."),
  Triple("Camalig Church", "Camalig, Albay", "A centuries-old church known for its wide facade."),
  Triple("Bacon Church", "Sorsogon", "A coastal parish church with a quiet, historic charm.")
), listOf("Built", "Style", "Feast Day", "Access"), "#5D4037").map { enrich(it) }

val museums = genPlaces("museum", "Museums", listOf(
  Triple("Cagsawa Ruins Museum", "Daraga, Albay", "Artifacts recovered from the 1814 Mayon eruption."),
  Triple("Naga City Museum", "Naga City", "Local history and the story of the Peñafrancia devotion."),
  Triple("Sorsogon Provincial Museum", "Sorsogon City", "Provincial heritage and natural history exhibits."),
  Triple("Museo de Legazpi", "Legazpi City", "Albay's culture, geology, and history under one roof."),
  Triple("Catanduanes Heritage Museum", "Virac", "Abaca trade history and island heritage.")
), listOf("Focus", "Hours", "Entrance Fee", "Access"), "#4527A0").map { enrich(it) }

// "Antipolo sa Bicol Novena" dropped - not a real event; the Peñafrancia
// novena is already covered by the Cultural Festivals entry (per source notes)
val religiousFestivals = genPlaces("fest_rel", "Religious Festivals", listOf(
  Triple("Traslacion sa Naga", "Naga City", "Solemn transfer of the image of Our Lady of Peñafrancia."),
  Triple("Santacruzan", "Region-wide", "May procession honoring Queen Helena and the Holy Cross."),
  Triple("Flores de Mayo", "Region-wide", "Month-long floral offerings to the Virgin Mary."),
  Triple("Feast of Our Lady of Salvacion", "Joroan, Tiwi, Albay", "One of Bicol's oldest Marian feast traditions.")
), listOf("Month", "Duration", "Type", "Main Venue"), "#5E35B1").map { enrich(it) }

val agriculturalFestivals = genPlaces("fest_agri", "Agricultural Festivals", listOf(
  Triple("Pili Festival", "Camarines Sur", "Celebrating the region's signature pili nut harvest."),
  Triple("Abaca Festival", "Catanduanes", "Honoring the island's abaca fiber industry."),
  Triple("Pineapple Festival", "Camarines Norte", "A harvest festival for the province's pineapple farms."),
  Triple("Coconut Festival", "San Pablo, Sorsogon", "Showcasing coconut-based products and cuisine."),
  Triple("Kaogma Festival", "Masbate", "A thanksgiving festival for Masbate's ranching and farming bounty.")
), listOf("Month", "Duration", "Type", "Main Venue"), "#558B2F").map { enrich(it) }

// Only two eco-tourism festivals kept - the rest aren't real Bicol events (per source notes)
val ecoFestivals = genPlaces("fest_eco", "Eco-Tourism Festivals", listOf(
  Triple("Butanding Festival", "Donsol, Sorsogon", "Celebrating the whale sharks that made Donsol famous."),
  Triple("Bulusan Green Festival", "Sorsogon", "A festival centered on the Bulusan Natural Park's conservation.")
), listOf("Month", "Duration", "Type", "Main Venue"), "#00796B").map { enrich(it) }

val hiking = genPlaces("act_hiking", "Hiking", listOf(
  Triple("Mayon Volcano Trek", "Albay", "A guided climb up the slopes of the perfect cone."),
  Triple("Mount Isarog Trail", "Camarines Sur", "A rainforest trek past waterfalls to the summit."),
  Triple("Bulusan Volcano Hike", "Sorsogon", "A trail through the natural park to the crater rim."),
  Triple("Mount Malinao Trail", "Albay", "A lesser-known climb with panoramic Bicol views."),
  Triple("Mount Iriga Trek", "Camarines Sur", "A day-hike with views over Lake Buhi.")
), listOf("Duration", "Difficulty", "Best Time", "Guide"), "#2E7D32").map { enrich(it) }

val islandHopping = genPlaces("act_island", "Island Hopping", listOf(
  Triple("Caramoan Island Hopping", "Camarines Sur", "A full-day tour of lagoons and limestone islets."),
  Triple("Calaguas Island Tour", "Camarines Norte", "Boat-hop between Calaguas' white-sand islets."),
  Triple("Ticao-Burias Hop", "Masbate", "A multi-island tour connecting Ticao and Burias."),
  Triple("Cagraray Island Tour", "Albay", "A relaxed island tour near Misibis Bay."),
  Triple("San Miguel Bay Islands", "Camarines Sur", "Quiet fishing islands off the coast.")
), listOf("Duration", "Difficulty", "Best Time", "Guide"), "#0277BD").map { enrich(it) }

val surfing = genPlaces("act_surf", "Surfing", listOf(
  Triple("Bagasbas Surf Break", "Camarines Norte", "Bicol's most accessible beginner-friendly surf spot."),
  Triple("Puraran Majestic Wave", "Catanduanes", "A world-class right-hand reef break."),
  Triple("Mananao Cove", "Rapu-Rapu, Albay", "A quiet cove break on Rapu-Rapu Island."),
  Triple("Lebanon Beach", "Bacon, Sorsogon", "A broad, consistent beach break popular with local surfers."),
  Triple("Buenavista Beach", "Gubat, Sorsogon", "A reliable break on Bicol's eastern Pacific coast.")
), listOf("Duration", "Difficulty", "Best Time", "Guide"), "#5C6BC0").map { enrich(it) }

val diving = genPlaces("act_dive", "Diving & Snorkeling", listOf(
  Triple("Manta Bowl Dive Site", "Ticao, Masbate", "One of the world's best manta ray dive sites."),
  Triple("Palambanes Island", "Catanduanes", "A quiet island with clear water snorkeling and diving."),
  Triple("Matukad Island", "Caramoan, Camarines Sur", "Shallow reef snorkeling by a lagoon."),
  Triple("Misibis Bay", "Bacacay, Albay", "An easy reef dive near the Misibis Bay resort."),
  Triple("Whale Shark Watching", "Donsol, Sorsogon", "Swim alongside gentle giants in Donsol's waters.")
), listOf("Duration", "Difficulty", "Best Time", "Guide"), "#00695C").map { enrich(it) }

val whaleShark = genPlaces("act_whale", "Whale Sharks", listOf(
  Triple("Donsol Whale Shark Tour", "Donsol, Sorsogon", "The Philippines' premier whale shark interaction site."),
  Triple("Ticao Whale Shark Encounter", "Masbate", "A quieter alternative to Donsol's peak crowds."),
  Triple("Burias Pass Interaction", "Masbate", "Seasonal sightings along the Burias Pass."),
  Triple("Albay Gulf", "Legazpi, Albay", "A calmer alternative viewing area along the Albay Gulf."),
  Triple("Agojo Reef", "Catanduanes", "A remote reef with seasonal whale shark sightings.")
), listOf("Duration", "Difficulty", "Best Time", "Guide"), "#37474F").map { enrich(it) }

val delicacies = genPlaces("food_delicacy", "Delicacies", listOf(
  Triple("Pili Nut Tart", "Camarines Sur", "A buttery tart filled with candied pili nuts."),
  Triple("Sili Ice Cream", "Legazpi City, Albay", "A local favorite blending chili heat with sweet cream."),
  Triple("Cassava Cake", "Region-wide", "A dense, chewy cassava dessert."),
  Triple("Mazapan de Pili", "Camarines Sur", "A pili-nut marzipan wrapped in colorful paper."),
  Triple("Suman sa Ibus", "Region-wide", "Glutinous rice cake wrapped in woven coconut leaves.")
), listOf("Base", "Spice", "Protein", "Salty"), "#AD1457").map { enrich(it) }

val restaurants = genPlaces("food_resto", "Restaurants", listOf(
  Triple("1st Colonial Grill", "Legazpi City, Albay", "Established in 2004, famous for pioneering Sili Ice Cream."),
  Triple("Small Talk Cafe", "Legazpi City, Albay", "A cozy fusion cafe known for Pinangat Pasta."),
  Triple("Balay Cena Una", "Daraga, Albay", "A heritage-house restaurant serving Bicolano classics."),
  Triple("Bigg's Diner", "Naga City, Camarines Sur", "A beloved Bicolano fast-food chain known for burger steak."),
  Triple("Graceland", "Naga City, Camarines Sur", "A local favorite for casual dining in Naga."),
  Triple("Bob Marlin", "Naga City, Camarines Sur", "A lively family restaurant known for crispy pata and Bicolano classics."),
  Triple("Sea Breeze Restaurant", "Virac, Catanduanes", "A seaside boulevard favorite for fresh-catch seafood at sunset."),
  Triple("Pandora's Bayview Bar and Grill", "Sorsogon City, Sorsogon", "A bayfront grill known for crispy sisig and Sorsogon's own Baluko."),
  Triple("Butanding Restaurant and Bar", "Donsol, Sorsogon", "A traveler favorite near the whale shark interaction center."),
  Triple("Hoogpaan Restaurant", "Daet, Camarines Norte", "A calm, airy spot near Bagasbas Beach popular with surfers."),
  Triple("Ocho Seafood and Grill", "Masbate City, Masbate", "A go-to for fresh island seafood in the provincial capital.")
), listOf("Opening Hours", "Price Range", "Location", "Category"), "#37474F").map { enrich(it) }

val destinationsData: Map<String, List<PlaceDetail>> = mapOf(
  "volcanoes" to volcanoes, "beaches" to beaches, "waterfalls" to waterfalls, "islands" to islands,
  "parks" to parks, "historical" to historicalSites, "churches" to churches, "museums" to museums
)

val festivalsData: Map<String, List<PlaceDetail>> = mapOf(
  "cultural" to culturalFestivals, "religious" to religiousFestivals,
  "agricultural" to agriculturalFestivals, "eco" to ecoFestivals
)

val activitiesData: Map<String, List<PlaceDetail>> = mapOf(
  "atv" to atvRides, "hiking" to hiking, "island_hopping" to islandHopping,
  "surfing" to surfing, "diving" to diving, "whale_shark" to whaleShark
)

val cuisinesData: Map<String, List<PlaceDetail>> = mapOf(
  "traditional" to traditionalDishes, "delicacies" to delicacies, "restaurants" to restaurants
)

fun findPlaceById(id: String): PlaceDetail? {
  return destinationsData.values.flatten().find { it.id == id }
    ?: festivalsData.values.flatten().find { it.id == id }
    ?: activitiesData.values.flatten().find { it.id == id }
    ?: cuisinesData.values.flatten().find { it.id == id }
    ?: cultureData.values.flatten().find { it.id == id }
}
