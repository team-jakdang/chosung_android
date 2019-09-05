package com.wlswnwns.chosung_android.waitRoom

interface WaitRoomContract{

    interface View{

        // 뷰를 초기화해줍니다
        fun viewInit()

        // 훈민정음 게임 프레그먼트를 띄워줍니다
        fun moveHunMinFragment()

        // 쿵쿵따 게임 프레그먼트를 띄워줍니다
        fun moveKungKungDdaFragment()

    }

    interface Presenter{
        // 프레그먼트의 뷰가 생성되면 호출됩니다
        fun viewDidLoad()

    }


}