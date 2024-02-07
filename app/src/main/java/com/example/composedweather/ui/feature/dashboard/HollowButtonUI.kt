package com.example.composedweather.ui.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedweather.R
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.FigtreeSemiBold
import com.example.composedweather.ui.theme.SilverChalice

@Composable
fun HollowButtonUI(
    ctaText: String,
    imageVector: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(percent = 20))
            .background(Color.Transparent)
            .border(width = 1.dp, color = SilverChalice, shape = RoundedCornerShape(20))
            .clickable {
                onClick()
            }
            .padding(6.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .padding(end = 4.dp)
        )
        Text(
            text = ctaText,
            maxLines = 1,
            fontFamily = FigtreeSemiBold,
            color = Color.Black,
        )
    }

}


@Preview
@Composable
fun HollowButtonUIPreview() {
    ComposedWeatherTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            val ctaText = remember {
                "View Analytics"
            }
            HollowButtonUI(
                ctaText = ctaText,
                imageVector = ImageVector.vectorResource(R.drawable.ic_analytics_arrow),
                onClick = {}
            )
        }
    }
}