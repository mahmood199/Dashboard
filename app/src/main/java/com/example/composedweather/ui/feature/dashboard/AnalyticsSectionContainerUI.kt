package com.example.composedweather.ui.feature.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedweather.R
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.data.model.response.AnalyticSection

@Composable
fun AnalyticsSectionContainerUI(
    analytics: List<AnalyticSection>,
    onViewAnalyticsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(
                count = analytics.size,
                key = {
                    val item = analytics[it]
                    "${item.resourceId} ${item.title} ${item.subTitle}"
                }
            ) {
                val item = analytics[it]
                AnalyticsSectionUI(
                    item = item,
                )
            }
        }

        val ctaText = remember {
            "View Analytics"
        }

        HollowButtonUI(
            ctaText = ctaText,
            imageVector = ImageVector.vectorResource(R.drawable.ic_analytics_arrow),
            onClick = onViewAnalyticsClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}

@Preview
@Composable
fun AnalyticsSectionContainerUIPreview() {
    ComposedWeatherTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            val analyticSection = remember {
                listOf(
                    AnalyticSection(
                        resourceId = R.drawable.ic_top_clicks,
                        title = "Top Clicks",
                        subTitle = "Today's clicks"
                    ),
                    AnalyticSection(
                        resourceId = R.drawable.ic_location,
                        title = "Ahmedabad",
                        subTitle = "Top Location"
                    ),
                    AnalyticSection(
                        resourceId = R.drawable.ic_globe,
                        title = "Instagram",
                        subTitle = "Top source"
                    ),
                )
            }
            AnalyticsSectionContainerUI(analytics = analyticSection, onViewAnalyticsClicked = {})
        }
    }
}


