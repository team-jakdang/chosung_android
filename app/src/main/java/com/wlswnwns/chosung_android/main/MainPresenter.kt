package com.wlswnwns.chosung_android.main

class MainPresenter(view : MainContract.View) : MainContract.Presenter {

    var view : MainContract.View

    init {
        this.view = view
    }


    override fun viewDidLoad() {
        view.viewInit()
    }
}