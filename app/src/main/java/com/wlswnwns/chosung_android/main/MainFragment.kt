package com.wlswnwns.chosung_android.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.dialog.GameSettingDialog
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room

import kotlinx.android.synthetic.main.layout_main.*
import kotlinx.android.synthetic.main.layout_wait_room.*
import net.glxn.qrgen.android.QRCode

class MainFragment internal constructor() : Fragment(), MainContract.View {

    // 이전 프레그먼트에서 전달 받아온 데이터들
    private val args: MainFragmentArgs by navArgs()


    // presenter 선언
    var presenter: MainContract.Presenter? = null

   lateinit var GameSettingDialog : GameSettingDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MainPresenter(this)
        presenter?.viewDidLoad(args.strNickName)

    }

    override fun viewInit() {
        // 훈민정음 버튼 클릭이벤트

        CreateRoomBtn.setOnClickListener {
            presenter?.onClickMakeRoomBtn()
        }
        SearchRoomBtn.setOnClickListener { moveQRCameraFragment() }

        NickNameView.text = args.strNickName




    }

    override fun moveWaitRoomFragment(game: Game,room : Room) {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(

            MainFragmentDirections.actionMainFragmentToWaitRoomFragment(args.strNickName,room,game)

        )
    }


    override fun moveQRCameraFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            MainFragmentDirections.actionMainFragmentToCameraFragment(args.strNickName)
        )
    }

    override fun showGameSettingDialog() {

        GameSettingDialog = GameSettingDialog(this@MainFragment.context!!, presenter!!).apply {
            window!!.setBackgroundDrawableResource(android.R.color.transparent)
            show()
        }
    }

    override fun closeGameSettingDialog() {

        GameSettingDialog.apply {
            if (isShowing) {
                cancel()
            }
        }
    }

    override fun showFailMakeRoomMsg() {

    }



}
