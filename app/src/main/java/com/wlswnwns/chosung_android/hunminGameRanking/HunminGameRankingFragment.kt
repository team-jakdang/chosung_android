package com.wlswnwns.chosung_android.hunminGameRanking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wlswnwns.chosung_android.R

class HunminGameRankingFragment : Fragment(), HunminGameRankingContract.View {

    var presenter: HunminGameRankingContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_hunmingame, container, false)
    }


    override fun viewInit() {

    }


}
