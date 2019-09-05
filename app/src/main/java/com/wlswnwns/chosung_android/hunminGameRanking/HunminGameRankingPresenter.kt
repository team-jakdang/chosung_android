package com.wlswnwns.chosung_android.hunminGameRanking

class HunminGameRankingPresenter(view: HunminGameRankingContract.View) : HunminGameRankingContract.Presenter {




    var view: HunminGameRankingContract.View
    var model: HunminGameRankingModel

    // 생성자
    init {
        this.view = view
        this.model = HunminGameRankingModel()
    }



    override fun viewDidLoad() {
        view.viewInit()
    }


}