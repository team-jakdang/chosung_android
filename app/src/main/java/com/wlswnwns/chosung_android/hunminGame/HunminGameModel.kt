package com.wlswnwns.chosung_android.hunminGame

import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Test

class HunminGameModel {

    var strUserInputEditText : String = ""
    var listGameLog = arrayListOf<Any>()
    var roomInfo : String = ""
    var chosungLength : Int = 2
    var iTime : Int = 2
    var userInfo : String = ""
    var randomChosung = arrayOf("ㅊ","ㅇ","ㅊ")

    var listUserOrderList = arrayListOf<Any>()

    var isRoomOwner = false

    var Game: Game = Game()
    var ChosungLog : ArrayList<Test>? = null

//    fun dummyGameLogs(){
//        ChosungLog = ArrayList()
//
//        for(i in 0..10){
//            ChosungLog?.add(Game().apply { strUserName = "User$i"  })
//            ChosungLog?.add(Game().apply { strChosung = "chosung$i"  })
//
//        }
//    }

}