package com.wlswnwns.chosung_android.nickname

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class NickNameModel {

    var strUserNikName : String = ""

    fun saveNickName(context: Context){
        var local : SharedPreferences = context.getSharedPreferences("nick",Context.MODE_PRIVATE)
        local.edit().apply {
            putString("nick",strUserNikName)
            commit()
        }
    }

    fun getNickName(context: Context): String{
        var local : SharedPreferences = context.getSharedPreferences("nick",Context.MODE_PRIVATE)
        strUserNikName = local.getString("nick","")

        return strUserNikName
    }



}