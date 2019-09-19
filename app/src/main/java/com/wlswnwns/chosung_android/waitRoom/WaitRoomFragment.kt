package com.wlswnwns.chosung_android.waitRoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.adapter.WaitRoomUserListAdapter
import com.wlswnwns.chosung_android.item.User
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
        GameStartBtn.setOnClickListener { presenter?.onClickGameStartBtn() }
        BackBtn.setOnClickListener { presenter?.onClickExitRoom() }
    }

    override fun moveHunMinFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            WaitRoomFragmentDirections.actionWaitRoomFragmentToHunminGameFragment()
        )

    }

    override fun moveKungKungDdaFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            WaitRoomFragmentDirections.actionWaitRoomFragmentToKungKungDdaGameFragment()
        )
    }

    override fun showEixtRoomDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.exit_wait_room_dialog_title)
            setMessage(R.string.exit_wait_room_dialog_content)
            setPositiveButton(R.string.dialog_confirm_btn) { _, _ -> presenter?.checkRoomOwner() }
            setNegativeButton(R.string.dialog_cancel_btn){ dialoginterface,_ -> dialoginterface.cancel()  }
            create()
            show()
        }
    }

    override fun exitRoom() {
        ((requireActivity().supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment)
            .navController).popBackStack()
    }

    override fun showFailExitRoom() {

    }

    override fun showUserList(Users: ArrayList<User>) {

        WaitRoomUserListAdapter(
            requireContext(),
            presenter!!,
            Users
        ).let {
            entryListView.adapter = it
        }


    }


}