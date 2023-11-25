package com.niyas.flipkartmock.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.niyas.flipkartmock.data.models.response.ChatListResponseItem
import com.niyas.flipkartmock.databinding.LayoutChatListItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatListAdapter(private val onItemClick: (selectedChat: ChatListResponseItem) -> Unit) :
    ListAdapter<ChatListResponseItem, ChatListAdapter.ChatListAdapterViewHolder>(DiffCallBack) {


    object DiffCallBack : DiffUtil.ItemCallback<ChatListResponseItem>() {
        override fun areItemsTheSame(
            oldItem: ChatListResponseItem,
            newItem: ChatListResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ChatListResponseItem,
            newItem: ChatListResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class ChatListAdapterViewHolder(private val binding: LayoutChatListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatListResponseItem: ChatListResponseItem) {
            binding.apply {
                titleTv.text = chatListResponseItem.title
                orderNoTv.text = chatListResponseItem.orderId
                Glide.with(root.context)
                    .load(chatListResponseItem.imageURL)
                    .into(profilePictureIv)

                val date = Date(chatListResponseItem.latestMessageTimestamp)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val formattedDate = sdf.format(date)
                timeStampTv.text = formattedDate

                root.setOnClickListener {
                    onItemClick(chatListResponseItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChatListAdapterViewHolder(
        LayoutChatListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: ChatListAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}