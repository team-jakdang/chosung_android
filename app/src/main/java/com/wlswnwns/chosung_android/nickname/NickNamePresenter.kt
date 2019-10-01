package com.wlswnwns.chosung_android.nickname

import android.content.Context

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