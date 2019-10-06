package com.wlswnwns.chosung_android.waitRoom

import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room

class WaitRoomPresenter (view : WaitRoomContract.View ) : WaitRoomContract.Presenter{


    var view : WaitRoomContract.View

    var model : WaitRoomModel


    init {
        this.view = view
        this.model = WaitRoomModel()
    }

    override fun viewDidLoad(game: Game, room : Room) {
        view.viewInit(game.iChosungLenght,game.iTime)
        model.dummyUsers()
        view.showUserList(model.Users!!)
        model.Game = game
        model.room = room


        view.showGameMode(game.strMode)
        view.showChosungLength(game.iChosungLenght)
        view.showTime(game.iTime)
        view.showQRCodeImage( model.makeRoomQRCode())


    }
    override fun onClickGameStartBtn() {

        view.moveHunMinFragment(model.Game.iChosungLenght,model.Game.iTime)


    }

    override fun onClickExitRoom() {
        view.showEixtRoomDialog()
    }

    override fun checkRoomOwner() {

        if(model.isRoomOwner){
            removeRoom()
            view.exitRoom()
        }else{
            view.exitRoom()
        }


    }

    override fun removeRoom() {

    }




}