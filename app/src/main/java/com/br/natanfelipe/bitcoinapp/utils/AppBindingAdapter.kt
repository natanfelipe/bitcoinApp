package com.br.natanfelipe.bitcoinapp.utils

import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.br.natanfelipe.bitcoinapp.model.Bitcoin
import com.br.natanfelipe.bitcoinapp.model.Values
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.ValueFormatter
import java.math.RoundingMode
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:today_value")
fun getTodayQuotation(textView: AppCompatTextView, bitcoin: Bitcoin?){
    val price = bitcoin?.values?.lastOrNull()?.y

    bitcoin?.let {
        textView.text = it.unit+" "+ roundValue(it.values.lastOrNull()?.y)
    }

}

fun roundValue(value: Double?): String {
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.CEILING
    return df.format(value)
}

@BindingAdapter("app:stats")
fun setLineChartData(chart: LineChart, values: List<Values>?) {
    val entries = arrayListOf<Entry>()

    values?.forEach {
        entries.add(Entry(it.x.toFloat(), it.y.toFloat()))
        chart.xAxis.setLabelCount(values.size, true)
    }

    val setData = LineDataSet(entries, "")
    val data = LineData(setData)

    chart.data = data

    customizeChart(chart)
}

fun customizeChart(chart: LineChart) {
    chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    chart.axisRight.isEnabled = false
    chart.description.isEnabled = false
    chart.legend.isEnabled = false
    chart.animateX(1800, Easing.EaseInExpo)
    chart.setTouchEnabled(true)

    chart.xAxis.valueFormatter = object : ValueFormatter() {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MMM", Locale.US)

        override fun getFormattedValue(value: Float): String {
            return dateFormat.format(value * 1000)
        }
    }

    refreshChart(chart)
}

fun refreshChart(chart: LineChart) {
    chart.data.notifyDataChanged()
    chart.notifyDataSetChanged()
    chart.invalidate()
}


