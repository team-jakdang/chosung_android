package com.wlswnwns.chosung_android.nikname

class NikNamePresenter(view : NikNameContract.View) : NikNameContract.Presenter{



    var view : NikNameContract.View
    var model : NikNameModel

    init {
        this.view = view
        this.model = NikNameModel()

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

        if(model.strUserNikName.length <= 10) view.confirmBtnActive() else view.confirmBtnUnActive(); view.longNikName()
    }

}