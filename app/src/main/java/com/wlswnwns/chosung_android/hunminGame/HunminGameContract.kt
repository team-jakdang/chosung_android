package com.wlswnwns.chosung_android.hunminGame

interface HunminGameContract {

    interface  View{

        // 뷰 초기화
        fun viewInit()

        // 유저가 입력하는 단어가 n 글자를 넘으면 안된다는 토스트 메세지
        fun longUserInputText(chosungLength: String)

        // 게임이 시작하면 프로그래스바를 활성화
        fun timeProgressBarActive()

        // 랜덤 초성을 텍스트에 띄어줌
        fun randomChosungSetTextView(chosung : ArrayList<String>)

        // 유저가 입력하는 단어가 정답에 속할때 바뀌는 뷰
        fun answerGameView()

        // 유저가 입력하는 단어가 정답이 아닐때 바뀌는 뷰
        fun wrongGameView()

        // 정답이 아닐때 보여주는 아이콘이 1초가 지나면 사라지게
        fun defaultGameView()

        // 유저가 입력하는 단어들을 보여주는 리스트
        fun listViewGameLogs(arg : ArrayList<Any>)

        // 순위 발표 프레그먼트 띄움
        fun moveHunminGameRankingFragment()

        // 에러 메세지를 다이얼로그로 보여줌
        fun showErrorMsg(msg : String)




    }

    interface Presenter{


        // 프레그먼트의 뷰가 생성되면 호출 (viewDidLoad()안에서 viewInit()을 실행한다)
        fun viewDidLoad()

        // 방 정보 모델에 전달
        fun setArrRoomInfo(chosungLength : Int)
        fun getArrRoomInfo():Int

        // 유저 정보 모델에 전달
        fun setArrUserInfo(userInfo : Any)
        fun getArrUserInfo():ArrayList<Any>

        // 서버에서 받아온 랜덤 초성을 모델에 전달
        fun setRandomChosung(chosung : ArrayList<String>)
        fun getRandomChosungFirst(first : String)
        fun getRandomChosungSecond(second : String)
        fun getRandomChosungThird(third : String)

        // 유저가 입력한 텍스트를 모델에 전달
        fun setStrUserInputEditText(inputText : String)
        fun getStrUserInputEditText():String

        // 유저들이 입력한 텍스트를 모델에 전달 (로그)
        fun setListGameLog(inputText : String)
        fun getListGameLog():ArrayList<Any>

        // 게임 시작한 시간을 서버에 전달합니다
        fun gameStartTimeSendToServer()

        // 게임 종료한 시간을 서버에 전달합니다
        fun gameEndTimeSendToServer()

        // n 글자를 넘는지 글자 수 체크
        fun checkUserInputTextLength()

        // 유저가 입력한 단어가 답에 속하는지 여부를 판단하기 위한 메소드(추후 서버와 통신)
        fun checkUserInputTextIsAnswer()

        // 실패뷰를(X표시) 1초뒤에 사라지게 하는 메소드
        fun wrongViewTimeSet()

        // 게임 로그를 쌓아 채팅방에 보여주는 메소드
        fun listViewGameLog()

        // 순위 프래그먼트로 이동하기 위한 임시 타이머
        fun gameTimerStart()




    }

}