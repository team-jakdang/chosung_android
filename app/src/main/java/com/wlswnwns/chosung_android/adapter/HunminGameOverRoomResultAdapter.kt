package com.wlswnwns.chosung_android.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wlswnwns.chosung_android.R
import com.wlswnwns.chosung_android.hunminGameOver.HunminGameOverContract
import com.wlswnwns.chosung_android.item.Game

class HunminGameOverRoomResultAdapter(
    private val context: Context,
    private var presenter: HunminGameOverContract.Presenter,
    items: ArrayList<Game>
) : RecyclerView.Adapter<HunminGameOverRoomResultAdapter.UserViewHolder>() {


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
            R.layout.item_hunmingameover_result,
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

        holder.UserOrder?.text = (items[position].iOrder + 1).toString()
        holder.UserNameView?.text = items[position].strUserName

        Log.e("들어왔 ? ", "" + items[position].iOrder.toString())

    }


    class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        var UserOrder: TextView? = null
        var UserNameView: TextView? = null


        init {
            UserOrder = itemView.findViewById(R.id.UserOrder)
            UserNameView = itemView.findViewById(R.id.UserNameView)

        }

    }
}