package com.wlswnwns.chosung_android.hunminGameOver

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.User
import org.json.JSONArray

class HunminGameOverModel {

    var Game: Game = Game()
    var strUserNikName : String = ""
    var ResultInfo: ArrayList<Game>? = null

    fun arrResultInfo(jsonArray: JSONArray): ArrayList<Game> {

        ResultInfo = ArrayList()

        for (index in 0 until jsonArray.length()) {
            var userObj = jsonArray.getJSONObject(index)

            var result = Game().apply {
                iOrder = userObj.getInt("iOrder")
                strUserName = userObj.getString("strNickName")
                strUserConnectionId = userObj.getString("strUserConnectionId")
                bIsFailed = userObj.getBoolean("bIsFailed")
                bIsMaster = userObj.getInt("bIsMaster")==1
            }

            ResultInfo?.add(result)

        }

        return ResultInfo!!

    }
    fun getNickName(context: Context): String{
        var local : SharedPreferences = context.getSharedPreferences("nick", Context.MODE_PRIVATE)
        strUserNikName = local.getString("nick","")

        return strUserNikName
    }
} 