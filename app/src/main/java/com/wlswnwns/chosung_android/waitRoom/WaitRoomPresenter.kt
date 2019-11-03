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

        if (!ChosungApplication.Player.bIsMaster) {
            view.hideGameStartBtn()
        }


        ChosungApplication.SocketConnect(object : ChosungApplication.Companion.SocketConnectListner {
            override fun onDataReceived(jsonObject: JSONObject) {
                try {
                    if(jsonObject.getString("strEvent")=="enterRoom"){
                        view.showUserList(model.InitUserList(jsonObject.getJSONArray("arrUserInfo")))
                        model.Game.strMode = jsonObject.getString("strGameMode")
                        model.Game.iChosungLenght = jsonObject.getInt("iWordLength")
                        model.Game.iTime = jsonObject.getInt("iTimeLimit")
                        view.showGameMode(model.Game.strMode)
                        view.showChosungLength(model.Game.iChosungLenght)
                        view.showTime(model.Game.iTime)


                    }else if(jsonObject.getString("strEvent")=="moveToGame"){
                        if (model.Game.strMode.equals("kkt")){
                            view.moveKungKungDdaFragment()
                        }else{
                            view.moveHunMinFragment(game.iChosungLenght,game.iTime)
                        }
                    }
                }catch (e:JSONException){
                    e.printStackTrace()
                }

            }
            override fun onConnet() {

                ChosungApplication.enterRoom(ChosungApplication.Player.bIsMaster, model.room.iRoomId!!, nickNmae)
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

        }
    }

    override fun removeRoom() {

    }

    override fun disConnectSocket() {
        ChosungApplication.client?.clearListeners()
    }


}