package com.wlswnwns.chosung_android.waitRoom

import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.SocketException

class WaitRoomPresenter(view: WaitRoomContract.View ) :
    WaitRoomContract.Presenter {


    var view: WaitRoomContract.View

    var model: WaitRoomModel

    init {
        this.view = view
        this.model = WaitRoomModel()
    }

    override fun viewDidLoad(game: Game, room: Room, nickNmae: String) {
        view.viewInit(game.iChosungLenght, game.iTime)
        model.Game = game
        model.room = room

        view.showGameMode(game.strMode)
        view.showChosungLength(game.iChosungLenght)
        view.showTime(game.iTime)
        view.showQRCodeImage(model.makeRoomQRCode())

        ChosungApplication.enterRoom(true, model.room.iRoomId!!, nickNmae)
        ChosungApplication.client?.clearListeners()
        Log.e("viewDidLoad", "WaitRoomPresenter 뷰 초기화 실행")
        ChosungApplication.client?.addListener(object : WebSocketAdapter() {
            override fun onTextMessage(websocket: WebSocket?, text: String?) {
                super.onTextMessage(websocket, text)
                Log.e("메세지===>", text)
                JSONObject(text)
                val action = Runnable {
                    Log.e("onDataReceived", JSONObject(text).getString("strEvent"))
                    if(JSONObject(text).getString("strEvent")=="enterRoom"){
                        view.showUserList(model.InitUserList(JSONObject(text).getJSONArray("arrUserInfo")))
                        model.Game.strMode = JSONObject(text).getString("strGameMode")
                        model.Game.iChosungLenght = JSONObject(text).getInt("iWordLength")
                        model.Game.iTime = JSONObject(text).getInt("iTimeLimit")
                        view.showGameMode(model.Game.strMode)
                        view.showChosungLength(model.Game.iChosungLenght)
                        view.showTime(model.Game.iTime)


                    }else if(JSONObject(text).getString("strEvent")=="moveToGame"){
                        if (model.Game.strMode.equals("kkt")){
                            view.moveKungKungDdaFragment()
                        }else{
                            view.moveHunMinFragment(game.iChosungLenght,game.iTime)
                        }
                    }
                }
                ChosungApplication.activity.runOnUiThread(action)
            }
        })



    }

    override fun onClickGameStartBtn() {
        ChosungApplication.moveToGame()
    }

    override fun onClickExitRoom() {
        view.showEixtRoomDialog()
    }

    override fun checkRoomOwner() {
        if (model.isRoomOwner) {
            removeRoom()
            view.exitRoom()
        } else {
            view.exitRoom()
            view.hideGameStartBtn()
        }
    }

    override fun removeRoom() {

    }

    override fun disConnectSocket() {
        ChosungApplication.client?.clearListeners()
    }


}