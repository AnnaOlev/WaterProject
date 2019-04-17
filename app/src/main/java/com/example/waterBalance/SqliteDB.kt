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
        val DateColumn = "date"
        val DailyQuantityColumn = "daily_quantity"
        val PartOfNormalQuantityColumn = "part_of_normal_quantity"
    }

    private val SQL_CREATE_ENTIRES = "CREATE TABLE $TABLE_NAME " +
            "(" +  "$DateColumn String primaty key, " + "$DailyQuantityColumn Double, " +
            "$PartOfNormalQuantityColumn Float" + ")"

    private val SQL_DELETE_ENTIRES = "DROP TABLE IF EXISTS ${TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTIRES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTIRES)
        onCreate(db)
    }

    fun addItem(data: DailyData): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(DailyQuantityColumn, data.dailyQuantity)
            put(PartOfNormalQuantityColumn, data.partOfNormalQuantity)
            put(DateColumn, data.date)
        }
        val result = db?.insert(TABLE_NAME, null, values)
        db?.close()
        Log.v("the quantity added", "$result")
        return (Integer.parseInt("$result") != -1)
    }

    fun getAllDays(): ArrayList<DailyData> {
        val db = readableDatabase
        val dataList = ArrayList<DailyData>()
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val date = cursor.getString(cursor.getColumnIndex(DateColumn))
                    val dailyQuantity = cursor.getDouble(cursor.getColumnIndex(DailyQuantityColumn))
                    val partOfNormalQuantity = cursor.getFloat(cursor.getColumnIndex(PartOfNormalQuantityColumn))

                    val dailyData = DailyData(dailyQuantity, partOfNormalQuantity.toFloat(), date)
                    dataList.add(dailyData)

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return dataList
    }


}