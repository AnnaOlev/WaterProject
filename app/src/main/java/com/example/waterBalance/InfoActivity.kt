package com.example.waterBalance

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

internal class InfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val intent = Intent(this, DayActivity::class.java)
    }
}
