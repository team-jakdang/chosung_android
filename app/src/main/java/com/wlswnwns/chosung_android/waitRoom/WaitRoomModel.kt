package com.wlswnwns.chosung_android.waitRoom

import android.graphics.Bitmap
import android.os.Messenger
import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketException
import com.neovisionaries.ws.client.WebSocketFactory
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room
import com.wlswnwns.chosung_android.item.User
import com.wlswnwns.chosung_android.utils.APiAsyc
import net.glxn.qrgen.android.QRCode
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.UnknownHostException


class WaitRoomModel {

    var isRoomOwner = false

    var Users: ArrayList<User>? = null

    var Game: Game = Game()

    var room: Room = Room()


    fun InitUserList(jsonArray: JSONArray): ArrayList<User> {
        Users = ArrayList()
        for (index in 0 until jsonArray.length()) {
            var userObj = jsonArray.getJSONObject(index)
            var user = User().apply {
                strUserName = userObj.getString("strNickName")
                bIsActive = userObj.getInt("bIsActive") == 1
                bIsMaster = userObj.getInt("bIsMaster") == 1
            }
            Users?.add(user)
        }

        return Users!!
    }

    fun OutGameUser(jsonArray: JSONArray): ArrayList<User> {
        var Outuser : User? = null

        for (index in 0 until jsonArray.length()) {
            var userObj = jsonArray.getJSONObject(index)
            Outuser = User().apply {
                strUserName = userObj.getString("strNickName")
            }
        }

        for(index in Users?.size!!-1 downTo 1){
            if (Users!![index].strUserName.equals(Outuser?.strUserName)){
                Users?.removeAt(index)
            }
        }


        return Users!!
    }



    fun makeRoomQRCode(): Bitmap {
        return QRCode.from(room.iRoomId.toString()).bitmap()
    }


}