package com.example.foodies_vezdecode.storage

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context, private val TAG: String) {

    private val sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)

    fun get(): String {
        return sharedPreferences.getString(TAG, "")!!
    }

    fun check(): Boolean {
        if (sharedPreferences.contains(TAG)) {
            if (get() != "") {
                return true
            }
        }
        return false
    }

    fun clean() {
        sharedPreferences.edit().remove(TAG).apply()
    }

    fun save(value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(TAG, value)
        editor.apply()
    }
}