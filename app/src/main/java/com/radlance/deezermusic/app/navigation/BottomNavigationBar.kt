package com.radlance.deezermusic.app.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    bottomNavigationState: BottomNavigationState,
    modifier: Modifier = Modifier
) {
    val currentBackStackEntry by bottomNavigationState.navHostController.currentBackStackEntryAsState()
    val routes = listOf(ApiTracks, DownloadedTracks)

    NavigationBar(modifier = modifier) {
        routes.forEach { destination ->
            val selected = currentBackStackEntry?.destination?.hierarchy?.any {
                it.route == destination::class.qualifiedName
            } ?: false

            val label = stringResource(destination.labelResId)

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        bottomNavigationState.navigateTo(destination)
                    }
                },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }

                    Icon(imageVector = icon, contentDescription = label)
                },
                label = { Text(text = label) },
                alwaysShowLabel = true
            )
        }
    }
}