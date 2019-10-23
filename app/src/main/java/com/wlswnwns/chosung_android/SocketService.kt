package com.wlswnwns.chosung_android

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketException
import com.neovisionaries.ws.client.WebSocketFactory
import org.json.JSONObject
import java.io.IOException
import java.net.UnknownHostException

class SocketService : Service(){


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
                                                    //InitUserList(this.getJSONArray("arrUserInfo"))
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


    override fun onBind(p0: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}