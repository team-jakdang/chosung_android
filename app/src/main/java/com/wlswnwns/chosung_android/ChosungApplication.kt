package com.wlswnwns.chosung_android

import android.app.Activity
import android.app.Application
import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketException
import com.neovisionaries.ws.client.WebSocketFactory
import com.wlswnwns.chosung_android.item.Game
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
        var Game: Game = Game()
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
                                                        e.printStackTrace()
                                                    }
                                                }

                                                ChosungApplication.activity.runOnUiThread(action)
                                            }
                                            "startHMJE" -> {
                                                val action = Runnable {
                                                    try {
                                                        onHunminGameStartInfo(
                                                            this.getString("iCountDown"),
                                                            this.getString("strInitialWord")
                                                        )
                                                    } catch (e: IOException) {
                                                        e.printStackTrace()
                                                    } catch (e: JSONException) {
                                                        Log.e("Error ===>", e.toString())
                                                        onHunminGameStartInfo(
                                                            this.getString("iCountDown"),
                                                            "없음"
                                                        )
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
                        Log.e("UnknownHost 에러 -->", e.toString())
                        e.printStackTrace()
                    } catch (e: IOException) {
                        Log.e("IOException 에러 -->", e.toString())
                        e.printStackTrace()
                    } catch (e: WebSocketException) {
                        Log.e("WebSocket 에러 -->", e.toString())
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



//        interface HunminStartListner {
//            fun onHunminGameStartInfo(count: String, chosung: String)
//
//        }



        fun onHunminGameStartInfo(count: String, chosung: String) {
            Log.e("카운트", count + chosung)

            Game.iCountDown = count
            Game.strInitialWord = chosung



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

        fun startHMJEGame() {


            var data_obj = JSONObject()

            try {

                data_obj.put("action", "startHMJE")
                data_obj.put("iRoomId", roomId)

                client?.let {
                    client?.sendText(data_obj.toString())
                    Log.e("메세지 보냄  훈민정음===>", data_obj.toString())
                }.let {

                }


            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }


        fun startHMJEGameTimeCheck() {
            var data_obj = JSONObject()

            try {

                data_obj.put("action", "checkTimeHMJE")
                data_obj.put("iRoomId", roomId)

                client?.let {
                    client?.sendText(data_obj.toString())
                    Log.e("메세지 보냄 훈민정음 게임 시작체크===>", data_obj.toString())
                }.let {

                }


            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

    }

}