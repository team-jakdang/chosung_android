package com.wlswnwns.chosung_android.KungKungDdaGameOver

interface KungKungDdaGameOverContract {


    interface  View {

        fun viewInit()
        fun exitRoom()
        fun restartGame()

    }

    interface Presenter{
        fun viewDidLoad()

        fun finishGame()

        fun restartGame()
    }
}