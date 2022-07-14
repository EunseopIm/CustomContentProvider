package com.eee.contentprovider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eee.contentprovider.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {

    private val binding: ActivityUserListBinding by lazy {
        ActivityUserListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}