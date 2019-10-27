package com.wlswnwns.chosung_android.item

import java.io.Serializable

class Game : Serializable{

    var strMode = "kkt"
    var iChosungLenght = 2
    var iTime = 5
    var strUserName : String = "test" // 유저 닉네임
    var strChosung : String = "test" // 유저가 입력한 초성임

    var iCountDown ="3" // 서버에서 받아온 랜덤 초성 내뱉기 전 카운트다
    var strInitialWord = "ㅊㅇㅊ" // 서버에서 받아온 랜덤 초성운
    var bTimeOver = false // 서버에서 받아온 게임 오버 여부
    var bIsAnswer = false // 서버에서 받아온 정답 여부
    var resultMessage = "" // 서버에서 받아온 정답 여부 결과값
    var iOrder = 0 // 서버에서 받아온 순위


}