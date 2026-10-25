package com.bicolandia.app.data

data class TrackItem(
    val id: String,
    val name: String,
    val kind: String,          // "Province", "Destination", "Festival", "Activity", "Restaurant", "Stay"
    val provinceId: String?,   // null when a place is region-wide
    val route: String          // navigation route that opens it
)

val provinceIdByName = mapOf(
    "Camarines Norte" to "camnorte",
    "Camarines Sur" to "camsur",
    "Albay" to "albay",
    "Sorsogon" to "sorsogon",
    "Catanduanes" to "catanduanes",
    "Masbate" to "masbate"
)

// Towns/cities that appear on their own (without the province name) in the place data
private val cityToProvince = listOf(
    "Naga" to "camsur", "Pili" to "camsur", "Caramoan" to "camsur", "Legazpi" to "albay",
    "Daraga" to "albay", "Camalig" to "albay", "Tabaco" to "albay", "Donsol" to "sorsogon",
    "Daet" to "camnorte", "Virac" to "catanduanes", "Baras" to "catanduanes",
    "Ticao" to "masbate", "Burias" to "masbate"
)

fun provinceIdOf(location: String): String? {
    for ((name, id) in provinceIdByName) if (location.contains(name, ignoreCase = true)) return id
    for ((city, id) in cityToProvince) if (location.contains(city, ignoreCase = true)) return id
    return null
}

fun isVisitable(place: PlaceDetail): Boolean =
    destinationClassifications.any { it.title == place.category } ||
        festivalClassifications.any { it.title == place.category } ||
        activityClassifications.any { it.title == place.category } ||
        place.category == "Restaurants"

private fun kindOf(place: PlaceDetail): String = when {
    place.category == "Restaurants" -> "Restaurant"
    festivalClassifications.any { it.title == place.category } -> "Festival"
    activityClassifications.any { it.title == place.category } -> "Activity"
    else -> "Destination"
}

fun allTrackItems(): List<TrackItem> {
    val out = mutableListOf<TrackItem>()
    provinces.forEach { out.add(TrackItem(it.id, it.name, "Province", it.id, "province_detail/${it.id}")) }
    (destinationsData.values.flatten() + festivalsData.values.flatten() + activitiesData.values.flatten() + cuisinesData["restaurants"].orEmpty())
        .forEach { out.add(TrackItem(it.id, it.name, kindOf(it), provinceIdOf(it.location), "place_detail/${it.id}")) }
    stayData.values.flatten().forEach {
        out.add(TrackItem(it.id, it.name, "Stay", provinceIdOf(it.province), "stay_detail/${it.id}"))
    }
    return out
}
