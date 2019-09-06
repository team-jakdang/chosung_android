package com.wlswnwns.chosung_android.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.wlswnwns.chosung_android.R

class GameSettingDialog(context: Context) : Dialog(context){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_game_setting)

    }

}