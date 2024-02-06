package com.example.composedweather.ui.feature.dashboard

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.composedweather.R
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.FigtreeRegular
import com.example.composedweather.ui.theme.SilverChalice
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

@Composable
fun GraphUI(
    state: DashboardViewState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        val context = LocalContext.current

        GraphUIHeaderContent(state = state)

        AndroidView(
            update = { chart ->
                val data = BarData(
                    getDataSet(context).toList(),
                )
                chart.setData(data)
                val description = Description()
                chart.description = description.apply {
                    text = "Initial setup"
                }
                chart.animateXY(200, 200)
                chart.invalidate()
            },
            factory = {
                val chart = BarChart(it)
                val data = BarData(
                    getDataSet(context).toList(),
                )
                chart.setData(data)
                val description = Description()
                chart.description = description.apply {
                    text = "Initial setup"
                }

                chart.animateXY(2000, 2000)
                chart
            },
            onReset = {

            },
            onRelease = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

    }
}

@Composable
fun GraphUIHeaderContent(
    state: DashboardViewState,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 4.dp, bottom = 12.dp)
    ) {
        val overViewText = remember {
            "Overview"
        }
        Text(
            text = overViewText,
            fontFamily = FigtreeRegular,
            color = SilverChalice,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .border(1.dp, SilverChalice.copy(alpha = 0.5f), RoundedCornerShape(20))
                .padding(vertical = 6.dp)
                .padding(start = 12.dp, end = 8.dp)
        ) {

            val dateRange = remember {
                state.dateRange
            }
            Text(
                text = dateRange,
                fontFamily = FigtreeRegular,
                color = Color.Black,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_time),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }

    }

}

private fun getXAxisValues(): ArrayList<String> {
    val xAxis = ArrayList<String>()
    xAxis.add("JAN")
    xAxis.add("FEB")
    xAxis.add("MAR")
    xAxis.add("APR")
    xAxis.add("MAY")
    xAxis.add("JUN")
    return xAxis
}

private fun getDataSet(
    context: Context
): ArrayList<BarDataSet> {
    val valueSet1 = ArrayList<BarEntry>()
    val v1e1 = BarEntry(110.000f, 0f) // Jan
    valueSet1.add(v1e1)
    val v1e2 = BarEntry(40.000f, 1f) // Feb
    valueSet1.add(v1e2)
    val v1e3 = BarEntry(60.000f, 2f) // Mar
    valueSet1.add(v1e3)
    val v1e4 = BarEntry(30.000f, 3f) // Apr
    valueSet1.add(v1e4)
    val v1e5 = BarEntry(90.000f, 4f) // May
    valueSet1.add(v1e5)
    val v1e6 = BarEntry(100.000f, 5f) // Jun
    valueSet1.add(v1e6)
    val valueSet2 = ArrayList<BarEntry>()
    val v2e1 = BarEntry(150.000f, 0f) // Jan
    valueSet2.add(v2e1)
    val v2e2 = BarEntry(90.000f, 1f) // Feb
    valueSet2.add(v2e2)
    val v2e3 = BarEntry(120.000f, 2f) // Mar
    valueSet2.add(v2e3)
    val v2e4 = BarEntry(60.000f, 3f) // Apr
    valueSet2.add(v2e4)
    val v2e5 = BarEntry(20.000f, 4f) // May
    valueSet2.add(v2e5)
    val v2e6 = BarEntry(80.000f, 5f) // Jun
    valueSet2.add(v2e6)
    val barDataSet1 = BarDataSet(valueSet1, "Brand 1")
    barDataSet1.setColor(ContextCompat.getColor(context, R.color.teal_200))
    val barDataSet2 = BarDataSet(valueSet2, "Brand 2")
    barDataSet2.setColors(*ColorTemplate.COLORFUL_COLORS)
    val dataSets = arrayListOf<BarDataSet>()
    dataSets.add(barDataSet1)
    dataSets.add(barDataSet2)
    return dataSets
}

@Preview
@Composable
fun GraphUIPreview() {
    ComposedWeatherTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            GraphUI(state = DashboardViewState.default())
        }
    }
}