package com.wlswnwns.chosung_android.main

class MainPresenter(view: MainContract.View) : MainContract.Presenter {


    var view: MainContract.View
    var model: MainModel

    init {
        this.view = view
        this.model = MainModel()
    }


    override fun viewDidLoad() {
        view.viewInit()
    }


    override fun gameSetting(mode: String, length: Int, time: Int) {
        model.strMode = mode
        model.iChosungLenght = length
        model.iTime = time
    }

    override fun makeGame() {
        view.closeGameSettingDialog()
        view.moveWaitRoomFragment()
    }


    override fun closeGameSetting() {
        view.closeGameSettingDialog()
    }

    override fun onClickMakeRoomBtn() {
        view.showGameSettingDialog()
    }



}