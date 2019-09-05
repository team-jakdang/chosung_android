package com.wlswnwns.chosung_android.qrcamera



interface CameraContract {

    interface View {

        // 뷰를 초기화해줍니다
        fun viewInit()

        // 카메라의 핸들러를 초기화해주고
        // 카메라를 시작해줍니다
        fun initCamera()

        // 대기방으로 이동합니다
        fun moveWaitRoomFragment()


    }

    interface Presenter {

        // 프레그먼트의 뷰가 생성되면 호출됩니다
        fun viewDidLoad()

        // QR 인식결과를 모델에 세팅해줍니다
        fun setQRData(data : String)

        // 모델에 세팅된 QR데이터를 확인합니다
        // 확인이 되면 대기방화면으로 넘어가게됩니다
        fun checkQRData()

    }


}