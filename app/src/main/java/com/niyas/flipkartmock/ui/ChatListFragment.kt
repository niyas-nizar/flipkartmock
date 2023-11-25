package com.niyas.flipkartmock.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.niyas.flipkartmock.data.EStatus
import com.niyas.flipkartmock.data.models.response.ChatListResponse
import com.niyas.flipkartmock.data.models.response.ChatListResponseItem
import com.niyas.flipkartmock.databinding.FragmentChatListBinding
import com.niyas.flipkartmock.navigate
import com.niyas.flipkartmock.viewmodel.ChatDetailsViewModel
import org.koin.android.viewmodel.ext.android.getViewModel

class ChatListFragment : Fragment() {

    private var chatList: ChatListResponse? = null
    private var binding: FragmentChatListBinding? = null
    private var viewModel: ChatDetailsViewModel? = null
    private val chatListAdapter by lazy {
        ChatListAdapter {
            navigateToChatDetailsPage(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatListBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
        getChatDetails()
        binding?.chatListRv?.adapter = chatListAdapter
        binding?.filterText?.addTextChangedListener { editable ->
            if (editable?.isNotEmpty() == true) {
                val filteredList = chatList?.filter {
                    it.title.lowercase().contains(editable.toString().lowercase()) ||
                            it.orderId.lowercase().contains(editable.toString().lowercase())
                }
                chatListAdapter.submitList(filteredList)
            } else
                chatListAdapter.submitList(chatList)
        }
    }

    private fun getChatDetails() {
        viewModel?.getChatDetails()
            ?.observe(viewLifecycleOwner) {
                when (it.status) {
                    EStatus.SUCCESS -> {
                        chatList = it.data
                        chatListAdapter.submitList(chatList)
                    }

                    EStatus.LOADING -> {
                    }

                    EStatus.ERROR -> {
                    }

                    EStatus.LOGOUT_USER -> {
                    }
                }
            }
    }

    private fun navigateToChatDetailsPage(chatListResponseItem: ChatListResponseItem) {
        (activity as AppCompatActivity).navigate(IndividualChatFragment.newInstance(chatListResponseItem))
    }

}