package com.wlswnwns.chosung_android.waitRoom

class WaitRoomPresenter (view : WaitRoomContract.View ) : WaitRoomContract.Presenter{


    var view : WaitRoomContract.View

    var model : WaitRoomModel


    init {
        this.view = view
        this.model = WaitRoomModel()
    }

    override fun viewDidLoad() {
        view.viewInit()
    }
    override fun onClickGameStartBtn() {

    }

    override fun onClickExitRoom() {

    }

    override fun checkRoomOwner() {

    }

    override fun removeRoom() {

    }




}