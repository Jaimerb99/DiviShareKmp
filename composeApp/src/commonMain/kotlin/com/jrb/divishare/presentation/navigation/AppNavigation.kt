package com.jrb.divishare.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // Definimos dónde arranca la app (Splash)
    NavHost(
        navController = navController,
        startDestination = Route.Splash,
        modifier = modifier.fillMaxSize()
    ) {

        // --- 1. SPLASH & ONBOARDING ---
        composable<Route.Splash> {
            // Aquí irá tu SplashScreen de verdad.
            // Para probar, ponemos un texto y un botón que simula navegar.
            DummyScreen("Splash Screen") {
                navController.navigate(Route.Onboarding) {
                    // Borra el Splash del historial para no volver al darle a "Atrás"
                    popUpTo(Route.Splash) { inclusive = true }
                }
            }
        }

        composable<Route.Onboarding> {
            DummyScreen("Onboarding") {
                navController.navigate(Route.Login)
            }
        }

        // --- 2. AUTH ---
        composable<Route.Login> {
            DummyScreen("Login") {
                navController.navigate(Route.Home) {
                    popUpTo(Route.Login) { inclusive = true }
                }
            }
        }

        // --- 3. MAIN ---
        composable<Route.Home> {
            DummyScreen("Home (Tus Grupos)") {
                // Simulamos entrar al grupo con ID 1
                navController.navigate(Route.ProjectView(groupId = 1))
            }
        }

        // --- 4. PROJECT / EXPENSES ---
        // ¡Mira qué fácil es recibir el ID del grupo!
        composable<Route.ProjectView> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.ProjectView>()
            val groupId = route.groupId

            DummyScreen("Viendo el Proyecto #$groupId") {
                navController.navigate(Route.NewExpense(groupId = groupId))
            }
        }

        composable<Route.NewExpense> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.NewExpense>()
            DummyScreen("Crear Gasto para grupo #${route.groupId}") {
                navController.popBackStack() // Volver atrás
            }
        }
    }
}

// Componente de mentira temporal para probar la navegación
@Composable
private fun DummyScreen(title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Button(onClick = onClick) {
            Text("Ir a la siguiente")
        }
    }
}