package com.example.waterBalance

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "WATER_PROJECT_DB"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "project"
        val DailyQuantityColumn = "id"
        val NormalQuantityColumn = "id"
    }

    private val SQL_CREATE_ENTIRES = "CREATE TABLE $TABLE_NAME + " +
            "(" + "${DailyQuantityColumn} INT" +
            "${NormalQuantityColumn} INT"

    private val SQL_DELETE_ENTIRES = "DROP TABLE IF EXISTS ${TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTIRES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTIRES)
        onCreate(db)
    }

    val db: SqliteDB? = null

    private fun DeleteItem() {

    }

    private fun AddItems(): Boolean {
        val person = Person()
        val helper = db?.writableDatabase
        val values = ContentValues().apply {
            put(DailyQuantityColumn, person.DailyQuantity)
            put(NormalQuantityColumn, person.NormalQuantity)
        }
        val result = helper?.insert(TABLE_NAME, null, values)
        helper?.close()
        Log.v("the quantity added", "$result")
        return (Integer.parseInt("$result") != -1)
    }


}