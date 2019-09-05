package com.wlswnwns.chosung_android.KungKungDdaGame

import com.wlswnwns.chosung_android.KungKungDdaGame.KungKungDdaGameContract
import com.wlswnwns.chosung_android.KungKungDdaGame.KungKungDdaGameModel

class KungKungDdaGamePresenter(view : KungKungDdaGameContract.View) : KungKungDdaGameContract.Presenter {



    var view : KungKungDdaGameContract.View
    var model : KungKungDdaGameModel

    init {
        this.view = view
        this.model= KungKungDdaGameModel()
    }




}