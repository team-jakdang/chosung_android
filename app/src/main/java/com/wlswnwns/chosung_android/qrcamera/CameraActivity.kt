package com.wlswnwns.chosung_android.qrcamera


import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import me.dm7.barcodescanner.zbar.Result


class CameraActivity : AppCompatActivity(), Camera.View, MyZBarScannerView.ResultHandler {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )

       // setContentView(R.layout.layout_camera)


    }
    override fun handleResult(rawResult: Result?, imageData: ByteArray?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}
