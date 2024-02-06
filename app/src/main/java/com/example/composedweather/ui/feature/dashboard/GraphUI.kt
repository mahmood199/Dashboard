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
import androidx.compose.ui.graphics.toArgb
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
import com.example.composedweather.ui.theme.ElectricBlue
import com.example.composedweather.ui.theme.FigtreeRegular
import com.example.composedweather.ui.theme.SilverChalice
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.renderer.XAxisRenderer
import com.github.mikephil.charting.utils.Transformer
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList


@Composable
fun GraphUI(
    state: DashboardViewState,
    dates: PersistentList<String>,
    values: PersistentList<Int>,
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
            factory = {
                val lineChart = LineChart(it)
                    .apply {

                        val lineData = LineData(
                            getDataSet(
                                context = context,
                                values = values
                            ).toList(),
                        )

                        data = lineData


                        val description = Description()
                        this.description = description.apply {
                            text = ""
                        }

                        setDrawBorders(false)
                        setBorderColor(Color.Transparent.toArgb())

                        xAxis.position = XAxis.XAxisPosition.BOTTOM

                        xAxis.valueFormatter = object : ValueFormatter() {
                            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                                val startDate = value / 10
                                return dates[startDate.toInt()]
                            }
                        }

                        axisRight.isEnabled = false
                    }

                lineChart.animateXY(200, 200)
                lineChart
            },
            update = { chart ->

            },
            onReset = {

            },
            onRelease = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
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

private fun getXAxisValues(
    lineChart: LineChart,
    dates: PersistentList<String>
): XAxis {
    val xAxis = lineChart.xAxis
    xAxis.valueFormatter = IndexAxisValueFormatter(dates)
    return xAxis
}

fun getDataSet(
    context: Context,
    values: PersistentList<Int>
): ArrayList<LineDataSet> {
    val valueSet1 = ArrayList<Entry>()

    values.forEachIndexed { index, i ->
        valueSet1.add(Entry(index * 10f, i.toFloat()))
    }

    val barDataSet1 = LineDataSet(valueSet1, "")
    barDataSet1.setDrawCircleHole(false)
    barDataSet1.setDrawCircles(false)
    barDataSet1.setDrawValues(false)
    barDataSet1.setValueTextColors(listOf(SilverChalice.toArgb()))
    barDataSet1.setColor(ElectricBlue.toArgb())

    barDataSet1.setDrawFilled(true)

    barDataSet1.fillDrawable = ContextCompat.getDrawable(context, R.drawable.bg_line_chart)

    barDataSet1.lineWidth = 2f
    val dataSets = arrayListOf<LineDataSet>()
    dataSets.add(barDataSet1)
    return dataSets
}

@Preview
@Composable
fun GraphUIPreview() {
    ComposedWeatherTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            GraphUI(
                state = DashboardViewState.default(),
                dates = mutableListOf(
                    "wdadsa"
                ).toPersistentList(),
                values = mutableListOf(
                    1
                ).toPersistentList(),
            )
        }
    }
}