package com.example.waterBalance

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_day.*
import android.text.TextUtils
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

open class DayActivity : AppCompatActivity() {

    protected var howManyWater = 0
    protected var usersNorm = 0
    protected var gender: String? = "null"
    protected var weight = 0
    protected val DATA_REQUEST = 1
    var prefs: Prefs? = null
    val sdf = SimpleDateFormat("dd/M/yyyy")
    val dbHandler = SqliteDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)

        prefs = Prefs(this)

        usersNorm = prefs!!.usersNorm
        gender = prefs!!.usersGender

        if (prefs!!.currentDate == sdf.format(Date()))
            howManyWater = prefs!!.currentAmount
        else{
            howManyWater = prefs!!.currentAmount
            val dailyData = DailyData(howManyWater, countPartOfNorm(), prefs!!.currentDate)
            dbHandler.addItem(dailyData)
            howManyWater = 0
            prefs!!.currentDate = sdf.format(Date())
            //предыдущие две строки немного костыль, но как лучше сделать не знаю
            mTodayData!!.text = dbHandler.getAllDays() //это тестовый вариант, ествественно
        }

        if (howManyWater != 0)
            mTodayData!!.text =  String.format(getString(R.string.waterAmount), howManyWater, countPartOfNorm())

        // Запуск сервиса уведомлений
        if (!NotificationService.isRunning){
            Intent(this, NotificationService::class.java).also {
                startService(it)
            }
        }

        mConfirmButton.setOnClickListener {
            val temp = mAddData!!.text.toString()
            if (!TextUtils.isEmpty(temp)) {
                // далее следует тестовый вывод информации, позже будет заменен на графику
                if (usersNorm == 0){
                    mTodayData!!.text = getString(R.string.toSettings)
                }
                else {
                    howManyWater += Integer.parseInt(temp)
                    prefs!!.currentAmount = howManyWater
                    mTodayData!!.text = String.format(getString(R.string.waterAmount), howManyWater, countPartOfNorm())

                    prefs!!.currentDate = sdf.format(Date()) //это тоже тестовый вариант
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

        mBodyVisualButton!!.setOnClickListener {
            if (gender == "female") {
                val femaleBodyVisualIntent = Intent(this@DayActivity, FemaleBodyVisualActivity::class.java)
                femaleBodyVisualIntent.putExtra("waterAmounts", howManyWater)
                femaleBodyVisualIntent.putExtra("usersNorm", usersNorm)
                startActivityForResult(femaleBodyVisualIntent, DATA_REQUEST)
            } else if (gender == "male") {
                val maleBodyVisualIntent = Intent(this@DayActivity, MaleBodyVisualActivity::class.java)
                maleBodyVisualIntent.putExtra("waterAmounts", howManyWater)
                maleBodyVisualIntent.putExtra("usersNorm", usersNorm)
                startActivityForResult(maleBodyVisualIntent, DATA_REQUEST)
            } else mTodayData!!.text = "Пожалуйста, укажите свой пол в настройках!"


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DATA_REQUEST)
            if (resultCode == RESULT_OK) {
                gender = data!!.getStringExtra("gender")
                prefs!!.usersGender = gender as String
                weight = data.getIntExtra("weight", 0)
                usersNorm = countNorm()
                prefs!!.usersNorm = usersNorm// оно находится здесь временно
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

    fun countPartOfNorm() : Float {
        return howManyWater.toFloat() / usersNorm.toFloat()
    }

}
