package com.wlswnwns.chosung_android.hunminGameOver

import android.content.Context
import com.wlswnwns.chosung_android.item.Game

interface HunminGameOverContract {


    interface  View {

        // 뷰 초기화
        fun viewInit()

        // 한 판더 버튼을 눌렀을때 다시 훈민정음으로 가는 버튼
        fun moveHunminGameFragment()

        // 게임종료 버튼을 눌렀을때 대기실로 가는 버튼
        fun moveMainFragment()

        fun showResult(gameResult : ArrayList<Game>)
    }

    interface Presenter{
        // 프레그먼트의 뷰가 생성되면 호출 (viewDidLoad()안에서 viewInit()을 실행한다)
        fun viewDidLoad(context: Context)

        fun setResult(resultArr: String)

        fun onClickGameReStartBtn()
    }
}