package com.wlswnwns.chosung_android.waitRoom

import android.graphics.Bitmap
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.MainActivity
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.adapter.WaitRoomUserListAdapter
import com.wlswnwns.chosung_android.item.User
import kotlinx.android.synthetic.main.layout_wait_room.*

class WaitRoomFragment : Fragment(), WaitRoomContract.View,MainActivity.OnBackPressedListener {
    override fun showKKTerror() {
        Toast.makeText(context, "쿵쿵따는 2명 이상이어야 게임시작이 가능합니다", Toast.LENGTH_SHORT)
            .show()
    }


    override fun onBackPressed() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.exit_wait_room_dialog_title)
            setMessage(R.string.exit_wait_room_dialog_content)
            setPositiveButton(R.string.dialog_confirm_btn) { _, _ -> presenter?.checkRoomOwner() }
            setNegativeButton(R.string.dialog_cancel_btn) { dialoginterface, _ -> dialoginterface.cancel() }
            create()
            show()
        }
    }


    private val args: WaitRoomFragmentArgs by navArgs()

    var presenter: WaitRoomPresenter? = null



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


    }

    override fun onResume() {
        super.onResume()
        presenter?.viewDidLoad(args.game, args.room,args.strNikName)

    }




    override fun viewInit(iLength: Int, iTime: Int) {
        GameStartBtn.setOnClickListener { presenter?.onClickGameStartBtn() }
        BackBtn.setOnClickListener { presenter?.onClickExitRoom() }
    }

    override fun moveHunMinFragment(iLength : Int, iTime : Int, bIsMaster: Boolean) {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            WaitRoomFragmentDirections.actionWaitRoomFragmentToHunminGameFragment(iLength,iTime, bIsMaster)
        )
    }


    override fun moveKungKungDdaFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            WaitRoomFragmentDirections.actionWaitRoomFragmentToKungKungDdaGameFragment(args.room,args.game)
        )
    }

    override fun showEixtRoomDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.exit_wait_room_dialog_title)
            setMessage(R.string.exit_wait_room_dialog_content)
            setPositiveButton(R.string.dialog_confirm_btn) { _, _ -> presenter?.checkRoomOwner() }
            setNegativeButton(R.string.dialog_cancel_btn) { dialoginterface, _ -> dialoginterface.cancel() }
            create()
            show()
        }
    }

    override fun exitRoom() {
//        ((requireActivity().supportFragmentManager
//            .findFragmentById(R.id.fragment_container) as NavHostFragment)
//            .navController).popBackStack()

        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            WaitRoomFragmentDirections.actionWaitRoomFragmentToMainFragment(ChosungApplication.nikname)
        )
    }




    override fun showFailExitRoom() {

    }

    override fun showUserList(Users: ArrayList<User>) {

        WaitRoomUserListAdapter(
            requireContext(),
            presenter!!,
            Users
        ).let {
            entryListView.layoutManager = GridLayoutManager(requireContext(),2)
            entryListView.adapter = it
        }
    }

    override fun showGameMode(mode: String) {
        GameNameView.text = mode
    }

    override fun showChosungLength(length: Int) {
        ChosungLengthView.text = length.toString() + "글자"
    }

    override fun showTime(time: Int) {
        GameTimeView.text = time.toString() + "초"
    }

    override fun showQRCodeImage(img: Bitmap) {
        QRCodeImgView.setImageBitmap(img)
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        presenter?.disConnectSocket()
    }

    override fun hideGameStartBtn() {
        GameStartBtn.visibility = View.GONE
    }


    override fun showPlayerNumber(number: String) {
        PlayerNumberView.text = number+"명"
    }

}