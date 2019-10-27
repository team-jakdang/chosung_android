package com.wlswnwns.chosung_android.hunminGame

import android.os.CountDownTimer
import android.os.Handler
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.item.Test
import com.wlswnwns.chosung_android.waitRoom.WaitRoomModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
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
        ChosungApplication.client?.clearListeners()
        Log.e("viewDidLoad2", "뷰 초기화 실행2")

        ChosungApplication.client?.addListener(object : WebSocketAdapter() {
            override fun onTextMessage(websocket: WebSocket?, text: String?) {
                super.onTextMessage(websocket, text)
                Log.e("메세지===>", text)
                JSONObject(text)
                val action = Runnable {
                    Log.e("onDataReceived", JSONObject(text).getString("strEvent"))
                    var iSetTime=iTime;
                    if (JSONObject(text).getString("strEvent") == "startHMJE") {
                        try {
                            Log.e("checkTimeHMJE2", JSONObject(text).getString("iCountDown"))
                            iSetTime = JSONObject(text).getInt("iSetTime")
                            setChosung(
                                JSONObject(text).getString("strInitialWord"),
                                JSONObject(text).getString("iCountDown")
                            )
                        }catch (e:JSONException){
                            e.printStackTrace()
                            setChosung(
                               "없음",
                                JSONObject(text).getString("iCountDown")
                            )
                        }
                    }else if (JSONObject(text).getString("strEvent") == "checkTimeHMJE") {
                        setTimer(iSetTime,JSONObject(text).getInt("iLeftTime"))
                    }else if (JSONObject(text).getString("strEvent") == "checkAnswerHMJE") {
                        Log.e("checkAnswerHMJE", JSONObject(text).getString("strMessage"))
                        model.Game.bIsAnswer = JSONObject(text).getBoolean("bIsAnswer")
                        checkUserInputTextIsAnswer(JSONObject(text).getBoolean("bIsAnswer"))
                    }
                }

                ChosungApplication.activity.runOnUiThread(action)


            }
        })
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
        ChosungApplication.startHMJEGameTimeCheck() // 타임체크 소켓 연결

        // 게임이 오버되었다면 순위 발표 페이지로 넘어가기 (서버에서 리턴값으로 주는 arrResultInfo 넘겨줘야함)
        if (model.Game.bTimeOver) {
            view.moveHunminGameOverFragment()
        }
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


    override fun checkUserInputTextIsAnswer(isAnswer : Boolean) {


        // 유저가 입력한 답이 정답이라면 성공뷰를 띄워주고
        if(model.Game.bIsAnswer){
            view.answerGameView()

        }
        // 아니라면 실패 뷰를 띄어준다.
        else{
            wrongViewTimeSet()
        }

//        if (model.strUserInputEditText == model.Game.strInitialWord) view.answerGameView() else wrongViewTimeSet()

    }



    override fun addChosungLog() {

        ChosungApplication.hunminGameIsAnswer(model.strUserInputEditText)

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

    override fun setChosung(strInitialWord: String, iCountDown: String) {
        Log.e("Tag", "카운트 들어오니 :: "  + iCountDown)
        if (iCountDown == "0") {
            view.showChosung(strInitialWord)
            gameStartTimeSendToServer() // 타임체크 시
        } else {
            Log.e("Tag", "else ")

            view.showChosung(iCountDown)

        }
    }

    override fun setTimer(iSetTime: Int, iLeftTime: Int) {
        Log.e("Tag", "카운트 들어오니 :: "  + iSetTime)

        view.timeProgressBarActive(iSetTime,iLeftTime)

        if(iLeftTime == 0) {
            view.moveHunminGameOverFragment()
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