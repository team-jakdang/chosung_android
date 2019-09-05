package com.wlswnwns.chosung_android.hunminGame

interface HunminGameContract {

    interface  View{

        // 뷰 초기화
        fun viewInit()

        // 유저가 입력하는 단어가 3글자를 넘으면 안된다는 토스트 메세지
        fun longUserInputText()

        // 제한 시간이 게임 시작하면 활성화
        fun timeLimitTextActive()

        // 제한 시간이 종료될때 비활성화
        fun timeLimitTextUnActive()

        // 유저가 입력하는 단어가 정답에 속할때 바뀌는 뷰
        fun answerGameView()

        // 유저가 입력하는 단어가 정답이 아닐때 바뀌는 뷰
        fun wrongGameView()

        // 정답이 아닐때 보여주는 아이콘이 1초가 지나면 사라지게
        fun defaultGameView()

        fun listViewGameLogs(arg : ArrayList<Any>)
        // 순위 발표 프레그먼트


    }

    interface Presenter{


        // 프레그먼트의 뷰가 생성되면 호출 (viewDidLoad()안에서 viewInit()을 실행한다)
        fun viewDidLoad()


        // 모델의 유저가 입력한 텍스트를 변경
        fun setStrUserInputEditText(inputText : String)
        fun getStrUserInputEditText():String
        fun setListGameLog(inputText : String)
        fun getListGameLog():ArrayList<Any>

        // 랜덤 초성은 3글자 이하이기 때문에 유저가 3글자 이상 쓸 경우 view 의 longUserInputText()를 실행해준다
        fun checkUserInputTextLength()

        // 유저가 입력한 단어가 답에 속하는지 여부를 판단하기 위한 메소드(추후 서버와 통신)
        fun checkUserInputTextIsAnswer()

        // 실패뷰를(X표시) 1초뒤에 사라지게 하는 메소드
        fun wrongViewTimeSet()

        // 게임 로그를 쌓아 채팅방에 보여주는 메소드
        fun listViewGameLog()


    }

}