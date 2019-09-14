package com.wlswnwns.chosung_android.intro

interface IntroContract{

    interface View{

        // 네트워크 연결이 되어있지 않을때 호출되는 메세지
        fun showNetWorknotConnectMsg()

        // 어플리케이션 종료
        fun finishApp()

        //닉네임 화면으로 이동합니다
        fun moveNickNameFragment()
    }

    interface Presenter{

        // 인트로 화면이 보이면 호춯됩니다
        fun startApplication()

        // 네트워크 연결을 체크합니다
        fun checkNetWorkState()

        // aws 환경설정 확인
        fun checkAWSInfo()

    }


}