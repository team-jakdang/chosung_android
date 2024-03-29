package com.wlswnwns.chosung_android.hunminGame

import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Test

interface HunminGameContract {

    interface  View{

        // 뷰 초기화
        fun viewInit()

        // 유저가 입력하는 단어가 n 글자를 넘으면 안된다는 토스트 메세지
        fun longUserInputText(chosungLength: String)

        // 게임이 시작하면 프로그래스바를 활성화
        fun timeProgressBarActive(iTimeLimit:Int, iCountDown: Int)

        // 랜덤 초성을 텍스트에 띄어줌
        fun randomChosungSetTextView(chosung : ArrayList<String>)

        // 유저가 입력하는 단어가 정답에 속할때 바뀌는 뷰
        fun answerGameView()

        // 유저가 입력하는 단어가 정답이 아닐때 바뀌는 뷰
        fun wrongGameView()

        // 정답이 아닐때 보여주는 아이콘이 1초가 지나면 사라지게
        fun defaultGameView()

        // 순위 발표 프레그먼트 띄움
        fun moveHunminGameOverFragment(rusultArr : String)

        // 에러 메세지를 다이얼로그로 보여줌
        fun showErrorMsg(msg : String)

        fun exitGame()

        fun showChosung(chosung : String)

        // 초성 로그를 보여줌
        fun showChosungLogList(ChosungLog : ArrayList<Game>)

    }

    interface Presenter{


        // 프레그먼트의 뷰가 생성되면 호출 (viewDidLoad()안에서 viewInit()을 실행한다)
        fun viewDidLoad(iLength : Int, iTime : Int)

        // 방 정보 모델에 전달
        fun setArrRoomInfo(chosungLength : Int)
        fun getArrRoomInfo():Int

        // 유저 정보 모델에 전달
        fun setArrUserInfo(userInfo : Any)
        fun getArrUserInfo():ArrayList<Any>

        // 서버에서 받아온 랜덤 초성을 모델에 전달
        fun setRandomChosung(chosung : String)
        fun getRandomChosung(): String

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

        // 초성 로그 리스트에 추가하는 메소드
        fun addChosungLog()

        // 실패뷰를(X표시) 1초뒤에 사라지게 하는 메소드
        fun wrongViewTimeSet()

        // 순위 프래그먼트로 이동하기 위한 임시 타이머
        fun gameTimerStart()

        fun setChosung(strInitialWord:String, iCountDown:String)

        fun setTimer(iSetTime:Int, iLeftTime:Int)
    }

}