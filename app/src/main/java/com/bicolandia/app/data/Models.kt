package com.bicolandia.app.data

data class SimpleItem(
  val id: String,
  val title: String,
  val subtitle: String = "",
  val badge: String = "",
  val colorHex: String = "#2E7D32"
)

data class QuickFact(val label: String, val value: String)

data class PlaceDetail(
  val id: String,
  val name: String,
  val category: String,     // section title shown on list/detail top bars
  val location: String,     // e.g. "Albay, Bicol"
  val status: String,      // small pill e.g. "ACTIVE", "MUST-TRY", "POPULAR"
  val statusColorHex: String = "#1E5631",
  val rating: Float,
  val engagement: String,    // e.g. "+12k"
  val shortDescription: String, // used on list card
  val about: String,       // longer About paragraph on detail screen
  val quickFacts: List<QuickFact>,
  val highlights: List<QuickFact>,   // title=name, value=subtitle text
  val nearbyAttractions: List<String>,
  val nearbyRestaurants: List<String>,
  val colorHex: String,
  // ---- richer detail-screen content (all optional; see PlaceExtras.kt) ----
  val bestTime: String = "",      // best time to visit / experience
  val gettingThere: String = "",    // how to get there / where to experience it
  val tips: List<String> = emptyList(), // practical visitor tips
  val funFact: String = ""       // a short "did you know"
)

data class Province(
  val id: String,
  val name: String,
  val about: String,
  val capital: String,
  val languages: String,
  val climate: String,
  val bestTimeToVisit: String,
  val destinationCount: Int,
  val rating: Float,
  val topAttractions: List<String>,
  val activities: List<String>,
  val localCuisines: List<String>,
  val festivals: List<String>,
  val bestRestaurants: List<String>,
  val souvenirShops: List<String>,
  val colorHex: String,
  // ---- richer detail-screen content (optional; see PlaceExtras.kt) ----
  val more: String = "",
  val gettingAround: String = "",
  val tips: List<String> = emptyList()
)

data class ExploreCategory(val id: String, val title: String, val colorHex: String)

val exploreCategories = listOf(
  ExploreCategory("province", "Province", "#2E7D32"),
  ExploreCategory("destinations", "Destinations", "#1565C0"),
  ExploreCategory("festivals", "Festivals", "#C62828"),
  ExploreCategory("activities", "Activities", "#EF6C00"),
  ExploreCategory("cuisines", "Cuisines", "#6A1B9A")
)

// grid style = 2 columns (Destinations, Activities), list style = 1 column big cards (Festivals, Cuisines)
val destinationClassifications = listOf(
  SimpleItem("volcanoes", "Volcanoes", "3 Active Peaks", colorHex = "#795548"),
  SimpleItem("beaches", "Beaches", "15+ Pristine Coasts", colorHex = "#0288D1"),
  SimpleItem("waterfalls", "Waterfalls", "8 Hidden Gems", colorHex = "#00838F"),
  SimpleItem("islands", "Islands", "Island Hopping", colorHex = "#00897B"),
  SimpleItem("parks", "Parks", "Nature Reserves", colorHex = "#388E3C"),
  SimpleItem("historical", "Historical Sites", "Cagsawa & More", colorHex = "#6D4C41"),
  SimpleItem("churches", "Churches", "Pilgrimage Sites", colorHex = "#8D6E63"),
  SimpleItem("museums", "Museums", "Local Arts & History", colorHex = "#4527A0")
)

val festivalClassifications = listOf(
  SimpleItem("cultural", "Cultural Festivals", "Heritage & Folk Traditions", colorHex = "#D81B60"),
  SimpleItem("religious", "Religious Festivals", "Faith & Devotion", colorHex = "#5E35B1"),
  SimpleItem("agricultural", "Agricultural Festivals", "Bounty & Harvest Celebrations", colorHex = "#558B2F"),
  SimpleItem("eco", "Eco-Tourism Festivals", "Nature & Conservation", colorHex = "#00796B")
)

val activityClassifications = listOf(
  SimpleItem("atv", "ATV Rides", colorHex = "#E64A19"),
  SimpleItem("hiking", "Hiking", colorHex = "#2E7D32"),
  SimpleItem("island_hopping", "Island Hopping", colorHex = "#0277BD"),
  SimpleItem("surfing", "Surfing", colorHex = "#5C6BC0"),
  SimpleItem("diving", "Diving & Snorkeling", colorHex = "#00695C"),
  SimpleItem("whale_shark", "Whale Sharks", colorHex = "#37474F")
)

val cuisineClassifications = listOf(
  SimpleItem("traditional", "Traditional Dishes", "Laing, Bicol Express, Pinangat", "MUST TRY", "#BF360C"),
  SimpleItem("delicacies", "Delicacies", "Pili Nuts, Kinalas, Mazapan", "SOUVENIRS", "#AD1457"),
  SimpleItem("restaurants", "Restaurants", "Top-rated local & fusion spots", "DINING", "#37474F")
)

// Culture & Heritage - 30 entries across 5 groups (see data/CultureData.kt)
val cultureClassifications = listOf(
  SimpleItem("crafts", "Traditional Crafts", "Weaving, Pottery & Carving", "HANDMADE", "#8D6E63"),
  SimpleItem("folklore", "Folklore & Legends", "The Ibalong Epic & Bicol Myths", "LEGEND", "#6A1B9A"),
  SimpleItem("figures", "Historical Figures", "Bicolano Heroes & Leaders", "HERITAGE", "#37474F"),
  SimpleItem("traditions", "Living Traditions", "Customs, Dance & Folk Song", "CUSTOM", "#C62828"),
  SimpleItem("indigenous", "Indigenous Heritage", "Agta Peoples & Folk Belief", "ANCESTRAL", "#00796B")
)
