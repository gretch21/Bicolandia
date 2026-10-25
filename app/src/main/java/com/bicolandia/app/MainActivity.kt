package com.bicolandia.app

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bicolandia.app.data.*
import com.bicolandia.app.screens.*
import com.bicolandia.app.ui.theme.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    AppStore.init(applicationContext)

    AuthStore.init(applicationContext)
    setContent {
      BicolandiaTheme {
        BicolandiaApp()
      }
    }
  }
}

private data class TabItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

private val leftTabs = listOf(
  TabItem("home", "Home", Icons.Filled.Home),
  TabItem("explore", "Explore", Icons.Filled.Explore)
)
private val rightTabs = listOf(
  TabItem("favorites", "Favorites", Icons.Filled.Favorite),
  TabItem("profile", "Profile", Icons.Filled.Person)
)

// ---- Hamburger drawer menu items (Home page menu button) ----
private val drawerItems = listOf(
  TabItem("home", "Home", Icons.Filled.Home),
  TabItem("explore", "Explore", Icons.Filled.Explore),
  TabItem("map", "Travel Guide", Icons.Filled.DirectionsBus),
  TabItem("tourist_essentials", "Tourist Essentials", Icons.Filled.HealthAndSafety),
  TabItem("itinerary", "Trip Planner", Icons.Filled.Route),
  TabItem("favorites", "Favorites", Icons.Filled.Favorite),
  TabItem("profile", "Profile", Icons.Filled.Person)
)

@Composable
fun BicolandiaApp() {
  val navController = rememberNavController()
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val scope = rememberCoroutineScope()
  val showAppChrome = currentRoute != "splash" && currentRoute != "onboarding" && currentRoute != "login" && currentRoute != "signup"

  ModalNavigationDrawer(
    drawerState = drawerState,
    gesturesEnabled = showAppChrome,
    drawerContent = {
      ModalDrawerSheet(drawerContainerColor = Color.White) {
        Column(Modifier.fillMaxHeight().padding(top = 28.dp)) {
          Row(Modifier.padding(horizontal = 24.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
              painter = painterResource(id = R.drawable.logo_mark),
              contentDescription = "Bicolandia",
              modifier = Modifier.size(38.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text("Bicolandia", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = BicolTextPrimary)
          }
          Spacer(Modifier.height(16.dp))
          HorizontalDivider(color = Color(0xFFF0F1F3))
          Spacer(Modifier.height(8.dp))
          drawerItems.forEach { item ->
            val selected = currentRoute == item.route
            NavigationDrawerItem(
              label = { Text(item.label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
              icon = { Icon(item.icon, contentDescription = null) },
              selected = selected,
              onClick = {
                scope.launch { drawerState.close() }
                navController.navigate(item.route) {
                  popUpTo("home") { inclusive = false }
                  launchSingleTop = true
                }
              },
              colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = BicolCoral.copy(alpha = 0.12f),
                selectedIconColor = BicolCoral,
                selectedTextColor = BicolCoral,
                unselectedIconColor = BicolTextSecondary,
                unselectedTextColor = BicolTextPrimary
              ),
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
          }
        }
      }
    }
  ) {
  Box(Modifier.fillMaxSize()) {
    NavHost(
      navController = navController,
      startDestination = "splash",
      modifier = Modifier.fillMaxSize()
    ) {
      composable("splash") {
        SplashScreen(onDone = {
          // Logged in -> straight to the dashboard; otherwise onboarding (first launch) or login
          val target = when {
            AuthStore.isLoggedIn -> "home"
            AuthStore.onboardingDone -> "login"
            else -> "onboarding"
          }
          navController.navigate(target) { popUpTo("splash") { inclusive = true } }
        })
      }
      composable("onboarding") {
        OnboardingScreen(onFinish = {
          AuthStore.markOnboardingDone()
          navController.navigate("login") {
            popUpTo("onboarding") { inclusive = true }
          }
        })
      }
      composable("login") {
        LoginScreen(
          onContinue = {
            navController.navigate("home") {
              popUpTo("login") { inclusive = true }
            }
          },
          onCreateAccount = { navController.navigate("signup") },
          onLogin = { email, password -> AuthStore.login(email, password) }
        )
      }
      composable("signup") {
        SignUpScreen(
          onBack = { navController.popBackStack() },
          onRegister = { name, email, password -> AuthStore.register(name, email, password) },
          onSignedUp = {
            navController.navigate("login") {
              popUpTo("login") { inclusive = true }
            }
          }
        )
      }
      composable("home") {
        HomeScreen(
          onMenuClick = { scope.launch { drawerState.open() } },
          onSeeAllDestinations = { navController.navigate("classification/destinations") },
          onSeeAllActivities = { navController.navigate("classification/activities") },
          onSeeAllCuisines = { navController.navigate("classification/cuisines") },
          onSeeAllFestivals = { navController.navigate("classification/festivals") },
          onPlaceClick = { place -> navController.navigate("place_detail/${place.id}") },
          onOpenTravelGuide = {
            navController.navigate("map") {
              popUpTo("home") { inclusive = false }
              launchSingleTop = true
            }
          },
          onOpenEssentials = {
            navController.navigate("tourist_essentials") {
              popUpTo("home") { inclusive = false }
              launchSingleTop = true
            }
          },
          onSearch = { q -> navController.navigate("search?q=${Uri.encode(q)}") }
        )
      }
      composable(
        "search?q={q}",
        arguments = listOf(navArgument("q") { type = NavType.StringType; defaultValue = "" })
      ) { backStackEntry ->
        SearchScreen(
          initialQuery = backStackEntry.arguments?.getString("q") ?: "",
          onBack = { navController.popBackStack() },
          onOpen = { route -> navController.navigate(route) }
        )
      }
      composable("map") { MapScreen() }
      composable("tourist_essentials") {
        TouristEssentialsScreen(
          onBack = { navController.popBackStack() },
          onOpenTravelGuide = {
            navController.navigate("map") {
              popUpTo("home") { inclusive = false }
              launchSingleTop = true
            }
          }
        )
      }
      composable("travel_guide/{placeId}") { backStackEntry ->
        val placeId = backStackEntry.arguments?.getString("placeId")
        MapScreen(initialPlaceId = placeId)
      }
      composable("itinerary") {
        ItineraryPlannerScreen(
          onBack = { navController.popBackStack() },
          onPlaceClick = { place -> navController.navigate("place_detail/${place.id}") }
        )
      }
      composable("favorites") {
        FavoritesScreen(onOpen = { route -> navController.navigate(route) })
      }
      composable("profile") {
        ProfileScreen(
          onMyTrips = { navController.navigate("my_trips") },
          onSavedGuides = { navController.navigate("saved_guides") },
          onAccount = { navController.navigate("account_settings") },
          onPrivacy = { navController.navigate("privacy_security") },
          onHelp = { navController.navigate("help_support") },
          onFavorites = {
            navController.navigate("favorites") {
              popUpTo("home") { inclusive = false }
              launchSingleTop = true
            }
          },
          onLogout = {
            AuthStore.logout()
            navController.navigate("login") { popUpTo("home") { inclusive = true } }
          }
        )
      }
      composable("my_trips") {
        BicolMapScreen(
          onBack = { navController.popBackStack() },
          onOpenRoute = { route -> navController.navigate(route) }
        )
      }
      composable("saved_guides") {
        SavedGuidesScreen(
          onBack = { navController.popBackStack() },
          onOpenGuide = { id -> navController.navigate("travel_guide/$id") },
          onBrowseGuides = { navController.navigate("map") { launchSingleTop = true } }
        )
      }
      composable("account_settings") { AccountSettingsScreen(onBack = { navController.popBackStack() }) }
      composable("privacy_security") { PrivacySecurityScreen(onBack = { navController.popBackStack() }) }
      composable("help_support") {
        HelpSupportScreen(
          onBack = { navController.popBackStack() },
          onOpenEssentials = { navController.navigate("tourist_essentials") { launchSingleTop = true } }
        )
      }

      // ---- Explore flow ----
      composable("explore") {
        ExploreHubScreen(
          onProvinceClick = { navController.navigate("province_list") },
          onDestinationCategory = { navController.navigate("classification/destinations") },
          onFestivalCategory = { navController.navigate("classification/festivals") },
          onActivityCategory = { navController.navigate("classification/activities") },
          onCuisineCategory = { navController.navigate("classification/cuisines") },
          onCultureCategory = { navController.navigate("classification/culture") },
          onStayCategory = { navController.navigate("classification/stay") }
        )
      }

      composable("province_list") {
        ProvinceListScreen(onBack = { navController.popBackStack() }) { province ->
          navController.navigate("province_detail/${province.id}")
        }
      }
      composable("province_detail/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id")
        val province = provinces.find { it.id == id }
        if (province != null) {
          ProvinceDetailScreen(
            province, onBack = { navController.popBackStack() },
            onTravelGuide = { p -> navController.navigate("travel_guide/${p.id}") }
          )
        } else {
          NotFoundScreen(onBack = { navController.popBackStack() })
        }
      }

      composable("classification/{categoryId}") { backStackEntry ->
        val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
        ClassificationScreen(
          categoryId = categoryId,
          onBack = { navController.popBackStack() }
        ) { catId, classificationId ->
          // Stay & Rest uses its own lightweight Accommodation model
          // (no ratings/reviews/booking), so it routes to its own
          // list screen instead of the generic PlaceDetail one.
          if (catId == "stay") {
            navController.navigate("stay_list/$classificationId")
          } else {
            navController.navigate("place_list/$catId/$classificationId")
          }
        }
      }

      composable("stay_list/{typeId}") { backStackEntry ->
        val typeId = backStackEntry.arguments?.getString("typeId") ?: ""
        StayListScreen(
          typeId = typeId,
          onBack = { navController.popBackStack() }
        ) { acc -> navController.navigate("stay_detail/${acc.id}") }
      }
      composable("stay_detail/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id") ?: ""
        val acc = findAccommodationById(id)
        if (acc != null) {
          StayDetailScreen(
            acc,
            onBack = { navController.popBackStack() },
            // No popUpTo here: Back returns to the stay being viewed
            onTravelGuide = { navController.navigate("map") { launchSingleTop = true } }
          )
        } else {
          NotFoundScreen(onBack = { navController.popBackStack() })
        }
      }

      composable("place_list/{categoryId}/{classificationId}") { backStackEntry ->
        val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
        val classificationId = backStackEntry.arguments?.getString("classificationId") ?: ""
        PlaceListScreen(
          categoryId = categoryId,
          classificationId = classificationId,
          onBack = { navController.popBackStack() }
        ) { place ->
          // Single-param route: a place can always be found by id alone,
          // so this link can never break even if called from a different
          // screen than the one that "owns" this place (Home, Favorites, etc).
          navController.navigate("place_detail/${place.id}")
        }
      }

      composable("place_detail/{placeId}") { backStackEntry ->
        val placeId = backStackEntry.arguments?.getString("placeId") ?: ""
        val place = findPlaceById(placeId)
        if (place != null) {
          PlaceDetailScreen(
            place, onBack = { navController.popBackStack() },
            onTravelGuide = { p -> navController.navigate("travel_guide/${p.id}") }
          )
        } else {
          NotFoundScreen(onBack = { navController.popBackStack() })
        }
      }
    }

    // ---- Bottom nav: floating oval/pill group - Home, Explore, [Map FAB pops above], Favorites, Profile ----
    if (showAppChrome) {
    Row(
      Modifier
        .align(Alignment.BottomCenter)
        .padding(horizontal = 22.dp)
        .padding(bottom = 20.dp)
        .fillMaxWidth()
        .height(70.dp)
        .shadow(elevation = 10.dp, shape = RoundedCornerShape(50))
        .clip(RoundedCornerShape(50))
        .background(Color.White),
      verticalAlignment = Alignment.CenterVertically
    ) {
      leftTabs.forEach { tab -> NavTab(tab, currentRoute, navController, Modifier.weight(1f)) }
      Spacer(Modifier.weight(1f)) // space reserved for the floating center FAB
      rightTabs.forEach { tab -> NavTab(tab, currentRoute, navController, Modifier.weight(1f)) }
    }

    // Raised circular Map button, centered, popping upward above the oval nav group
    Box(
      Modifier
        .align(Alignment.BottomCenter)
        .padding(bottom = 52.dp)
        .size(58.dp)
        .clip(CircleShape)
        .background(Color.White)
        .padding(4.dp)
        .clip(CircleShape)
        .background(if (currentRoute == "map") BicolDeepGreen else BicolCoral)
        .clickable {
          navController.navigate("map") {
            popUpTo("home") { inclusive = false }
            launchSingleTop = true
          }
        },
      contentAlignment = Alignment.Center
    ) {
      Icon(Icons.Filled.DirectionsBus, contentDescription = "Travel Guide", tint = Color.White, modifier = Modifier.size(24.dp))
    }
    }
  }
  }
}

@Composable
private fun NavTab(tab: TabItem, currentRoute: String?, navController: NavHostController, modifier: Modifier) {
  val selected = currentRoute == tab.route
  val tint = if (selected) BicolDeepGreen else BicolTextSecondary
  Column(
    modifier
      .fillMaxHeight()
      .clickable {
        navController.navigate(tab.route) {
          popUpTo("home") { inclusive = false }
          launchSingleTop = true
        }
      },
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Icon(tab.icon, contentDescription = tab.label, tint = tint, modifier = Modifier.size(22.dp))
    Spacer(Modifier.height(2.dp))
    Text(tab.label, color = tint, fontSize = 11.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
  }
}
