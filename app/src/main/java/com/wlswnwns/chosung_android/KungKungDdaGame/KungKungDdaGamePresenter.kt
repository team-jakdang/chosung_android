package com.wlswnwns.chosung_android.KungKungDdaGame

import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import com.wlswnwns.chosung_android.KungKungDdaGame.KungKungDdaGameContract
import com.wlswnwns.chosung_android.KungKungDdaGame.KungKungDdaGameModel

/**
 * Contract.Presenter에서 정의한 내용 구현
 *  뷰의 동작을 구현한다.
 **/

class KungKungDdaGamePresenter(view : KungKungDdaGameContract.View) : KungKungDdaGameContract.Presenter {



    var view : KungKungDdaGameContract.View


    var model : KungKungDdaGameModel
    //생성자
    init {
        this.view = view
        this.model= KungKungDdaGameModel()
    }

    // 콘트렉트에 정의한 뷰 객체를 생성.
    override fun viewDidLoad() {
        view.viewInit()
    }

    // 유저가 입력한 답이 '은채' 이면 성공뷰를 아니라면 실패뷰를 띄어준다. todo.추후 서버에서 받아온 성공 플래그로 수정해야함.
    override fun checkUserInputTextIsAnswer() {
        if (model.strUserInputEditText == "은채")
            view.answerGameView()

        else wrongViewTimeSet()
    }

    //오답뷰가 유저에게 보여질 시간 지정
    override fun wrongViewTimeSet() {
        // 0.4초 뒤에 default 뷰 호출
        view.wrongGameView()
        Handler().postDelayed({
            view.defaultGameView()
        }, 400)

    }

    //유저 순서 set
    override fun setUserOrder(){
        //todo.서버에서 가져온 순서대로 뷰에 지정해준다.

        //뷰에 게임 순서 지정
//        view.changeUserOrder()
    }
    //유저 순서 get
    override fun getUserOrder(){

        //서버에서 유저 게임 순서 가져오는 api 호출


    }

    //
    override fun setArrRoomInfo(chosungLength: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getArrRoomInfo(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setArrUserInfo(userInfo: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getArrUserInfo(): ArrayList<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRandomChosung(chosung: ArrayList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRandomChosung() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setStrUserInputEditText(inputText: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStrUserInputEditText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setListGameLog(inputText: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getListGameLog(): ArrayList<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun gameStartTimeSendToServer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun gameEndTimeSendToServer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkUserInputTextLength() {

    }

    //게임 제한시간 카운트다운 시작
    override fun gameTimerStart() {

        val countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("kungddaCntTime", "millisUntilFinished $millisUntilFinished")
//                val time:Long = (millisUntilFinished/1000) % 60

            }
            override fun onFinish() {
                view.moveToKungDdaGameOverFragment()
            }
        }
        countDownTimer.start()
    }



}
