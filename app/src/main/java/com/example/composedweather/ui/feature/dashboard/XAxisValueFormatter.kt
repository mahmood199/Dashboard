package com.example.composedweather.ui.feature.dashboard

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler
import kotlinx.collections.immutable.PersistentList

class XAxisValueFormatter(
    private val date: PersistentList<String>
) : ValueFormatter() {

    override fun getFormattedValue(
        value: Float,
        entry: Entry?,
        dataSetIndex: Int,
        viewPortHandler: ViewPortHandler?
    ): String {
        return date[dataSetIndex]
    }
}