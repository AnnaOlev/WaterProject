package com.example.waterBalance

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MainMenuActivity : AppCompatActivity() {

    protected val DATA_REQUEST = 1
    protected var gender: String? = "null"
    protected var weight = 0
    protected var usersNorm = 0
    var prefs: Prefs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        prefs = Prefs(this)

        usersNorm = prefs!!.usersNorm
        gender = prefs!!.usersGender


        mStatButton!!.setOnClickListener {
            val statisticsIntent = Intent(this@MainMenuActivity, StatisticsActivity::class.java)
            startActivity(statisticsIntent)
        }

        mGraphButton.setOnClickListener {
            val graphIntent = Intent(this@MainMenuActivity, GraphActivity::class.java)
            startActivity(graphIntent)
        }

        mTodayButton.setOnClickListener {
            val todayIntent = Intent(this@MainMenuActivity, DayActivity::class.java)
            todayIntent.putExtra("norm", usersNorm)
            todayIntent.putExtra("gender", gender)
            startActivity(todayIntent)
        }

        mSettingsButton.setOnClickListener{
            val settingsIntent = Intent(this@MainMenuActivity, SettingsActivity::class.java)
            startActivityForResult(settingsIntent, DATA_REQUEST)
        }

        mInfoButton.setOnClickListener {
            val infoIntent = Intent(this@MainMenuActivity, InfoActivity::class.java)
            startActivity(infoIntent)
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
                prefs!!.usersNorm = usersNorm
            }
    }

    private fun countNorm() : Int {

        if (gender == "female")
            return weight * 31
        else if (gender == "male")
            return weight * 35

        return 0
    }
}