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
        ChosungApplication.Game = game

        if(model.Game.strMode.contains("kkt")){
            view.showGameMode("쿵쿵따")
        }else{
            view.showGameMode("훈민정음")
        }
        view.showChosungLength(game.iChosungLenght)
        view.showTime(game.iTime)
        view.showQRCodeImage(model.makeRoomQRCode())

        if (!ChosungApplication.Player.bIsMaster) {
            view.hideGameStartBtn()
        }

        ChosungApplication.client?.clearListeners()


        ChosungApplication.SocketConnect(object : ChosungApplication.Companion.SocketConnectListner {
            override fun onDataReceived(jsonObject: JSONObject) {
                try {
                    if(jsonObject.getString("strEvent")=="enterRoom"){
                        view.showUserList(model.InitUserList(jsonObject.getJSONArray("arrUserInfo")))
                        model.Game.strMode = jsonObject.getString("strGameMode")
                        model.Game.iChosungLenght = jsonObject.getInt("iWordLength")
                        model.Game.iTime = jsonObject.getInt("iTimeLimit")
                        if(model.Game.strMode.contains("kkt")){
                            view.showGameMode("쿵쿵따")
                        }else{
                            view.showGameMode("훈민정음")
                        }

                        view.showChosungLength(model.Game.iChosungLenght)
                        view.showTime(model.Game.iTime)
                        view.showPlayerNumber(model.Users?.size.toString())


                    }else if(jsonObject.getString("strEvent")=="moveToGame"){
                        if (model.Game.strMode.equals("kkt")){
                            view.moveKungKungDdaFragment()
                        }else{
                            view.moveHunMinFragment(game.iChosungLenght,game.iTime, game.bIsMaster)
                        }
                    }else if(jsonObject.getString("strEvent")=="OUT_GAME"){

                        view.showUserList( model.InitUserList(jsonObject.getJSONArray("arrUserInfo")))
                        view.showPlayerNumber(model.Users?.size.toString())

                    }else if(jsonObject.getString("strEvent").equals("EXIT_ROOM")){
                        view.showUserList(model.InitUserList(jsonObject.getJSONArray("arrUserOrderInfo")))
                    }else if(jsonObject.getString("strEvent").equals("THE_ROOM_IS_DESTROYED")){
                        view.exitRoom()
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

        if(model.Users?.size == 1 && model.Game.strMode.equals("kkt")){

        }else{
            ChosungApplication.moveToGame()
        }


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
        ChosungApplication.client?.disconnect()
    }

    override fun removeRoom() {

    }

    override fun disConnectSocket() {
        ChosungApplication.client?.clearListeners()
    }


}