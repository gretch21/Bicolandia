package com.bicolandia.app.data

data class Accommodation(
  val id: String,
  val name: String,
  val type: String,      // "Hotel", "Resort", "Homestay", "Inn", "Beach Stay", "Mountain Stay"
  val location: String,
  val province: String,
  val shortDescription: String,     // shown on the list card
  val nearbyAttractions: List<String> = emptyList(),
  val nearbyActivities: List<String> = emptyList(),
  val howToGetThere: String,       // one-line summary of the route
  val travelConsiderations: List<String>,
  val prepInfo: List<String>,
  val colorHex: String,
  val imageId: String,

  val overview: String = "",       //  About text
  val facts: List<QuickFact> = emptyList(),   // at-a-glance rows
  val highlights: List<String> = emptyList(),  // facilities / what stands out
  val areaNote: String = "",       // the surrounding area
  val routeSteps: List<String> = emptyList(),  // step-by-step route
  val bestTime: String = ""       // best time to visit
)

val stayClassifications = listOf(
  SimpleItem("hotels", "Hotels", "City & town-center stays", colorHex = "#455A64"),
  SimpleItem("resorts", "Resorts", "Beachfront & leisure resorts", colorHex = "#00838F"),
  SimpleItem("homestays", "Homestays", "Local family-run stays", colorHex = "#6D4C41"),
  SimpleItem("inns", "Inns", "Budget-friendly lodging", colorHex = "#8D6E63"),
  SimpleItem("beach", "Beach Accommodations", "Steps from the shore", colorHex = "#0288D1"),
  SimpleItem("mountain", "Mountain Accommodations", "Cool-climate retreats", colorHex = "#2E7D32")
)

val stayData: Map<String, List<Accommodation>> = mapOf(
  "hotels" to hotelStays,
  "resorts" to resortStays,
  "homestays" to homestayStays,
  "inns" to innStays,
  "beach" to beachStays,
  "mountain" to mountainStays
)

fun findAccommodationById(id: String): Accommodation? = stayData.values.flatten().find { it.id == id }
