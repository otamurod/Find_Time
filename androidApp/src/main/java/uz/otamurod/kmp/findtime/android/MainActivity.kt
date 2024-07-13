package uz.otamurod.kmp.findtime.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import uz.otamurod.kmp.compose.theme.AppTheme
import uz.otamurod.kmp.compose.ui.MainView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Napier.base(DebugAntilog())

        setContent {
            uz.otamurod.kmp.compose.theme.AppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.background)
                ) {
                    uz.otamurod.kmp.compose.ui.MainView {
                        TopAppBar(title = {
                            when (it) {
                                0 -> Text(text = stringResource(id = R.string.world_clocks))
                                else -> Text(text = stringResource(id = R.string.find_meeting))
                            }
                        })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    uz.otamurod.kmp.compose.theme.AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            uz.otamurod.kmp.compose.ui.MainView {
                TopAppBar(title = {
                    when (it) {
                        0 -> Text(text = stringResource(id = R.string.world_clocks))
                        else -> Text(text = stringResource(id = R.string.find_meeting))
                    }
                })
            }
        }
    }
}
