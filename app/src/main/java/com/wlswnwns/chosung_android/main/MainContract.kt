package com.wlswnwns.chosung_android.main

interface MainContract {

    interface  View {

        fun viewInit()

        // 게임 세팅 다이얼로그 생성
        fun showGameSettingDialog()

        // 게임 세팅 다이얼로그 닫기
        fun closeGameSettingDialog()


        // 대기방으로 이동합니다
        fun moveWaitRoomFragment()

        // QR카메라를 띄워줍니다
        fun moveQRCameraFragment()
    }


    interface Presenter{

        // 프레그먼트의 뷰가 생성되면 호출됩니다
        fun viewDidLoad()

        // 게임 세팅 다이얼로그를 생성합니다
        fun makeGameSetting()

        // 게임 세팅 다이얼로그를 닫습니다
        fun closeGameSetting()

        //게임 세팅 값을 모델에 전달합니다
        fun gameSetting(mode:String,length:Int,time:Int)

        //게임방을 만듭니다
        fun makeGame()


    }
}