package com.wlswnwns.chosung_android

import android.app.Activity
import android.app.Application
import com.neovisionaries.ws.client.WebSocket

class ChosungApplication : Application() {

    companion object{
       lateinit var activity : Activity
    }

}