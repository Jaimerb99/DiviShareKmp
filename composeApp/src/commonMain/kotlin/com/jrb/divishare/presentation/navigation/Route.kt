package com.jrb.divishare.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    // 1. Splash & Onboarding
    @Serializable data object Splash : Route
    @Serializable data object Onboarding : Route

    // 2. Auth
    @Serializable data object Login : Route
    @Serializable data object Register : Route
    @Serializable data object ForgotPassword : Route

    // 3. Main (Grupos)
    @Serializable data object Home : Route

    // 4. Proyectos (Grupos) y Gastos
    @Serializable data class ProjectView(val groupId: Int) : Route // Vista de gastos y saldos de un grupo
    @Serializable data object NewProject : Route
    @Serializable data class NewExpense(val groupId: Int) : Route // Para añadir gasto a un grupo

    // 5. Perfil y Ajustes
    @Serializable data object Profile : Route
    @Serializable data object EditProfile : Route
    @Serializable data object Settings : Route
}