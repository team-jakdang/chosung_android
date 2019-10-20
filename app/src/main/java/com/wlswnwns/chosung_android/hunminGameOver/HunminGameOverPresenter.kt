package com.wlswnwns.chosung_android.hunminGameOver

class HunminGameOverPresenter(view: HunminGameOverContract.View) : HunminGameOverContract.Presenter {




    var view: HunminGameOverContract.View
    var model: HunminGameOverModel
 
    // 생성자
    init {
        this.view = view
        this.model = HunminGameOverModel()
    }



    override fun viewDidLoad() {
        view.viewInit()
    }


}