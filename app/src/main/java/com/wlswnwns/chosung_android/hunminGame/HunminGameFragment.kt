package com.wlswnwns.chosung_android.hunminGame

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.item.Game
import kotlinx.android.synthetic.main.layout_hunmingame.*

class HunminGameFragment : Fragment(), HunminGameContract.View {

    private val args : HunminGameFragmentArgs by navArgs()


    var presenter: HunminGameContract.Presenter? = null
    private val TAG = "HunminGameFragment ==>"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_hunmingame, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HunminGamePresenter(this)
        presenter?.viewDidLoad(args.iLength,args.iTime)
        presenter?.gameTimerStart() // 게임 타이머 시작

    }

    override fun showChosungLogList(Game: ArrayList<Game>) {

    }

    override fun viewInit() {
        UserInputEditTextView.addTextChangedListener(object : TextWatcher {

            // 유저가 입력한 텍스트가 변경되면
            override fun afterTextChanged(p0: Editable?) {
                // 유저가 입력한 단어를 model 에 정의한 userInputEditTextView 에 set 해주기 위해
                // 'presenter'의 setStrUserInputEditText()를 호출한다
                presenter?.setStrUserInputEditText(UserInputEditTextView.text.toString())

                // 유저가 입력한 단어가 3글자가 넘는지 확인하는 'presenter'의 checkUserInputTextLength()를 호출한다
                presenter?.checkUserInputTextLength()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


        // 유저가 키보드에 있는 완료 버튼을 누르면 답이 맞는지 체크하는 'presenter'의  checkUserInputTextIsAnswer() 호출
        UserInputEditTextView.setOnEditorActionListener { v, actionId, event ->
            Log.e(TAG, v.toString()+actionId.toString()+event)
            presenter?.checkUserInputTextIsAnswer()
//            presenter?.listViewGameLog()
            true
        }



    }

    override fun showErrorMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun randomChosungSetTextView(chosung: ArrayList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun longUserInputText(chosungLength: String) {
        Toast.makeText(context, "단어의 길이가 3자를 넘으면 안됩니다", Toast.LENGTH_SHORT).show()
    }


    // 기본 게임 뷰
    override fun defaultGameView() {
        UserInputEditTextView.isVisible = true
        WrongImageView.isVisible = false
        AnswerImageView.isVisible = false
    }


    // 유저가 정답일때 바뀌는 뷰
    // ChosungTextView ,UserInputEditTextView 를 숨긴다
    // AnswerImageView 을 보이게 한다
    override fun answerGameView() {
        UserInputEditTextView.isVisible = false
        AnswerImageView.isVisible = true
    }

    // 유저가 정답이 아닐때 바뀌는 뷰
    // ChosungTextView ,UserInputEditTextView , WrongImageView 보이게
    override fun wrongGameView() {
        UserInputEditTextView.isVisible = true
        WrongImageView.isVisible = true
    }

    override fun listViewGameLogs(arg: ArrayList<Any>) {

    }


    // 순위 프래그먼트로 이동
    override fun moveHunminGameRankingFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            HunminGameFragmentDirections.actionHunminGameFragmentToHunminGameRankingFragment()
        )
    }



    override fun timeProgressBarActive() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}