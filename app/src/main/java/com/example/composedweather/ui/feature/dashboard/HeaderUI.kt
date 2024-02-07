package com.example.composedweather.ui.feature.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.FigtreeBold
import com.example.composedweather.ui.theme.FigtreeRegular
import com.example.composedweather.ui.theme.SilverChalice

@Composable
fun HeaderUI(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            color = SilverChalice,
            fontFamily = FigtreeRegular,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = subTitle,
            fontFamily = FigtreeBold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview
@Composable
fun HeaderUIPreview() {
    ComposedWeatherTheme {
        HeaderUI(
            title = "Good morning",
            subTitle = "Ajay Manva \uD83D\uDC4B"
        )
    }
}