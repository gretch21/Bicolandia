package com.bicolandia.app.data

data class ProvinceAttraction(
  val name: String,
  val location: String,
  val category: String,
  val imageId: String?,
  val blurb: String
)

data class ProvinceActivity(val name: String, val desc: String)
data class ProvinceFestival(val name: String, val desc: String, val imageId: String?)
data class ProvinceFood(val name: String, val imageId: String?)
data class ProvinceStep(val title: String, val detail: String)


private val extraAttractions: Map<String, List<ProvinceAttraction>> = mapOf(
  "albay" to listOf(
    // stand-in photo: Mayon (the viewpoint's main subject)
    ProvinceAttraction("Lignon Hill", "Legazpi City, Albay", "Parks", "volcano_mayon", "A hilltop nature park with a lookout over Legazpi and a front-row view of Mayon.")
  ),
  "camsur" to listOf(
    // stand-in photo: island_0
    ProvinceAttraction("CamSur Watersports Complex", "Pili, Camarines Sur", "Parks", "island_0", "Bicol's wakeboarding and cable-ski hub in Pili.")
  ),
  "sorsogon" to listOf(
    ProvinceAttraction("Donsol Whale Shark Interaction", "Donsol, Sorsogon", "Islands", "act_whale_0", "The registration and briefing point for swimming with butanding in Donsol.")
  ),
  "catanduanes" to listOf(
    // stand-in photos: beach_4 and act_surf_1 (Puraran)
    ProvinceAttraction("Twin Rock Beach", "Virac, Catanduanes", "Beaches", "beach_4", "A pair of sea-carved rock formations just off a quiet shoreline near Virac."),
    ProvinceAttraction("Binurong Point", "Baras, Catanduanes", "Beaches", "act_surf_1", "A rugged cliff-top viewpoint facing the open Pacific.")
  ),
  "camnorte" to listOf(
    // stand-in photo: falls_0
    ProvinceAttraction("Mananap Falls", "Camarines Norte", "Waterfalls", "falls_0", "A cascading falls and cool swimming pool reached by a short trek.")
  ),
  "masbate" to listOf(
    ProvinceAttraction("Manta Bowl", "Ticao, Masbate", "Islands", "act_dive_0", "One of the world's best manta ray dive sites, off Ticao Island."),
    // stand-in photo: island_1 (Ticao)
    ProvinceAttraction("Buntod Sandbar", "Masbate", "Islands", "island_1", "A sandbar that appears at low tide, popular for swimming and photos.")
  )
)

private val destinationOrder = destinationClassifications.map { it.title }

fun provinceAttractions(province: Province): List<ProvinceAttraction> {
  val fromData = destinationsData.values.flatten()
    .filter { provinceIdOf(it.location) == province.id }
    .map { ProvinceAttraction(it.name, it.location, it.category, it.id, it.shortDescription) }
  val extras = extraAttractions[province.id].orEmpty()
  return (fromData + extras)
    .distinctBy { it.name.lowercase() }
    .sortedBy { a -> destinationOrder.indexOf(a.category).let { if (it < 0) 99 else it } }
}

// ---------- MUST-DO ACTIVITIES (complete list) ----------
private val mustDoActivities: Map<String, List<ProvinceActivity>> = mapOf(
  "albay" to listOf(
    ProvinceActivity("ATV Lava Trail Adventure", "Ride the Cagsawa, Black Lava, Green Lava, Forest and River Bed trails around Mayon"),
    ProvinceActivity("Hiking Mayon", "A guided climb up the slopes of the perfect cone"),
    ProvinceActivity("Mount Malinao Trail", "A lesser-known climb with panoramic Bicol views"),
    ProvinceActivity("Heritage Sightseeing", "Cagsawa Ruins, Daraga Church and Camalig Church in one day"),
    ProvinceActivity("Quitinday Hills Walk", "Stroll the rolling green mounds often called Bicol's 'Chocolate Hills'"),
    ProvinceActivity("Cagraray Island Tour", "A relaxed island tour near Misibis Bay"),
    ProvinceActivity("Misibis Bay Reef Dive", "An easy reef dive near the Misibis Bay resort"),
    ProvinceActivity("Mananao Cove Surfing", "A quiet cove break on Rapu-Rapu Island"),
    ProvinceActivity("Museum Hopping", "Museo de Legazpi and the Cagsawa Ruins Museum tell the story of Mayon")
  ),
  "camsur" to listOf(
    ProvinceActivity("Wakeboarding", "Cable-ski and wakeboard at the CamSur Watersports Complex"),
    ProvinceActivity("Island Hopping", "A full-day tour of Caramoan's lagoons and limestone islets"),
    ProvinceActivity("Hiking Isarog", "A rainforest trek past waterfalls to the summit"),
    ProvinceActivity("Mount Iriga Trek", "A day-hike with views over Lake Buhi"),
    ProvinceActivity("Waterfall Trekking", "Malabsay and Nabuntolan Falls inside Mount Isarog Natural Park"),
    ProvinceActivity("Panicuason Hot Springs Soak", "Relax in natural hot spring pools at the foot of Isarog"),
    ProvinceActivity("San Miguel Bay Islands", "Quiet fishing islands off the coast"),
    ProvinceActivity("Matukad Island Snorkeling", "Shallow reef snorkeling by a lagoon in Caramoan"),
    ProvinceActivity("Naga Church & Museum Walk", "Peñafrancia Basilica, Naga Cathedral and Naga City Museum")
  ),
  "sorsogon" to listOf(
    ProvinceActivity("Whale Shark Swimming", "Swim alongside butanding in Donsol, roughly November to May"),
    ProvinceActivity("Firefly Watching", "Evening river tours in Donsol"),
    ProvinceActivity("Hiking Bulusan", "A trail through the natural park to the crater rim"),
    ProvinceActivity("Bulusan Lake Trek", "Walk the trail around Bulusan Lake"),
    ProvinceActivity("Surfing", "Lebanon Beach in Bacon and Buenavista Beach in Gubat"),
    ProvinceActivity("Beach Day at Rizal & Subic", "Calm, clear water and a lively sunset boardwalk"),
    ProvinceActivity("Paguriran Island Visit", "Rock formations and tide pools"),
    ProvinceActivity("Heritage Walk", "Casa Real, Bacon Church and the Sorsogon Provincial Museum")
  ),
  "catanduanes" to listOf(
    ProvinceActivity("Surfing", "Ride the Majestic Puraran wave, a world-class reef break"),
    ProvinceActivity("Waterfall Trekking", "Swim at Maribina Falls"),
    ProvinceActivity("Snorkeling", "Clear-water snorkeling around Palambanes Island"),
    ProvinceActivity("Cliff & Coast Viewpoints", "Twin Rock Beach and Binurong Point face the open Pacific"),
    ProvinceActivity("Agojo Reef Visit", "A remote reef with seasonal whale shark sightings"),
    ProvinceActivity("Abaca Craft Visit", "See how the island's abaca fiber is turned into crafts"),
    ProvinceActivity("Catanduanes Heritage Museum", "Abaca trade history and island heritage in Virac")
  ),
  "camnorte" to listOf(
    ProvinceActivity("Surfing", "Beginner-friendly waves at Bagasbas Beach"),
    ProvinceActivity("Island Hopping", "Boat-hop between Calaguas' white-sand islets"),
    ProvinceActivity("Waterfall Trekking", "Colasi Falls and Mananap Falls"),
    ProvinceActivity("Sunset at Bagasbas", "A long gray-sand beach popular with sunset watchers"),
    ProvinceActivity("Daet Heritage Stop", "Visit Daet Freedom Park, site of the first Rizal monument"),
    ProvinceActivity("Calaguas Camping", "Overnight on powdery white sand if the tide and weather allow")
  ),
  "masbate" to listOf(
    ProvinceActivity("Manta Ray Diving", "Dive Manta Bowl off Ticao Island, best roughly March to June"),
    ProvinceActivity("Island Hopping", "Connect Ticao and Burias in a multi-island tour"),
    ProvinceActivity("Rodeo Watching", "Bull riding, calf roping and horse racing during the April rodeo"),
    ProvinceActivity("Whale Shark Encounter", "A quieter alternative to Donsol's peak crowds"),
    ProvinceActivity("Buntod Sandbar Swim", "Swim and take photos on the low-tide sandbar"),
    ProvinceActivity("Ticao Heritage Houses", "Ancestral houses reflecting old trade-route wealth")
  )
)

fun provinceActivities(province: Province): List<ProvinceActivity> =
  mustDoActivities[province.id] ?: province.activities.map { ProvinceActivity(it, "A signature Bicol experience") }

private val foodImageByName: Map<String, String> = mapOf(
  "bicol express" to "food_bicolexpress",
  "laing" to "food_laing",
  "pinangat" to "food_pinangat",
  "kinunot" to "food_kinunot",
  "kinalas" to "food_kinalas",
  "pili nut delicacies" to "food_delicacy_0",
  "pili nut tart" to "food_delicacy_0",
  "sili ice cream" to "food_delicacy_1",
  "cassava cake" to "food_delicacy_2",
  "mazapan de pili" to "food_delicacy_3",
  "suman sa ibus" to "food_delicacy_4",
  "sinantol" to "food_laing",
  "gulay na natong" to "food_laing",
  "balaw-balaw" to "food_kinunot",
  "fresh seafood" to "food_kinunot",
  "beef dishes" to "food_bicolexpress"
)

private val extraFlavors: Map<String, List<String>> = mapOf(
  "albay" to listOf("Laing", "Cassava Cake", "Suman sa Ibus"),
  "camsur" to listOf("Kinalas", "Mazapan de Pili", "Pili Nut Tart"),
  "sorsogon" to listOf("Laing", "Suman sa Ibus"),
  "catanduanes" to listOf("Laing", "Suman sa Ibus"),
  "camnorte" to listOf("Laing", "Suman sa Ibus"),
  "masbate" to listOf("Suman sa Ibus")
)

fun provinceFoods(province: Province): List<ProvinceFood> =
  (province.localCuisines + extraFlavors[province.id].orEmpty())
    .distinctBy { it.lowercase() }
    .map { ProvinceFood(it, foodImageByName[it.lowercase()]) }

// ---------- FESTIVALS (horizontal banner row) ----------
fun provinceFestivals(province: Province): List<ProvinceFestival> {
  val fromData = festivalsData.values.flatten()
    .filter { provinceIdOf(it.location) == province.id }
    .map { ProvinceFestival(it.name, it.shortDescription, it.id) }
  val fromProvince = province.festivals.map { name ->
    ProvinceFestival(name, "Celebrating the beauty and heritage of ${province.name}.", null)
  }
  // Data-backed entries (with photo + description) first, then the province's own names not already covered
  val out = fromData.toMutableList()
  fromProvince.forEach { f ->
    val covered = out.any { it.name.equals(f.name, true) || it.name.contains(f.name, true) || f.name.contains(it.name, true) }
    if (!covered) out.add(f)
  }
  // keep the province's headline festival first, as before
  val first = province.festivals.firstOrNull()
  return out.sortedBy { f -> if (first != null && (f.name.contains(first, true) || first.contains(f.name, true))) 0 else 1 }
}

// ---------- BEST DINING (round layout) ----------
data class ProvinceDining(val name: String, val imageId: String?)

fun provinceDining(province: Province): List<ProvinceDining> {
  val fromData = restaurants.filter { provinceIdOf(it.location) == province.id }.map { ProvinceDining(it.name, it.id) }
  val named = province.bestRestaurants.map { ProvinceDining(it, null) }
  return (fromData + named).distinctBy { it.name.lowercase() }
}

// ---------- GETTING AROUND - step by step (from Manila) ----------
// Hub facts match TravelGuideData.kt (airports, bus durations, ferry routes);
// last-mile detail stays general on purpose - no invented fares or schedules.
private val gettingThereSteps: Map<String, List<ProvinceStep>> = mapOf(
  "albay" to listOf(
    ProvinceStep("Choose how to leave Manila", "By air: fly Manila to Bicol International Airport (DRP) in Daraga, about 1 hr. By land: take a provincial bus from Cubao, Pasay or Alabang to Legazpi City along the Maharlika Highway, about 9-10 hrs."),
    ProvinceStep("Arrive in Legazpi City", "Legazpi is the province's hub. From the airport or bus terminal, a taxi, van or tricycle takes you to your hotel."),
    ProvinceStep("Move around town", "Tricycles and habal-habal (motorbike taxis) handle short trips around Legazpi and Daraga."),
    ProvinceStep("Go to the sights", "Jeepneys and vans connect the towns along the Maharlika Highway. A rented van or private car is the easiest way to combine Cagsawa Ruins, Daraga Church and Quitinday Hills in one day."),
    ProvinceStep("Book Mayon activities ahead", "Reserve guides and ATV or 4x4 rides a day before, especially December to May, and check the PHIVOLCS alert level before any activity near the volcano."),
    ProvinceStep("Heading on?", "Buses and vans continue to Sorsogon. For Catanduanes, take a bus to Tabaco and board the RORO ferry.")
  ),
  "camsur" to listOf(
    ProvinceStep("Choose how to leave Manila", "By air: fly Manila to Naga Airport (WNP) in Pili, about 1 hr. By land: direct buses from Manila to Naga City run daily along the Maharlika Highway, about 8-9 hrs."),
    ProvinceStep("Arrive in Naga City", "Naga is the hub. From the airport or bus terminal, take a taxi, van or tricycle to your stay."),
    ProvinceStep("Move around Naga", "Jeepneys and tricycles cover Naga City itself, including the Peñafrancia Basilica and the cathedral."),
    ProvinceStep("Reach Pili, CWC and other towns", "V-hire vans along the Maharlika Highway are the fastest way to reach Pili, the CamSur Watersports Complex and nearby towns."),
    ProvinceStep("Going to Caramoan", "Take a van from Naga to Sabang Port in San Jose, then an outrigger boat to Guijalo Port. Catch a morning boat; the last public trip is usually around 11 AM."),
    ProvinceStep("Mount Isarog side trips", "Malabsay Falls and Panicuason Hot Springs are a short ride from Naga by tricycle or habal-habal.")
  ),
  "sorsogon" to listOf(
    ProvinceStep("Choose how to leave Manila", "By land: buses run about 10-11 hrs and continue past Legazpi to Sorsogon City. By air: Sorsogon has no airport, so fly to Legazpi (DRP) and continue by bus or van, about 1.5-2 hrs."),
    ProvinceStep("Arrive in Sorsogon City", "Sorsogon City is the hub. Tricycles cover trips within the city."),
    ProvinceStep("Go to Donsol", "Take a jeepney or van from Sorsogon City to Donsol, then a tricycle to the Whale Shark Interaction Center."),
    ProvinceStep("Register for the whale sharks", "Register at the Donsol Interaction Center in the morning for the best odds. Season runs roughly November to May."),
    ProvinceStep("Reach Bulusan, Gubat and Irosin", "Jeepneys link Sorsogon City to these towns; tricycles cover the last stretch."),
    ProvinceStep("Heading on?", "Buses and vans continue south to the Matnog ferry terminal for Samar.")
  ),
  "catanduanes" to listOf(
    ProvinceStep("Choose how to leave Manila", "By air: fly Manila to Virac Airport (VRC), about 1-1.5 hrs, sometimes via Legazpi. By land and sea: take a bus to Tabaco, Albay, then a RORO ferry (about 2 hrs) to Virac or San Andres; about 12-14 hrs in total."),
    ProvinceStep("Check the weather first", "Flights and RORO ferries are weather-dependent, especially July to November, so keep your dates flexible."),
    ProvinceStep("Arrive in Virac", "Virac is the hub. Tricycles are the main way around town."),
    ProvinceStep("Go to Baras and Puraran", "Take a jeepney or van along the island's main road to Baras, then a tricycle to Puraran Beach. A private or rented vehicle covers more ground in a day."),
    ProvinceStep("Bring cash", "ATMs are limited once you leave Virac.")
  ),
  "camnorte" to listOf(
    ProvinceStep("Choose how to leave Manila", "By land: buses run daily, about 7-8 hrs to Daet. The province has no commercial airport, so by air you fly to Naga or Legazpi and take a bus or van, about 2-3 hrs."),
    ProvinceStep("Arrive in Daet", "Daet is the hub. Tricycles handle trips within Daet and out to Bagasbas Beach."),
    ProvinceStep("Surf at Bagasbas", "Book a lesson through a local surf club if you are a first-timer."),
    ProvinceStep("Going to Calaguas", "Take a van or bus to a coastal jump-off town (commonly Vinzons, Mercedes or Paracale), then a boat across to the islands."),
    ProvinceStep("Plan around the tide", "Make Calaguas a full day or an overnight, since the crossing depends on the tide and weather.")
  ),
  "masbate" to listOf(
    ProvinceStep("Choose how to leave Manila", "By air: fly to Masbate Airport (MBT), about 1-1.5 hrs, often via Manila or Cebu; flights are limited, so check schedules early. By land and sea: take a bus or van to Pilar, Sorsogon, then a RORO ferry (about 2-2.5 hrs) to Masbate City."),
    ProvinceStep("Arrive in Masbate City", "Masbate City is the hub. Tricycles handle in-town trips."),
    ProvinceStep("Going to Ticao or Burias", "Take a boat from Masbate City, or a RORO ferry from Pilar, Sorsogon. Island transport is limited, so arrange trips a day ahead."),
    ProvinceStep("Diving Manta Bowl", "Manta ray sightings are best roughly March to June."),
    ProvinceStep("Carry cash", "Card payments are far less common outside Masbate City.")
  )
)

fun provinceGettingThereSteps(province: Province): List<ProvinceStep> =
  gettingThereSteps[province.id] ?: emptyList()
