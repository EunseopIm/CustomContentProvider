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

        }.start()

        binding.btnTest.setOnClickListener {

            Thread {

                val contentResolverHelper = ContentResolverHelper(this)
                contentResolverHelper.insertCompanyTMRecord("제목1", "내용1")
                contentResolverHelper.allItems()
            }.start()
        }

        binding.btnLoad.setOnClickListener {

            Thread {

                val cursor = db.itemDao().getAllItem()
                Log.v(">>>", "a.size : " + cursor.count)

                runOnUiThread {

                    val stringBuilder = StringBuilder()
                    while (cursor.moveToNext()) {

                        val itemIdIndex = cursor.getColumnIndex("itemId")
                        val titleIndex = cursor.getColumnIndex("title")
                        val contentIndex = cursor.getColumnIndex("content")

                        val id = cursor.getLong(itemIdIndex)
                        val title = cursor.getString(titleIndex)
                        val content = cursor.getString(contentIndex)

                        val data = "id[$id] title[$title] content[$content]"
                        if (stringBuilder.isNotEmpty()) stringBuilder.append("\n")
                        stringBuilder.append(data)
                        Log.v(">>>", "@# $data")

                    }

                    binding.tvData.text = stringBuilder.toString()
                }

            }.start()
        }
    }
}