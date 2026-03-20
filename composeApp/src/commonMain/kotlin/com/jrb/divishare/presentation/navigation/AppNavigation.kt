package com.jrb.divishare.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jrb.divishare.presentation.component.BottomBar
import com.jrb.divishare.presentation.screens.forgotpassword.ForgotPasswordScreen
import com.jrb.divishare.presentation.screens.login.LoginScreen
import com.jrb.divishare.presentation.screens.onboarding.OnboardingScreen
import com.jrb.divishare.presentation.screens.registration.RegistrationScreen
import com.jrb.divishare.presentation.screens.splash.SplashScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = currentDestination?.hasRoute(Route.Home::class) == true ||
            currentDestination?.hasRoute(Route.Profile::class) == true ||
            currentDestination?.hasRoute(Route.Settings::class) == true

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                val bottomNavItems = listOf(
                    ItemBottomNav.Home,
                    ItemBottomNav.Profile,
                    ItemBottomNav.Settings
                )

                val currentRouteObject = when {
                    currentDestination.hasRoute(Route.Home::class) -> Route.Home
                    currentDestination.hasRoute(Route.Profile::class) -> Route.Profile
                    currentDestination.hasRoute(Route.Settings::class) -> Route.Settings
                    else -> null
                }

                BottomBar(
                    list = bottomNavItems,
                    currentRoute = currentRouteObject,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(Route.Home) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.Splash,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // --- 1. SPLASH & ONBOARDING ---
            composable<Route.Splash> {
                SplashScreen(
                    onNavigateToLogin = {
                        navController.navigate(Route.Login) {
                            popUpTo(Route.Splash) { inclusive = true }
                        }
                    },
                    onNavigateToHome = {
                        navController.navigate(Route.Home) {
                            popUpTo(Route.Splash) { inclusive = true }
                        }
                    }
                )
            }

            composable<Route.Onboarding> {
                OnboardingScreen(
                    onNavigateToLogin = {
                        navController.navigate(Route.Login) {
                            popUpTo(Route.Onboarding) { inclusive = true }
                        }
                    }
                )
            }

            // --- 2. AUTH (CONECTADO) ---
            composable<Route.Login> {
                LoginScreen(
                    onNavigateToOnboarding = {
                        navController.navigate(Route.Onboarding)
                    },
                    onNavigateToHome = {
                        navController.navigate(Route.Home) {
                            popUpTo(Route.Login) { inclusive = true }
                        }
                    },
                    onNavigateToForgotPassword = {
                        navController.navigate(Route.ForgotPassword)
                    },
                    onNavigateToRegister = {
                        navController.navigate(Route.Register)
                    }
                )
            }

            composable<Route.Register> {
                RegistrationScreen(
                    onBack = { navController.popBackStack() },
                    onNavigateToHome = {
                        navController.navigate(Route.Home) {
                            popUpTo(Route.Login) { inclusive = true }
                        }
                    }
                )
            }

            composable<Route.ForgotPassword> {
                ForgotPasswordScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            // --- 3. MAIN
            composable<Route.Home> {
                DummyScreen("Home (Tus Grupos)") {
                    navController.navigate(Route.ProjectView(groupId = 1))
                }
            }

            // --- 4. PROYECTOS / GASTOS ---
            composable<Route.ProjectView> { backStackEntry ->
                val route = backStackEntry.toRoute<Route.ProjectView>()
                DummyScreen("Viendo el Proyecto #${route.groupId}") {
                    navController.navigate(Route.NewExpense(groupId = route.groupId))
                }
            }

            composable<Route.NewProject> {
                DummyScreen("Crear Nuevo Proyecto") { navController.popBackStack() }
            }

            composable<Route.NewExpense> { backStackEntry ->
                val route = backStackEntry.toRoute<Route.NewExpense>()
                DummyScreen("Crear Gasto para grupo #${route.groupId}") {
                    navController.popBackStack()
                }
            }

            // --- 5. PERFIL Y AJUSTES ---
            composable<Route.Profile> { DummyScreen("Perfil del Usuario") { } }
            composable<Route.Settings> { DummyScreen("Ajustes de la App") { } }
        }
    }
}

@Composable
private fun DummyScreen(title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Button(onClick = onClick) { Text("Siguiente") }
    }
}