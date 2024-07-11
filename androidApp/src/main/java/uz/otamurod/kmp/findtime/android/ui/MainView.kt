package uz.otamurod.kmp.findtime.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.otamurod.kmp.findtime.android.theme.AppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainView(actionBarFun: topBarFun = { emptyComposable() }) {
    val showAddDialog = remember { mutableStateOf(false) }
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }
    val selectedIndex = remember { mutableStateOf(0) }

    AppTheme {
        Scaffold(
            topBar = {
                actionBarFun(selectedIndex.value)
            },
            floatingActionButton = {
                if (selectedIndex.value == 0) {
                    FloatingActionButton(
                        modifier = Modifier.padding(16.dp),
                        onClick = {
                            showAddDialog.value = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            },
            bottomBar = {
                BottomNavigation {
                    bottomNavigationItems.forEachIndexed { i, bottomNavigationItem ->
                        BottomNavigationItem(
                            selected = selectedIndex.value == i,
                            onClick = { selectedIndex.value = i },
                            icon = {
                                Icon(
                                    imageVector = bottomNavigationItem.icon,
                                    contentDescription = bottomNavigationItem.iconContentDescription
                                )
                            }
                        )
                    }
                }
            }
        ) {
            if (showAddDialog.value) {
                AddTimeZoneDialog(
                    onAdd = { newTimezones ->
                        showAddDialog.value = false
                        for (zone in newTimezones) {
                            if (!currentTimezoneStrings.contains(zone)) {
                                currentTimezoneStrings.add(zone)
                            }
                        }
                    },
                    onDismiss = {
                        showAddDialog.value = false
                    }
                )
            }

            when (selectedIndex.value) {
                0 -> TimeZoneScreen(currentTimezoneStrings)
                1 -> FindMeetingScreen(currentTimezoneStrings)
            }
        }
    }
}

@Preview(name = "MainView")
@Composable
private fun PreviewMainView() {
    MainView()
}