package com.wlswnwns.chosung_android.nickname

import android.content.Context
import android.util.Log
import com.wlswnwns.chosung_android.ChosungApplication
import org.json.JSONException
import org.json.JSONObject
import java.net.SocketException

class NickNamePresenter(view: NickNameContract.View) : NickNameContract.Presenter {


    var view: NickNameContract.View
    var model: NickNameModel

    init {
        this.view = view
        this.model = NickNameModel()

    }

    override fun viewDidLoad(context: Context) {
        view.viewInit(model.getNickName(context))
        view.requestPermission()
        try {
            ChosungApplication.SocketConnect(object : ChosungApplication.Companion.SocketConnectListner {
                override fun onDataReceived(jsonObject: JSONObject) {
                    try {
                        Log.e("onDataReceived", "onDataReceived")
                    }catch (e: JSONException){
                        e.printStackTrace()
                    }

                }
                override fun onConnet() {
                    Log.e("onConnected", "Success")


                }
            })
        }catch (e: SocketException){
            Log.e("WebSocket 에러2 -->", e.toString())
        }
    }

    override fun setStrNikName(nikname: String) {
        model.strUserNikName = nikname
    }

    override fun getStrNikName(): String {
        return model.strUserNikName
    }

    override fun checkNikNameLength() {

        if (model.strUserNikName.length <= 10) {
            view.confirmBtnActive()
        } else {
            view.confirmBtnUnActive()
            view.longNikName()
        }
    }

    override fun onClickConfirmBtn(context: Context) {
        model.saveNickName(context)
        view.moveMainFragment()
    }


}