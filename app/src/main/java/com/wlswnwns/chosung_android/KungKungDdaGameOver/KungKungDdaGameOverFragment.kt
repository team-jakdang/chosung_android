package com.wlswnwns.chosung_android.KungKungDdaGameOver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.KungKungDdaGame.KungKungDdaGameFragmentDirections
import com.wlswnwns.chosung_android.MainActivity
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.waitRoom.WaitRoomFragmentArgs
import kotlinx.android.synthetic.main.layout_game_over.*

class KungKungDdaGameOverFragment : Fragment(), KungKungDdaGameOverContract.View, MainActivity.OnBackPressedListener {


    override fun onBackPressed() {

        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.game_over_dialog_title)
            setMessage(R.string.game_over_dialog_content)
            setPositiveButton(R.string.dialog_confirm_btn) { _, _ -> presenter?.finishGame() }
            setNegativeButton(R.string.dialog_cancel_btn) { dialoginterface, _ -> dialoginterface.cancel() }
            create()
            show()
        }
    }

    private val args: KungKungDdaGameOverFragmentArgs by navArgs()


    var presenter: KungKungDdaGameOverContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_game_over, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰가 만들어지는 시점에서 프레젠터 생성.
        presenter = KungKungDdaGameOverPresenter(this)
        presenter?.viewDidLoad() //set View

    }

    override fun viewInit() {
        TitleTextView.text = "걸린사람"
        gameResultRecyclerView.visibility = View.GONE
        ResultTextView.visibility = View.VISIBLE
        ResultTextView.setText(args.nickName)

        if(!ChosungApplication.Player.bIsMaster){
            OneMoreGameBtnView.visibility = View.GONE

            ConstraintSet().apply {
                clone(layout)

                setHorizontalBias(GameEndBtnView.id,0.5f)

                applyTo(layout)
            }

        }
        OneMoreGameBtnView.setOnClickListener { presenter?.restartGame() }
        GameEndBtnView.setOnClickListener { presenter?.finishGame() }

    }

    override fun exitRoom() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            KungKungDdaGameOverFragmentDirections.actionKungKungDdaGameOverFragmentToMainFragment(ChosungApplication.nikname)
        )
    }
    override fun restartGame() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            KungKungDdaGameOverFragmentDirections.actionKungKungDdaGameOverFragmentToKungKungDdaGameFragment(args.room,args.game)
        )
    }



}