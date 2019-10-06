package com.wlswnwns.chosung_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.item.User
import com.wlswnwns.chosung_android.waitRoom.WaitRoomContract

class WaitRoomUserListAdapter(
    private val context: Context,
    private var presenter: WaitRoomContract.Presenter,
    items: ArrayList<User>
) : RecyclerView.Adapter<WaitRoomUserListAdapter.UserViewHolder>() {


    var items: ArrayList<User>


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
            R.layout.item_wait_room_user,
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

    }


    class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        var UserNameView: TextView? = null

        init {
            UserNameView = itemView.findViewById(R.id.UserNameView)
        }





    }


}