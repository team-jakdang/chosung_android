package com.wlswnwns.chosung_android.KungKungDdaGameOver

class KungKungDdaGameOverPresenter(view: KungKungDdaGameOverContract.View) : KungKungDdaGameOverContract.Presenter {


    var view : KungKungDdaGameOverContract.View

    init {
        this.view = view

    }


    override fun viewDidLoad() {
        view.viewInit()
    }
}