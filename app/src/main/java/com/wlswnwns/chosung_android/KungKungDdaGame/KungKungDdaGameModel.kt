package com.wlswnwns.chosung_android.KungKungDdaGame

import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room
import com.wlswnwns.chosung_android.item.User
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class KungKungDdaGameModel {

    //유저가 입력한 텍스트 값
    var strUserInputEditText : String = ""

    var UserList = ArrayList<User>()


    var iTimeLimit:Int = 0

    var room = Room()

    var game = Game()

    var ChosungLog = ArrayList<Game>()

    var strNowTurnUserName = ""


    var NowTurnUser : User = User()


    fun InitSockerListner(sockerListner: SockerListner){
        ChosungApplication.client?.clearListeners()

        ChosungApplication.client?.addListener(object : WebSocketAdapter() {
            override fun onTextMessage(websocket: WebSocket?, text: String?) {
                super.onTextMessage(websocket, text)
                Log.e("메세지===>", text)
                var jsonObj =  JSONObject(text)
                val action = Runnable {
                    Log.e("onDataReceived", JSONObject(text).getString("strEvent"))
                    sockerListner.onDataReceived(jsonObj)
                }

                ChosungApplication.activity.runOnUiThread(action)


            }
        })
    }

    interface SockerListner{
        fun onDataReceived(jsonObject: JSONObject)

    }


    fun GameStart(){
        ChosungApplication.startKKTGame()
    }


    fun InitUserList(jsonArray : JSONArray) : ArrayList<User> {
        UserList = ArrayList()
        for (index in 0 until jsonArray.length()) {
            var userObj = jsonArray.getJSONObject(index)
            var user = User().apply {
                strUserName = userObj.getString("strNickName")
                bIsActive = userObj.getInt("bIsActive") == 1
                bIsMaster = userObj.getInt("bIsMaster") == 1
                iOrder = userObj.getInt("iOrder")
            }
            UserList?.add(user)
        }

        Collections.sort<User>(UserList) { o1, o2 -> o1.iOrder.compareTo(o2.iOrder) }

        return UserList

    }





}