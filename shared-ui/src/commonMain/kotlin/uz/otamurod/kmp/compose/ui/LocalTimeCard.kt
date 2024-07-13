package uz.otamurod.kmp.compose.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.otamurod.kmp.compose.theme.primaryColor
import uz.otamurod.kmp.compose.theme.primaryDarkColor
import uz.otamurod.kmp.compose.theme.typography

@Composable
fun LocalTimeCard(city: String, time: String, date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(color = Color.White)
            .padding(8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(size = 8.dp),
            border = BorderStroke(width = 1.dp, color = Color.Black),
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                primaryColor,
                                primaryDarkColor
                            )
                        )
                    )
                    .padding(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Spacer(modifier = Modifier.weight(1f))

                        Text(text = "Your Location", style = typography.h4)

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = city, style = typography.h2)

                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(horizontalAlignment = Alignment.End) {
                        Spacer(modifier = Modifier.weight(1f))

                        Text(text = time, style = typography.h1)

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = date, style = typography.h3)

                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}

@Preview(name = "LocalTimeCard")
@Composable
private fun PreviewLocalTimeCard() {
    LocalTimeCard("Zaamin", "8:30 am", "Tuesday June 18")
}