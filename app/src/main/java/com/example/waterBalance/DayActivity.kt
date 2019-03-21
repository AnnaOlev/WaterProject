package com.example.waterBalance

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_day.*

class DayActivity : AppCompatActivity() {

    private var howManyWater = 0
    private var usersNorm = 0
    private var gender: String? = "null"
    private var weight = 0
    private val DATA_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)

        mConfirmButton.setOnClickListener {
            val temp = mAddData!!.text.toString()
            if (!TextUtils.isEmpty(temp)) {
                // далее следует тестовый вывод информации, позже будет заменен на графику
                if (usersNorm == 0){
                    mTodayData!!.text = getString(R.string.toSettings)
                }
                else {
                    howManyWater += Integer.parseInt(temp)
                    mTodayData!!.text = String.format(getString(R.string.waterAmount), howManyWater, countPartOfNorm())
                }
            }
        }

        mStatisticsButton!!.setOnClickListener {
            val statisticsIntent = Intent(this@DayActivity, StatisticsActivity::class.java)
            startActivity(statisticsIntent)
        }

        mSettingsButton!!.setOnClickListener {
            val settingsIntent = Intent(this@DayActivity, SettingsActivity::class.java)
            startActivityForResult(settingsIntent, DATA_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DATA_REQUEST)
            if (resultCode == RESULT_OK) {
                gender = data!!.getStringExtra("gender")
                weight = data.getIntExtra("weight", 0)
                usersNorm = countNorm() // оно находится здесь временно
                // так как потом будет храниться долгосрочно, надо решить, где поставить функцию подсчта, чтобы запускалось только если правда надо
                // аналогичный вопрос про "когда надо" касается и пола с весом
            }
    }

    private fun countNorm() : Int {

        if (gender == "female")
            return weight * 31
        else if (gender == "male")
            return weight * 35

        return 0
    }

    private fun countPartOfNorm() : Float {
        return howManyWater.toFloat() / usersNorm.toFloat()
    }
}
