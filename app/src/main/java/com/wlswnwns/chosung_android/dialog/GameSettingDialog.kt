package com.wlswnwns.chosung_android.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.main.MainContract
import kotlinx.android.synthetic.main.dialog_game_setting.*

class GameSettingDialog(context: Context,presenter : MainContract.Presenter) : Dialog(context){


    var presenter : MainContract.Presenter

    init {
        this.presenter = presenter
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_game_setting)

        //확인을 누르면 게임 설정이 모델에 전달되고
        //대기방 화면으로 넘어갑니다
        ConfirmBtnView.setOnClickListener { presenter.apply {  gameSetting("",2,5) ; makeGame() }  }

        CancelBtnView.setOnClickListener { presenter.closeGameSetting() }


    }

}