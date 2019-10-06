package com.wlswnwns.chosung_android.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.main.MainContract
import kotlinx.android.synthetic.main.dialog_game_setting.*
import java.sql.Time

class GameSettingDialog(context: Context,presenter : MainContract.Presenter) : Dialog(context){


    var presenter : MainContract.Presenter
    var strMode : String = "hunmin"
    var iLength : Int = 2
    var iTime : Int = 10

    init {
        this.presenter = presenter
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_game_setting)

        ModeHunMin.isChecked = true
        Length2.isChecked = true
        Time10.isChecked = true


        GameModeRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(GameModeRadioGroup.checkedRadioButtonId){
                R.id.ModeHunMin -> strMode = "hunmin"
                R.id.ModeKungKung -> strMode = "kkt"
            }
        }

        LengthRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(LengthRadioGroup.checkedRadioButtonId){
                R.id.Length2 -> iLength = 2
                R.id.Length3 -> iLength = 3
            }
        }

        TimeRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(TimeRadioGroup.checkedRadioButtonId){
                R.id.Time10 -> iTime = 10
                R.id.Time20 -> iTime = 20
            }
        }








        //확인을 누르면 게임 설정이 모델에 전달되고
        //대기방 화면으로 넘어갑니다
        ConfirmBtnView.setOnClickListener { presenter.apply {  gameSetting(strMode,iLength,iTime) ; makeGame() }  }

        CancelBtnView.setOnClickListener { presenter.closeGameSetting() }


    }

}