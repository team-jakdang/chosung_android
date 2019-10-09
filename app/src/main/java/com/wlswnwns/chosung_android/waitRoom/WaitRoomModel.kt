package com.wlswnwns.chosung_android.waitRoom

import android.graphics.Bitmap
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room
import com.wlswnwns.chosung_android.item.User
import com.wlswnwns.chosung_android.utils.APiAsyc
import net.glxn.qrgen.android.QRCode


class WaitRoomModel {

    var isRoomOwner = false

    var Users: ArrayList<User>? = null

    var Game: Game = Game()

    lateinit var apiAsyc: APiAsyc

    var room: Room = Room()

    fun makeRoomQRCode(): Bitmap {
      
        room.bitmapQRCode = QRCode.from(room.iRoomId.toString()).bitmap()
        return room.bitmapQRCode!!
    }


    fun dummyUsers() {
        Users = ArrayList()

        for (i in 0..10) {

            Users?.add(User().apply { strUserName = "User$i" })
        }
    }


}