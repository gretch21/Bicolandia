package com.bicolandia.app.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirportShuttle
import androidx.compose.material.icons.filled.DirectionsBoat
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.ui.graphics.vector.ImageVector
private val IconFlight = Icons.Filled.Flight
private val IconBus = Icons.Filled.DirectionsBus
private val IconVan = Icons.Filled.AirportShuttle
private val IconTricycle = Icons.Filled.TwoWheeler
private val IconBoat = Icons.Filled.DirectionsBoat

data class TravelLeg(val icon: ImageVector, val mode: String, val detail: String, val duration: String)
data class TravelGuide(
  val placeName: String,
  val province: String,
  val category: String,
  val fromManila: List<TravelLeg>,
  val fromHub: List<TravelLeg>,
  val localTransport: List<TravelLeg>,
  val totalTime: String,
  val beforeYouGo: List<String>,
  val bestTimeToGo: String,
  val travelTips: List<String>,
  val goodToKnow: List<String>
)

private data class HubInfo(
  val hubCity: String,
  val airportName: String?,
  val flightTime: String,
  val busTimeFromManila: String,
  val busNote: String
)

// Real, verifiable hub facts only - no invented schedules/fares.
private val provinceHubs = mapOf(
  "Albay" to HubInfo("Legazpi City", "Bicol International Airport (DRP), Daraga", "~1 hr", "~9-10 hrs", "Direct provincial buses run daily from Manila (Cubao/Pasay/Alabang) to Legazpi via the Maharlika Highway."),
  "Camarines Sur" to HubInfo("Naga City", "Naga Airport (WNP), Pili", "~1 hr", "~8-9 hrs", "Direct buses from Manila to Naga City run daily along the Maharlika Highway."),
  "Camarines Norte" to HubInfo("Daet", null, "Fly to Naga or Legazpi, then bus/van (~2-3 hrs)", "~7-8 hrs", "Buses from Manila to Daet run daily; there's no scheduled commercial airport in the province."),
  "Sorsogon" to HubInfo("Sorsogon City", null, "Fly to Legazpi (DRP), then bus/van (~1.5-2 hrs)", "~10-11 hrs", "Buses continue past Legazpi to Sorsogon City and on to the Matnog ferry port for Samar."),
  "Catanduanes" to HubInfo("Virac", "Virac Airport (VRC)", "~1-1.5 hrs (often via Legazpi)", "Bus + ferry, ~12-14 hrs total", "Alternatively, take a bus to Tabaco, Albay, then a RORO ferry (~2 hrs) to Virac or San Andres."),
  "Masbate" to HubInfo("Masbate City", "Masbate Airport (MBT)", "~1-1.5 hrs (often via Manila or Cebu)", "Bus + ferry, ~12+ hrs total", "Alternatively, take a bus/van to Pilar, Sorsogon, then a RORO ferry (~2-2.5 hrs) to Masbate.")
)

private val knownProvinces = listOf("Camarines Norte", "Camarines Sur", "Albay", "Sorsogon", "Catanduanes", "Masbate")

fun parseProvinceFromLocation(location: String): String {
  for (p in knownProvinces) {
    if (location.contains(p, ignoreCase = true)) return p
  }
  // fall back: first comma segment often names a town whose province we can't
  // infer further - default to Albay (regional center) so the guide never breaks
  return "Albay"
}

// Category buckets drive local-transport + prep-tip wording. Falls back to a
// sensible generic sightseeing profile for anything unmatched.
private enum class GuideCategory { VOLCANO_HIKE, BEACH_ISLAND, WATERFALL_PARK, HISTORICAL, CHURCH, MUSEUM, FESTIVAL, ATV, SURF_DIVE, WHALE_SHARK, CUISINE, CULTURE, PROVINCE, GENERIC }

private fun classify(category: String): GuideCategory {
  val c = category.lowercase()
  return when {
    c.contains("volcano") || c.contains("hiking") -> GuideCategory.VOLCANO_HIKE
    c.contains("beach") || c.contains("island") -> GuideCategory.BEACH_ISLAND
    c.contains("waterfall") || c.contains("park") -> GuideCategory.WATERFALL_PARK
    c.contains("historical") -> GuideCategory.HISTORICAL
    c.contains("church") -> GuideCategory.CHURCH
    c.contains("museum") -> GuideCategory.MUSEUM
    c.contains("festival") -> GuideCategory.FESTIVAL
    c.contains("atv") -> GuideCategory.ATV
    c.contains("surf") || c.contains("div") -> GuideCategory.SURF_DIVE
    c.contains("whale") -> GuideCategory.WHALE_SHARK
    c.contains("cuisine") || c.contains("dish") || c.contains("delicac") || c.contains("restaurant") -> GuideCategory.CUISINE
    c.contains("craft") || c.contains("folklore") || c.contains("figure") || c.contains("tradition") || c.contains("indigenous") || c.contains("culture") -> GuideCategory.CULTURE
    c.contains("province") -> GuideCategory.PROVINCE
    else -> GuideCategory.GENERIC
  }
}

fun buildTravelGuide(placeName: String, location: String, category: String): TravelGuide {
  val province = parseProvinceFromLocation(location)
  val hub = provinceHubs[province] ?: provinceHubs.getValue("Albay")
  val kind = classify(category)

  val fromManila = mutableListOf<TravelLeg>()
  if (hub.airportName != null) {
    fromManila.add(TravelLeg(IconFlight, "By Air", "Fly Manila → ${hub.airportName}.", hub.flightTime))
  } else {
    fromManila.add(TravelLeg(IconFlight, "By Air (indirect)", hub.flightTime, "varies"))
  }
  fromManila.add(TravelLeg(IconBus, "By Bus", hub.busNote, hub.busTimeFromManila))

  val fromHub = mutableListOf<TravelLeg>()
  fromHub.add(TravelLeg(IconVan, "Van/Bus to nearest town", "From ${hub.hubCity}, take a provincial van or bus toward $placeName's area.", "~30 min - 2 hrs, varies by distance"))

  val localTransport = mutableListOf<TravelLeg>()
  val beforeYouGo = mutableListOf<String>()
  val goodToKnow = mutableListOf<String>()
  val travelTips = mutableListOf<String>()
  var bestTimeToGo: String
  var totalTime: String

  when (kind) {
    GuideCategory.VOLCANO_HIKE -> {
      localTransport.add(TravelLeg(IconTricycle, "Habal-habal / 4x4", "Local habal-habal or a chartered 4x4 covers the rough road to the jump-off point.", "~20-45 min"))
      beforeYouGo.addAll(listOf("Wear proper hiking shoes and moisture-wicking clothing.", "Bring enough drinking water and trail snacks.", "Book a local guide - required on most Bicol volcano trails.", "Charge your phone/power bank; signal is limited on the trail."))
      bestTimeToGo = "Best during dry season (Dec-May) and clear weather - check the latest PHIVOLCS advisory before any Mayon-area trek."
      travelTips.addAll(listOf("Start early to avoid afternoon rain and heat.", "Confirm guide availability a day ahead.", "Check the volcano's current alert level before heading out."))
      goodToKnow.add("Mobile signal can be weak or absent on the trail. Trekking access may be restricted during heightened volcanic alert levels.")
      totalTime = "Half-day to full-day trip from the hub city, depending on the trail."
    }
    GuideCategory.BEACH_ISLAND -> {
      localTransport.add(TravelLeg(IconBoat, "Boat / Banca", "A local outrigger boat (banca) from the nearest port or beach launch takes you to the island or cove.", "~15 min - 1.5 hrs, depending on distance"))
      beforeYouGo.addAll(listOf("Bring cash - boat fees are usually paid on-site.", "Pack sun protection (sunscreen, hat, sunglasses).", "Use a waterproof or dry bag for electronics.", "Confirm boat departure times with the boatmen's association if visiting a remote island."))
      bestTimeToGo = "Best in dry season (Dec-May) with calm seas. Boat trips may be cancelled in rough weather."
      travelTips.addAll(listOf("Check sea/weather conditions before booking a boat.", "Ask about the last return trip so you don't get stranded.", "Keep small bills for boatmen and beach fees."))
      goodToKnow.add("Boat trips depend on weather and sea conditions and may be suspended during storms. Some islands have limited facilities - bring your own water and snacks.")
      totalTime = "Half-day to full-day trip, plus boat time."
    }
    GuideCategory.WATERFALL_PARK -> {
      localTransport.add(TravelLeg(IconTricycle, "Tricycle / Habal-habal", "From the nearest town center, a tricycle or habal-habal takes you to the trailhead or entrance.", "~15-40 min"))
      beforeYouGo.addAll(listOf("Wear outdoor footwear with good grip - trails can be muddy or slippery.", "Bring a change of clothes if swimming.", "Bring drinking water and insect repellent."))
      bestTimeToGo = "Best during dry season for safer trails and clearer water. Avoid right after heavy rain."
      travelTips.addAll(listOf("Ask locals about current trail conditions before heading in.", "Leave early to have more daylight for the trek back.", "Keep valuables in a secured, water-resistant bag."))
      goodToKnow.add("Trails may close temporarily after heavy rain due to flash flood risk. Entrance fees are typically paid locally, not in advance.")
      totalTime = "Half-day trip from the hub city."
    }
    GuideCategory.HISTORICAL -> {
      localTransport.add(TravelLeg(IconTricycle, "Tricycle / Jeepney", "Tricycles and jeepneys serve most historical sites right from town or city centers.", "~10-25 min"))
      beforeYouGo.addAll(listOf("Wear comfortable walking shoes.", "Bring sun protection for outdoor ruins/sites.", "Bring small bills for entrance fees or local guides."))
      bestTimeToGo = "Best in the morning or late afternoon to avoid midday heat. Fine to visit year-round."
      travelTips.addAll(listOf("Hire a local guide on-site for historical context.", "Combine nearby sites in one trip to save on transport.", "Check opening hours before heading out."))
      goodToKnow.add("Some sites have limited signage - a local guide adds a lot of context. Facilities may be basic at smaller sites.")
      totalTime = "1-3 hours including travel."
    }
    GuideCategory.CHURCH -> {
      localTransport.add(TravelLeg(IconTricycle, "Tricycle / Jeepney", "Most churches sit in town centers, reachable by tricycle or jeepney from the terminal.", "~10-20 min"))
      beforeYouGo.addAll(listOf("Dress modestly - shoulders and knees covered is customary.", "Check for ongoing mass schedules if visiting during service hours.", "Bring small bills for candles/donations."))
      bestTimeToGo = "Any time of year; avoid visiting during private ceremonies unless invited."
      travelTips.addAll(listOf("Visit early morning for a quieter experience.", "Ask permission before taking photos during services.", "Combine with nearby historical sites in the same town."))
      goodToKnow.add("Some churches restrict photography or entry during religious services.")
      totalTime = "30 min - 1 hour including travel."
    }
    GuideCategory.MUSEUM -> {
      localTransport.add(TravelLeg(IconTricycle, "Tricycle / Jeepney", "Museums are typically in town/city centers, a short tricycle or jeepney ride from the terminal.", "~10-20 min"))
      beforeYouGo.addAll(listOf("Check the museum's operating days/hours before heading out - some close on Mondays or holidays.", "Bring a valid ID; some museums log visitors.", "Bring small bills for entrance fees."))
      bestTimeToGo = "Any time of year - an easy option if the weather isn't good for outdoor activities."
      travelTips.addAll(listOf("Call ahead to confirm hours, especially on weekends and holidays.", "Combine with nearby historical sites or churches in the same trip."))
      goodToKnow.add("Some smaller/local museums have limited hours or require advance coordination for group visits.")
      totalTime = "1-2 hours including travel."
    }
    GuideCategory.FESTIVAL -> {
      localTransport.add(TravelLeg(IconTricycle, "Tricycle / Jeepney", "Festival venues are usually walkable from the town/city center once you arrive; tricycles cover the rest.", "~10-30 min"))
      beforeYouGo.addAll(listOf("Book accommodations early - festival dates get crowded fast.", "Keep valuables secure in crowds.", "Bring cash - vendors and small transport often don't accept cards.", "Check the exact festival schedule, as some events move by year."))
      bestTimeToGo = "Time your trip to the festival's specific month - check the current year's official schedule before booking."
      travelTips.addAll(listOf("Arrive a day early to beat traffic and secure lodging.", "Expect heavier-than-usual foot traffic and transport demand.", "Agree on transport fares beforehand during peak crowds."))
      goodToKnow.add("Transport and lodging prices commonly rise during festival dates. Some roads may be closed for parades/processions.")
      totalTime = "Plan for a full day at minimum, more if attending multiple events."
    }
    GuideCategory.ATV -> {
      localTransport.add(TravelLeg(IconTricycle, "Habal-habal / Shuttle", "Most ATV operators are a short ride from the town center; some offer pickup coordination.", "~15-30 min"))
      beforeYouGo.addAll(listOf("Wear clothes and shoes you don't mind getting dirty.", "Bring a change of clothes.", "Secure loose belongings - the terrain gets bumpy.", "Book your ATV slot ahead, especially on weekends."))
      bestTimeToGo = "Best in dry season; trails get muddier and more slippery in the rain."
      travelTips.addAll(listOf("Book in advance during weekends and holidays.", "Confirm what's included (guide, gear, fuel) before paying.", "Ask about the return time so you can plan the rest of your day."))
      goodToKnow.add("Trail conditions and difficulty can change after rain. Most operators require a signed waiver.")
      totalTime = "1-3 hours including the ride itself."
    }
    GuideCategory.SURF_DIVE -> {
      localTransport.add(TravelLeg(IconBoat, "Boat / Local Transfer", "Dive/surf spots are usually reached by boat from the nearest resort area or port.", "~15 min - 1 hr"))
      beforeYouGo.addAll(listOf("Bring or rent appropriate gear in advance.", "Confirm certification requirements if diving.", "Check surf/dive conditions and tide schedules beforehand.", "Bring reef-safe sunscreen."))
      bestTimeToGo = "Varies by spot and swell/season - ask local operators for current conditions before booking."
      travelTips.addAll(listOf("Book with a registered local operator or dive shop.", "Check weather and sea conditions the morning of.", "Confirm equipment rental availability ahead of time."))
      goodToKnow.add("Conditions can change quickly with weather - always confirm with a local operator before heading out.")
      totalTime = "Half-day to full-day trip depending on the spot."
    }
    GuideCategory.WHALE_SHARK -> {
      localTransport.add(TravelLeg(IconBoat, "Boat (Butanding Interaction Center)", "Registered boats depart from the local interaction center, mainly in Donsol, Sorsogon.", "~15-30 min out to the viewing area"))
      beforeYouGo.addAll(listOf("Book through the official Butanding Interaction Office/registered operator only.", "Bring swimwear, a rash guard, and reef-safe sunscreen.", "Basic swimming ability is recommended.", "Arrive early - trips are weather- and sighting-dependent."))
      bestTimeToGo = "Whale shark season generally runs November-June, peaking around Feb-May. Confirm current-season sightings with the operator."
      travelTips.addAll(listOf("Book directly with accredited operators to support responsible tourism.", "Sightings aren't guaranteed - ask about the operator's policy for no-sighting trips.", "Follow the interaction guidelines briefed before the trip."))
      goodToKnow.add("This is a seasonal, weather-dependent activity - trips can be rescheduled if conditions aren't right.")
      totalTime = "Half-day trip including briefing and boat time."
    }
    GuideCategory.CUISINE -> {
      val isRestaurant = category.equals("Restaurants", ignoreCase = true)
      localTransport.add(TravelLeg(IconTricycle, "Tricycle / Jeepney", "Most well-known eateries are within town/city centers, reachable by tricycle or jeepney.", "~10-20 min"))
      beforeYouGo.addAll(
        if (isRestaurant) listOf(
          "Bring cash - many local favorites, especially heritage houses and food parks, are cash-only.",
          "Call ahead for groups of 6+ or if you're set on a specific specialty dish (some sell out early).",
          "Casual wear is fine almost everywhere in Bicol - dress codes are rare outside fine dining."
        ) else listOf(
          "Bring cash - smaller local eateries may not accept cards.",
          "Ask locally for the current best-reviewed spot, as popularity shifts."
        )
      )
      bestTimeToGo = if (isRestaurant) "Lunch (11:30am-1pm) or dinner (6-8pm) for the full menu - some specialty items sell out by mid-afternoon." else "Any time of year - a good option to pair with any other stop on your trip."
      travelTips.addAll(
        if (isRestaurant) listOf(
          "Order the house specialty first - it's usually what the place built its name on.",
          "Pair a meal here with a nearby attraction so the trip isn't just about the food.",
          "Weekend lunch crowds can mean a short wait at the busier spots - walk-ins are still welcome."
        ) else listOf(
          "Ask locals or your accommodation for current recommendations.",
          "Pair a food stop with a nearby attraction to save on transport."
        )
      )
      goodToKnow.add(if (isRestaurant) "Hours and menus can shift seasonally - it doesn't hurt to check their Facebook page the same day." else "Availability of specific dishes can vary by season and by vendor.")
      totalTime = "30 min - 1 hour including travel."
    }
    GuideCategory.CULTURE -> {
      localTransport.add(TravelLeg(IconTricycle, "Tricycle / Jeepney", "Cultural sites, workshops, and heritage spots are usually reachable by tricycle or jeepney from town centers.", "~10-25 min"))
      beforeYouGo.addAll(listOf("Bring cash for entrance fees, craft purchases, or local guides.", "Dress respectfully when visiting religious or heritage sites.", "Ask about visiting hours in advance - some are family-run and informal."))
      bestTimeToGo = "Any time of year, though festival-linked traditions are best experienced during their actual celebration dates."
      travelTips.addAll(listOf("Ask a local guide for context - much of Bicolano heritage is oral tradition.", "Support artisans directly when buying traditional crafts.", "Combine with a nearby historical site or museum."))
      goodToKnow.add("Some heritage practices/sites are informal (family workshops, community-led) rather than fixed tourist attractions - confirm access ahead of time where possible.")
      totalTime = "1-2 hours including travel."
    }
    GuideCategory.PROVINCE -> {
      localTransport.add(TravelLeg(IconVan, "Provincial Van/Bus + Tricycle", "From ${hub.hubCity}, provincial vans/buses reach the towns, with tricycles or habal-habal for the last stretch.", "Varies by specific destination within the province"))
      beforeYouGo.addAll(listOf("Plan your specific stops first - this province has multiple attractions across different towns.", "Bring cash for local transport and fees.", "Check the weather for your travel dates."))
      bestTimeToGo = "Generally best in the dry season (Dec-May) for outdoor activities; check specific attractions for their own best times."
      travelTips.addAll(listOf("Base yourself in ${hub.hubCity} and take day trips to nearby attractions.", "Group nearby destinations together to save on transport.", "Ask your accommodation for current local transport options."))
      goodToKnow.add("This is a province-wide overview - open a specific Destination, Activity, or Culture entry for that place's exact route.")
      totalTime = "Varies widely - plan per destination within the province."
    }
    GuideCategory.GENERIC -> {
      localTransport.add(TravelLeg(IconTricycle, "Tricycle / Habal-habal", "Local tricycles or habal-habal connect the hub town to most nearby points of interest.", "~15-30 min"))
      beforeYouGo.addAll(listOf("Bring cash for local transportation.", "Charge your phone/power bank.", "Check the weather before heading out."))
      bestTimeToGo = "Best during dry season for outdoor plans; check the weather before traveling."
      travelTips.addAll(listOf("Confirm the last available ride back before heading to remote areas.", "Ask locally for the current best route."))
      goodToKnow.add("Mobile signal and transport frequency may be limited outside major towns.")
      totalTime = "Varies - allow a half-day round trip from the hub city."
    }
  }

  // Universal, always-true additions
  goodToKnow.add(0, "Travel time can increase due to traffic or weather - the times above are approximate.")
  beforeYouGo.add(0, "Bring enough cash - card/digital payment isn't reliable outside major towns.")

  return TravelGuide(
    placeName = placeName, province = province, category = category,
    fromManila = fromManila, fromHub = fromHub, localTransport = localTransport,
    totalTime = totalTime, beforeYouGo = beforeYouGo.distinct(), bestTimeToGo = bestTimeToGo,
    travelTips = travelTips, goodToKnow = goodToKnow.distinct()
  )
}

// Resolves either a Province or a PlaceDetail id into (name, location, category)
// so the same guide generator serves every existing place type.
data class TravelSubject(val id: String, val name: String, val location: String, val category: String, val colorHex: String)

fun resolveTravelSubject(id: String): TravelSubject? {
  provinces.find { it.id == id }?.let {
    return TravelSubject(it.id, it.name, it.name, "Province", it.colorHex)
  }
  findPlaceById(id)?.let {
    return TravelSubject(it.id, it.name, it.location, it.category, it.colorHex)
  }
  return null
}

// All searchable subjects for the unified "Where are you going?" search - // provinces, destinations, activities, culture & heritage, and restaurants.
fun allTravelSubjects(): List<TravelSubject> {
  val places = destinationsData.values.flatten() + activitiesData.values.flatten() +
    cultureData.values.flatten() + restaurants
  return provinces.map { TravelSubject(it.id, it.name, it.name, "Province", it.colorHex) } +
    places.map { TravelSubject(it.id, it.name, it.location, it.category, it.colorHex) }
}

// Coarse type bucket per subject, used to drive the search filter chips
// (Provinces / Destinations / Activities / Culture / Restaurants).
enum class TravelBucket(val label: String) {
  PROVINCE("Provinces"), DESTINATION("Destinations"), ACTIVITY("Activities"),
  CULTURE("Culture"), RESTAURANT("Restaurants")
}

fun bucketOf(subject: TravelSubject): TravelBucket = when {
  subject.category == "Province" -> TravelBucket.PROVINCE
  subject.category == "Restaurants" -> TravelBucket.RESTAURANT
  destinationClassifications.any { it.title == subject.category } -> TravelBucket.DESTINATION
  activityClassifications.any { it.title == subject.category } -> TravelBucket.ACTIVITY
  else -> TravelBucket.CULTURE
}
