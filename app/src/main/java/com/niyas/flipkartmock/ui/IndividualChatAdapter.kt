package com.niyas.flipkartmock.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.niyas.flipkartmock.data.models.response.Message
import com.niyas.flipkartmock.databinding.LayoutChatMessageItemBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class IndividualChatAdapter(private val onItemClick: (selectedChat: Message) -> Unit) :
    ListAdapter<Message, IndividualChatAdapter.ChatListAdapterViewHolder>(DiffCallBack) {


    object DiffCallBack : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(
            oldItem: Message,
            newItem: Message
        ): Boolean {
            return oldItem.messageId == newItem.messageId
        }

        override fun areContentsTheSame(
            oldItem: Message,
            newItem: Message
        ): Boolean {
            return oldItem.messageId == newItem.messageId
        }
    }

    inner class ChatListAdapterViewHolder(private val binding: LayoutChatMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(messages: Message) {
            // BOT -> Receiver
            // USER -> Sender
            if (messages.sender.equals("USER", false)) {
                binding.senderMessage.root.isVisible = true
                binding.senderMessage.messageTv.text = messages.message
                binding.senderMessage.timeTv.text = formatTimeWithAMPM(messages.timestamp)
                binding.receiverMessage.root.isVisible = false
            } else {
                binding.receiverMessage.root.isVisible = true
                binding.receiverMessage.messageTv.text = messages.message
                binding.receiverMessage.timeTv.text = formatTimeWithAMPM(messages.timestamp)
                binding.senderMessage.root.isVisible = false
            }

            // Set the date label
            binding.dateLabelTv.text = getDateLabel(messages.timestamp)
            binding.dateLabelTv.isVisible = adapterPosition == 0 ||
                    getDateLabel(getItem(adapterPosition - 1).timestamp) !=
                    getDateLabel(messages.timestamp)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChatListAdapterViewHolder(
        LayoutChatMessageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: ChatListAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getDateLabel(timestamp: Long): String {
        val currentDate = Calendar.getInstance()
        val messageDate = Calendar.getInstance().apply { timeInMillis = timestamp }

        return when {
            currentDate.get(Calendar.YEAR) == messageDate.get(Calendar.YEAR) &&
                    currentDate.get(Calendar.DAY_OF_YEAR) == messageDate.get(Calendar.DAY_OF_YEAR) ->
                "Today"

            currentDate.get(Calendar.YEAR) == messageDate.get(Calendar.YEAR) &&
                    currentDate.get(Calendar.DAY_OF_YEAR) == messageDate.get(Calendar.DAY_OF_YEAR) - 1 ->
                "Yesterday"

            else ->
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(timestamp))
        }
    }

    fun formatTimeWithAMPM(timestamp: Long): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val date = Date(timestamp)
        return sdf.format(date)
    }

}