package com.wlswnwns.chosung_android.KungKungDdaGame

interface KungKungDdaGameContract {

    interface  View{

        // 뷰 초기화
        fun viewInit()

        // 유저가 입력하는 단어가 3글자를 넘으면 안된다는 토스트 메세지
        fun longUserInputText()

        // 제한 시간이 활성화
        fun timeLimitTextActive()

        // 제한 시간이 종료될때 비활성화
        fun timeLimitTextUnActive()

        //


    }

    interface Presenter{



    }
}