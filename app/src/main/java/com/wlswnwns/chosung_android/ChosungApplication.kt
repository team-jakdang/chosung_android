package com.wlswnwns.chosung_android

import android.app.Activity
import android.app.Application
import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketException
import com.neovisionaries.ws.client.WebSocketFactory
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.UnknownHostException

class ChosungApplication : Application() {

    companion object {
        lateinit var activity: Activity


        var client: WebSocket? = null

        //서버 주소
        var ip = "wss://an7iczphaj.execute-api.ap-northeast-2.amazonaws.com/dev"
        var thread: Thread? = null

        var IsMaster: Boolean = false

        var roomId: Int = 0

        lateinit var nikname: String


        fun SocketConnect(socketConnectListner: SocketConnectListner) {


            Log.e("소켓이 생성 안됐음", "생성함")

            thread = object : Thread() {
                override fun run() {
                    super.run()
                    try {
                        WebSocketFactory().apply {
                            client = createSocket((ip))
                            client?.addListener(object : WebSocketAdapter() {
                                override fun onTextMessage(websocket: WebSocket?, text: String?) {
                                    super.onTextMessage(websocket, text)
                                    Log.e("메세지===>", text)

                                    JSONObject(text).apply {
                                        when (this.getString("strEvent")) {
                                            "enterRoom" -> {
                                                val action = Runnable {
                                                    try {
                                                        // InitUserList(this.getJSONArray("arrUserInfo"))
                                                        socketConnectListner.onLoadUserList(
                                                            this.getJSONArray(
                                                                "arrUserInfo"
                                                            )
                                                        )
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

                            client?.addListener(object : WebSocketAdapter() {
                                override fun onConnected(
                                    websocket: WebSocket?,
                                    headers: MutableMap<String, MutableList<String>>?
                                ) {
                                    super.onConnected(websocket, headers)
                                    socketConnectListner.onConnet()
                                }
                            })

                            client?.addListener(object : WebSocketAdapter() {
                                override fun onError(
                                    websocket: WebSocket?,
                                    cause: WebSocketException?
                                ) {
                                    super.onError(websocket, cause)
                                    Log.e("커넥트 에러 -->", cause.toString())
                                }
                            })

                        }
                        client?.connect()

                    } catch (e: UnknownHostException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: WebSocketException) {
                        e.printStackTrace()
                    }
                }
            }

            thread?.start()


        }


        interface SocketConnectListner {
            fun onConnet()
            fun onLoadUserList(jsonArray: JSONArray)
        }

        fun enterRoom(IsMaster: Boolean, roomId: Int, nikname: String) {

            this.IsMaster = IsMaster

            this.roomId = roomId

            this.nikname = nikname

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


        fun startKKTGame() {


            var data_obj = JSONObject()

            try {

                data_obj.put("action", "startKKT")
                data_obj.put("iRoomId", roomId)

                client?.let {
                    client?.sendText(data_obj.toString())
                    Log.e("메세지 보냄  쿵쿵따===>", data_obj.toString())
                }.let {

                }


            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

    }

}