package com.wlswnwns.chosung_android.nickname

import android.Manifest
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wlswnwns.chosung_android.R
import kotlinx.android.synthetic.main.layout_nickname.*


class NickNameFragment : Fragment(), NickNameContract.View {


    var presenter: NickNameContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_nickname, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NickNamePresenter(this)
        presenter?.viewDidLoad()

    }


    override fun viewInit() {
        NikNameEditTextView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                presenter?.setStrNikName(NikNameEditTextView.text.toString())
                presenter?.checkNikNameLength()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        ConfirmBtnView.setOnClickListener { moveMainFragment() }

    }


    override fun longNikName() {
        Toast.makeText(context, "닉네임 길이가 10자를 넘으면 안됩니다", Toast.LENGTH_SHORT).show()
    }


    override fun confirmBtnActive() {
        ConfirmBtnView.isEnabled = true

    }

    override fun confirmBtnUnActive() {
        ConfirmBtnView.isEnabled = false
    }

    override fun moveMainFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            NickNameFragmentDirections.actionNickNameFragmentToMainFragment(presenter?.getStrNikName() ?: "")
        )
    }

    override fun requestPermission() {

        RxPermissions(this).let {
            it.requestEach(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { permission ->
                    if (permission.granted) {

                    } else if (permission.shouldShowRequestPermissionRationale) {

                    } else {

                    }
                }
        }
    }

}
