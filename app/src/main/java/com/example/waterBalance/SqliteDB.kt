package com.example.waterBalance

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "WATER_PROJECT_DB"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "project"
        val DATE = "date"
        val DailyQuantity = 0
        val PartOfNormalQuantity = 0
    }

    //попыталась привести все это в порядок, не вполне уверена, что верно

    private val SQL_CREATE_ENTIRES = "CREATE TABLE $TABLE_NAME + " +
            "(" + " _id integer primary key autoincrement, " + "$DATE DATE"+"$DailyQuantity DOUBLE" +
            "$PartOfNormalQuantity DOUBLE" + ")"

    private val SQL_DELETE_ENTIRES = "DROP TABLE IF EXISTS ${TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTIRES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTIRES)
        onCreate(db)
    }

    private fun DeleteItem() {

    }


}