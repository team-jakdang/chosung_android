package com.wlswnwns.chosung_android.hunminGame

import com.wlswnwns.chosung_android.item.Game

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

    var GameLog : ArrayList<Game>? = null

    fun dummyGameLogs(){
        GameLog = ArrayList()

        for(i in 0..10){
            GameLog?.add(Game().apply { strUserName = "User$i"  })
            GameLog?.add(Game().apply { strChosung = "chosung$i"  })

        }
    }

}