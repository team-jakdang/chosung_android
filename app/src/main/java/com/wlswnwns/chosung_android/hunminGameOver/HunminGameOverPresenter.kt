package com.wlswnwns.chosung_android.hunminGameOver

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



    override fun viewDidLoad() {
        view.viewInit()

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


}