package com.bicolandia.app.data

data class EmergencyContact(
  val label: String,
  val number: String,
  val iconKey: String,
  val note: String
)

val emergencyContacts = listOf(
  EmergencyContact("Police", "911", "police", "Unified national emergency line - dispatches the PNP."),
  EmergencyContact("Fire", "911", "fire", "Unified national emergency line - dispatches the Bureau of Fire Protection."),
  EmergencyContact("Medical", "911", "medical", "Unified national emergency line - ambulance and medical dispatch."),
  EmergencyContact("Coast Guard / Maritime", "911", "coastguard", "For boat, ferry, or at-sea emergencies - also dispatches the Philippine Coast Guard."),
  EmergencyContact("Philippine Red Cross", "143", "redcross", "Ambulance, rescue, and disaster response.")
)

data class TouristAssistanceContact(
  val name: String,
  val phone: String,
  val altPhone: String?,
  val email: String?,
  val location: String,
  val hours: String,
  val purpose: String
)

val touristAssistanceContacts = listOf(
  TouristAssistanceContact(
    name = "DOT Tourist Assistance Call Center",
    phone = "151-TOUR (151-8687)",
    altPhone = "+63 995 835 5155",
    email = "touristassistance@tourism.gov.ph",
    location = "Nationwide - Department of Tourism",
    hours = "24/7",
    purpose = "The national one-stop hotline for tourist concerns, information, and assistance anywhere in the Philippines."
  ),
  TouristAssistanceContact(
    name = "DOT Regional Office V (Bicol)",
    phone = "(052) 742-5004",
    altPhone = "0917 705 9335",
    email = null,
    location = "Regional Center Site, Rawis, Legazpi City",
    hours = "Government office hours",
    purpose = "The regional tourism office covering all six Bicol provinces."
  ),
  TouristAssistanceContact(
    name = "Legazpi City Tourism Service Unit",
    phone = "(052) 742-6933",
    altPhone = null,
    email = "legazpitourismservices@gmail.com",
    location = "Legazpi City Hall",
    hours = "Government office hours",
    purpose = "City-level tourism desk for Legazpi, the main gateway to Bicol."
  )
)

val transportationReminders = listOf(
  "Check transportation availability before long trips - schedules can change with little notice.",
  "Local bus and van schedules may vary, especially outside major routes.",
  "Carry small bills for tricycles, jeepneys, and van fares.",
  "Boat travel can depend on weather and sea conditions - delays are common in the rainy season.",
  "Confirm the correct terminal and route before departure."
)

val travelPrecautions = listOf(
  "Keep valuables secure, especially in crowded terminals and markets.",
  "Carry enough cash - electronic payment isn't always reliable outside major towns.",
  "Keep your phone and power bank charged, especially before trips to remote areas.",
  "Check the weather before outdoor or sea travel.",
  "Follow local rules and instructions at tourist sites."
)

val goodToKnowLocalInfo = listOf(
  "Weather can affect outdoor and sea travel, especially from June to December.",
  "Transportation availability can vary by location and time of day.",
  "Remote destinations may have weaker mobile signal.",
  "Some destinations require multiple transportation transfers.",
  "Local transportation primarily runs on cash.",
  "Attractions may have their own schedules or entry rules - checking ahead helps."
)

const val essentialsSourceNote = "Contact details reflect official sources at time of writing - please confirm before you travel, as numbers can change."
