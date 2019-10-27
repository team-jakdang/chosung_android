package com.wlswnwns.chosung_android.waitRoom

import android.graphics.Bitmap
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room

import com.wlswnwns.chosung_android.item.User

interface WaitRoomContract{

    interface View{

        // 뷰를 초기화해줍니다
        fun viewInit(iLength : Int, iTime : Int)

        // 훈민정음 게임 프레그먼트를 띄워줍니다
        fun moveHunMinFragment(iLength : Int, iTime : Int)

        // 쿵쿵따 게임 프레그먼트를 띄워줍니다
        fun moveKungKungDdaFragment()

        // 방에서 나갈것인지 사용자에게 확인하는 다이얼로그를 띄워줍니다
        fun showEixtRoomDialog()

        //대기방 화면을 닫아줍니다 -> 메인화면이 나타날것입니다
        fun exitRoom()

        // 사용자가 어떠한 에러로 방에서 나가기를 실패할 경우 나타내주는 메세지
        fun showFailExitRoom()

        fun showUserList(Users : ArrayList<User>)

        fun showGameMode(mode:String)

        fun showChosungLength(length : Int)

        fun showTime(time : Int)

        fun showQRCodeImage(img : Bitmap)

        fun hideGameStartBtn()



    }

    interface Presenter{
        // 프레그먼트의 뷰가 생성되면 호출됩니다
        fun viewDidLoad(game: Game,room : Room, nickNmae : String)


        //게임 시작 버튼을 누르면 호출됩니다
        fun onClickGameStartBtn()

        //방 나가기 버튼을 누르면 호출됩니다
        fun onClickExitRoom()

        //방장인지 체크합니다
        fun checkRoomOwner()

        //방장이 방을 나가는경우 model에서
        //방을 제거해달라고 서버측에 요청을 보냅니다
        fun removeRoom()

        fun disConnectSocket()


    }


}