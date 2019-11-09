package com.wlswnwns.chosung_android.KungKungDdaGame

import android.os.Handler
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room
import com.wlswnwns.chosung_android.item.User
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * Contract.Presenter에서 정의한 내용 구현
 *  뷰의 동작을 구현한다.
 **/

class KungKungDdaGamePresenter(view: KungKungDdaGameContract.View) :
    KungKungDdaGameContract.Presenter {


    var view: KungKungDdaGameContract.View


    var model: KungKungDdaGameModel


    //생성자
    init {
        this.view = view
        this.model = KungKungDdaGameModel()
    }

    // 콘트렉트에 정의한 뷰 객체를 생성.
    override fun viewDidLoad(gameListner: GameListner, room: Room, game: Game) {
        view.viewInit()

        model.game = game
        model.room = room


        model.InitSockerListner(object : KungKungDdaGameModel.SockerListner {
            override fun onDataReceived(jsonObject: JSONObject) {
                val action = Runnable {
                    try {
                        if (jsonObject.getString("strEvent").equals("startKKT") || jsonObject.getString(
                                "strEvent"
                            ).equals("nextTurnKKT") ||jsonObject.getString(
                                "strEvent"
                            ).equals("OUT_GAME")
                        ) {

                            view.showCountDownText(jsonObject.getInt("iCountDown").toString())



                            view.showChosungText(jsonObject.getString("strInitialWord"))
                            model.iTimeLimit = jsonObject.getInt("iSetTime")

                            if (jsonObject.getString("strEvent").equals("startKKT")) {
                                model.InitUserList(jsonObject.getJSONArray("arrUserOrderInfo"))
                                    .let {

                                        if (model.UserList.size < 3) {

                                            if (model.UserList.size == 2) {
                                                model.UserList.add(User().apply {
                                                    strUserName =
                                                        model.UserList[0].strUserName
                                                    bIsActive = model.UserList[0].bIsActive
                                                    bIsMaster = model.UserList[0].bIsMaster
                                                    iOrder = 3
                                                })

                                                model.UserList.add(User().apply {
                                                    strUserName =
                                                        model.UserList[1].strUserName
                                                    bIsActive = model.UserList[1].bIsActive
                                                    bIsMaster = model.UserList[1].bIsMaster
                                                    iOrder = 4
                                                })
                                            }
                                        }
                                        OrderViewChange()
                                    }

                            }



                            if (ChosungApplication.Player.strUserName.equals(model.UserList[0].strUserName)) {


                                if (jsonObject.getInt("iCountDown") == 0) {
                                    gameListner.onGameStart()

                                } else {
                                    gameListner.onGameStart()

                                }
                            }


                        } else if (jsonObject.getString("strEvent").equals("checkTimeKKT")) {
                            view.timeProgressBarActive(
                                model.iTimeLimit,
                                jsonObject.getInt("iLeftTime")
                            )
                            model.strNowTurnUserName = jsonObject.getString("strNickname")

                            if (jsonObject.getBoolean("bTimeOver")) {
                                view.MoveGameResult(model.strNowTurnUserName)
                            }

                        } else if (jsonObject.getString("strEvent").equals("checkAnswerKKT")) {


                            model.ChosungLog?.add(Game().apply {
                                strUserName = jsonObject.getString("strNickname")
                                strChosung = jsonObject.getString("strMessage")
                            })
                            view.showChosungLogList(model.ChosungLog!!)

                            if (jsonObject.getBoolean("bIsAnswer")) {

                                if (ChosungApplication.Player.bIsMaster) {
                                    ChosungApplication.nextTurnKKT()
                                }
                                answerViewTimeSet()



                                model.NowTurnUser = model.UserList[0]
                                model.NowTurnUser.iOrder = +model.UserList.size + 1
                                model.UserList.removeAt(0)
                                model.UserList.add(model.NowTurnUser)
                                OrderViewChange()

                            } else {

                                if(jsonObject.getInt("responseCode")==208){
                                    wrongViewTimeSet("이미 나왔어요!")
                                }else{
                                    wrongViewTimeSet("틀렸어요!")
                                }


                            }
                        }else if(jsonObject.getString("strEvent").equals("THE_ROOM_IS_DESTROYED")){
                            view.exitRoom()
                        }



                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()

                    }

                }

                ChosungApplication.activity.runOnUiThread(action)


            }
        })



        if (ChosungApplication.Player.bIsMaster) {
            model.GameStart()
        }


    }

    interface GameListner {
        fun onGameStart()
    }

    fun OrderViewChange() {
        model.UserList[0]?.let {
            view.setOrder1TextView(it)
        }

        model.UserList[1]?.let {
            view.setOrder2TextView(it)
        }


        model.UserList[2]?.let {
            view.setOrder3TextView(it)
        }
    }


    //오답뷰가 유저에게 보여질 시간 지정
    override fun wrongViewTimeSet(msg : String) {
        // 0.4초 뒤에 default 뷰 호출
        view.wrongGameView(msg)
        Handler().postDelayed({
            view.defaultGameView()
        }, 400)

    }

    //정답뷰가 유저에게 보여질 시간 지정
    fun answerViewTimeSet() {
        // 0.4초 뒤에 default 뷰 호출
        view.answerGameView()
        Handler().postDelayed({
            view.defaultGameView()
        }, 400)

    }

    //유저 순서 set
    override fun setUserOrder() {
        //todo.서버에서 가져온 순서대로 뷰에 지정해준다.

        //뷰에 게임 순서 지정
//        view.changeUserOrder()
    }


    override fun getArrRoomInfo(): Int {
        return model.game.iChosungLenght
    }


    override fun setStrUserInputEditText(inputText: String) {
        model.strUserInputEditText = inputText
    }


    override fun checkUserInputTextLength() {
        if (model.strUserInputEditText.length > getArrRoomInfo()) view.longUserInputText(
            getArrRoomInfo().toString()
        )
    }

    override fun checkAnswerKKT() {

        if (ChosungApplication.Player.strUserName.equals(model.strNowTurnUserName)) {

            ChosungApplication.checkAnswerKKT(model.strUserInputEditText)

        }
    }


}
