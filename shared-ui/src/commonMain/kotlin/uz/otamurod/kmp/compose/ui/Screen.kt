package uz.otamurod.kmp.compose.ui

sealed class Screen(val title: String) {
    object TimeZonesScreen : Screen("Timezones")
    object FindTimeScreen : Screen("Find Time")
}