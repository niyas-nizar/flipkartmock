package com.niyas.flipkartmock.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.niyas.flipkartmock.data.models.response.ChatListResponseItem
import com.niyas.flipkartmock.data.models.response.Message
import com.niyas.flipkartmock.databinding.FragmentIndividualChatBinding
import java.util.UUID


class IndividualChatFragment : Fragment() {


    companion object {
        fun newInstance(chatListResponseItem: ChatListResponseItem) =
            IndividualChatFragment().apply {
                this.chatListResponseItem = chatListResponseItem
            }
    }

    private var chatListResponseItem: ChatListResponseItem? = null
    private var binding: FragmentIndividualChatBinding? = null
    private val chatDetailsAdapter by lazy {
        IndividualChatAdapter {
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIndividualChatBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            sendMessageEt.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        binding?.sendMessageIv?.performClick()
                        true
                    }

                    else -> false
                }
            }

            titleTv.text = chatListResponseItem?.title
            Glide.with(root.context)
                .load(chatListResponseItem?.imageURL)
                .into(profilePictureIv)
            if (chatListResponseItem?.messageList?.isEmpty() == true) {
                emptyMessageTv.isVisible = true
            }
            chatMessagesRv.adapter = chatDetailsAdapter
            chatDetailsAdapter.submitList(chatListResponseItem?.messageList)

            sendMessageIv.setOnClickListener {
                val message = chatListResponseItem?.messageList?.toMutableList()
                message?.add(
                    Message(
                        sendMessageEt.text.toString(),
                        messageId = UUID.randomUUID().toString(),
                        messageType = "text",
                        sender = "BOT",
                        timestamp = System.currentTimeMillis()
                    )
                )
                message?.toList()?.let {
                    chatListResponseItem?.messageList = it
                }
                chatDetailsAdapter.submitList(message)
                sendMessageEt.setText("")
                emptyMessageTv.isVisible = false
            }
        }
    }

}