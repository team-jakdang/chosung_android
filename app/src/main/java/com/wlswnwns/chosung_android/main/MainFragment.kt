package com.wlswnwns.chosung_android.main

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.wlswnwns.chosung_android.R
import kotlinx.android.synthetic.main.layout_main.*


class MainFragment : Fragment(), MainContract.View {


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
        HunminGameBtnView.setOnClickListener { MoveHunminGameFragment() }


    }

    // 버튼 클릭시 호출되는 메소드 (훈민정음 프레그먼트로 이동)
    override fun MoveHunminGameFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
           R.id.action_mainFragment_to_hunminGameFragment
        )}


}
