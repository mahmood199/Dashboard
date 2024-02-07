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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.composedweather.ui.theme.ElectricBlue
import com.example.composedweather.ui.theme.FigtreeSemiBold
import com.example.composedweather.ui.theme.NaturalGreen
import com.example.composedweather.ui.theme.SoftBabyBlue

@Composable
fun CtaSectionContainerUI(
    onContactUsClicked: () -> Unit,
    onFaqSectionClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CtaSectionUI(
            text = "Talk with us",
            icon = R.drawable.ic_contact,
            backgroundColor = NaturalGreen.copy(alpha = 0.25f),
            strokeColor = NaturalGreen,
            onClick = onContactUsClicked
        )

        CtaSectionUI(
            text = "Frequently Asked Questions",
            icon = R.drawable.ic_question_mark,
            backgroundColor = SoftBabyBlue.copy(alpha = 0.5f),
            strokeColor = ElectricBlue,
            onClick = onFaqSectionClicked
        )
    }

}


@Composable
fun CtaSectionUI(
    text: String,
    icon: Int,
    backgroundColor: Color,
    strokeColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, strokeColor, RoundedCornerShape(10))
            .clip(RoundedCornerShape(10))
            .background(backgroundColor)
            .clickable {
                onClick()
            }
            .padding(20.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FigtreeSemiBold,
            color = textColor
        )
    }
}


@Preview
@Composable
fun CtaSectionUIPreview() {
    ComposedWeatherTheme {
        CtaSectionContainerUI(
            onContactUsClicked = {},
            onFaqSectionClicked = {}
        )
    }
}