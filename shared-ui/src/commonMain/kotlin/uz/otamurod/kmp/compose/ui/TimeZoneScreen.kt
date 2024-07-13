package uz.otamurod.kmp.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import uz.otamurod.kmp.findtime.TimeZoneHelper
import uz.otamurod.kmp.findtime.TimeZoneHelperImpl

const val timeMillis = 1000 * 60L // 1 minute

@Composable
fun TimeZoneScreen(
    currentTimezoneStrings: SnapshotStateList<String>,
    innerPadding: PaddingValues
) {
    val timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        var time by remember {
            mutableStateOf(timezoneHelper.currentTime())
        }

        LaunchedEffect(key1 = 0) {
            while (true) {
                time = timezoneHelper.currentTime()
                delay(timeMillis)
            }
        }

        LocalTimeCard(
            city = timezoneHelper.currentTimeZone(),
            time = time,
            date = timezoneHelper.getDate(timezoneHelper.currentTimeZone())
        )

        Spacer(modifier = Modifier.size(16.dp))
        // Add Timezone items
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(
                items = currentTimezoneStrings,
                key = { timezone -> timezone }) { timezoneString ->
                AnimatedSwipeDismiss(
                    item = timezoneString,
                    background = { _ ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(50.dp)
                                .background(Color.Red)
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            val alpha = 1f
                            Icon(
                                Icons.Filled.Delete, contentDescription = "Delete",
                                modifier = Modifier.align(Alignment.CenterEnd),
                                tint = Color.White.copy(alpha = alpha)
                            )
                        }
                    },
                    content = {
                        TimeCard(
                            timezone = timezoneString,
                            hours = timezoneHelper.hoursFromTimeZone(timezoneString),
                            time = timezoneHelper.getTime(timezoneString),
                            date = timezoneHelper.getDate(timezoneString)
                        )
                    },
                    onDismiss = { zone ->
                        if (currentTimezoneStrings.contains(zone)) {
                            currentTimezoneStrings.remove(zone)
                        }
                    }
                )
            }
        }
    }
}