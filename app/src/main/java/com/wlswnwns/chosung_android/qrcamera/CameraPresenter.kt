package com.wlswnwns.chosung_android.qrcamera




open class CameraPresenter(view: CameraContract.View) : CameraContract.Presenter {



    var view : CameraContract.View
    var model : CameraModel

    init {
        this.view = view
        this.model = CameraModel()
    }


    override fun viewDidLoad() {
        view.viewInit()
        view.initCamera()
    }



    override fun checkQRData(data: String) {
        model.room.iRoomId = data.toInt()
        model.findRoomRequest(object : CameraModel.findRoomListner{
            override fun onSuccess() {
                view.moveWaitRoomFragment(model.room)
            }
            override fun onFail() {
                view.showFailFindRoomData()
            }
        })

    }


}