package com.wlswnwns.chosung_android.waitRoom

import com.wlswnwns.chosung_android.item.User

class WaitRoomModel {

    var isRoomOwner = false

    var Users : ArrayList<User>? = null

    var strMode : String = ""
    var iLength : Int = 2
    var iTime : Int = 10


    fun dummyUsers(){
        Users = ArrayList()

        for(i in 0..10){
            Users?.add(User().apply { strUserName = "User$i" })
        }
    }



}