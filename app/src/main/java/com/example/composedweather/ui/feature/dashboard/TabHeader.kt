package com.example.composedweather.ui.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.ElectricBlue
import com.example.composedweather.ui.theme.FigtreeSemiBold
import com.example.composedweather.ui.theme.SilverChalice

@Composable
fun TabHeaderUI(
    tabs: List<String>,
    selectedTab: Int,
    onTabChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(tabs.size) {
                val item = tabs[it]
                TabItemUI(
                    item = item,
                    isSelected = selectedTab == it,
                    onTabClicked = {
                        onTabChanged(it)
                    }
                )
            }
        }

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = SilverChalice,
                    shape = RoundedCornerShape(25)
                )
                .padding(6.dp)
                .align(Alignment.CenterEnd)
        )
    }

}

@Composable
fun TabItemUI(
    item: String,
    isSelected: Boolean,
    onTabClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = item,
        fontFamily = FigtreeSemiBold,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) ElectricBlue else Color.Transparent)
            .clickable { onTabClicked() }
            .padding(horizontal = 20.dp, vertical = 10.dp),
        color = if (isSelected) Color.White else SilverChalice
    )
}

@Preview
@Composable
fun TabHeaderUIPreview() {
    ComposedWeatherTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            val tabs = remember {
                listOf(
                    "Top Links",
                    "Recent Links",
                    "Recent Links",
                )
            }
            var selectedTab by remember {
                mutableIntStateOf(0)
            }

            TabHeaderUI(tabs = tabs, selectedTab = selectedTab, onTabChanged = {
                selectedTab = it
            })
        }
    }
}