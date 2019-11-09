package com.wlswnwns.chosung_android.KungKungDdaGameOver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.waitRoom.WaitRoomFragmentArgs
import kotlinx.android.synthetic.main.layout_game_over.*

class KungKungDdaGameOverFragment : Fragment(), KungKungDdaGameOverContract.View{

    private val args: KungKungDdaGameOverFragmentArgs by navArgs()


    var presenter: KungKungDdaGameOverContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_game_over, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰가 만들어지는 시점에서 프레젠터 생성.
        presenter = KungKungDdaGameOverPresenter(this)
        presenter?.viewDidLoad() //set View

    }

    override fun viewInit() {

        gameResultRecyclerView.visibility = View.GONE
        ResultTextView.visibility = View.VISIBLE
        ResultTextView.setText(args.nickName)

    }


}