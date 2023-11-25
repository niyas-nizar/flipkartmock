package com.niyas.flipkartmock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.niyas.flipkartmock.databinding.ActivityMainBinding
import com.niyas.flipkartmock.ui.ChatListFragment

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        navigate(ChatListFragment(),false)
    }
}