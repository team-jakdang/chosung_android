package com.wlswnwns.chosung_android.waitRoom

class WaitRoomPresenter (view : WaitRoomContract.View ) : WaitRoomContract.Presenter{


    var view : WaitRoomContract.View

    var model : WaitRoomModel


    init {
        this.view = view
        this.model = WaitRoomModel()
    }

    override fun viewDidLoad(mode: String, iLength: Int, iTime: Int) {
        view.viewInit()
        model.dummyUsers()
        view.showUserList(model.Users!!)
        model.strMode = mode
        model.iLength = iLength
        model.iTime = iTime
        view.showGameMode(mode)
        view.showChosungLength(iLength)
        view.showTime(iTime)

    }
    override fun onClickGameStartBtn() {

        view.moveHunMinFragment()

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