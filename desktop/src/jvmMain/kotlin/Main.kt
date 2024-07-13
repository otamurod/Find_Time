import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

// Entry point to the app
fun main() {
    // Create a new app
    application {
        val windowState = rememberWindowState()

        // Create a new window with the window state
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "TimeZone"
        ) {
            Surface(modifier = Modifier.fillMaxSize()){

            }
        }
    }
}