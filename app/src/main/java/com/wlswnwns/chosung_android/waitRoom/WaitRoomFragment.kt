package com.wlswnwns.chosung_android.waitRoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.main.MainPresenter
import com.wlswnwns.chosung_android.nickname.NickNameFragmentDirections
import kotlinx.android.synthetic.main.layout_wait_room.*

class WaitRoomFragment : Fragment(),WaitRoomContract.View{



    private val args : WaitRoomFragmentArgs by navArgs()

    var presenter : WaitRoomContract.Presenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_wait_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = WaitRoomPresenter(this)
        presenter?.viewDidLoad()

    }


    override fun viewInit() {
        HunMinBtn.setOnClickListener { moveHunMinFragment() }
        KungKungDdaBtn.setOnClickListener { moveKungKungDdaFragment() }
    }

    override fun moveHunMinFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            WaitRoomFragmentDirections.actionWaitRoomFragmentToHunminGameFragment()
        )

    }

    override fun moveKungKungDdaFragment() {

    }


}