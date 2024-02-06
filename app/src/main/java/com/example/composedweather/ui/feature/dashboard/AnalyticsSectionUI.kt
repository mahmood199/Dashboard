package com.example.composedweather.ui.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedweather.R
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.FigtreeBold
import com.example.composedweather.ui.theme.FigtreeRegular
import com.example.composedweather.ui.theme.SilverChalice
import com.example.data.model.response.AnalyticSection

@Composable
fun AnalyticsSectionUI(
    item: AnalyticSection,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12))
            .background(Color.White)
            .aspectRatio(1f)
            .padding(12.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(item.resourceId),
            contentDescription = null,
            tint = Color.Unspecified,
        )

        Text(
            text = item.title,
            fontFamily = FigtreeRegular,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = item.subTitle,
            color = SilverChalice,
            fontFamily = FigtreeBold,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

@Preview
@Composable
fun AnalyticsSectionUIPreview() {
    ComposedWeatherTheme {
        val item = AnalyticSection(
            resourceId = R.drawable.ic_top_clicks,
            title = "Top Clicks",
            subTitle = "Today's clicks"
        )

        Column(modifier = Modifier.fillMaxSize()) {
            AnalyticsSectionUI(item)
        }
    }
}