package com.wlswnwns.chosung_android.hunminGameRanking

interface HunminGameRankingContract {


    interface  View {

        // 뷰 초기화
        fun viewInit() 
    }

    interface Presenter{
        // 프레그먼트의 뷰가 생성되면 호출 (viewDidLoad()안에서 viewInit()을 실행한다)
        fun viewDidLoad()
    }
}