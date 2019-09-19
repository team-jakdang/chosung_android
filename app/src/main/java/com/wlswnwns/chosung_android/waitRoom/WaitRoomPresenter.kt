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
        model.dummyUsers()
        view.showUserList(model.Users!!)
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