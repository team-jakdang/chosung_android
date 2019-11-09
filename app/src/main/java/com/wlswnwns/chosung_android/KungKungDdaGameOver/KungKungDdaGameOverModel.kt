package com.wlswnwns.chosung_android.KungKungDdaGameOver

import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.KungKungDdaGame.KungKungDdaGameModel
import org.json.JSONObject

class KungKungDdaGameOverModel {

    fun InitSockerListner(sockerListner: KungKungDdaGameModel.SockerListner){
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

}