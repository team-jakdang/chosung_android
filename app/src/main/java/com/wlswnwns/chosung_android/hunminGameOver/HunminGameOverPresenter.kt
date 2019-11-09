package com.wlswnwns.chosung_android.hunminGameOver

import android.content.Context
import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.item.Game
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONArray



class HunminGameOverPresenter(view: HunminGameOverContract.View) : HunminGameOverContract.Presenter {




    var view: HunminGameOverContract.View
    var model: HunminGameOverModel


    // 생성자
    init {
        this.view = view
        this.model = HunminGameOverModel()
    }



    override fun viewDidLoad(context: Context) {
        view.viewInit()
        model.getNickName(context)

        ChosungApplication.client?.clearListeners()
        Log.e("viewDidLoad", "Hunmin Game Over Presenter")

        ChosungApplication.client?.addListener(object : WebSocketAdapter() {
            override fun onTextMessage(websocket: WebSocket?, text: String?) {
                super.onTextMessage(websocket, text)
                Log.e("메세지===>", text)
                JSONObject(text)
                val action = Runnable {
                    Log.e("Game OVer Presenter", "onDataReceived"+JSONObject(text).getString("strEvent"))
                    if (JSONObject(text).getString("strEvent") == "moveToGame") {
                        try {
                            Log.e("strEvent moveToGame", text)
                            view.moveHunminGameFragment()
                        }catch (e:JSONException){
                            e.printStackTrace()
                        }

                    }else if (JSONObject(text).getString("strEvent") == "THE_ROOM_IS_DESTROYED") {

                        //방장이 방 파괴

                    }
                }

                ChosungApplication.activity.runOnUiThread(action)
            }
        })

    }
    override fun setResult(resultArr: String) {
        Log.e("setResult ?" ,"" + resultArr)
        if(resultArr !== "[]"){
            val gameResult: JSONArray

            try {
                gameResult = JSONArray(resultArr)
                view.showResult(model.arrResultInfo(gameResult))
            } catch (e: JSONException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
    }

    override fun onClickGameReStartBtn() {
        ChosungApplication.moveToGame()
    }


}