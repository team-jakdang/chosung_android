package com.wlswnwns.chosung_android.qrcamera

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.Room
import com.wlswnwns.chosung_android.nickname.NickNameFragmentDirections
import com.wlswnwns.chosung_android.qrcamera.View.MyZBarScannerView
import kotlinx.android.synthetic.main.layout_qrcamera.*
import me.dm7.barcodescanner.zbar.Result

class CameraFragment internal constructor() : Fragment(), CameraContract.View, MyZBarScannerView.ResultHandler {



    private val args: CameraFragmentArgs by navArgs()

    private val TAG = "CameraFragment ==>"

    var presenter: CameraContract.Presenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_qrcamera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        presenter = CameraPresenter(this)

        presenter?.viewDidLoad()


    }

    override fun viewInit() {
        scannerView.apply {
            setPresenter(presenter)
            setAppCompatActivity(activity as AppCompatActivity)
            visibility = View.VISIBLE
        }
    }


    override fun initCamera() {
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }



    // QR 코드를 인식하게되면 핸들러에 데이터가 나오게된다
    // 데이터를 Model로 전달하고
    // Model의 데이터를 체크하여 대기방으로 이동 할 수 있는지 결정한다
    override fun handleResult(rawResult: Result?) {
        Log.e(TAG, rawResult?.getContents()); // Prints scan results
        Log.e(TAG, rawResult?.getBarcodeFormat()?.getName()); // Prints the scan format (qrcode, pdf417 etc.)

        presenter?.checkQRData(rawResult?.getContents() ?: "")
    }

    override fun moveWaitRoomFragment(room: Room) {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            CameraFragmentDirections.actionCameraFragmentToWaitRoomFragment(args.strNickName,
                room, Game()
            )
        )
    }

    override fun showFailFindRoomData() {
        Toast.makeText(requireContext(),R.string.qrcamera_fail_find_room,Toast.LENGTH_SHORT).show()
    }


}