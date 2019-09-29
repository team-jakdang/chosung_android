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


    override fun setQRData(data: String) {
        model.strQRData = data
    }

    override fun checkQRData() {
        view.moveWaitRoomFragment()
    }


}