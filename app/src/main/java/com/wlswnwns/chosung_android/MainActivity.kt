package com.wlswnwns.chosung_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.FrameLayout
import com.android.example.cameraxbasic.utils.FLAGS_FULLSCREEN
import com.wlswnwns.chosung_android.utils.OnBackPressedListener
import com.wlswnwns.chosung_android.waitRoom.WaitRoomFragment


private const val IMMERSIVE_FLAG_TIMEOUT = 500L

class MainActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        container = findViewById(R.id.fragment_container)


    }

    override fun onResume() {
        super.onResume()

        ChosungApplication.activity = this

        // Before setting full screen flags, we must wait a bit to let UI settle; otherwise, we may
        // be trying to set app to immersive mode before it's ready and the flags do not stick
//        container.postDelayed({
//            container.systemUiVisibility = FLAGS_FULLSCREEN
//        }, IMMERSIVE_FLAG_TIMEOUT)
    }

    override fun onBackPressed() {
        // super.onBackPressed()
        val fragmentList = supportFragmentManager.fragments

        Log.e(" 이거눌림???", "이거이거")

        fragmentList?.let {
            for (fragment in it) {
                if(fragment is OnBackPressedListener){
                    (fragment as OnBackPressedListener).onBackPressed()
                }
            }
        }.let {

        }

    }


}
