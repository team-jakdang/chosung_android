package com.wlswnwns.chosung_android.KungKungDdaGame

import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room
import com.wlswnwns.chosung_android.item.User

/**
 * View와 Presenter 정의
 * **/
interface KungKungDdaGameContract {

    /**
     * 뷰 인터페이스.
     * Fragment 에서 전달받은 view 이벤트
     *
     */

    interface View {

        // 뷰 초기화
        fun viewInit()


        // 카운트 다운 텍스트 보여줌
        fun showCountDownText(count: String)

        fun showChosungText(chosung: String)

        // 유저가 입력하는 단어가 3글자를 넘으면 안된다는 토스트 메세지
        fun longUserInputText(chosungLength: String)

        // 유저가 입력한 단어가 정답 일 때 보여줄 뷰
        fun answerGameView()

        // 유저가 입력한 단어가 오답 일 때 보여줄 결과뷰
        fun wrongGameView()

        // 게임중 기본 뷰로 돌아가도록 함. (유저가 정답을 입력 할 때의 뷰) 오/정답표시 숨김
        fun defaultGameView()


        // 에러 메세지를 다이얼로그로 보여줌
        fun showErrorMsg(msg: String)

        //유저의 게임 순서를 뷰에 표시해준다.
        fun changeUserOrder(order1: String, order2: String, order3: String)

        fun setOrder1TextView(user: User)

        fun setOrder2TextView(user: User)

        fun setOrder3TextView(user: User)

        // 게임이 시작하면 프로그래스바를 활성화
        fun timeProgressBarActive(iTimeLimit: Int, iCountDown: Int)

        fun showChosungLogList(ChosungLog: ArrayList<Game>)

        fun MoveGameResult(nickName : String)

    }

    /**
     * 프레젠터 인터페이스.
     * Presenter 파일에서 메소드 상세 구현.
     * view 에서 전달된 이벤트에 대한 처리. view와 무관한 처리.
     *
     */

    interface Presenter {

        // 프레그먼트의 뷰가 생성되면 호출 (viewDidLoad()안에서 viewInit()을 실행한다)
        fun viewDidLoad(gameListner: KungKungDdaGamePresenter.GameListner, room : Room, game : Game)

        fun getArrRoomInfo(): Int

        // 유저가 입력한 텍스트를 모델에 전달
        fun setStrUserInputEditText(inputText: String)


        // 유저가 입력한 단어의 글자수가 지정한 수를 넘는지 체크
        fun checkUserInputTextLength()

        // 유저가 입력한 단어가 답에 속하는지 여부를 판단하기 위한 메소드(추후 서버와 통신)
        fun checkUserInputTextIsAnswer()

        // 실패뷰를(X표시) 지정한 시간 후 사라지게 하는 메소드
        fun wrongViewTimeSet()

        //유저 게임 순서 셋팅
        fun setUserOrder()

        // 초성 로그
        fun checkAnswerKKT()

    }

}