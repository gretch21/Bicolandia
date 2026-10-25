package com.bicolandia.app.data
internal val hotelStays: List<Accommodation> = listOf(

  Accommodation(
    id = "stay_hotel_01",
    name = "The Oriental Hotel Legazpi",
    type = "Hotel",
    location = "Taysan Hill, Old Albay, Legazpi City",
    province = "Albay",
    shortDescription = "A hillside landmark above Legazpi with an infinity pool, spa and fitness center, and open views toward Mayon Volcano and the city.",
    overview = "Set on Taysan Hill in the Old Albay district, The Oriental Hotel Legazpi is one of the city's best-known upscale stays. Its modern glass-and-white building overlooks Legazpi, and its infinity pool is the signature feature, framing Mayon Volcano and the cityscape.\n\nThe hotel also has a spa, a fitness center and on-site dining, which makes it a comfortable, all-in-one base for a first visit to Albay. Listings note that it accepts pets, so confirm the current policy if you are traveling with one.",
    facts = listOf(
      QuickFact("Setting", "Hillside, edge of Old Albay"),
      QuickFact("Style", "Upscale city hotel"),
      QuickFact("Facilities", "Infinity pool, spa, fitness center"),
      QuickFact("Nearest hub", "Legazpi City · Bicol International Airport (DRP), Daraga"),
      QuickFact("Good for", "First-time Albay visitors, couples, Mayon views")
    ),
    highlights = listOf(
      "Infinity pool with views toward Mayon Volcano and the city",
      "Spa and fitness center on site",
      "Restaurant and café dining without leaving the property",
      "Free Wi-Fi and air-conditioned rooms with modern bathrooms",
      "Airport pickup has been offered to guests - ask when you reserve",
      "Listed as pet-friendly (confirm current policy)"
    ),
    areaNote = "Taysan Hill rises over Old Albay, the historic heart of Legazpi. From the hill you look toward Mayon on one side and down over the city on the other, and the city's museum, parks and dining are a short ride away.",
    howToGetThere = "Fly to Bicol International Airport (DRP) in Daraga, or ride a Manila–Legazpi bus, then take a taxi or hotel pickup up to Taysan Hill.",
    routeSteps = listOf(
      "Fly Manila to Bicol International Airport (DRP) in Daraga (about 1 hour), or take a Manila–Legazpi bus (about 9–10 hours).",
      "From the airport or the Legazpi Grand Central Terminal, take a taxi or tricycle to Sto. Niño Village on Taysan Hill.",
      "Ask the hotel about an airport pickup before you travel; give the driver the hotel name and the landmark \"Taysan Hill\"."
    ),
    nearbyAttractions = listOf(
      "Mayon Volcano (view from the pool area)",
      "Lignon Hill Nature Park",
      "Legazpi City Museum",
      "Peñaranda Park",
      "Cagsawa Ruins, Daraga"
    ),
    nearbyActivities = listOf(
      "Mayon ATV lava-wall tour",
      "Sunrise Mayon viewing",
      "Bicol food trail (sili ice cream, pinangat)",
      "Day trip to Donsol whale sharks (Nov–May)"
    ),
    bestTime = "Mornings from roughly March to May give the best odds of a clear Mayon. Clouds often cover the summit by midday, so plan viewing early.",
    travelConsiderations = listOf(
      "Mayon views depend on the weather - build a flexible day into your itinerary.",
      "Reserve early for festival weekends and long holidays, when Legazpi fills up.",
      "Ask about airport pickup ahead of time instead of relying on taxis at arrival.",
      "Volcano activities can be paused during unrest - follow official advisories."
    ),
    prepInfo = listOf(
      "Check the PHIVOLCS Mayon advisory before booking any volcano tour.",
      "Bring a valid ID for check-in and a light rain jacket for sudden showers.",
      "Carry small bills for tricycles and market stops around the city."
    ),
    colorHex = "#455A64",
    imageId = "stay_hotel_01"
  ),

  Accommodation(
    id = "stay_hotel_02",
    name = "The Avenue Plaza Hotel",
    type = "Hotel",
    location = "Magsaysay Avenue, Naga City",
    province = "Camarines Sur",
    shortDescription = "A well-known Naga City hotel with an outdoor pool, gym, sauna and restaurant, plus guided tour packages to Mt. Isarog, Mayon and Caramoan.",
    overview = "The Avenue Plaza Hotel stands on Magsaysay Avenue, Naga City's lively corridor of hotels and restaurants, which makes it a convenient base for exploring Camarines Sur. Facilities include an outdoor pool, a gym, a sauna, a restaurant and a 24-hour business center.\n\nThe hotel also arranges half-day and full-day tour packages with a professional guide and a private van, covering Naga, Mt. Isarog, Mayon Volcano and the Caramoan Islands - handy when you want to see several places without arranging transport yourself.",
    facts = listOf(
      QuickFact("Setting", "City center, Magsaysay Avenue"),
      QuickFact("Facilities", "Outdoor pool, gym, sauna, restaurant"),
      QuickFact("Tours", "Guided packages: Naga, Isarog, Mayon, Caramoan"),
      QuickFact("Nearest hub", "Naga City · Naga Airport (WNP), Pili"),
      QuickFact("Good for", "Business trips, families, multi-day Bicol itineraries")
    ),
    highlights = listOf(
      "Outdoor swimming pool, gym and sauna",
      "On-site restaurant and a 24-hour business center",
      "Guided half-day and full-day tour packages with a private van",
      "Free Wi-Fi in public areas",
      "Airport shuttle and free self-parking are listed for guests",
      "Central Magsaysay Avenue location with dining nearby"
    ),
    areaNote = "Magsaysay Avenue is a main hotel-and-dining strip in Naga. The city's religious and cultural landmarks, including the Basilica of Our Lady of Peñafrancia and the Metropolitan Cathedral, are in the city center a short ride away.",
    howToGetThere = "Fly to Naga Airport (WNP) in Pili or ride a Manila–Naga bus, then take a taxi or tricycle to Magsaysay Avenue.",
    routeSteps = listOf(
      "Fly Manila to Naga Airport (WNP) in Pili (about 1 hour), or take a Manila–Naga bus (about 8–9 hours).",
      "From the Naga bus terminal beside SM City Naga, take a taxi or tricycle to Magsaysay Avenue.",
      "Ask the hotel about its airport shuttle and tour packages when you make your arrangements."
    ),
    nearbyAttractions = listOf(
      "Basilica of Our Lady of Peñafrancia",
      "Naga Metropolitan Cathedral",
      "Mt. Isarog National Park",
      "Camarines Sur Watersports Complex, Pili",
      "Caramoan Islands (multi-day trip)"
    ),
    nearbyActivities = listOf(
      "Peñafrancia devotion and river procession (September)",
      "Cable wakeboarding at CamSur Watersports Complex",
      "Mt. Isarog hiking",
      "Bicol restaurant hopping along Magsaysay Avenue"
    ),
    bestTime = "September is the busiest time because of the Peñafrancia Festival, so expect full hotels and heavy traffic. For hiking and island trips, the drier months (roughly March to May) are more reliable.",
    travelConsiderations = listOf(
      "Book well ahead if your dates overlap the Peñafrancia Festival in September.",
      "Tour packages need advance arrangement - ask what is included before you go.",
      "Caramoan and Isarog trips are long day trips; consider spreading them over several days.",
      "Roads and boats can be affected by typhoons - keep dates flexible during the rainy season."
    ),
    prepInfo = listOf(
      "Mt. Isarog treks need a permit and an accredited guide.",
      "Bring cash for tricycles and local eateries even though the hotel takes cards.",
      "Pack light layers - the mountain area is noticeably cooler."
    ),
    colorHex = "#455A64",
    imageId = "stay_hotel_02"
  ),

  Accommodation(
    id = "stay_hotel_03",
    name = "Fernando's Hotel",
    type = "Hotel",
    location = "N. Pareja Street, Bitan-o, Sorsogon City",
    province = "Sorsogon",
    shortDescription = "A popular Sorsogon City hotel with a garden-style pool, restaurant and café, and easy access to Museo Sorsogon, Palogtok Falls and Donsol day trips.",
    overview = "Fernando's Hotel is on N. Pareja Street in Bitan-o, Sorsogon City, arranged around a garden compound with a large outdoor pool and a separate children's swimming area. Guests also have a restaurant, a café and snack bar, massage services, laundry service and an airport shuttle option.\n\nSorsogon City is the provincial capital, so the hotel works well as a home base for museum visits, waterfalls, Bulusan Lake and whale shark trips to Donsol.",
    facts = listOf(
      QuickFact("Setting", "Sorsogon City, Bitan-o"),
      QuickFact("Facilities", "Pool, restaurant, café, massage"),
      QuickFact("Good for", "Families, road-trippers, business travelers"),
      QuickFact("Getting in", "No commercial airport - fly to Legazpi (DRP), then bus or van"),
      QuickFact("Transfers", "Airport shuttle listed by the hotel")
    ),
    highlights = listOf(
      "Outdoor pool plus a children's swimming area",
      "Restaurant, café and snack bar on the property",
      "Massage and spa-style services",
      "Free Wi-Fi in public areas and free parking",
      "Laundry service and an airport shuttle option",
      "Close to Museo Sorsogon and Palogtok Falls"
    ),
    areaNote = "Bitan-o is part of Sorsogon City, the provincial capital on Sorsogon Bay. The city is the transport hub for the province - jeepneys and vans fan out to Gubat, Bulusan, Irosin and Matnog, and buses continue on to the Matnog ferry port for Samar.",
    howToGetThere = "Fly to Legazpi (DRP), then take a bus or van to Sorsogon City (about 1.5–2 hours) and a tricycle to the hotel.",
    routeSteps = listOf(
      "Fly Manila to Bicol International Airport (DRP), or ride a Manila–Sorsogon bus (about 10–11 hours).",
      "From Legazpi Grand Central Terminal, take a bus or van to the Sorsogon Grand Terminal (about 1.5–2 hours).",
      "From the terminal, take a tricycle to N. Pareja Street in Bitan-o, or ask the hotel about its airport shuttle."
    ),
    nearbyAttractions = listOf(
      "Museo Sorsogon",
      "Palogtok Falls, Castilla",
      "Sorsogon Bay",
      "Bulusan Lake (day trip)",
      "Donsol Whale Shark Interaction Center (day trip)"
    ),
    nearbyActivities = listOf(
      "Museum and heritage stop",
      "Waterfall trek",
      "Butanding interaction, Donsol (Nov–May)",
      "Kayaking and canopy walk at Bulusan Lake"
    ),
    bestTime = "The drier months (roughly March to May) suit waterfalls, Bulusan Lake and boat trips best. Whale shark encounters in Donsol run roughly November to May.",
    travelConsiderations = listOf(
      "Sorsogon City has no commercial airport, so plan a bus or van leg from Legazpi.",
      "Some attractions are day trips of an hour or more - start early.",
      "Waterfall trails can be slippery after rain; check conditions locally.",
      "Bring cash for jeepneys, tricycles and small eateries."
    ),
    prepInfo = listOf(
      "Confirm the airport shuttle time with the hotel a day before arrival.",
      "Wear water-friendly footwear for falls and lake activities.",
      "Whale shark tours use registered boats and guides - book through the Donsol center."
    ),
    colorHex = "#455A64",
    imageId = "stay_hotel_03"
  ),

  Accommodation(
    id = "stay_hotel_04",
    name = "Treasure Island Hotel",
    type = "Hotel",
    location = "Quezon Street, Masbate City",
    province = "Masbate",
    shortDescription = "A waterfront hotel in Masbate City with a pool complex, gym and restaurant, minutes from Masbate Airport and close to Buntod Sandbar boat trips.",
    overview = "Treasure Island Hotel faces the water on Quezon Street in Masbate City. Listings describe a resort-style pool area with several outdoor pools and a children's pool, a fitness room, a restaurant, in-room safes, Wi-Fi and a 24-hour front desk.\n\nIt is only minutes from Masbate Airport and close to where boats leave for Buntod Sandbar and Reef Marine Sanctuary, so it is a practical first or last night when traveling between the mainland and Masbate's islands.",
    facts = listOf(
      QuickFact("Setting", "Waterfront, Masbate City"),
      QuickFact("Facilities", "Multiple pools, gym, restaurant"),
      QuickFact("Nearest hub", "Masbate Airport (MBT), a few minutes away"),
      QuickFact("Good for", "Island-hopping starts, families, stopovers"),
      QuickFact("Front desk", "24 hours")
    ),
    highlights = listOf(
      "Waterfront location in the provincial capital",
      "Several outdoor pools and a children's pool",
      "Fitness room and on-site restaurant",
      "Free Wi-Fi and in-room safes",
      "24-hour front desk",
      "Breakfast is mentioned in listings - confirm what is included"
    ),
    areaNote = "Masbate City is the provincial capital and main air and sea gateway to Masbate, Ticao and Burias islands. The nearby Buntod Sandbar and Reef Marine Sanctuary is the most popular short boat trip from the city.",
    howToGetThere = "Fly to Masbate Airport (MBT), or take a bus/van to Pilar, Sorsogon and a RORO ferry (about 2–2.5 hours) to Masbate City.",
    routeSteps = listOf(
      "By air: fly to Masbate Airport (MBT), often via Manila or Cebu (about 1–1.5 hours flying).",
      "By land and sea: ride a bus toward Legazpi, take a bus or van to Pilar, Sorsogon, then board a RORO ferry to Masbate (about 2–2.5 hours).",
      "From the airport or port, take a tricycle to Quezon Street."
    ),
    nearbyAttractions = listOf(
      "Buntod Sandbar and Reef Marine Sanctuary",
      "Masbate City waterfront",
      "Ticao Island (by boat)",
      "Burias Island group (by boat)"
    ),
    nearbyActivities = listOf(
      "Sandbar and snorkeling trip",
      "Island hopping around Masbate",
      "Rodeo Masbateño festival season (spring)",
      "Seafood dining by the waterfront"
    ),
    bestTime = "Sea conditions decide boat trips. The calmer months (roughly March to June) are best for Buntod Sandbar and Ticao; typhoon season (about July to October) can cancel sailings.",
    travelConsiderations = listOf(
      "RORO and boat schedules shift with the weather - keep a buffer day.",
      "Sandbar visits depend on the tide and sea conditions.",
      "Ticao and Burias transfers have limited services; arrange them in advance.",
      "Flights to Masbate are limited - check current schedules before locking in dates."
    ),
    prepInfo = listOf(
      "Bring sun protection and a dry bag for boat trips.",
      "Carry cash - card payments are less common outside the city center.",
      "Keep a printed copy of ferry tickets and ID for port checks."
    ),
    colorHex = "#455A64",
    imageId = "stay_hotel_04"
  )
)

internal val resortStays: List<Accommodation> = listOf(

  Accommodation(
    id = "stay_resort_01",
    name = "Misibis Bay Resort",
    type = "Resort",
    location = "Cagraray Island, Bacacay",
    province = "Albay",
    shortDescription = "A private-cove island resort with beachfront and pool-view villas, two sea-view pools, a spa, a beach club and a long list of land and water activities.",
    overview = "Misibis Bay sits in an exclusive cove on Cagraray Island, across the channel from mainland Albay, and is often described as a self-contained island resort. Accommodations range from garden and pool-view rooms to beachfront villas.\n\nOn site are two outdoor pools with sea views, a spa and wellness area, a gym, restaurants such as Spice Market, a beach club and the Sula bar, plus a private beach. Activities include ATV or bicycle rides with views of Nag-Aso Lake and Mayon, parasailing, windsurfing, diving and fishing.",
    facts = listOf(
      QuickFact("Setting", "Private cove, Cagraray Island"),
      QuickFact("Facilities", "2 pools, spa, gym, beach club, bars"),
      QuickFact("Check-in", "Minimum age 22 · no after-hours check-in"),
      QuickFact("Transfers", "Airport transfer offered (advance notice required)"),
      QuickFact("Good for", "Couples, families, resort-style getaways")
    ),
    highlights = listOf(
      "Private beach area and two outdoor pools with sea views",
      "Spa, wellness area and fitness center",
      "Spice Market restaurant, Beach Club and the Sula bar",
      "Parasailing, windsurfing, diving, fishing and a games room",
      "ATV and bicycle rentals with Mayon and Nag-Aso Lake views",
      "Children are welcome; childcare services are listed",
      "Pets are not allowed, according to listings"
    ),
    areaNote = "Cagraray Island is a quiet, rural island with the separate Cagraray Eco-Park about 500 meters from the resort. The mainland side of the trip passes through Bacacay and, on the road route, historic towns such as Santo Domingo with its centuries-old church.",
    howToGetThere = "Fly to Legazpi (DRP) and arrange the resort's transfer in advance - the trip is roughly an hour or more by road, sometimes with a short channel crossing.",
    routeSteps = listOf(
      "Fly Manila to Bicol International Airport (DRP) in Daraga, or ride a Manila–Legazpi bus.",
      "Message the resort with your arrival details so the airport transfer can be arranged (listed as a surcharge service).",
      "Transfers have used a road leg toward Bacacay and, at times, a short crossing of the Sula Channel by boat or barge - confirm the current route and timing with the resort.",
      "Expect roughly one to one and a half hours or more door to door from Legazpi, depending on the route."
    ),
    nearbyAttractions = listOf(
      "Cagraray Eco-Park (about 500 m)",
      "Nag-Aso Lake",
      "Mayon Volcano views",
      "Santo Domingo Church, on the road route",
      "Legazpi City (about 37 km)"
    ),
    nearbyActivities = listOf(
      "Parasailing and windsurfing",
      "Diving and snorkeling",
      "ATV or bicycle rides",
      "Spa and wellness",
      "Fishing"
    ),
    bestTime = "Calm seas in the drier months (roughly March to June) make boat and water activities more reliable. Typhoon season can affect channel crossings.",
    travelConsiderations = listOf(
      "The resort is far from town - plan meals, shopping and tours around its own services.",
      "Channel crossings and transfers depend on weather and tides; confirm a day before you travel.",
      "Guests must be at least 22 to check in, and there is no after-hours check-in.",
      "Combine with Donsol or Legazpi sightseeing by arranging separate transport."
    ),
    prepInfo = listOf(
      "Send flight and arrival details to the resort in advance for the airport transfer.",
      "Bring a valid government ID and payment card for incidentals at check-in.",
      "Pack reef-safe sunscreen and water shoes for the shoreline."
    ),
    colorHex = "#00838F",
    imageId = "stay_resort_01"
  ),

  Accommodation(
    id = "stay_resort_02",
    name = "Tugawe Cove Resort",
    type = "Resort",
    location = "Brgy. Colongcogong, Caramoan",
    province = "Camarines Sur",
    shortDescription = "A cove-side resort on the Caramoan peninsula with a pool, playground and beach loungers, used by many visitors as their island-hopping base.",
    overview = "Tugawe Cove Resort sits in a sheltered cove in Barangay Colongcogong on the Caramoan peninsula. Guides have started full-day Caramoan island-hopping tours from its shore, which is why many visitors choose it as a base.\n\nListed facilities include an outdoor pool, a children's pool, a playground, an arcade and game room, a restaurant, sun loungers and beach umbrellas, and snorkeling from the shore. Room service and a 24-hour front desk are also listed, with breakfast available for a fee.",
    facts = listOf(
      QuickFact("Setting", "Secluded cove, Caramoan peninsula"),
      QuickFact("Facilities", "Pool, kids' pool, game room, restaurant"),
      QuickFact("Access", "Recent guests report boat transfers - confirm with the resort"),
      QuickFact("Getting in", "Naga → Sabang Port → boat to Guijalo Port"),
      QuickFact("Good for", "Island hopping, snorkeling, families")
    ),
    highlights = listOf(
      "Outdoor pool, children's pool and playground",
      "Restaurant on site; room service and a 24-hour front desk",
      "Beach loungers and umbrellas with snorkeling from the shore",
      "Island-hopping tours start from the resort",
      "Free Wi-Fi is listed",
      "Breakfast available for an additional charge"
    ),
    areaNote = "Caramoan is a limestone-and-white-sand peninsula famous for its islands and coves, and as a filming location for international Survivor series. The resort is about 1.3 km from Patag Bay, with Tinago Cove also nearby.",
    howToGetThere = "Reach Naga, ride to Sabang Port, take a boat to Guijalo Port in Caramoan, then continue to the resort - roughly a full travel day from Manila.",
    routeSteps = listOf(
      "Get to Naga City: fly to Naga Airport (WNP) or take a Manila–Naga bus (about 8–9 hours).",
      "From Naga, take a van or bus to Sabang Port in San Jose, Camarines Sur (about 1–2 hours).",
      "Board a boat from Sabang to Guijalo Port, Caramoan (about 1 hour 45 minutes to 2 hours). Public boats generally run in the morning - the last is around 11 AM.",
      "From Guijalo, take a tricycle or jeepney toward the resort, or ask the resort to arrange the transfer, including any boat leg to the cove.",
      "Alternative routes: a weather-dependent road via the Fuentebella Highway (about 4–5 hours), or via Virac and San Andres, Catanduanes, then a boat across the channel."
    ),
    nearbyAttractions = listOf(
      "Patag Bay (about 1.3 km)",
      "Tinago Cove",
      "Hunongan Cove",
      "Katanawan Island",
      "Matukad, Lahos and Cagbalinad islands"
    ),
    nearbyActivities = listOf(
      "Caramoan island hopping",
      "Snorkeling",
      "Beach lounging and sunset walks",
      "Cove and limestone-cliff photography"
    ),
    bestTime = "Calmer seas from roughly March to June make boat transfers and island hopping smoother. Rough seas in typhoon season can halt boats altogether.",
    travelConsiderations = listOf(
      "Some recent guests mention aging facilities, limited food choices after the first day and boat-only access - ask the resort about current conditions before you go.",
      "Public boats from Sabang leave in the morning; arrive early to avoid a forced overnight.",
      "An environmental fee is collected on arrival in Caramoan.",
      "Low tide can make Sabang boarding awkward - expect to wade or be carried to the boat."
    ),
    prepInfo = listOf(
      "Bring cash - ATMs and card payments are limited on the peninsula.",
      "Pack a dry bag, water shoes and motion-sickness tablets for the boat.",
      "Confirm meal arrangements and transfer schedule with the resort ahead of time."
    ),
    colorHex = "#00838F",
    imageId = "stay_resort_02"
  ),

  Accommodation(
    id = "stay_resort_03",
    name = "Elysia Beach Resort",
    type = "Resort",
    location = "Pangpang, Donsol",
    province = "Sorsogon",
    shortDescription = "A small beachfront resort in Donsol, a short walk from the Whale Shark Interaction Center, with a pool, restaurant and balconied rooms.",
    overview = "Elysia Beach Resort, built in 2010, sits on the beach in Barangay Pangpang in Donsol - the town known worldwide for its butanding (whale shark) encounters. It has 15 air-conditioned rooms in four low buildings, each with a balcony, plus an outdoor pool (open 7 AM to 9 PM), a garden, a terrace, a bar and a restaurant.\n\nThe Donsol Whale Shark Interaction Center is about 0.7 km away - roughly an 8-minute walk - so joining the early tour registration is easy.",
    facts = listOf(
      QuickFact("Setting", "Beachfront, Pangpang, Donsol"),
      QuickFact("Size", "15 rooms in 4 buildings"),
      QuickFact("Whale shark season", "Roughly November to May"),
      QuickFact("Interaction Center", "About 0.7 km (8-minute walk)"),
      QuickFact("Nearest hub", "Legazpi / DRP, about 50–100 minutes by road")
    ),
    highlights = listOf(
      "On the beach, with beach umbrellas",
      "Outdoor pool with pool umbrellas (7 AM–9 PM)",
      "Restaurant and bar/lounge",
      "Every room has a balcony, air-conditioning, a fridge and cable TV",
      "Free Wi-Fi in public areas and free parking",
      "Airport shuttle on request (fee) and luggage storage",
      "Smoke-free property"
    ),
    areaNote = "Donsol is Sorsogon's northern gateway, on a stretch of coast between Legazpi and the Burias Pass. Around the resort are dive and whale shark operators, small restaurants and the Donsol River, where evening firefly-watching cruises are offered.",
    howToGetThere = "From Legazpi it is roughly an hour by van or bus toward Donsol, then a short tricycle ride to Pangpang.",
    routeSteps = listOf(
      "Fly to Bicol International Airport (DRP) in Daraga, or ride a Manila–Legazpi bus.",
      "Take a van, bus or private transfer to Donsol (about 1 hour from Legazpi city; longer from the airport, depending on traffic).",
      "Tricycle to Pangpang and the resort - the Interaction Center is only about 700 meters away.",
      "Ask the resort about its airport shuttle service, which is available on request for a fee."
    ),
    nearbyAttractions = listOf(
      "Donsol Whale Shark Interaction Center (0.7 km)",
      "Donsol Public Market (about 3–4 km)",
      "Peralta Park",
      "Donsol River firefly watching",
      "Ticao and Burias Pass waters"
    ),
    nearbyActivities = listOf(
      "Butanding (whale shark) interaction tour",
      "Scuba diving and snorkeling",
      "Firefly river cruise",
      "Boat tours"
    ),
    bestTime = "The best window for whale shark encounters is roughly November to May. The tours are wildlife-dependent, so sightings are never guaranteed.",
    travelConsiderations = listOf(
      "Whale shark trips are arranged through the Interaction Center with registered boats and guides.",
      "Follow the guides' rules - no touching, no flash photography, and a respectful distance from the animals.",
      "The resort's front desk has limited hours; tell the staff your arrival time in advance.",
      "Sightings vary by day and season, so leave more than one day for the tour."
    ),
    prepInfo = listOf(
      "Bring reef-safe sunscreen and a rash guard.",
      "Carry cash for tour fees, tricycles and small eateries.",
      "Reserve airport transfers early - the shuttle is on request, not scheduled."
    ),
    colorHex = "#00838F",
    imageId = "stay_resort_03"
  ),

  Accommodation(
    id = "stay_resort_04",
    name = "Twin Rock Beach Resort",
    type = "Resort",
    location = "Igang, Virac",
    province = "Catanduanes",
    shortDescription = "Catanduanes' adventure-beach resort in Igang, named for the twin rock formations offshore, with pools, a zipline, a climbing wall and kayaks.",
    overview = "Twin Rock Beach Resort sits along Igang Beach south of Virac town, only a short ride from the airport. It is named for the two rock formations just offshore, which visitors can reach by kayak.\n\nBeyond the cream-colored sand, the resort is known for its adventure facilities: swimming pools, a zipline, a zipbike, a 40-foot climbing wall, a canopy walk, ATVs and an obstacle course, plus water-sports gear and a customized vintage Volkswagen van that guests can drive around.",
    facts = listOf(
      QuickFact("Setting", "Beachfront, Igang, Virac"),
      QuickFact("Facilities", "Pools, zipline, climbing wall, kayaks"),
      QuickFact("Beach", "About 60 meters from the rooms"),
      QuickFact("Nearest hub", "Virac Airport (VRC), about 6.3 km"),
      QuickFact("Good for", "Active travelers, families, groups")
    ),
    highlights = listOf(
      "Twin rock formations reachable by kayak",
      "Zipline, zipbike, climbing wall, canopy walk and obstacle course",
      "Swimming pools and water-sports equipment",
      "24-hour check-in and 24-hour room service are listed",
      "Airport transfer on request",
      "Free Wi-Fi, air-conditioned rooms and family rooms",
      "Tour desk and rental car options"
    ),
    areaNote = "Igang lies on the southern coast of Virac, the capital of Catanduanes. Virac's other beaches - Marilima, Talisoy, Batag and Mamangal - are nearby, and the Old Capitol Building in town houses the provincial tourism office and the Museo de Catanduanes.",
    howToGetThere = "Fly to Virac Airport (VRC), or take a bus to Tabaco and a RORO ferry to Virac, then a short tricycle or resort transfer to Igang.",
    routeSteps = listOf(
      "Fly to Virac Airport (VRC), about 1–1.5 hours from Manila and often via Legazpi.",
      "Or ride a bus to Tabaco, Albay, then take a RORO ferry (about 2 hours) to Virac.",
      "From the airport or pier, take a tricycle or arrange a resort transfer to Igang - the airport is about 6 km away.",
      "Register at the provincial tourism office at the Old Capitol Building if asked."
    ),
    nearbyAttractions = listOf(
      "Igang Beach (60 m)",
      "Old Capitol Building and Museo de Catanduanes",
      "Mamangal, Talisoy, Batag and Marilima beaches",
      "Marinawa Falls (about 7 km north of Virac)",
      "Boto ni Kurakog (about 7.5 km)",
      "Nagngangang Buwaya rock formation (about 8.3 km)"
    ),
    nearbyActivities = listOf(
      "Kayak to the twin rocks",
      "Zipline and zipbike rides",
      "Wall climbing",
      "Swimming and beach lounging",
      "Island tour by road to Puraran and Binurong Point"
    ),
    bestTime = "Catanduanes is nicknamed the Land of the Howling Winds. Typhoon season (roughly July to November) affects flights and boats; the calmer months (roughly March to June) are best for the beach and outdoor activities.",
    travelConsiderations = listOf(
      "Day visitors may pay an entrance fee - ask when you arrive.",
      "Flights and RORO crossings are weather-dependent, especially in typhoon season.",
      "Adventure activities may pause in rough weather or high seas.",
      "Puraran and Binurong Point are long day trips from Virac; plan the road time."
    ),
    prepInfo = listOf(
      "Bring water shoes for the rocky shoreline and a dry bag for kayak trips.",
      "Carry cash for tricycles, entrance fees and local eateries.",
      "Try local specialties such as tabogtabog, kaluko and koping in Virac."
    ),
    colorHex = "#00838F",
    imageId = "stay_resort_04"
  )
)
