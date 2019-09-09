package com.wlswnwns.chosung_android.hunminGame

import android.os.CountDownTimer
import android.os.Handler
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList



class HunminGamePresenter(view: HunminGameContract.View) : HunminGameContract.Presenter {


    var timer: Timer?=null
    var view: HunminGameContract.View
    var model: HunminGameModel


    // 생성자
    init {
        this.view = view
        this.model = HunminGameModel()
    }

    // 뷰 초기화 실행
    override fun viewDidLoad() {
        view.viewInit()
    }

    // 유저가 입력한 텍스트를 'model'에 선언한 strUserInputEditText 변수에 set 해준다
    override fun setStrUserInputEditText(inputText: String) {
        model.strUserInputEditText = inputText
    }

    // 'model'에 선언한 'strUserInputEditText' 를 가져온다
    override fun getStrUserInputEditText(): String {
        return model.strUserInputEditText

    }

    // 유저가 입력한 인풋 텍스트가 3글자를 넘으면
    // 3글자 이상은 안된다는 토스트 메세지를 띄어주는 'view'의 longUserInputText()를 호출한다
    override fun checkUserInputTextLength() {
        if (model.strUserInputEditText.length > 3) view.longUserInputText()

    }


    // 유저가 입력한 답이 '은채' 이면 성공뷰를 아니라면 실패뷰를 띄어준다
    override fun checkUserInputTextIsAnswer() {
        if(model.strUserInputEditText =="은채") view.answerGameView() else wrongViewTimeSet()

    }

    override fun wrongViewTimeSet() {
        // 실패뷰를 띄우고 0.4초 뒤에 defult뷰 호출
        view.wrongGameView()
        Handler().postDelayed({
            view.defaultGameView()
        }, 400)

    override fun gameTimerStart() {
        val countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("tekloon", "millisUntilFinished $millisUntilFinished")
                val time:Long = (millisUntilFinished/1000) % 60
                view.timeSetTextView(time.toString())
            }
            override fun onFinish() {
                view.moveHunminGameRankingFragment()
            }
        }
        countDownTimer.start()

    }


    }


}