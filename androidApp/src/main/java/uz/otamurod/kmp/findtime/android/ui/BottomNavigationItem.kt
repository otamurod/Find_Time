package uz.otamurod.kmp.findtime.android.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = Screen.TimeZonesScreen.title,
        icon = Icons.Filled.Language,
        iconContentDescription = "Timezones"
    ),
    BottomNavigationItem(
        route = Screen.TimeZonesScreen.title,
        icon = Icons.Filled.Place,
        iconContentDescription = "Find Time"
    ),
)
