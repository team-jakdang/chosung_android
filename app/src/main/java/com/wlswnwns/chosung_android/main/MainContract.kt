package com.wlswnwns.chosung_android.main

interface MainContract {

    interface  View {

        fun viewInit()

//        fun hunminGameBtn()


        // 대기방으로 이동합니다
        fun moveWaitRoomFragment()

        // QR카메라를 띄워줍니다
        fun moveQRCameraFragment()
    }


    interface Presenter{

        // 프레그먼트의 뷰가 생성되면 호출됩니다
        fun viewDidLoad()


    }
}