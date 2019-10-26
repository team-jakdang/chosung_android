package com.wlswnwns.chosung_android.hunminGame

import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room
import com.wlswnwns.chosung_android.item.Test
import com.wlswnwns.chosung_android.item.User
import org.json.JSONArray

class HunminGameModel {

    var strUserInputEditText : String = ""
    var listGameLog = arrayListOf<Any>()
    var chosungLength : Int = 2
    var iTime : Int = 2


    var Game: Game = Game()
    var User: User = User()
    var Room: Room= Room()


    var ChosungLog : ArrayList<Game>? = null



//    fun InitUserList(jsonArray : JSONArray) : ArrayList<Game> {
//
//
//
//        var gameInfo =  jsonArray.getJSONObject(index)
//
//        for(index in 0 until jsonArray.length()){
//            var userObj =  jsonArray.getJSONObject(index)
//
//            var user = Game().apply { strUserName = userObj.getString("strNickName") }
//
//            Game?.add(user)
//        }
//
//        return Users!!
//
//    }
}