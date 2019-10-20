package com.wlswnwns.chosung_android.main

import android.graphics.Bitmap
import android.util.Log
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room
import com.wlswnwns.chosung_android.item.User
import com.wlswnwns.chosung_android.utils.APiAsyc
import org.json.JSONObject

class MainModel{


   var Game : Game = Game()

   lateinit var apiAsyc : APiAsyc

   lateinit var room : Room

   lateinit var user : User


   fun CreateUser(nickName : String){
      user = User().apply { strUserName = nickName }
   }


   fun makeRoomRequest(makeRoomListner: makeRoomListner) {
      val gameData = JSONObject()
      try {
         gameData.put("strGameMode", Game.strMode)
         gameData.put("iWordLength", Game.iChosungLenght)
         gameData.put("iTimeLimit", Game.iTime)
         apiAsyc = APiAsyc("room-control/makeRoom", "POST", gameData)
         apiAsyc.execute()

         val r = apiAsyc.get()
         val result = JSONObject(r)
         Log.e("result", r)


         if (r != "{}") {

            room = Room()

            room.iRoomId = result.getInt("iRoomId")

            makeRoomListner.onSuccess()
         } else {
            makeRoomListner.onFail()
         }

      } catch (e: Exception) {
         e.printStackTrace()
      }
   }

   interface makeRoomListner{
      fun onSuccess()
      fun onFail()
   }


}