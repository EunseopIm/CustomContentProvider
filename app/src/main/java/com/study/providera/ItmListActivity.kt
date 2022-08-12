package com.study.providera

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.study.providera.database.ItemDatabase
import com.study.providera.databinding.ActivityItemListBinding
import com.study.providera.provider.ContentResolverHelper

class ItmListActivity : AppCompatActivity() {

    private val binding: ActivityItemListBinding by lazy {
        ActivityItemListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = ItemDatabase.getInstance(this)

        // 데이터 불러오기
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

        // 데이터 추가
        binding.btnAdd.setOnClickListener {

            Thread {

                val contentResolverHelper = ContentResolverHelper(this)

                contentResolverHelper.insertItem("제목2", "내용2")
                contentResolverHelper.allItems()

            }.start()
        }

        // 데이터 삭제
        binding.btnRemove.setOnClickListener {

            Thread {

                val contentResolverHelper = ContentResolverHelper(this)

            }.start()
        }
    }
}