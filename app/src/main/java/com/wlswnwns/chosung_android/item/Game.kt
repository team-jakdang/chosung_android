package com.wlswnwns.chosung_android.item

import java.io.Serializable

class Game : Serializable{

    var strMode = "kkt"
    var iChosungLenght = 2
    var iTime = 5
    var strUserName : String = "test" // 유저 닉네
    var strChosung : String = "test" // 유저가 입력한 초성임

    var iCountDown ="3"
    var strInitialWord = "ㅊㅇㅊ" // 서버에서 받아온 랜덤 초성

}