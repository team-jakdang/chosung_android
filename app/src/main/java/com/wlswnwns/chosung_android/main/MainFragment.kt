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
import kotlinx.android.synthetic.main.layout_main.*


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
        presenter?.viewDidLoad()

    }

    override fun viewInit() {
        // 훈민정음 버튼 클릭이벤트

        CreateRoomBtn.setOnClickListener {
            presenter?.onClickMakeRoomBtn()
        }
        SearchRoomBtn.setOnClickListener { moveQRCameraFragment() }


    }

    override fun moveWaitRoomFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(

            MainFragmentDirections.actionMainFragmentToWaitRoomFragment(args.strNickName)
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
