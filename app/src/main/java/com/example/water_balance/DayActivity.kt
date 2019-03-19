package com.example.water_balance

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class DayActivity : AppCompatActivity() {

    private var mConfirmButton: Button? = null
    private var mAddText: EditText? = null
    private var mTodayData: TextView? = null
    private var mStatisticsButton: Button? = null
    private var mSettingsButton: ImageButton? = null
    internal var howManyWater = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)

        mAddText = findViewById(R.id.addData)
        mTodayData = findViewById(R.id.todayData)

        mConfirmButton = findViewById(R.id.confirmButton)
        mConfirmButton!!.setOnClickListener {
            try {
                howManyWater += Integer.parseInt(mAddText!!.text.toString())
                mTodayData!!.text = String.format(getString(R.string.waterAmount), howManyWater)
            } catch (e: NullPointerException) {
                e.stackTrace
            }
        }

        mStatisticsButton = findViewById(R.id.statisticsButton)
        mStatisticsButton!!.setOnClickListener {
            val statisticsIntent = Intent(this@DayActivity, StatisticsActivity::class.java)
            startActivity(statisticsIntent)
        }

        mSettingsButton = findViewById(R.id.settingsButton)
        mSettingsButton!!.setOnClickListener {
            val settingsIntent = Intent(this@DayActivity, SettingsActivity::class.java)
            startActivity(settingsIntent)
        }
    }
}
