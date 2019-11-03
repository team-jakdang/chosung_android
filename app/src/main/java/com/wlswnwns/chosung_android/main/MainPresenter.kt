package com.wlswnwns.chosung_android.main

import android.util.Log
import com.wlswnwns.chosung_android.ChosungApplication
import org.json.JSONException
import org.json.JSONObject
import java.net.SocketException

class MainPresenter(view: MainContract.View) : MainContract.Presenter {


    var view: MainContract.View
    var model: MainModel

    init {
        this.view = view
        this.model = MainModel()
    }


    override fun viewDidLoad(nickName: String) {
        view.viewInit()
        model.CreateUser(nickName)
    }


    override fun gameSetting(mode: String, length: Int, time: Int) {
        model.Game.strMode = mode
        model.Game.iChosungLenght = length
        model.Game.iTime = time
    }

    override fun makeGame() {
        view.closeGameSettingDialog()

        model.makeRoomRequest(object : MainModel.makeRoomListner {
            override fun onSuccess() {
                view.moveWaitRoomFragment(model.Game,model.room)
            }
            override fun onFail() {
                view.showFailMakeRoomMsg()
            }

        })

    }


    override fun closeGameSetting() {
        view.closeGameSettingDialog()
    }

    override fun onClickMakeRoomBtn() {
        view.showGameSettingDialog()
    }


}