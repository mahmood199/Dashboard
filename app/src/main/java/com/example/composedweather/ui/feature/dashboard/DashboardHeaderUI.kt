package com.example.composedweather.ui.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

@Composable
fun DashboardHeaderUI(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(vertical = 24.dp)
    ) {
        val dashboardHeaderText = remember {
            "Dashboard"
        }
        Text(
            text = dashboardHeaderText,
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = FigtreeSemiBold,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_settings),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(Color.White.copy(alpha = 0.25f))
                .padding(4.dp)
        )
    }
}

@Preview
@Composable
fun DashboardHeaderUIPreview() {
    ComposedWeatherTheme {
        DashboardHeaderUI()
    }
}