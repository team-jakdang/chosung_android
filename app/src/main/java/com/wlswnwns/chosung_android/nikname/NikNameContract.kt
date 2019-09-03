package com.wlswnwns.chosung_android.nikname


// MVP 에서
// View 와 Presenter 의 뼈대를 정의해줍니다
// MVP 에서 View와 Presenter는 1:1 로 결합됩니다
// View에는 UI에 관련된 코드를 작성합니다
// View에는 로직이 필요없게 작성하는게 좋습니다

// Presenter는 View 에서 요청이오면
// Model 에서 필요한 데이터 값을 가져와
// View의 UI를 갱신해주는 역할을 합니다

// 닉네임이 10글자를 넘으면 토스트 메세지를 띄워주고 '확인' 버튼을 비활성화합니다
// 닉네임이 10글자를 넘지 않으면 확인 버튼읋 활성화해줍니다
// 확인 버튼을 누르면 메인 프레그먼트를 띄워줍니다

interface NikNameContract{

    interface View{

        // 뷰를 초기화해줍니다
        fun viewInit()

        // 닉네임이 10글자를 넘으면 안된다는 토스트 메세지를 띄워줍니다
        fun longNikName()

        // '확인' 버튼을 활성화해줍니다
        fun confirmBtnActive()

        // '확인' 버튼을 비활성화해줍니다
        fun confirmBtnUnActive()

        // 메인 프레그먼트를 띄워줍니다
        fun MoveMainFragment()


    }

    interface Presenter{

        // 프레그먼트의 뷰가 생성되면 호출됩니다
        fun viewDidLoad()

        // 모델의 닉네임을 변경해줍니다
        fun setStrNikName(nikname : String)
        fun getStrNikName():String

        // 모델에 입력된 닉네임의 길이를 확인해
        // 10 글자가 넘으면 view 의 longNikName(), confirmBtnUnActive() 를 실행해줍니다
        // 10 글자가 넘지 않으면 confirmBtnActive() 를 실행해줍니다
        fun checkNikNameLength()

    }


}