package ru.skillbranch.devintensive.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chat_single.view.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.ui.custom.AvatarImageView

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>() {
    var items : List<ChatItem> = listOf()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_chat_single, parent, false)
        Log.d("M_Activity", "onCreateViewHolder")
        return SingleViewHolder(convertView)
    }

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        Log.d("M_Activity", "onBindViewHolder $position")
        holder.bind(items[position])
    }

    inner class SingleViewHolder(convertView : View) : RecyclerView.ViewHolder(convertView){

        fun bind(item:ChatItem){
            itemView.iv_avatar_single.setInitials(item.initials)
            itemView.tv_title_single.text = item.shortDescription
        }
    }
}