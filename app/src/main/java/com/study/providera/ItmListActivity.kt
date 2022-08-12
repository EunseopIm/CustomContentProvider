package com.study.providera

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.study.providera.database.ItemDatabase
import com.study.providera.databinding.ActivityItemListBinding
import com.study.providera.provider.ContentResolverHelper
import java.text.SimpleDateFormat
import java.util.*

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

                // 단건 데이터 조회
                /*val contentResolverHelper = ContentResolverHelper(this)
                val item = contentResolverHelper.getItem(1)
                Log.v(">>>", item.toString())*/

                val cursor = db.itemDao().getAllItem()
                Log.v(">>>", "cursor count : " + cursor.count)

                // 모든 데이터 조회
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

                        Log.v(">>>", "data : $data")
                    }

                    binding.tvData.text = stringBuilder.toString()

                    runOnUiThread { Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show() }
                }

            }.start()
        }

        // 데이터 추가
        binding.btnAdd.setOnClickListener {

            Thread {

                val contentResolverHelper = ContentResolverHelper(this)
                val sdf = SimpleDateFormat("HH:mm:ss")

                contentResolverHelper.insertItem("제목", "Content - ${sdf.format(Date())}")

                runOnUiThread { Toast.makeText(this, "Add!", Toast.LENGTH_SHORT).show() }

            }.start()
        }

        // 데이터 삭제
        binding.btnRemove.setOnClickListener {

            Thread {

                db.itemDao().deleteAll()

                runOnUiThread { Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show() }

            }.start()
        }
    }
}