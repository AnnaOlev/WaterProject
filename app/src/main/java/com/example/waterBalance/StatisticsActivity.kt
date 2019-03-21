package com.example.waterBalance

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class StatisticsActivity : AppCompatActivity() {

    // тут будут выводиться данные за предыдущие дни
    // формат: дата, количество выпитого, какую часть от нормы составляет это количество
    // важно подумать: за сколько предыдущих дней будет выводиться информация

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
    }
}
