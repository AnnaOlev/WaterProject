package com.example.waterBalance

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_FILENAME = "com.example.waterbalance"
    val USERS_NORM = "users_norm"
    val CURRENT_AMOUNT = "current_amount"
    val CURRENT_DATE = "current_date"
    val USERS_GENDER = "users_gender"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var usersNorm: Int
        get() = prefs.getInt(USERS_NORM, 0)
        set(value) = prefs.edit().putInt(USERS_NORM, value).apply()

    var currentAmount: Int
        get() = prefs.getInt(CURRENT_AMOUNT, 0)
        set(value) = prefs.edit().putInt(CURRENT_AMOUNT, value).apply()

    var currentDate: String
        get() = prefs.getString(CURRENT_DATE, "default")
        set(value) = prefs.edit().putString(CURRENT_DATE, value).apply()

    var usersGender: String
        get() = prefs.getString(USERS_GENDER, "default")
        set(value) = prefs.edit().putString(USERS_GENDER, value).apply()
}