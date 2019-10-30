package com.wlswnwns.chosung_android.KungKungDdaGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.item.User
import kotlinx.android.synthetic.main.layout_kungkungddagame.*

/**
 * 뷰에 일어날 이벤트를 구현한다.
 **/

class KungKungDdaGameFragment : Fragment(), KungKungDdaGameContract.View {


    //presenter 선언
    var presenter: KungKungDdaGameContract.Presenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_kungkungddagame, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //뷰가 만들어지는 시점에서 프레젠터 생성.
        presenter = KungKungDdaGamePresenter(this)
        presenter?.viewDidLoad() //set View
        presenter?.gameTimerStart() // 게임 타이머 시작


    }

    //뷰 초기셋팅. 프레젠터가 생성될 때
    override fun viewInit() {
        //버튼 클릭리스너 등 뷰에 할당할 이벤트를 적는다.

        //


        // 유저가 키보드에 있는 완료 버튼을 누르면 답이 맞는지 체크하는 'presenter'의  checkUserInputTextIsAnswer() 호출
        UserInputEditTextView.setOnEditorActionListener { v, actionId, event ->

            presenter?.checkUserInputTextIsAnswer()
            true
        }

    }

    override fun defaultGameView() {

        UserInputEditTextView.isVisible = true //답 입력칸 보이기
        WrongImageView.isVisible = false //오답 뷰 숨김
        AnswerImageView.isVisible = false //정답 뷰 숨김
    }

    //유저의 입력값이 오답일 때 보여주는 오답 뷰.
    override fun wrongGameView() {

        UserInputEditTextView.isVisible = false //답 입력칸 숨기기
        WrongImageView.isVisible = true //오답 이미지(X) 보이기

    }

    //유저의 입력값이 정답일 때 보여주는 정답 뷰.
    override fun answerGameView() {

        UserInputEditTextView.isVisible = false //답 입력칸 숨기기
        AnswerImageView.isVisible = true //정답 이미지(O) 보이기


    }

    //사용자가 입력한 단어의 길이가 3글자를 초과할 때 토스트를 띄움
    override fun longUserInputText() {
        Toast.makeText(context, "단어의 길이가 3자를 넘으면 안됩니다", Toast.LENGTH_SHORT).show()
    }

    //
    override fun showCountDownText(count: String) {
        CountTextView.text = count
    }

    override fun readyView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listViewGameLogs(arg: ArrayList<Any>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun moveToKungDdaGameOverFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            KungKungDdaGameFragmentDirections.actionKungKungDdaGameFragmentToKungKungDdaGameOverFragment()

        )
    }

    override fun showErrorMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeUserOrder(order1: String, order2: String, order3: String) {

        Order1TextView.text = order1
        Order2TextView.text = order2
        Order3TextView.text = order3


    }

    override fun showChosungText(chosung: String) {
        ChosungTextView.text = chosung
    }

    override fun setOrder1TextView(user: User) {
        Order1TextView.text = user.strUserName
    }

    override fun setOrder2TextView(user: User) {
        Order2TextView.text = user.strUserName
    }

    override fun setOrder3TextView(user: User) {
        Order3TextView.text = user.strUserName
    }


}