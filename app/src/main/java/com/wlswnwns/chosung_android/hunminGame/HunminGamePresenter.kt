package com.wlswnwns.chosung_android.hunminGame

import android.os.CountDownTimer
import android.os.Handler
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.item.Test
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList


class HunminGamePresenter(view: HunminGameContract.View) : HunminGameContract.Presenter {


    var view: HunminGameContract.View
    var model: HunminGameModel


    // 생성자
    init {
        this.view = view
        this.model = HunminGameModel()
    }

    // 뷰 초기화 실행
    override fun viewDidLoad(iLength: Int, iTime: Int) {
        view.viewInit()
        model.chosungLength = iLength
        model.iTime = iTime
        model.ChosungLog = ArrayList()
        Log.e("viewDidLoad", "뷰 초기화 실행")
        ChosungApplication.startHMJEGame() // 게임시작 체크
//        ChosungApplication.startHMJEGameTimeCheck()


    }

    // 유저가 입력한 텍스트를 set
    override fun setStrUserInputEditText(inputText: String) {
        model.strUserInputEditText = inputText
    }

    // 유저가 입력한 텍스트를 get
    override fun getStrUserInputEditText(): String {
        return model.strUserInputEditText

    }

    // 유저들이 입력한 텍스트를 set
    override fun setListGameLog(inputText: String) {
        model.listGameLog.add(inputText)
    }

    // 유저들이 입력한 텍스트를 get
    override fun getListGameLog(): ArrayList<Any> {
        return model.listGameLog
    }

    // 룸정보 set (임시로 글자 수 변수를 따로 만들었음, 나중에 오브젝트에 룸 정보를 다같이 넣을예정)
    override fun setArrRoomInfo(chosungLength: Int) {
        model.chosungLength = chosungLength
    }

    // 룸정보 get
    override fun getArrRoomInfo(): Int {
        return model.chosungLength

    }


    // 유저정보 set
    override fun setArrUserInfo(userInfo: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // 유저정보 get
    override fun getArrUserInfo(): ArrayList<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRandomChosung(chosung: String) {

    }

    override fun getRandomChosung(): String {
        Log.e("카운트", model.Game.iCountDown)
        Log.e("초성", model.Game.strInitialWord)
        return model.Game.strInitialWord
    }


    override fun gameStartTimeSendToServer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun gameEndTimeSendToServer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    // 글자 수 체크
    override fun checkUserInputTextLength() {
        if (model.strUserInputEditText.length > getArrRoomInfo()) view.longUserInputText(
            getArrRoomInfo().toString()
        )

    }

    // 유저가 입력한 답이 '은채' 이면 성공뷰를 아니라면 실패뷰를 띄어준다
    override fun checkUserInputTextIsAnswer() {
        if (model.strUserInputEditText == model.Game.strInitialWord) view.answerGameView() else wrongViewTimeSet()

    }

    override fun addChosungLog() {


//        model.ChosungLog?.add(Test("은채", model.strUserInputEditText) )
        model.ChosungLog?.add(model.Game.apply {
            strUserName = "은채";strChosung = model.strUserInputEditText
        })


        view.showChosungLogList(model.ChosungLog!!)


    }

    override fun wrongViewTimeSet() {
        // 실패뷰를 띄우고 0.4초 뒤에 defult뷰 호출
        view.wrongGameView()
        Handler().postDelayed({
            view.defaultGameView()
        }, 400)
    }

    override fun setChosung() {
        if (model.Game.iCountDown == "0") {
            view.showChosung(model.Game.strInitialWord)

        } else {
            view.showChosung(getRandomChosung())
        }
    }


    override fun gameTimerStart() {
//        val countDownTimer = object : CountDownTimer(12000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                Log.d("tekloon", "millisUntilFinished $millisUntilFinished")
////                val time:Long = (millisUntilFinished/1000) % 60
//
//            }
//            override fun onFinish() {
//                view.moveHunminGameOverFragment()
//            }
//        }
//        countDownTimer.start()

    }


}