package com.bicolandia.app.data

internal val beachStays: List<Accommodation> = listOf(

  Accommodation(
    id = "stay_beach_01",
    name = "Majestic Puraran Beach Resort",
    type = "Beach Stay",
    location = "Puraran Beach, Baras",
    province = "Catanduanes",
    shortDescription = "A beachfront lodge on Puraran Beach, home of the famous \"Majestics\" surf break, with a garden, a restaurant and a relaxed surf-town feel.",
    overview = "Majestic Puraran Beach Resort faces the sand in Puraran, the small cove in Baras that has drawn surfers since Australian surfers discovered it in 1979. Listings mention a garden, an on-site restaurant serving American and Filipino dishes, and free private parking.\n\nPuraran is best known for \"Majestics,\" a powerful right-hand reef-break barrel that peaks in the surf season. Non-surfers come for the wide, uncrowded cream-colored beach, the rock formations and the slow island pace.",
    facts = listOf(
      QuickFact("Setting", "Beachfront, Puraran Cove, Baras"),
      QuickFact("Surf season", "Roughly July to October (peak September–October)"),
      QuickFact("Distance", "About 1 hour by road from Virac"),
      QuickFact("Nearest hub", "Virac Airport (VRC)"),
      QuickFact("Good for", "Surfers, beach lovers, quiet getaways")
    ),
    highlights = listOf(
      "Directly on Puraran Beach",
      "Garden and on-site restaurant",
      "Free private parking",
      "Steps from the Majestics surf break",
      "Nearby Tilod cove offers gentler waves for practice"
    ),
    areaNote = "Puraran is about a 5-kilometer drive from Baras town on a paved road. From the beach, a short boat ride reaches the three-tiered Nahulugan Falls, and Binurong Point, a coastal viewpoint, is also in Baras.",
    howToGetThere = "Fly or ferry to Virac, then ride a van or jeep to Baras (about 1 hour) and a tricycle for the last stretch to Puraran.",
    routeSteps = listOf(
      "Fly to Virac Airport (VRC), or take a bus to Tabaco and a RORO ferry (about 2 hours) to Virac.",
      "From Virac, take a van, jeep or private ride toward Baras (about 1 hour); the road is paved.",
      "From Baras town, take a tricycle for the roughly 5-kilometer ride to Puraran (10–15 minutes); some jeeps go straight to Puraran.",
      "Ask the resort about arrival times, since transport thins out later in the day."
    ),
    nearbyAttractions = listOf(
      "Majestics surf break",
      "Tilod cove",
      "Nahulugan Falls (boat from Puraran)",
      "Binurong Point, Baras",
      "Bato Church (on the road back toward Virac)"
    ),
    nearbyActivities = listOf(
      "Surfing and surf lessons",
      "Beach walks and photography",
      "Boat ride to Nahulugan Falls",
      "Sunrise viewing at Binurong Point"
    ),
    bestTime = "Surf season runs roughly July to October, with September and October the strongest. Typhoon season overlaps, so check weather and flights closely.",
    travelConsiderations = listOf(
      "Majestics is a powerful reef break for experienced surfers; beginners should use Tilod cove and a local instructor.",
      "Surf season overlaps with typhoon season - flights and ferries can be disrupted.",
      "Public transport gets sparse later in the day; plan your arrival in daylight.",
      "Services are limited - bring what you need for a beach-town stay."
    ),
    prepInfo = listOf(
      "Bring cash; ATMs are far from Puraran.",
      "Pack reef booties for rocky shorelines and reef-break sessions.",
      "Wear a rash guard and reef-safe sunscreen."
    ),
    colorHex = "#0288D1",
    imageId = "stay_beach_01"
  ),

  Accommodation(
    id = "stay_beach_02",
    name = "Ticao Altamar Boutique Resort",
    type = "Beach Stay",
    location = "Ticao Island, San Jacinto",
    province = "Masbate",
    shortDescription = "A secluded beach resort on Ticao Island with a rooftop terrace, pool and sea views - a base for Manta Bowl dives and Catandayagan Falls.",
    overview = "Ticao Altamar Boutique Resort sits on Ticao Island near San Jacinto. Listings describe a beach location, a rooftop terrace, a pool and breakfast for a fee. A past guest noted that the resort is fairly secluded: from the terminal, guests rode motorbikes along a short dirt stretch to reach it.\n\nTicao is known to divers as the Manta Capital of the Philippines. The Manta Bowl seamount lies about 7 km offshore, and Catandayagan Falls in San Jacinto is unusual for spilling directly into the sea.",
    facts = listOf(
      QuickFact("Setting", "Beachfront, Ticao Island"),
      QuickFact("Facilities", "Pool, rooftop terrace, breakfast (for a fee)"),
      QuickFact("Manta season", "Roughly March to June"),
      QuickFact("Getting in", "Boat from Masbate City or Pilar, Sorsogon"),
      QuickFact("Good for", "Divers, quiet island escapes")
    ),
    highlights = listOf(
      "Beach location with sea views",
      "Rooftop terrace",
      "Pool",
      "Breakfast available (additional charge)",
      "Access to Manta Bowl diving and island tours",
      "Secluded setting, a short ride from the San Jacinto terminal"
    ),
    areaNote = "Ticao is the smallest of Masbate's three main islands and a tranquil, rural place with waterfalls, secluded beaches and lush forest. Four towns share the island, including San Jacinto, Monreal, San Fernando and Batuan, and the island group is flanked by the Ticao and Burias passes.",
    howToGetThere = "Fly to Masbate City and take a boat to Ticao, or go by land to Pilar, Sorsogon and take a ferry. Services are limited, so arrange transfers in advance.",
    routeSteps = listOf(
      "Fly to Masbate Airport (MBT), or ride a bus/van to Pilar, Sorsogon.",
      "Board a boat or ferry to Ticao Island: from Masbate City by boat, or from Pilar Port by ferry (about 1.5 hours to Monreal; ferries to San Jacinto also operate).",
      "From the San Jacinto terminal, arrange a pickup with the resort - a short motorbike ride over a dirt stretch has been reported.",
      "Confirm ferry days and times before you travel, as services are limited."
    ),
    nearbyAttractions = listOf(
      "San Jacinto Port and marina",
      "Manta Bowl seamount (about 7 km offshore)",
      "Catandayagan Falls, San Jacinto",
      "San Jacinto Parish Church",
      "Monreal, home of the pre-colonial Monreal Stones"
    ),
    nearbyActivities = listOf(
      "Manta ray diving at Manta Bowl",
      "Falls-by-boat trip to Catandayagan",
      "Island tour by motorbike",
      "Snorkeling and beach time"
    ),
    bestTime = "March to June is the best window for manta rays and clearer diving visibility. Typhoon season (about July to October) is best avoided.",
    travelConsiderations = listOf(
      "Ferry and boat services to Ticao are limited - check schedules and arrange them ahead.",
      "The resort is secluded; food and transport options nearby are limited.",
      "Dive conditions and manta sightings vary by season and tide.",
      "Sea conditions can change quickly - build in a buffer day."
    ),
    prepInfo = listOf(
      "Bring cash; card payments and ATMs are limited on the island.",
      "Book dives through a licensed operator and bring your certification card.",
      "Pack a dry bag and motion-sickness tablets for boat crossings."
    ),
    colorHex = "#0288D1",
    imageId = "stay_beach_02"
  ),

  Accommodation(
    id = "stay_beach_03",
    name = "Kendi's Beach Garden",
    type = "Beach Stay",
    location = "Near Rizal Beach, Gubat",
    province = "Sorsogon",
    shortDescription = "A self-catering garden cottage with a pool close to Rizal Beach in Gubat, Bicol's east-coast surf town, with room for up to eight guests.",
    overview = "Kendi's Beach Garden is a host-run cottage stay in Gubat, listed as an entire cottage with two bedrooms, five beds and two bathrooms for up to eight guests. It is one of the few places in the area with a pool, and it offers free garage parking, self check-in with the building staff, and sunrise and full-moon views from the deck.\n\nIt is close to Rizal Beach - a 5-kilometer stretch with a sandy floor that is friendly for first-time surfers - and to the surf camp, the Gubat Heritage Museum and the Tulay sa Tibo Mangrove Reserve.",
    facts = listOf(
      QuickFact("Setting", "Garden cottage near Rizal Beach, Gubat"),
      QuickFact("Capacity", "Up to 8 guests (2 bedrooms, 5 beds, 2 baths)"),
      QuickFact("Perks", "Pool, garage parking, self check-in"),
      QuickFact("Surf season", "Year-round for learners; strongest roughly Oct–Mar"),
      QuickFact("Good for", "Families, friend groups, surf trips")
    ),
    highlights = listOf(
      "Whole cottage to yourselves - good for groups and families",
      "Pool in a garden setting",
      "Deck with sunrise and full-moon views",
      "Free garage parking and self check-in with building staff",
      "Close to Rizal Beach and the surf camp",
      "Near Gubat Heritage Museum, Tulay sa Tibo Mangrove Reserve and Agoho Forest Reserve"
    ),
    areaNote = "Gubat sits on Sorsogon's east coast facing the Pacific, which makes it Bicol's year-round beginner-friendly surf town; the annual Gubat October Swellfest draws surfers each October. Nearby are Bulusan Lake, hot and cold springs, Paguriran Island and the Agoho forest reserve.",
    howToGetThere = "Reach Sorsogon City, take a jeepney to Gubat (about 45 minutes), then a tricycle to the beach. Buses from Manila also serve Gubat.",
    routeSteps = listOf(
      "Get to Sorsogon: fly Manila to Legazpi (DRP) and take a bus or van to Sorsogon City (about 1.5–2 hours), or ride a Manila–Sorsogon bus (about 10–12 hours).",
      "From Sorsogon City, ride a jeepney to Gubat town (about 45 minutes).",
      "From Gubat town proper, take a tricycle to Rizal Beach and the cottage.",
      "Some Manila buses continue directly to Gubat - check with the operators."
    ),
    nearbyAttractions = listOf(
      "Rizal Beach (5-km shoreline)",
      "Gubat Heritage Museum",
      "Tulay sa Tibo Mangrove Reserve",
      "Agoho Forest Reserve",
      "Bulusan Lake",
      "Paguriran Island"
    ),
    nearbyActivities = listOf(
      "Surf lessons at the Rizal Beach surf camp",
      "Mangrove walk",
      "Hot and cold springs visit",
      "Beach time and sunrise watching"
    ),
    bestTime = "Rizal Beach is beginner-friendly year-round; stronger swells usually run roughly October to March. Early morning is the best time for sunrise and calmer water.",
    travelConsiderations = listOf(
      "This is a self-catering cottage - confirm meals, kitchen access and house rules with the host.",
      "The exact address is shared after reservation; coordinate directions in advance.",
      "Surf conditions vary; take lessons before paddling out if you are a beginner.",
      "Amenities can vary between listings - verify what is included."
    ),
    prepInfo = listOf(
      "Bring groceries or plan meals - the cottage is a home-style stay, not a restaurant resort.",
      "Carry cash for tricycles, surf lessons and local eateries.",
      "Bring reef-safe sunscreen and a rash guard."
    ),
    colorHex = "#0288D1",
    imageId = "stay_beach_03"
  ),

  Accommodation(
    id = "stay_beach_04",
    name = "Gota Village Resort",
    type = "Beach Stay",
    location = "Ilawod (Gota area), Caramoan",
    province = "Camarines Sur",
    shortDescription = "A resort in the Gota area of Caramoan, close to Gota Beach, with a restaurant, a 24-hour front desk and easy access to the peninsula's island-hopping tours.",
    overview = "Gota Village Resort sits in the Gota area of Caramoan, close to Gota Beach, one of the beaches that helped make the peninsula famous. Listings describe a restaurant, free self-parking, a 24-hour front desk, free Wi-Fi in public areas and dry-cleaning service.\n\nCaramoan is known for its limestone islands, white-sand coves and clear water, and has been a filming location for international Survivor series. The resort is a convenient base for joining island-hopping trips.",
    facts = listOf(
      QuickFact("Setting", "Gota area, close to Gota Beach"),
      QuickFact("Facilities", "Restaurant, parking, 24-hour front desk"),
      QuickFact("Getting in", "Naga → Sabang Port → boat to Guijalo Port"),
      QuickFact("Best months", "Roughly March to June for calm seas"),
      QuickFact("Good for", "Island hopping, groups, beach stays")
    ),
    highlights = listOf(
      "Located in the Gota area, close to Gota Beach",
      "On-site restaurant",
      "Free self-parking",
      "24-hour front desk",
      "Free Wi-Fi in public areas and dry-cleaning service"
    ),
    areaNote = "The Gota area is one of Caramoan's best-known beaches. Island-hopping tours reach Matukad, Lahos, Hunongan, Cagbalinad and other islands, and fast-craft group transfers from Nato Port in Sagñay have been arranged through the Camarines Sur Watersports Complex - ask if that option is still offered.",
    howToGetThere = "Reach Naga, ride to Sabang Port, take a boat to Guijalo Port in Caramoan, then continue to Gota by tricycle or transfer.",
    routeSteps = listOf(
      "Get to Naga City: fly to Naga Airport (WNP) or take a Manila–Naga bus (about 8–9 hours).",
      "From Naga, take a van or bus to Sabang Port in San Jose, Camarines Sur (about 1–2 hours).",
      "Board a boat to Guijalo Port, Caramoan (about 1 hour 45 minutes to 2 hours); public boats run in the morning, last around 11 AM.",
      "From Guijalo, take a tricycle or arrange a resort transfer to the Gota area.",
      "Alternative: a weather-dependent road route via the Fuentebella Highway, or via Virac and San Andres, Catanduanes."
    ),
    nearbyAttractions = listOf(
      "Gota Beach",
      "Matukad Island",
      "Lahos Island",
      "Hunongan Cove",
      "Cagbalinad Island"
    ),
    nearbyActivities = listOf(
      "Caramoan island hopping",
      "Snorkeling",
      "Beach lounging",
      "Photography of limestone islands"
    ),
    bestTime = "Calmer seas from roughly March to June make boat transfers and island hopping smoother. Typhoon season can stop boats entirely.",
    travelConsiderations = listOf(
      "Public boats from Sabang run in the morning - start early.",
      "Land routes are weather-dependent; confirm road conditions.",
      "An environmental fee is collected on arrival in Caramoan.",
      "Limited services on the peninsula - bring essentials."
    ),
    prepInfo = listOf(
      "Bring cash; ATMs are scarce in Caramoan.",
      "Pack a dry bag, water shoes and motion-sickness tablets.",
      "Confirm meal and transfer arrangements with the resort."
    ),
    colorHex = "#0288D1",
    imageId = "stay_beach_04"
  )
)

internal val mountainStays: List<Accommodation> = listOf(

  Accommodation(
    id = "stay_mountain_01",
    name = "Panicuason Hot Spring Resort",
    type = "Mountain Stay",
    location = "Brgy. Panicuason, Naga City (foot of Mt. Isarog)",
    province = "Camarines Sur",
    shortDescription = "A nature resort at the foot of Mt. Isarog with hot-spring and cool mountain pools, ziplines and overnight rooms - a common base for Isarog treks.",
    overview = "Panicuason Hot Spring Resort is set beside Mt. Isarog National Park in Naga City. The City of Naga describes it as a nature-focused complex of hot springs, pools and adventure activities: cool, flowing pools fed from the mountain, hot-spring pools filled with mineral-rich sulfuric water from Isarog, and regular and bike ziplines.\n\nThe resort is open every day, with check-in from 3 PM and check-out at noon. It is about 15 km from central Naga and near the Panicuason trailhead used for Mt. Isarog climbs.",
    facts = listOf(
      QuickFact("Setting", "Foot of Mt. Isarog, Brgy. Panicuason"),
      QuickFact("Facilities", "Hot-spring pools, cool pools, ziplines"),
      QuickFact("Check-in", "From 3 PM · check-out at noon · open daily"),
      QuickFact("Distance", "About 15 km from central Naga City"),
      QuickFact("Good for", "Relaxation, families, hikers")
    ),
    highlights = listOf(
      "Hot-spring pools with mineral-rich sulfuric water",
      "Cool mountain-fed pools for hot days",
      "Regular and bike ziplines",
      "Overnight rooms with access to the pools",
      "Beside Mt. Isarog National Park and its trailhead",
      "Forest setting with mountain air"
    ),
    areaNote = "Barangay Panicuason is home to several resorts and the jump-off for Mt. Isarog (1,966 m), a dormant volcano with mossy forest, waterfalls and, on a clear day, a view of Mayon from the summit. Nearby are Malabsay Falls for a hike and other small resorts such as Stonehouse Gardens.",
    howToGetThere = "From Naga City, ride a tricycle to the jeepney terminal and then a jeepney to Panicuason (about 30–45 minutes), or go by car or private transfer.",
    routeSteps = listOf(
      "Get to Naga City: fly to Naga Airport (WNP) or take a Manila–Naga bus (about 8–9 hours).",
      "From central Naga (near the city market/People's Mall), take a tricycle to the Panicuason jeepney terminal (about 10–15 minutes).",
      "Board a jeepney to Panicuason (about 30–45 minutes; jeepneys leave when full) and tell the driver to stop at the resort.",
      "Renting a car or hiring a private ride is faster and avoids waiting for the jeepney to fill."
    ),
    nearbyAttractions = listOf(
      "Mt. Isarog National Park (trailhead)",
      "Malabsay Falls",
      "Stonehouse Gardens Resort",
      "Naga City center (about 15 km)",
      "Basilica of Our Lady of Peñafrancia"
    ),
    nearbyActivities = listOf(
      "Hot-spring soaking",
      "Zipline and bike zipline rides",
      "Mt. Isarog trek (permit required)",
      "Malabsay Falls hike"
    ),
    bestTime = "The drier months (roughly March to May) are best for trails and waterfalls. Cool evenings make hot-spring soaks especially pleasant year-round.",
    travelConsiderations = listOf(
      "Mt. Isarog climbs need a permit and a guide - arrange them well in advance.",
      "Jeepneys leave only when full, so allow extra time or hire a private ride.",
      "People with heart conditions or high blood pressure should check with a doctor before long hot soaks.",
      "Trails and waterfalls can be slippery after rain."
    ),
    prepInfo = listOf(
      "Bring swimwear, a towel and a change of clothes.",
      "Pack light layers; it is noticeably cooler near the mountain.",
      "Carry cash for tricycles and small stores."
    ),
    colorHex = "#2E7D32",
    imageId = "stay_mountain_01"
  ),

  Accommodation(
    id = "stay_mountain_02",
    name = "Balay Buhay sa Uma Bee Farm",
    type = "Mountain Stay",
    location = "Purok Bacolod, San Roque, Bulusan (slopes of Mt. Bulusan)",
    province = "Sorsogon",
    shortDescription = "A DOT-accredited agri-tourism farm on Mt. Bulusan's slopes with native cottages, a bee farm, farm-to-table dining and a cold mountain spring pool.",
    overview = "Balay Buhay sa Uma, meaning \"Farm Life Home,\" is a three-hectare agri-tourism site on the slopes of Mt. Bulusan that combines a resort, a bee farm and a restaurant. It offers around 16 rooms and two small houses, including native cottages with balconies, and the air is cool enough at night that a blanket is welcome.\n\nThe farm's dishes are produced and harvested on site, honey and honey-based products are made from its own bees, and guests can taste honey, feed fish, garden or swim in Batis sa Uma, a cold spring-fed pool. The atmosphere is quiet and unhurried.",
    facts = listOf(
      QuickFact("Setting", "Mt. Bulusan slopes, San Roque, Bulusan"),
      QuickFact("Size", "About 16 rooms plus 2 small houses on 3 hectares"),
      QuickFact("Feature", "Bee farm, organic dining, cold spring pool"),
      QuickFact("Distance", "About 2.8 km from Bulusan Lake"),
      QuickFact("Good for", "Quiet retreats, nature and family stays")
    ),
    highlights = listOf(
      "DOT-accredited agri-tourism farm site",
      "Native cottages with balconies, cool nights",
      "Bee farm with honey tasting and organic products",
      "Farm-to-table restaurant with local Bicolano dishes",
      "Batis sa Uma, a cold mountain spring-fed swimming area",
      "Gardens, fish ponds and quiet walking paths"
    ),
    areaNote = "Bulusan sits at the foot of Mt. Bulusan (about 1,565 m), whose Bulusan Lake is a short 2.8-km drive away with kayaking, a canopy walk and trekking. The town is reached by way of Gubat on Sorsogon's east coast.",
    howToGetThere = "Reach Sorsogon City, take a jeepney to Gubat and another to Bulusan, then continue to Purok Bacolod, Barangay San Roque.",
    routeSteps = listOf(
      "Fly to Bicol International Airport (DRP) or ride to Legazpi.",
      "Take a bus to the Sorsogon Grand Terminal (about 1 hour 20 minutes from Legazpi).",
      "From Sorsogon City, take a jeepney to Gubat, then another jeepney to Bulusan.",
      "Continue to Purok Bacolod in Barangay San Roque; ask locals for Balay Buhay or arrange a pickup with the farm."
    ),
    nearbyAttractions = listOf(
      "Bulusan Lake (about 2.8 km)",
      "Bulusan Volcano Natural Park",
      "Rizal Beach, Gubat (day trip)",
      "Irosin hot and cold springs (day trip)"
    ),
    nearbyActivities = listOf(
      "Honey tasting and farm tour",
      "Swimming in Batis sa Uma",
      "Kayaking and canopy walk at Bulusan Lake",
      "Garden and bird-watching walks"
    ),
    bestTime = "Drier months (roughly March to May) make hiking and lake activities easier. The farm stays cool at night even in the summer heat.",
    travelConsiderations = listOf(
      "The farm is a quiet, low-tech retreat - ask about Wi-Fi and power if they matter to you.",
      "Farm hours and dining hours may be limited; confirm meals before you arrive.",
      "Bulusan Volcano activity can affect access to the lake - check PHIVOLCS advisories.",
      "Reserve directly with the farm, which has its own website (balaybuhaysauma.com)."
    ),
    prepInfo = listOf(
      "Bring a light blanket or jacket for cool nights.",
      "Carry cash for meals, farm products and jeepneys.",
      "Pack insect repellent and water shoes for the spring pool."
    ),
    colorHex = "#2E7D32",
    imageId = "stay_mountain_02"
  ),

  Accommodation(
    id = "stay_mountain_03",
    name = "Casa Basilisa Hotel & Resort",
    type = "Mountain Stay",
    location = "San Rafael, Guinobatan (Mayon foothills)",
    province = "Albay",
    shortDescription = "A garden resort in the cool foothill country near Mayon, with two pools, a restaurant and bar, villa-style rooms and space for group gatherings.",
    overview = "Casa Basilisa Hotel & Resort sits inland in San Rafael, Guinobatan, on the foothill side of Mayon Volcano - a quieter, cooler alternative to staying in Legazpi city. It has about 15 guestrooms, including a three-bedroom villa that sleeps seven and pool-view rooms.\n\nFacilities include two outdoor pools and a children's pool, a restaurant and bar, room service, a garden, karaoke, charcoal grills, a picnic area, a banquet hall and meeting rooms. The compound is guarded, and its pools, grills and hall make it a natural fit for family gatherings.",
    facts = listOf(
      QuickFact("Setting", "Inland foothill area near Mayon"),
      QuickFact("Size", "About 15 guestrooms, including a 3-bedroom villa"),
      QuickFact("Facilities", "2 pools, kids' pool, restaurant, bar, grills"),
      QuickFact("Nearest hub", "Bicol Intl. Airport (DRP), about 35 minutes by car"),
      QuickFact("Good for", "Family gatherings, groups, quiet stays")
    ),
    highlights = listOf(
      "Two outdoor pools plus a children's pool",
      "Restaurant and bar/lounge with room service",
      "Garden, picnic area and charcoal grills",
      "Villa with three bedrooms for larger groups",
      "Banquet hall and meeting rooms for events",
      "Free Wi-Fi and free parking",
      "Guarded compound"
    ),
    areaNote = "Guinobatan lies on the slopes and foothills around Mayon. The resort is about 8 km from Kawa-Kawa Hill Park, a hilltop park near a Christ the Redeemer statue, and about 10 km from Sumlang Lake in Camalig, known for boat rides with Mayon views.",
    howToGetThere = "From Legazpi it is roughly a 25-minute drive; fly to Bicol International Airport (DRP), then take a taxi, van or a bus along the highway toward Guinobatan.",
    routeSteps = listOf(
      "Fly Manila to Bicol International Airport (DRP) (about 1 hour), or ride a Manila–Legazpi bus (about 9–10 hours).",
      "From the airport it is about a 35-minute drive to San Rafael, Guinobatan; from Legazpi city, about 25 minutes.",
      "Ask the resort for directions to Diversion Road in San Rafael, Guinobatan."
    ),
    nearbyAttractions = listOf(
      "Kawa-Kawa Hill Park (about 8 km)",
      "Sumlang Lake (about 10 km)",
      "Mayon Volcano foothills",
      "Legazpi City (about 20 km)"
    ),
    nearbyActivities = listOf(
      "Mayon viewing from the foothills",
      "Kawa-Kawa Hill visit",
      "Sumlang Lake boat ride",
      "Family gathering with grills"
    ),
    bestTime = "Mornings from roughly March to May offer the clearest Mayon views. The foothill setting is cooler in the evenings.",
    travelConsiderations = listOf(
      "The resort is inland - you'll need a ride to reach Legazpi's city sights.",
      "Check Mayon advisories before planning close-range activities.",
      "Group events may be busy on weekends and holidays.",
      "Confirm villa and pool hours when you arrange your stay."
    ),
    prepInfo = listOf(
      "Bring a light jacket for cooler evenings.",
      "Carry cash for tricycles, market stops and local eateries.",
      "Ask the resort about airport pickup options in advance."
    ),
    colorHex = "#2E7D32",
    imageId = "stay_mountain_03"
  ),

  Accommodation(
    id = "stay_mountain_04",
    name = "Hotel Pentaqua",
    type = "Mountain Stay",
    location = "Purok 7, Patag, Irosin",
    province = "Sorsogon",
    shortDescription = "A quiet rural guesthouse in Irosin with two pools, a garden and mountain-view rooms, near the Bulusan volcano country and Irosin's hot springs.",
    overview = "Hotel Pentaqua is a small, rural guesthouse in Patag, Irosin, in the highland country near Mt. Bulusan. It has five rooms - Basic, Comfort and a Comfort Studio that sleeps up to six - many with mountain views.\n\nFacilities include two outdoor pools, a bar and lounge, a snack bar, a terrace, a garden, a picnic area and charcoal grills, with hiking and biking trails around the property. The front desk is open from 7 AM to 10 PM, and it is a peaceful choice for travelers who want cool air and green surroundings.",
    facts = listOf(
      QuickFact("Setting", "Rural highlands, Patag, Irosin"),
      QuickFact("Size", "5 rooms (Basic, Comfort, Comfort Studio)"),
      QuickFact("Front desk", "7 AM–10 PM · check-in 1–10 PM · check-out noon"),
      QuickFact("Facilities", "2 pools, bar, garden, grills"),
      QuickFact("Good for", "Quiet retreats, small groups, hot-spring trips")
    ),
    highlights = listOf(
      "Mountain-view rooms and a Comfort Studio for up to six",
      "Two outdoor pools",
      "Bar and lounge, snack bar and a terrace",
      "Garden, picnic area and charcoal grills",
      "Hiking and biking trails around the property",
      "Free Wi-Fi and air-conditioned rooms",
      "Pets are not allowed; service animals are welcome"
    ),
    areaNote = "Irosin lies in the caldera-and-volcano country near Mt. Bulusan and is known for its hot and cold springs. Restaurants such as Sagurong are a short drive away, and Cogon Highlands Leisure Hub is about 18 minutes away.",
    howToGetThere = "Reach Sorsogon City or Irosin by bus or van, then take a tricycle to Purok 7 in Patag. Contact the hotel first for parking and directions.",
    routeSteps = listOf(
      "Fly to Bicol International Airport (DRP) or ride a bus to Legazpi.",
      "Take a bus or van toward Matnog (the route runs through Sorsogon City) and ask to be dropped in Irosin.",
      "From Irosin, take a tricycle to Purok 7, Patag.",
      "If arriving after 6 PM, contact the hotel in advance; parking must be reserved ahead."
    ),
    nearbyAttractions = listOf(
      "Irosin hot and cold springs",
      "Mt. Bulusan area",
      "Cogon Highlands Leisure Hub (about 18 minutes)",
      "Sagurong Restaurant (about 6 minutes)",
      "Bulusan Lake"
    ),
    nearbyActivities = listOf(
      "Hiking and biking trails",
      "Hot-spring soaking nearby",
      "Barbecue and picnic in the garden",
      "Bulusan Lake day trip"
    ),
    bestTime = "The drier months (roughly March to May) suit trails and lake visits. Evenings are cooler at this elevation year-round.",
    travelConsiderations = listOf(
      "Late arrivals after 6 PM require prior contact with the hotel.",
      "On-site parking must be reserved in advance.",
      "Rooms are few, so book early for weekends.",
      "Check PHIVOLCS advisories for Mt. Bulusan before hiking."
    ),
    prepInfo = listOf(
      "Bring light layers for cooler evenings.",
      "Carry cash - the property accepts cash and nearby options are limited.",
      "Pack insect repellent for trails and garden areas."
    ),
    colorHex = "#2E7D32",
    imageId = "stay_mountain_04"
  )
)
