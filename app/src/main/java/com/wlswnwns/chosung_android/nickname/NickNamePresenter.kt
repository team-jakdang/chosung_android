package com.wlswnwns.chosung_android.nickname

class NickNamePresenter(view : NickNameContract.View) : NickNameContract.Presenter{



    var view : NickNameContract.View
    var model : NickNameModel

    init {
        this.view = view
        this.model = NickNameModel()

    }

    override fun viewDidLoad() {
        view.viewInit()
    }

    override fun setStrNikName(nikname: String) {
       model.strUserNikName = nikname
    }
    override fun getStrNikName(): String {
        return model.strUserNikName
    }

    override fun checkNikNameLength() {

        if(model.strUserNikName.length <= 10) {
            view.confirmBtnActive()
        } else {
            view.confirmBtnUnActive()
            view.longNikName()
        }
    }

}