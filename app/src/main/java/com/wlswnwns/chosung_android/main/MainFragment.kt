package com.wlswnwns.chosung_android.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.wlswnwns.chosung_android.R
import kotlinx.android.synthetic.main.layout_main.*


class MainFragment internal constructor(): Fragment(), MainContract.View {



    // 이전 프레그먼트에서 전달 받아온 데이터들
    private val args : MainFragmentArgs by navArgs()


    // presenter 선언
    var presenter: MainContract.Presenter? = null

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

        CreateRoomBtn.setOnClickListener { moveWaitRoomFragment() }
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




}
