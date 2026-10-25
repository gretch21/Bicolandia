package com.bicolandia.app.data

private val baseProvinces = listOf(
  Province(
    id = "albay", name = "Albay",
    about = "Known for the iconic, perfectly symmetrical Mayon Volcano, Albay is the heart of Bicol's tourism. It offers a blend of historical ruins, colonial-era churches, and adrenaline-pumping adventures against a backdrop of breathtaking natural landscapes.",
    capital = "Legazpi City", languages = "Bicolano, Tagalog", climate = "Tropical", bestTimeToVisit = "March to May",
    destinationCount = 6, rating = 4.9f,
    topAttractions = listOf("Cagsawa Ruins", "Daraga Church", "Lignon Hill", "Quitinday Hills"),
    activities = listOf("ATV Lava Trail Adventure", "Hiking Mayon", "Heritage Sightseeing"),
    localCuisines = listOf("Bicol Express", "Pinangat", "Sili Ice Cream"),
    festivals = listOf("Magayon Festival", "Ibalong Festival"),
    bestRestaurants = listOf("1st Colonial Grill", "Small Talk Cafe"),
    souvenirShops = listOf("Abaca products & local crafts"),
    colorHex = "#3E7CB1"
  ),
  Province(
    id = "camsur", name = "Camarines Sur",
    about = "CamSur is Bicol's adventure and MICE tourism hub - turquoise river islands, watersports, and Naga City's festival energy all in one province.",
    capital = "Pili", languages = "Bikol Naga, Tagalog", climate = "Tropical", bestTimeToVisit = "Dec to May",
    destinationCount = 12, rating = 4.8f,
    topAttractions = listOf("CamSur Watersports Complex", "Caramoan Islands", "Panicuason Hot Springs"),
    activities = listOf("Wakeboarding", "Island Hopping", "Hiking Isarog"),
    localCuisines = listOf("Pili Nut Delicacies", "Bicol Express", "Sinantol"),
    festivals = listOf("Peñafrancia Festival", "Kamundagan Festival"),
    bestRestaurants = listOf("Bigg's Diner", "Bob Marlin", "Geewan Restaurant"),
    souvenirShops = listOf("Pili nut store", "Sabli souvenirs"),
    colorHex = "#1E8A6E"
  ),
  Province(
    id = "sorsogon", name = "Sorsogon",
    about = "Sorsogon is the whale shark capital of the Philippines - home to Donsol's gentle giants and pristine, uncrowded beaches.",
    capital = "Sorsogon City", languages = "Bikol Sorsogon, Tagalog", climate = "Tropical maritime", bestTimeToVisit = "Nov to May",
    destinationCount = 8, rating = 4.9f,
    topAttractions = listOf("Donsol Whale Shark Interaction", "Bulusan Volcano Natural Park", "Paguriran Island"),
    activities = listOf("Whale Shark Swimming", "Firefly Watching", "Hiking Bulusan"),
    localCuisines = listOf("Sinantol", "Gulay na Natong", "Kinunot"),
    festivals = listOf("Kasanggayahan Festival", "Palapak Festival"),
    bestRestaurants = listOf("Fitzgerald's"),
    souvenirShops = listOf("Sorsogon City Public Market"),
    colorHex = "#1177A8"
  ),
  Province(
    id = "catanduanes", name = "Catanduanes",
    about = "The 'Land of the Howling Winds' is a surfer's paradise fronting the Pacific, with dramatic cliffs, abaca farms, and untouched coves.",
    capital = "Virac", languages = "Bikol Catanduanes, Tagalog", climate = "Typhoon-exposed", bestTimeToVisit = "Mar to Jun",
    destinationCount = 5, rating = 4.7f,
    topAttractions = listOf("Puraran Beach", "Twin Rock Beach", "Maribina Falls"),
    activities = listOf("Surfing", "Waterfall Trekking", "Snorkeling"),
    localCuisines = listOf("Pinangat", "Balaw-balaw"),
    festivals = listOf("Abaca Festival", "Catandungan Festival"),
    bestRestaurants = listOf("Puraran Beach House Grill"),
    souvenirShops = listOf("Abaca Craft Center"),
    colorHex = "#3F8A5B"
  ),
  Province(
    id = "camnorte", name = "Camarines Norte",
    about = "Known as 'Bicol's Little Boracay,' Camarines Norte offers powdery white-sand beaches at Bagasbas and Calaguas plus a rich mining heritage.",
    capital = "Daet", languages = "Tagalog, Bikol", climate = "Tropical", bestTimeToVisit = "Mar to Jun",
    destinationCount = 4, rating = 4.6f,
    topAttractions = listOf("Calaguas Islands", "Bagasbas Beach", "Mananap Falls"),
    activities = listOf("Surfing", "Island Hopping", "Waterfall Trekking"),
    localCuisines = listOf("Pinangat", "Fresh Seafood", "Kinalas"),
    festivals = listOf("Pineapple Festival", "Aripaap Festival"),
    bestRestaurants = listOf("Bagasbas Breeze Restobar"),
    souvenirShops = listOf("Daet Public Market"),
    colorHex = "#2E6FA8"
  ),
  Province(
    id = "masbate", name = "Masbate",
    about = "Masbate is the 'Rodeo Capital of the Philippines,' blending ranch culture with untouched islands like Ticao and Burias.",
    capital = "Masbate City", languages = "Masbateño, Tagalog", climate = "Tropical", bestTimeToVisit = "Mar to May",
    destinationCount = 7, rating = 4.7f,
    topAttractions = listOf("Ticao Island", "Manta Bowl", "Buntod Sandbar"),
    activities = listOf("Manta Ray Diving", "Island Hopping", "Rodeo Watching"),
    localCuisines = listOf("Beef Dishes", "Fresh Seafood", "Kinalas"),
    festivals = listOf("Rodeo Masbateño Festival", "Kaogma Festival"),
    bestRestaurants = emptyList(),
    souvenirShops = listOf("Ticao Handicraft Stalls"),
    colorHex = "#2E9B8F"
  )
)

// Same provinces, plus the richer text kept in PlaceExtras.kt
val provinces: List<Province> = baseProvinces.map { p ->
  provinceExtras[p.id]?.let { p.copy(more = it.more, gettingAround = it.gettingAround, tips = it.tips) } ?: p
}
