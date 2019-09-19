package com.wlswnwns.chosung_android.waitRoom

import com.wlswnwns.chosung_android.item.User

class WaitRoomModel {

    var isRoomOwner = false

    var Users : ArrayList<User>? = null

    fun dummyUsers(){
        Users = ArrayList()

        for(i in 0..10){
            Users?.add(User().apply { strUserName = "User$i" })
        }
    }



}