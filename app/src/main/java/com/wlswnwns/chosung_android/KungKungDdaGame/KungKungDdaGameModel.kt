package com.wlswnwns.chosung_android.KungKungDdaGame

class KungKungDdaGameModel {

    //유저가 입력한 텍스트 값
    var strUserInputEditText : String = ""
    //제출한 답을 기록할 로그 리스트
    var listGameLog = arrayListOf<Any>()
    //방정보 :방id
    var roomInfo : String = ""
    //유저정보 : 유저id
    var userInfo : String = ""

}