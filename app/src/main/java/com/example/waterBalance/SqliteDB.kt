package com.example.waterBalance

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "WATER_PROJECT_DB"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "project"
        val DailyQuantity = 0
        val NormalQuantity = 0
    }

    private val SQL_CREATE_ENTIRES = "CREATE TABLE $TABLE_NAME + " +
            "(" + "${DailyQuantity} INT" +
            "${NormalQuantity} INT"

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