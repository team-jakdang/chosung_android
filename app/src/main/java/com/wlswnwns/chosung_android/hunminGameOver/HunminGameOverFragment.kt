package com.wlswnwns.chosung_android.hunminGameOver

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.MainActivity
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.adapter.HunminGameOverRoomResultAdapter

import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room

import kotlinx.android.synthetic.main.layout_game_over.*
import kotlinx.android.synthetic.main.layout_game_over.TitleTextView
import kotlinx.android.synthetic.main.layout_hunmingame.*
import org.json.JSONArray
import org.json.JSONException

class HunminGameOverFragment : Fragment(), HunminGameOverContract.View, MainActivity.OnBackPressedListener {

    override fun onBackPressed() {

    }
    private val args : HunminGameOverFragmentArgs by navArgs()

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
        presenter?.viewDidLoad(requireContext())
        presenter?.setResult(args.resultArr)

    }

    override fun viewInit() {

        TitleTextView.setText(requireActivity().resources.getString(R.string.game_over_hunmin_title))
        OneMoreGameBtnView.setOnClickListener { presenter?.onClickGameReStartBtn() }
        GameEndBtnView.setOnClickListener { presenter?.onClickGameQuiteBtn()}


    }

    override fun showResult(gameResult: ArrayList<Game>) {
        HunminGameOverRoomResultAdapter(
            requireContext(),
            presenter!!,
            gameResult
        ).let {
            Log.e("showResult", "방장 ${ChosungApplication.Player.bIsMaster}")
            gameResultRecyclerView.adapter = it
            if (ChosungApplication.Player.bIsMaster) {
                OneMoreGameBtnView.visibility = View.VISIBLE
            } else {
                OneMoreGameBtnView.visibility = View.GONE
            }
        }
    }

    override fun moveHunminGameFragment() {

        Log.e("ff", "ff1")
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            HunminGameOverFragmentDirections.actionHunminGameOverFragmentToHunminGameFragment(
                ChosungApplication.Game.iChosungLenght,
                ChosungApplication.Game.iTime,
                ChosungApplication.Player.bIsMaster
            )
        )
    }

    override fun exitRoom() {
        Log.e("ff", "ff2")

        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            HunminGameOverFragmentDirections.actionHunminGameOverFragmentToMainFragment(ChosungApplication.nikname)

        )
    }
}
