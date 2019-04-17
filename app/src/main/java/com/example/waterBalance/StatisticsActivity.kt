package com.example.waterBalance

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_statistics.*


class StatisticsActivity : AppCompatActivity() {

    private lateinit var adapter: StatisticsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var mStatisticsList = ArrayList<DailyData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val dbHandler = SqliteDB(this)
        mStatisticsList = dbHandler.getAllDays()

        adapter = StatisticsAdapter(mStatisticsList)
        recyclerView.adapter = adapter
    }

}
