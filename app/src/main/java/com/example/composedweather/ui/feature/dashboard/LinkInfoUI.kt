package com.example.composedweather.ui.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.example.composedweather.ui.common.ContentLoaderUI
import com.example.composedweather.ui.common.dashedBorder
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.ElectricBlue
import com.example.composedweather.ui.theme.FigtreeBold
import com.example.composedweather.ui.theme.FigtreeRegular
import com.example.composedweather.ui.theme.SilverChalice
import com.example.composedweather.ui.theme.SoftBabyBlue
import com.example.data.model.response.LinkData
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun LinkInfoUI(
    linkData: LinkData,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(top = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.925f),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            GlideImage(
                imageModel = {
                    linkData.originalImage
                },
                failure = {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.ic_image_load_failed
                        ),
                        contentDescription = null,
                        modifier = Modifier.matchParentSize()
                    )
                },
                loading = {
                    ContentLoaderUI(modifier = Modifier.matchParentSize())
                },
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10))
                    .border(
                        width = 1.dp,
                        color = SilverChalice.copy(alpha = 0.25f),
                        shape = RoundedCornerShape(10)
                    )
            )

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = linkData.title,
                    fontFamily = FigtreeRegular,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = linkData.timesAgo,
                    color = SilverChalice,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "${linkData.totalClicks}",
                    fontFamily = FigtreeBold,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Clicks",
                    fontFamily = FigtreeRegular,
                    color = SilverChalice,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SoftBabyBlue)
                .dashedBorder(
                    color = ElectricBlue,
                    shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp)
                )
                .clickable {

                }
                .padding(12.dp)
        ) {
            Text(
                text = linkData.webLink,
                color = ElectricBlue,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FigtreeRegular,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_copy_files),
                contentDescription = null,
                tint = ElectricBlue
            )
        }
    }
}

@Preview
@Composable
fun LinkInfoUIPreview() {
    ComposedWeatherTheme {
        val linkData by remember {
            mutableStateOf(LinkData.default())
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinkInfoUI(
                linkData = linkData,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 20.dp)
            )
        }
    }
}