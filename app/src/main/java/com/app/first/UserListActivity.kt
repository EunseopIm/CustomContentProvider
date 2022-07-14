package com.app.first

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.first.database.ItemDatabase
import com.app.first.database.model.Item
import com.app.first.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {

    private val binding: ActivityUserListBinding by lazy {
        ActivityUserListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = ItemDatabase.getInstance(this)

        Thread {

            db.itemDao().insertBook(Item(1, "title1", "content1"))
            db.itemDao().insertBook(Item(2, "title2", "content2"))

            val cursor = db.itemDao().getAllItem()
            Log.v(">>>", "a.size : " + cursor.count)

        }.start()

        Thread {

        }.start()
    }
}