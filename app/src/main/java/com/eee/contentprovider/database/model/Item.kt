package com.eee.contentprovider.database.model


import android.content.ContentValues
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "item")
data class Item(

    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    var title: String = "",
    var content: String = ""

): Parcelable {

    companion object {

        fun fromContentValues(values: ContentValues?): Item {

            val item = Item()

            values?.let {

                if (values.containsKey("title")) item.title = values.getAsString("title")
                if (values.containsKey("content")) item.title = values.getAsString("content")
            }

            return item
        }
    }
}