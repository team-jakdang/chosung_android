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

    lateinit var apiAsyc: APiAsyc

    var room: Room = Room()

    var client: WebSocket? = null

    //서버 주소
    var ip = "wss://an7iczphaj.execute-api.ap-northeast-2.amazonaws.com/dev"
    lateinit var thread: Thread



    fun SocketConnect(socketConnectListner : SocketConnectListner) {

        thread = object : Thread() {
            override fun run() {
                super.run()
                try {
                    WebSocketFactory().apply { client =  createSocket((ip))
                        client?.addListener( object : WebSocketAdapter(){
                            override fun onTextMessage(websocket: WebSocket?, text: String?) {
                                super.onTextMessage(websocket, text)
                                Log.e("메세지===>",text)

                                JSONObject(text).apply {
                                    when(this.getString("strEvent")){
                                        "enterRoom" ->{
                                            val action = Runnable {
                                                try {
                                                    InitUserList(this.getJSONArray("arrUserInfo"))
                                                    socketConnectListner.onLoadUserList()
                                                } catch (e: IOException) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace()
                                                }
                                            }

                                            ChosungApplication.activity.runOnUiThread(action)
                                        }
                                    }
                                }
                            }
                        })

                        client?.addListener(object : WebSocketAdapter(){
                            override fun onConnected(
                                websocket: WebSocket?,
                                headers: MutableMap<String, MutableList<String>>?
                            ) {
                                super.onConnected(websocket, headers)
                                socketConnectListner.onConnet()
                            }
                        })

                        client?.addListener(object : WebSocketAdapter(){
                            override fun onError(
                                websocket: WebSocket?,
                                cause: WebSocketException?
                            ) {
                                super.onError(websocket, cause)
                                Log.e("커넥트 에러 -->",cause.toString())
                            }
                        })

                    }
                    client?.connect()

                } catch (e: UnknownHostException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }

        thread.start()
    }


    interface SocketConnectListner{
        fun onConnet()
        fun onLoadUserList()
    }

    fun InitUserList(jsonArray : JSONArray) {

        Users = ArrayList()

        for(index in 0 until jsonArray.length()){
            var userObj =  jsonArray.getJSONObject(index)

            var user = User().apply { strUserName = userObj.getString("strNickName") }

            Users?.add(user)
        }

    }


    fun enterRoom(IsMaster : Boolean,roomId : Int, nikname : String){

        var data_obj = JSONObject()

        try {


            data_obj.put("action", "enterRoom")
            data_obj.put("bIsMaster", IsMaster)
            data_obj.put("iRoomId", roomId)
            data_obj.put("strNickName", nikname)

            client?.let {
                client?.sendText(data_obj.toString())
                Log.e("메세지 보냄===>", data_obj.toString())
            }.let {

            }


        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }



    fun makeRoomQRCode(): Bitmap {
        room.bitmapQRCode = QRCode.from(room.iRoomId.toString()).bitmap()
        return room.bitmapQRCode!!
    }





}