package com.example.waterBalance

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_day.*
import android.text.TextUtils
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

var selectedType : Int = 0 // Выбирается тип напитка(вне класса для того, чтобы можно было вызвать в адаптере)

open class DayActivity : AppCompatActivity() {

    protected var howManyWater : Double = 0.0
    val dbHandler = SqliteDB(this)
    protected var usersNorm = 0
    protected var gender: String? = "null"
    var prefs: Prefs? = null
    val sdf = SimpleDateFormat("dd/M/yyyy")
    var waterTypes : ArrayList<String> = ArrayList() // Типы напитков
    var waterTypesImg : ArrayList<Int> = ArrayList() // Картинки для напитков
    var waterPercentage : Double = 1.0 // Процент воды в определенном напитке

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)

        gender = intent.getStringExtra("gender")
        usersNorm = intent.getIntExtra("norm",0)

        addWaterTypes()
        addWaterTypesImages()
        // Создание горизонтального recycler view
        recycle_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycle_view.adapter = NewAdapter(waterTypes, waterTypesImg, this)

        prefs = Prefs(this)

        if (prefs!!.currentDate == sdf.format(Date()))
            howManyWater = prefs!!.currentAmount.toDouble()
        else if (prefs!!.currentDate != "default"){
            howManyWater = prefs!!.currentAmount.toDouble()
            val dailyData = DailyData(howManyWater, countPartOfNorm(), prefs!!.currentDate)
            dbHandler.addItem(dailyData)
            howManyWater = 0.0
            prefs!!.currentDate = sdf.format(Date())
            //предыдущие две строки немного костыль, но как лучше сделать не знаю
        }

        if (howManyWater != 0.0)
            mTodayData!!.text =  String.format(getString(R.string.waterAmount), howManyWater, countPartOfNorm())

        // Запуск сервиса уведомлений
        if (!NotificationService.isRunning){
            Intent(this, NotificationService::class.java).also {
                startService(it)
            }
        }

        mTypeCheck.setOnClickListener{
            selectedWaterType(selectedType)
        }

        mConfirmButton.setOnClickListener {
            //selectedWaterType(selectedType) Вообще, должен вызываться в 75 строке, но для проверки, при нажатии
            // кнопки "подтвердить ввод" будет меняться hint в зависимости от выбранного напитка
            val temp = mAddData!!.text.toString()
            if (!TextUtils.isEmpty(temp)) {
                // далее следует тестовый вывод информации, позже будет заменен на графику
                if (usersNorm == 0){
                    mTodayData!!.text = getString(R.string.toSettings)
                }
                else {
//                  selectedWaterType(selectedType)
                    Log.d("Water Percentage", "Water Percentage = " + waterPercentage)
                    howManyWater += Integer.parseInt(temp) * waterPercentage
                    prefs!!.currentAmount = howManyWater.toInt()
                    mTodayData!!.text = String.format("Выпито %.0f мл, что составляет %.3f от нормы", howManyWater, countPartOfNorm())

                    prefs!!.currentDate = sdf.format(Date()) //это тоже тестовый вариант
                    mAddData!!.setText("")
                }
            }
        }

        mBodyVisualButton!!.setOnClickListener {
            if (gender == "female") {
                val femaleBodyVisualIntent = Intent(this@DayActivity, FemaleBodyVisualActivity::class.java)
                femaleBodyVisualIntent.putExtra("waterAmounts", howManyWater)
                femaleBodyVisualIntent.putExtra("usersNorm", usersNorm)
                startActivity(femaleBodyVisualIntent)
            } else if (gender == "male") {
                val maleBodyVisualIntent = Intent(this@DayActivity, MaleBodyVisualActivity::class.java)
                maleBodyVisualIntent.putExtra("waterAmounts", howManyWater)
                maleBodyVisualIntent.putExtra("usersNorm", usersNorm)
                startActivity(maleBodyVisualIntent)
            } else mTodayData!!.text = "Пожалуйста, укажите свой пол в настройках!"
        }

    }

    fun countPartOfNorm() : Float {
        return howManyWater.toFloat() / usersNorm.toFloat()
    }




    // Добавление типов напитков
    fun addWaterTypes() {
        waterTypes.add("Вода")
        waterTypes.add("Чай черный")
        waterTypes.add("Кофе")
        waterTypes.add("Молоко")
        waterTypes.add("Сок")
    }


    // Добавление картинок для каждого типа напитков
    fun addWaterTypesImages() {
        waterTypesImg.add(R.drawable.watertypewater)
        waterTypesImg.add(R.drawable.watertypetea)
        waterTypesImg.add(R.drawable.watertypecoffee)
        waterTypesImg.add(R.drawable.watertypemilk)
        waterTypesImg.add(R.drawable.watertypejuice)
    }

    fun selectedWaterType(type : Int) {
        if (type == 0) {
            waterPercentage = 1.0
            mAddData!!.hint = "Введите количество выпитой воды \n (в мл)"
        }
        else if (type == 1) {
            waterPercentage = 0.80
            mAddData!!.hint = "Введите количество выпитого чая \n (в мл)"
        }
        else if (type == 2) {
            waterPercentage = 0.30
            mAddData!!.hint = "Введите количество выпитого кофе \n (в мл)"
        }
        else if (type == 3) {
            waterPercentage = 0.87
            mAddData!!.hint = "Введите количество выпитого молока \n (в мл)"
        }
        else if (type == 4) {
            waterPercentage = 0.84
            mAddData!!.hint = "Введите количество выпитого сока \n (в мл)"
        }
    }

    // Сохранение выбранного напитка
    fun setSelectedType(type : Int) {
        selectedType = type
        Log.d("New Type", "New Type = " + selectedType)
    }
}
