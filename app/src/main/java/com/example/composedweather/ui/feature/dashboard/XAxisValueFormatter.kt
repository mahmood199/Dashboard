package com.example.composedweather.ui.feature.dashboard

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler

class XAxisValueFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return super.getFormattedValue(value, axis)
    }

    override fun getFormattedValue(
        value: Float,
        entry: Entry?,
        dataSetIndex: Int,
        viewPortHandler: ViewPortHandler?
    ): String {
        return super.getFormattedValue(value, entry, dataSetIndex, viewPortHandler)
    }
}