package com.eee.contentprovider.database

import android.database.Cursor
import androidx.room.*
import com.eee.contentprovider.database.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(item: Item): Long

    @Update
    fun updateItem(item: Item): Int

    @Query("DELETE FROM item WHERE itemId = :id")
    fun deleteItem(id: Long): Int

    @Query("SELECT * FROM item")
    fun getAllItem(): Cursor

    @Query("SELECT * FROM item WHERE itemId = :id")
    fun getItem(id: Long): Cursor
}