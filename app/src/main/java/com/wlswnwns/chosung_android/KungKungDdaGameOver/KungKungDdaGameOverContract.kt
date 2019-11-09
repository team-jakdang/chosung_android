package com.wlswnwns.chosung_android.KungKungDdaGameOver

interface KungKungDdaGameOverContract {


    interface  View {

        fun viewInit()
        fun exitRoom()

    }

    interface Presenter{
        fun viewDidLoad()

        fun finishGame()
    }
}