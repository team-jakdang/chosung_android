package com.wlswnwns.chosung_android.hunminGameOver

import android.content.Context
import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.wlswnwns.chosung_android.ChosungApplication
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

    }

    override fun setResult(resultArr: String) {
        Log.e("setResult ?" ,"" + resultArr)
        var bIsMaster=false
        if(resultArr !== "[]"){
            val gameResult: JSONArray

            try {
                gameResult = JSONArray(resultArr)
                for (i in 0 until gameResult.length()) {
                    Log.e("setResult ?" ,".getString(\"strNickName\")" + gameResult.getJSONObject(i).getString("strNickName"))
                    Log.e("setResult" ,"strUserName" + model.strUserNikName)
                    if (gameResult.getJSONObject(i).getString("strNickName")==model.strUserNikName){
                        bIsMaster = gameResult.getJSONObject(i).getInt("bIsMaster")==1
                    }
                }
                Log.e("setResult ?" ,"" + bIsMaster)
                view.showResult(model.arrResultInfo(gameResult), bIsMaster)
            } catch (e: JSONException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
    }


}