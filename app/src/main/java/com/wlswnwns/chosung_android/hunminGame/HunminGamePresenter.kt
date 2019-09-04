package com.wlswnwns.chosung_android.hunminGame

class HunminGamePresenter(view : HunminGameContract.View) : HunminGameContract.Presenter {



    var view : HunminGameContract.View
    var model : HunminGameModel

    init {
        this.view = view
        this.model= HunminGameModel()
    }




}