package com.wlswnwns.chosung_android.hunminGameRanking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.wlswnwns.chosung_android.R
import kotlinx.android.synthetic.main.layout_hunmingame_ranking.*

class HunminGameRankingFragment : Fragment(), HunminGameRankingContract.View {

    var presenter: HunminGameRankingContract.Presenter? = null

    override fun onCreateView( 
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_hunmingame_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HunminGameRankingPresenter(this)
        presenter?.viewDidLoad()

    }

    override fun viewInit() {

        OneMoreGameBtnView.setOnClickListener{moveHunminGameFragment()}
        GameEndBtnView.setOnClickListener{ moveMainFragment()}

    }

    override fun moveHunminGameFragment() {

        Log.e("ff", "ff1")
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            HunminGameRankingFragmentDirections.actionHunminGameRankingFragmentToHunminGameFragment()
        )
    }

    override fun moveMainFragment() {
        Log.e("ff", "ff2")

        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            HunminGameRankingFragmentDirections.actionHunminGameRankingFragmentToWaitRoomFragment("은채")
        )    }
}
