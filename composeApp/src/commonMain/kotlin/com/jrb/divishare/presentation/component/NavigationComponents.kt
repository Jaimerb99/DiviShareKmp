package com.jrb.divishare.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jrb.divishare.presentation.navigation.ItemBottomNav
import com.jrb.divishare.presentation.navigation.Route
import com.jrb.divishare.presentation.theme.DiviShareTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BottomBar(
    list: List<ItemBottomNav>,
    currentRoute: Route?,
    onNavigate: (Route) -> Unit
) {
    NavigationBar(
        modifier = Modifier.padding(horizontal = 5.dp).padding(bottom = 5.dp)
            .clip(RoundedCornerShape(12.dp)),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        tonalElevation = 0.dp
    ) {
        list.forEach { navigationItem ->
            NavigationBarItem(
                selected = navigationItem.route == currentRoute,
                onClick = { onNavigate(navigationItem.route) },
                icon = {
                    Icon(
                        painter = painterResource(navigationItem.icon),
                        contentDescription = navigationItem.title
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Composable
fun NavigationSideBar(
    items: List<ItemBottomNav>,
    currentRoute: Route?,
    onItemClick: (Route) -> Unit
) {
    NavigationRail(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(12.dp)),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        items.forEach { navigationItem ->
            val isSelected = navigationItem.route == currentRoute
            NavigationRailItem(
                selected = isSelected,
                onClick = { onItemClick(navigationItem.route) },
                icon = {
                    Icon(
                        painter = painterResource(navigationItem.icon),
                        contentDescription = navigationItem.title
                    )
                },
                modifier = Modifier.padding(vertical = 12.dp).weight(1f),
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                label = {
                    Text(
                        text = navigationItem.title,
                        style = if (isSelected) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}

// =========================================================================
// PREVIEWS
// =========================================================================

@Preview(showBackground = true)
@Composable
private fun NavigationComponentsPreview() {
    val navItems = listOf(
        ItemBottomNav.Home,
        ItemBottomNav.Profile,
        ItemBottomNav.Settings
    )

    DiviShareTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Preview de la Barra Inferior (Mobile)
            Column {
                Text(
                    text = "Bottom Bar (Mobile)",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                BottomBar(
                    list = navItems,
                    currentRoute = Route.Home,
                    onNavigate = {}
                )
            }

            // Preview de la Barra Lateral (Tablet/Desktop)
            Column {
                Text(
                    text = "Navigation Side Bar (Expanded)",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(modifier = Modifier.height(300.dp)) {
                    NavigationSideBar(
                        items = navItems,
                        currentRoute = Route.Profile,
                        onItemClick = {}
                    )
                    // Simulación del contenido de la pantalla al lado de la barra
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    )
                }
            }
        }
    }
}