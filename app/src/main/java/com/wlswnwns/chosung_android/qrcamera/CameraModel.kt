package com.wlswnwns.chosung_android.qrcamera


import java.text.SimpleDateFormat
import java.util.*


class CameraModel {


    var strQRData = ""



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