package com.wlswnwns.chosung_android.hunminGameOver

import android.util.Log
import com.wlswnwns.chosung_android.item.Game
import org.json.JSONArray

class HunminGameOverModel {

    var Game: Game = Game()
    var ResultInfo: ArrayList<Game>? = null

    fun arrResultInfo(jsonArray: JSONArray): ArrayList<Game> {

        ResultInfo = ArrayList()

        for (index in 0 until jsonArray.length()) {
            var userObj = jsonArray.getJSONObject(index)

            var result = Game().apply {
                iOrder = userObj.getInt("iOrder"); strUserName = userObj.getString("strNickName")
            }

            ResultInfo?.add(result)

        }

        return ResultInfo!!

    }
} 