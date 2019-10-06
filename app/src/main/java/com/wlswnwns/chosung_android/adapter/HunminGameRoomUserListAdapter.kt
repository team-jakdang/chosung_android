package com.wlswnwns.chosung_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.hunminGame.HunminGameContract
import com.wlswnwns.chosung_android.item.Game


class HunminGameRoomUserListAdapter(
    private val context: Context,
    private var presenter: HunminGameContract.Presenter,
    items: ArrayList<Game> //User에 관리하든지, GameUsers를 만들어 따로 하든지 회의후 결정 어찌되었든 닉네임 + 방장 여부도 있어야함

) : RecyclerView.Adapter<HunminGameRoomUserListAdapter.UserViewHolder>() {

    var items: ArrayList<Game>


    init {
        this.items = items
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {

        val view: ViewGroup

        val viewHolder: RecyclerView.ViewHolder


        view = LayoutInflater.from(context).inflate(
            R.layout.item_hunmungame_room,
            parent,
            false
        ) as ViewGroup

        viewHolder =
            UserViewHolder(view)


        return viewHolder


    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.UserNameView?.text = items[position].strUserName
        holder.ChosungView?.text = items[position].strChosung


    }


    class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        var UserNameView: TextView? = null
        var ChosungView: TextView? = null


        init {
            UserNameView = itemView.findViewById(R.id.UserNameView)
            ChosungView = itemView.findViewById(R.id.ChosungView)

        }

    }

}



