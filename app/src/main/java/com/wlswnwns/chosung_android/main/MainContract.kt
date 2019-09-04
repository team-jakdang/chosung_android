package com.wlswnwns.chosung_android.main

interface MainContract {

    interface  View {

        fun viewInit()

//        fun hunminGameBtn()

        fun MoveHunminGameFragment()
    }


    interface Presenter{

        // 프레그먼트의 뷰가 생성되면 호출됩니다
        fun viewDidLoad()


    }
}