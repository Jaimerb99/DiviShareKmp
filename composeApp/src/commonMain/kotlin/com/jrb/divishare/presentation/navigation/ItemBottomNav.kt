package com.jrb.divishare.presentation.navigation

import divishare.composeapp.generated.resources.Res
import divishare.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

sealed class ItemBottomNav(
    val route: Route,
    val icon: DrawableResource,
    val title: String
) {
    data object Home : ItemBottomNav(
        route = Route.Home,
        icon = Res.drawable.ic_home,
        title = "Home"
    )

    data object Profile : ItemBottomNav(
        route = Route.Profile,
        icon = Res.drawable.ic_users,
        title = "Profile"
    )

    data object Settings : ItemBottomNav(
        route = Route.Settings,
        icon = Res.drawable.ic_settings_gear,
        title = "Settings"
    )
}