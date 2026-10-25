package com.bicolandia.app.data
data class ProvinceExtra(val more: String, val gettingAround: String, val tips: List<String>)

val provinceExtras: Map<String, ProvinceExtra> = mapOf(
    "albay" to ProvinceExtra(
        "Albay's capital, Legazpi City, sits right on Albay Gulf with Mayon Volcano as a constant backdrop, so almost every viewpoint in the province, from Lignon Hill to the Cagsawa Ruins, is built around catching the cone at its best. Beyond Mayon, the province mixes centuries-old churches, ATV trails on old lava flows and the pastel-colored Quitinday Hills.",
        "Tricycles and habal-habal (motorbike taxis) handle short trips around Legazpi and Daraga. Jeepneys and vans connect the towns along the Maharlika Highway, and a rented van or private car is the easiest way to combine several sights, like Cagsawa, Daraga Church and Quitinday Hills, in one day.",
        listOf(
            "Book Mayon-area tours a day ahead during peak season (December to May) as guides and vehicles fill up fast.",
            "Check the current PHIVOLCS alert level before any activity close to the volcano.",
            "Bring cash for tricycles, entrance fees and small eateries outside the city center."
        )
    ),
    "camsur" to ProvinceExtra(
        "Camarines Sur pairs Naga City's festival energy (home to the Peñafrancia devotion each September) with wide-open adventure spots like the CamSur Watersports Complex and the limestone islands of Caramoan, made famous internationally as a Survivor filming location.",
        "Jeepneys and tricycles cover Naga City itself. For Caramoan, the usual route is a van from Naga to Sabang Port in San Jose, then an outrigger boat to Guijalo Port. V-hire vans along the Maharlika Highway are the fastest way to reach Pili, CWC and other towns.",
        listOf(
            "If you are heading to Caramoan, catch a morning boat from Sabang Port; the last public trip is usually around 11 AM.",
            "September is Naga's busiest month because of the Peñafrancia Festival, so book accommodations early.",
            "Bring a dry bag for island-hopping trips around Caramoan."
        )
    ),
    "sorsogon" to ProvinceExtra(
        "Sorsogon is best known for Donsol, where the Whale Shark Interaction Center makes it one of the few places in the world with a reliable, well-regulated way to swim alongside butanding. The province also has Bulusan Volcano Natural Park, quiet eastern-coast beaches and Gubat's beginner-friendly surf break.",
        "Buses and vans from Legazpi continue on to Sorsogon City and further south to the Matnog ferry terminal for Samar. Jeepneys link Sorsogon City to Donsol, Bulusan, Gubat and Irosin; tricycles cover the last stretch into most towns.",
        listOf(
            "Whale shark season runs roughly November to May; register at the Donsol Interaction Center in the morning for the best odds of a sighting.",
            "Follow the interaction guides' rules around the whale sharks: no touching, no flash photography and a respectful distance.",
            "Sorsogon City has no airport of its own, so plan a bus or van leg from Legazpi."
        )
    ),
    "catanduanes" to ProvinceExtra(
        "Nicknamed the Land of the Howling Winds, Catanduanes faces the open Pacific, which is exactly what gives Puraran Beach its famous \"Majestics\" surf break. The island is quieter and less developed than the mainland provinces, with waterfalls, sea cliffs and the Old Capitol Building's museum in Virac as its main cultural stop.",
        "Tricycles are the main way to get around Virac town. For trips to Baras (Puraran) or other coastal towns, jeepneys and vans run along the island's main roads; a private or rented vehicle covers more ground in a day.",
        listOf(
            "Flights and RORO ferries to Catanduanes are weather-dependent, especially from July to November; keep travel dates flexible.",
            "Surf season at Puraran runs roughly July to October, peaking in September and October.",
            "Bring cash; ATMs are limited once you leave Virac."
        )
    ),
    "camnorte" to ProvinceExtra(
        "Camarines Norte is best known for Bagasbas Beach, a long, accessible surf break just outside Daet, and for the Calaguas Islands, whose powdery white sand has drawn comparisons to Boracay. Daet itself holds a small but proud claim to fame: the first monument built anywhere for national hero José Rizal.",
        "Tricycles handle trips within Daet and Bagasbas. Calaguas requires a van or bus to a coastal jump-off town (commonly Vinzons, Mercedes or Paracale), followed by a boat crossing to the islands.",
        listOf(
            "Camarines Norte has no commercial airport, so plan a bus or van leg from Manila, Naga or Legazpi.",
            "A Calaguas trip is best planned as a full day or an overnight, since the boat crossing depends on the tide and weather.",
            "Book a surf lesson through a local club at Bagasbas if you are a first-timer."
        )
    ),
    "masbate" to ProvinceExtra(
        "Masbate is the country's cattle-ranching heartland, which is why it hosts the only cattle-themed rodeo in Southeast Asia every April. Its real hidden gem is offshore: Ticao Island's Manta Bowl is regarded as one of the world's best manta ray dive sites.",
        "Masbate City has tricycles for in-town trips. Getting to Ticao or Burias means a boat from Masbate City or a RORO ferry from Pilar, Sorsogon; island transport is limited, so trips there are best arranged a day ahead.",
        listOf(
            "Flights to Masbate are limited, so check current schedules well before your trip.",
            "Manta ray sightings at Manta Bowl are best roughly March to June.",
            "Carry cash outside Masbate City, where card payments are far less common."
        )
    )
)

data class PlaceExtra(val bestTime: String, val gettingThere: String, val tips: List<String>, val funFact: String)

// ---------- category-level content, applied to every place in that category ----------
private val volcanoExtra = PlaceExtra(
    "Clearest views and the safest trail conditions are typically from December to May, the region's dry season. Early morning is usually the best time to see the summit before afternoon clouds roll in.",
    "Reached from the nearest hub city by van, tricycle or habal-habal to the trailhead or viewpoint, then on foot or by 4x4 for the final stretch. A local guide is required for any trek onto the volcano itself.",
    listOf(
        "Check the current PHIVOLCS alert level before planning any trek or close-range viewing.",
        "Wear proper hiking shoes and bring more water than you think you'll need.",
        "Book a local guide in advance; trekking without one is not allowed on most Bicol volcanoes."
    ),
    "Bicol's volcanoes are part of the Pacific Ring of Fire, the same tectonic belt responsible for most of the world's active volcanoes and earthquakes."
)

private val festivalExtra = PlaceExtra(
    "Falls on the same dates each year (see the Month above), so plan lodging in the host city well in advance if your visit lines up with it.",
    "Most festival activities take place in the town or city center listed as the main venue, which is reachable by jeepney, tricycle or a short walk from most in-town accommodations.",
    listOf(
        "Book accommodations early; hotels in the host town fill up fast during the festival.",
        "Expect road closures and heavy foot traffic near the main venue on festival days.",
        "Bring cash for the trade fairs, food stalls and souvenir vendors that usually accompany the festivities."
    ),
    "Many Bicol festivals blend pre-colonial legends and harvest traditions with Catholic devotion, a mix that reflects the region's long history before and after Spanish contact."
)

private val atvExtra = PlaceExtra(
    "Best in the dry season (December to May), when trails are firmer and less muddy. Morning rides also mean cooler temperatures and, on clear days, better views of Mayon.",
    "Tour operators are based near the trailheads in Daraga, Camalig, Malilipot, Santo Domingo or Guinobatan; most offer pickup from Legazpi City hotels for a fee.",
    listOf(
        "Wear closed shoes, long pants and a face covering for dust and loose gravel.",
        "Confirm what safety gear (helmet, gloves) is included before you book.",
        "Ask about the tour's difficulty level; some trails cross rougher volcanic terrain than others."
    ),
    "Several Bicol ATV trails cross real lava flows from Mayon's past eruptions, including the hardened 2006 lava wall that riders can still see today."
)

private val dishExtra = PlaceExtra(
    "Available year-round in most Bicolano restaurants and markets, though dishes made for a festival (like Bicol Express during Ibalong) are especially easy to find at that time.",
    "Best tried at a local restaurant or public market in the towns noted above, where it's typically made fresh daily; many Legazpi and Naga restaurants serve it as a regular menu item.",
    listOf(
        "Ask how spicy it is before ordering. Bicolano cooking uses siling labuyo generously by default.",
        "Pair it with plain rice; Bicolano dishes are built around heavy coconut milk (gata) sauces meant to be eaten with rice.",
        "Try it at a public market for a more affordable, authentic version than some tourist-facing restaurants."
    ),
    "The heavy use of coconut milk (gata) and chili in Bicolano cooking is what sets the region's cuisine apart from the rest of the Philippines, earning it a reputation as the country's spiciest culinary tradition."
)

private val beachExtra = PlaceExtra(
    "Calmest seas and clearest skies run roughly December to May. Weekday mornings are quieter than weekends, when local day-trippers arrive.",
    "Reached from the nearest town by tricycle or habal-habal; some beaches (like Calaguas) also require a short boat crossing from a coastal jump-off town.",
    listOf(
        "Bring your own sun protection and drinking water; not every stretch of beach has vendors nearby.",
        "Check for lifeguards or safe-swimming markers, especially on open-coast beaches with stronger currents.",
        "If a boat crossing is involved, ask about the last return trip so you don't get stranded."
    ),
    "Several Bicol beaches face the open Pacific rather than a sheltered bay, which is what gives the region's east coast its surf breaks and bigger waves compared to typical Philippine beach towns."
)

private val waterfallExtra = PlaceExtra(
    "Best in the dry season (December to May), when trails are less slippery and the water is clearer for swimming. Avoid visiting right after heavy rain, when flash floods are a risk.",
    "Usually a short tricycle or habal-habal ride from the nearest town center to the trailhead, followed by a walk of anywhere from a few minutes to over an hour depending on the falls.",
    listOf(
        "Wear outdoor footwear with good grip; rocks near the falls are often slick.",
        "Bring a change of clothes if you plan to swim, plus a dry bag for your phone and valuables.",
        "Ask locals about current trail conditions before heading in, especially in the rainy season."
    ),
    "Many of Bicol's waterfalls sit inside protected natural parks around Mount Isarog and Mount Bulusan, feeding streams that also supply nearby towns with drinking water."
)

private val islandExtra = PlaceExtra(
    "Dry season (roughly December to May) brings the calmest seas for island crossings. Typhoon season (July to November) can cancel boat trips with little notice.",
    "Reached by outrigger boat (banca) from the nearest port or beach launch; some islands are a full-day trip requiring an early morning departure.",
    listOf(
        "Confirm the boat schedule and the last return trip before you go.",
        "Bring cash, sun protection and a dry bag; facilities on smaller islands are often limited.",
        "Book through a registered boatman or tour operator rather than an informal arrangement."
    ),
    "Camarines Sur's Caramoan island group has been used as a filming location for international versions of the reality show Survivor, drawing production crews from several countries."
)

private val parkExtra = PlaceExtra(
    "Best visited in the dry season (December to May) for clearer trails and safer conditions. Early morning is cooler and usually less crowded.",
    "Most parks are a short tricycle or van ride from the nearest town center, with a ranger station or entrance gate marking the start of the trails.",
    listOf(
        "Register at the ranger station or entrance gate before hiking in; some parks require this for safety tracking.",
        "Bring insect repellent and enough water, since services inside the park are limited.",
        "Check current advisories if the park sits on or near an active volcano."
    ),
    "Bicol's natural parks protect some of the last stands of old-growth forest on Luzon Island, sheltering wildlife found nowhere else in the Philippines."
)

private val historicalExtra = PlaceExtra(
    "Open most days of the week; visit in the morning for softer light and smaller crowds, especially at popular sites like the Cagsawa Ruins.",
    "Usually within or just outside a town center, reachable by tricycle, jeepney or a short walk from most in-town accommodations.",
    listOf(
        "Hire a local guide on-site for historical context that isn't always on the signage.",
        "Bring cash for entrance fees and souvenirs, since card payment is uncommon at smaller sites.",
        "Respect any roped-off or restricted areas, particularly around fragile ruins."
    ),
    "The Cagsawa Ruins are the bell tower of a church buried by Mayon's catastrophic 1814 eruption, one of the volcano's deadliest recorded events."
)

private val churchExtra = PlaceExtra(
    "Open for viewing most days; Mass schedules vary by parish, so visit outside service hours if you're mainly there to look around and take photos.",
    "Typically in or near a town center, reachable by tricycle, jeepney or on foot from most in-town accommodations.",
    listOf(
        "Dress modestly and keep noise down, especially during Mass or other services.",
        "Ask before taking photos inside, since some churches restrict photography during services.",
        "Check the parish's feast day if you want to see the church at its liveliest."
    ),
    "Many Bicol churches were built with fortress-like buttresses and thick coral-stone walls, a defensive style adopted during a period when coastal raids were a real threat."
)

private val museumExtra = PlaceExtra(
    "Best visited on a weekday morning when it's quieter; check ahead, since some smaller museums close on Mondays or public holidays.",
    "Usually within a town or city center, reachable by tricycle, jeepney or a short walk from most in-town accommodations.",
    listOf(
        "Call or check online ahead of time to confirm current operating hours.",
        "Ask about guided tours; small local museums often have a staff member happy to walk you through the exhibits.",
        "Combine with a nearby historical site or church for a fuller cultural half-day."
    ),
    "Several of Bicol's local museums grew out of artifacts recovered after disasters, like Mayon's eruptions, preserving pieces of daily life that would otherwise have been lost."
)

private val hikingExtra = PlaceExtra(
    "Dry season (December to May) offers the safest, clearest trail conditions. Start early to finish before afternoon rain or heat sets in.",
    "Reached from the nearest hub town by van, tricycle or habal-habal to the jump-off point, where a local guide typically joins for the trek itself.",
    listOf(
        "Book a local guide in advance; most Bicol trails require one, both for safety and as local practice.",
        "Wear proper hiking shoes and bring more water than you think you'll need.",
        "Check the mountain's current advisory level before you go, especially for volcano treks."
    ),
    "Mount Isarog's rainforest slopes are one of the last strongholds for several endemic Philippine bird and mammal species found only on this part of Luzon."
)

private val islandHoppingExtra = PlaceExtra(
    "Calmest seas run roughly December to May. Book the earliest available departure for the best light and fewer crowds at each stop.",
    "Boats typically leave from a designated port or beach launch near the jump-off town; most tours are arranged as a full-day package with several island stops.",
    listOf(
        "Confirm what's included (lunch, snorkeling gear, entrance fees) before booking.",
        "Bring a dry bag, reef-safe sunscreen and a change of clothes.",
        "Ask about the tour's cancellation policy in case of rough weather."
    ),
    "Camarines Sur's Caramoan peninsula alone has dozens of coves and islets, so most operators only visit a handful of the best-known ones on a typical day tour."
)

private val surfingExtra = PlaceExtra(
    "Swell and wind patterns vary by break; Bagasbas is fairly consistent year-round, while spots facing the open Pacific, like Puraran, peak roughly July to October.",
    "Local surf shops and clubs near the beach rent boards and offer lessons; most breaks are a short walk or tricycle ride from nearby accommodations.",
    listOf(
        "Book a lesson with a local instructor if you're new to surfing; conditions can be stronger than they look.",
        "Check the day's swell and wind forecast before paddling out.",
        "Respect the local surf lineup and any surfers who arrived before you."
    ),
    "Puraran Beach's \"Majestics\" wave, a powerful right-hand reef break, has drawn international surfers to this corner of Catanduanes since Australian surfers first discovered it in 1979."
)

private val diveExtra = PlaceExtra(
    "Visibility and conditions are generally best from March to June, before the rainier months bring more runoff and choppier seas.",
    "Dive operators are based near the nearest coastal town or resort and typically arrange the boat trip to the dive site as part of the package.",
    listOf(
        "Bring your certification card and dive log if you're booking through a new operator.",
        "Confirm gear rental and boat fees are included before you book.",
        "Ask about current conditions at the site, since visibility can change day to day."
    ),
    "Ticao Island's Manta Bowl is considered one of the best manta ray dive sites in the world, drawing divers from well beyond the Philippines to this corner of Masbate."
)

private val whaleSharkExtra = PlaceExtra(
    "Whale shark season runs roughly November to May, with sightings never guaranteed since the animals move freely through open water.",
    "Register in the morning at the local Interaction Center, which arranges the registered boat and certified spotters and guides for the trip out.",
    listOf(
        "Arrive early; boats typically go out shortly after sunrise for the best chance of a sighting.",
        "Follow the guides' rules: no touching, no flash photography and a respectful distance from the animals.",
        "Bring a rash guard and reef-safe sunscreen instead of regular sunscreen, which can harm marine life."
    ),
    "Donsol's whale shark tourism program, one of the first of its kind in the world, has been credited with turning a once-hunted animal into the town's biggest source of income."
)

private val delicacyExtra = PlaceExtra(
    "Available year-round at local markets and pasalubong (souvenir) shops, though freshest right after it's made, so ask vendors when their latest batch came out.",
    "Best bought directly from a public market or a known pasalubong shop in the towns noted above, where it's usually made in-house rather than shipped in.",
    listOf(
        "Ask about shelf life if you're bringing it home as a souvenir, since some delicacies are best eaten within a few days.",
        "Compare a few stalls at the public market; recipes and quality can vary noticeably between vendors.",
        "Buy a small amount first if you haven't tried it before, especially for chili-based treats."
    ),
    "Pili is one of the few nut varieties in the world grown almost exclusively in one country, and the Bicol Region produces the vast majority of the Philippines' pili nut supply."
)

private val restaurantExtra = PlaceExtra(
    "Open daily for lunch and dinner in most cases, though smaller family-run spots may have shorter hours, especially early in the week.",
    "Usually in or near a town or city center, reachable by tricycle, jeepney or a short walk from most in-town accommodations.",
    listOf(
        "Call ahead for a table on weekends or holidays, when local favorites can get busy.",
        "Ask for the spice level to be adjusted if you're not used to Bicolano heat.",
        "Bring cash, since some smaller restaurants outside the city centers don't accept cards."
    ),
    "Bicolano cooking's heavy use of gata (coconut milk) and siling labuyo (bird's eye chili) has earned the region a reputation as the spiciest culinary tradition in the Philippines."
)

private val craftExtra = PlaceExtra(
    "Best seen at a local market, cooperative or workshop rather than a single fixed site; weekday visits give you a better chance of watching artisans actually at work.",
    "Look for these crafts at public markets, pasalubong centers and cooperative shops in the towns associated with them, or ask your accommodation for a nearby workshop to visit.",
    listOf(
        "Buying directly from a weaver's or artisan's cooperative supports the community that keeps the craft alive.",
        "Ask about the material and process; many Bicolano crafts use plant fibers like abaca that take real skill to prepare.",
        "Expect handmade pieces to vary slightly from one another; that variation is part of the craft."
    ),
    "Abaca, a fiber from a relative of the banana plant, was once one of the Philippines' most valuable exports and is still hand-processed by Bicolano weavers today."
)

private val folkloreExtra = PlaceExtra(
    "Best experienced through festivals and cultural performances, especially the Ibalong Festival in August, which re-enacts these stories through mask parades and street performances.",
    "Told and re-enacted throughout the region, especially in Legazpi and Naga, and increasingly taught in local schools as part of Bicolano heritage education.",
    listOf(
        "Watch for the Ibalong story retold as street theater during Legazpi's Ibalong Festival if your visit lines up with August.",
        "Ask locals for their version of the story; oral traditions like these often have regional variations.",
        "Look for these characters referenced in local place names, murals and public art around Albay."
    ),
    "The Ibalong is considered the oldest known epic in the Philippines, first documented in writing by a Spanish friar in the 19th century from oral traditions passed down for generations."
)

private val figureExtra = PlaceExtra(
    "Commemorated year-round through local monuments, street names and museum exhibits, with extra attention around their birth or death anniversaries.",
    "Look for markers, monuments or museum exhibits in the towns associated with each figure, often paired with a local historical site or museum.",
    listOf(
        "Visit the associated town's museum or heritage site for more context than a roadside marker alone provides.",
        "Ask a local guide for the human side of the story; official plaques often only give dates and titles.",
        "Check for anniversary events if your visit falls near a figure's birth or death date."
    ),
    "Camarines Norte proudly claims the first monument ever built for national hero José Rizal, erected in Daet just months after his execution in 1896."
)

private val traditionExtra = PlaceExtra(
    "Best witnessed during the specific festival, feast day or season each tradition is tied to; ask locally for the nearest upcoming occasion.",
    "Practiced throughout Bicolano communities rather than at a single site; your best chance of seeing one firsthand is around a related festival or town fiesta.",
    listOf(
        "Ask your accommodation or a local guide if a related tradition or performance is happening during your stay.",
        "Approach with respect, especially for traditions tied to religious devotion like the Peñafrancia.",
        "Photography is often welcome, but ask first during more solemn or religious moments."
    ),
    "The Peñafrancia fluvial procession draws over a million devotees to Naga City each September, making it one of the largest religious gatherings in the entire country."
)

private val indigenousExtra = PlaceExtra(
    "Best approached respectfully and, where possible, through a community-endorsed guide or cultural center rather than an unplanned visit.",
    "Indigenous communities and heritage sites are spread across Camarines Norte, Camarines Sur and Sorsogon; local tourism offices can point you to responsible ways to learn more.",
    listOf(
        "Support community-led tourism initiatives rather than informal, uncoordinated visits.",
        "Ask permission before photographing people or sacred sites.",
        "Approach beliefs and practices as living traditions, not just historical curiosities."
    ),
    "The Agta are among the Philippines' Negrito peoples, believed to be descended from some of the earliest inhabitants of the archipelago, long before the Austronesian migrations that shaped most of the country's modern populations."
)

private val genericExtra = PlaceExtra(
    "Best visited during Bicol's dry season, roughly December to May, for easier travel and clearer weather.",
    "Reachable from the nearest town center by tricycle, habal-habal or a short van ride; check with your accommodation for the latest local transport options.",
    listOf(
        "Start early in the day to avoid the afternoon heat and any sudden rain.",
        "Bring cash, as many small vendors and local transport don't accept cards.",
        "Confirm current entrance fees and opening hours locally, since these can change."
    ),
    "This spot is one of the reasons Bicol has become one of the Philippines' fastest-growing regional tourism destinations over the past decade."
)

private fun extraFor(category: String): PlaceExtra = when {
    category == "Volcanoes" -> volcanoExtra
    category.contains("Festival") -> festivalExtra
    category == "ATV Rides" -> atvExtra
    category == "Traditional Dishes" -> dishExtra
    category == "Beaches" -> beachExtra
    category == "Waterfalls" -> waterfallExtra
    category == "Islands" -> islandExtra
    category == "Parks" -> parkExtra
    category == "Historical Sites" -> historicalExtra
    category == "Churches" -> churchExtra
    category == "Museums" -> museumExtra
    category == "Hiking" -> hikingExtra
    category == "Island Hopping" -> islandHoppingExtra
    category == "Surfing" -> surfingExtra
    category == "Diving & Snorkeling" -> diveExtra
    category == "Whale Sharks" -> whaleSharkExtra
    category == "Delicacies" -> delicacyExtra
    category == "Restaurants" -> restaurantExtra
    category == "Traditional Crafts" -> craftExtra
    category == "Folklore & Legends" -> folkloreExtra
    category == "Historical Figures" -> figureExtra
    category == "Living Traditions" -> traditionExtra
    category == "Indigenous Heritage" -> indigenousExtra
    else -> genericExtra
}


fun enrich(place: PlaceDetail): PlaceDetail {
    if (place.bestTime.isNotBlank()) return place  // already hand-authored (e.g. Stay & Rest doesn't use this model)
    val e = extraFor(place.category)
    return place.copy(bestTime = e.bestTime, gettingThere = e.gettingThere, tips = e.tips, funFact = e.funFact)
}
