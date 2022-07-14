package com.eee.contentprovider.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eee.contentprovider.database.model.Item

@Database(
    entities = [Item::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(OrmConverter::class)
abstract class ItemDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {

        @Volatile
        private var INSTANCE: ItemDatabase? = null

        private fun buildDatabase(context: Context): ItemDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                ItemDatabase::class.java,
                "db_item"
            ).build()

        fun getInstance(context: Context): ItemDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
    }
}