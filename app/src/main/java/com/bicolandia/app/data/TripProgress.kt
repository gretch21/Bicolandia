package com.bicolandia.app.data

data class ProvinceProgress(
    val province: Province,
    val visited: Int,
    val total: Int,
    val stars: Int,           // 0..3
    val starTwoAt: Int,       // visits needed for the 2nd star
    val starThreeAt: Int      // visits needed for the 3rd star
)

data class TripBadge(
    val id: String,
    val title: String,
    val hint: String,         // what to do to earn it
    val iconKey: String,
    val earned: Boolean
)

data class TripLevel(val name: String, val current: Int, val nextAt: Int?, val nextName: String?)

object TripProgress {
    // Journey order used on the map: the way most travelers enter Bicol from Manila
    val journeyOrder = listOf("camnorte", "camsur", "albay", "sorsogon", "masbate", "catanduanes")

    private val items: List<TrackItem> by lazy { allTrackItems() }
    private val itemById: Map<String, TrackItem> by lazy { items.associateBy { it.id } }

    fun totalPlaces() = items.size
    fun visitedItems(): List<TrackItem> = items.filter { AppStore.visited.contains(it.id) }
    fun visitedCount() = visitedItems().size
    fun placesIn(provinceId: String): List<TrackItem> = items.filter { it.provinceId == provinceId }

    private fun categoryOf(id: String): String = findPlaceById(id)?.category ?: ""

    fun provinceProgress(provinceId: String): ProvinceProgress? {
        val p = provinces.find { it.id == provinceId } ?: return null
        val all = placesIn(provinceId)
        val visited = all.count { AppStore.visited.contains(it.id) }
        val two = minOf(5, maxOf(2, (all.size + 3) / 4))
        val three = minOf(10, maxOf(halfOf(all.size), two + 1))
        val stars = when {
            visited >= three -> 3
            visited >= two -> 2
            visited >= 1 -> 1
            else -> 0
        }
        return ProvinceProgress(p, visited, all.size, stars, two, three)
    }

    private fun halfOf(total: Int) = (total + 1) / 2

    fun allProvinceProgress(): List<ProvinceProgress> = journeyOrder.mapNotNull { provinceProgress(it) }

    fun level(): TripLevel {
        val n = visitedCount()
        val steps = listOf(0 to "Tourist", 1 to "Traveler", 5 to "Explorer", 15 to "Adventurer", 30 to "Bicolano at Heart")
        val idx = steps.indexOfLast { n >= it.first }
        val next = steps.getOrNull(idx + 1)
        return TripLevel(steps[idx].second, n, next?.first, next?.second)
    }

    fun badges(): List<TripBadge> {
        val v = visitedItems()
        fun count(kind: String) = v.count { it.kind == kind }
        val volcano = v.any { categoryOf(it.id) == "Volcanoes" }
        val shore = v.count { categoryOf(it.id) == "Beaches" || categoryOf(it.id) == "Islands" }
        val provinceStars = allProvinceProgress()

        val list = mutableListOf(
            TripBadge("first_steps", "First Steps", "Mark your first place as visited", "flag", v.isNotEmpty()),
            TripBadge("volcano", "Volcano Chaser", "Visit a volcano", "volcano", volcano),
            TripBadge("shore", "Beach Hopper", "Visit 2 beaches or islands", "beach", shore >= 2),
            TripBadge("foodie", "Foodie", "Visit 3 restaurants", "food", count("Restaurant") >= 3),
            TripBadge("festival", "Festival Goer", "Attend a festival", "festival", count("Festival") >= 1),
            TripBadge("thrill", "Thrill Seeker", "Try 2 activities", "adventure", count("Activity") >= 2),
            TripBadge("stay", "Cozy Traveler", "Stay at a place from Stay & Rest", "stay", count("Stay") >= 1)
        )
        provinceStars.forEach {
            list.add(TripBadge("prov_${it.province.id}", "${it.province.name} Explorer", "Earn your first star in ${it.province.name}", "pin", it.stars >= 1))
        }
        list.add(TripBadge("trailblazer", "Bicol Trailblazer", "Earn a star in all 6 provinces", "route", provinceStars.all { it.stars >= 1 }))
        list.add(TripBadge("legend", "Bicol Legend", "Earn 3 stars in all 6 provinces", "trophy", provinceStars.all { it.stars >= 3 }))
        return list
    }

    fun earnedBadgeCount() = badges().count { it.earned }
    fun provincesTouched() = allProvinceProgress().count { it.stars >= 1 }
}
