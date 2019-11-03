package com.wlswnwns.chosung_android.qrcamera


import android.util.Log
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.item.Room
import com.wlswnwns.chosung_android.utils.APiAsyc
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class CameraModel {


    var room : Room = Room()

    var bRoomExist : Boolean = false

    lateinit var apiAsyc : APiAsyc


    fun findRoomRequest(findRoomListner: findRoomListner) {
        val gameData = JSONObject()
        try {
            gameData.put("iRoomId", room.iRoomId)
            apiAsyc = APiAsyc("room-control/findRoom", "GET", gameData)
            apiAsyc.execute()

            val r = apiAsyc.get()
            val result = JSONObject(r)
            Log.e("result", r)


            if (r != "{}") {

                //방이 존재하면 true 값을 응답받습니다
                if(result.getBoolean("bHasRoom")){
                    bRoomExist = true

                    ChosungApplication.Player.bIsMaster = false

                    findRoomListner.onSuccess()
                }else{
                    findRoomListner.onFail()
                }


            } else {
                findRoomListner.onFail()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface findRoomListner{
        fun onSuccess()
        fun onFail()
    }



    //현재 시간을 변수에 세팅하는 메서드
    fun SetCurrentTimeSet() {
//        val now = System.currentTimeMillis()
//
//
//        val date = Date(now)
//
//        val sdf = SimpleDateFormat("yyyyMMdd")
//        yyyyMMdd = sdf.format(date)
//
//        val sdf2 = SimpleDateFormat("HHmmss")
//
//        HHmmss = sdf2.format(date)

    }

    fun getUTCTime(): String {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        df.setTimeZone(TimeZone.getTimeZone("utc"))
        val gmtTime = df.format(Date())

        return gmtTime
    }

}