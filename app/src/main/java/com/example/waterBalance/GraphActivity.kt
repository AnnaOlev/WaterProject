package com.example.waterBalance

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


class GraphActivity : AppCompatActivity() {
    protected var usersNorm = 0
    private var dailyQuantityList = ArrayList<Double>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        val dbHandler = SqliteDB(this)
        usersNorm = intent.getIntExtra("norm",0)

        dailyQuantityList = dbHandler.getAllDaysDailyQuantity()
        val datesList: Array<String>
        datesList = dbHandler.getAllDaysDate()
        val graph = findViewById<GraphView>(R.id.graph)
        val staticLabelsFormatter = StaticLabelsFormatter(graph)
        if (datesList.size > 2){
            staticLabelsFormatter.setHorizontalLabels(datesList)
            graph.gridLabelRenderer.labelFormatter = staticLabelsFormatter
            val series = LineGraphSeries<DataPoint>()
            for (i in 0 until datesList.size) {
                series.appendData(DataPoint(i.toDouble(),dailyQuantityList.get(i)), true, datesList.size)
            }
            val series1 = LineGraphSeries<DataPoint>()
            for (i in 0 until datesList.size) {
                series1.appendData(DataPoint(i.toDouble(),usersNorm.toDouble()), true, datesList.size)
            }
            graph.addSeries(series1)
            series1.color = Color.BLUE
            graph.addSeries(series)
            series.color = Color.YELLOW
            graph.addSeries(series)
        }
    }
}

