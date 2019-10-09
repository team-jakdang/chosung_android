package com.wlswnwns.chosung_android.hunminGameOver

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.wlswnwns.chosung_android.R

import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room

import kotlinx.android.synthetic.main.layout_game_over.*

class HunminGameOverFragment : Fragment(), HunminGameOverContract.View {

    var presenter: HunminGameOverContract.Presenter? = null

    override fun onCreateView( 
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_game_over, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HunminGameOverPresenter(this)
        presenter?.viewDidLoad()

    }

    override fun viewInit() {

        TitleTextView.setText(requireActivity().resources.getString(R.string.game_over_hunmin_title))
        OneMoreGameBtnView.setOnClickListener{moveHunminGameFragment()}
        GameEndBtnView.setOnClickListener{ moveMainFragment()}

    }

    override fun moveHunminGameFragment() {

        Log.e("ff", "ff1")
//        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
//            HunminGameRankingFragmentDirections.actionHunminGameRankingFragmentToHunminGameFragment(2,20,)
//        )
    }

    override fun moveMainFragment() {
        Log.e("ff", "ff2")

        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            HunminGameOverFragmentDirections.actionHunminGameOverFragmentToWaitRoomFragment("은채",
                Room(), Game()
            )

        )    }
}
