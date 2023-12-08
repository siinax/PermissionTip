package com.kedaya.permissiontip

import android.content.Context
import android.content.SharedPreferences

object SharedUtils {
    const val FILE_NAME = "JsLibrary"
    lateinit var sp: SharedPreferences

    fun init(context: Context) {
        sp = context.applicationContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * 添加boolean值
     */
    fun putBoolean(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }
    /**
     * 添加int值
     */
    fun putInt(key: String, value: Int) {
        sp.edit().putInt("${key}_int", value).apply()
    }

    /**
     * 获取boolean值
     */
    fun getBoolean(key: String, default: Boolean): Boolean {
        return sp.getBoolean(key, default)
    }

    /**
     * 获取int值
     */
    fun getInt(key: String, default: Int = 0): Int {
        return sp.getInt("${key}_int", default)
    }

    /**
     * 判断是否存在
     */
    fun contains(key: String): Boolean {
        return sp.contains(key)
    }
}