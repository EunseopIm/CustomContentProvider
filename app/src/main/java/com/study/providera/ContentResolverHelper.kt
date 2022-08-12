package com.study.providera

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.util.Log

class ContentResolverHelper(private var mContext: Context) {

    companion object {

        const val TABLE_NAME = "item"
        const val PROVIDER_NAME = "com.app.first.provider.MyContentProvider"
        const val URL = "content://$PROVIDER_NAME/$TABLE_NAME"
        val CONTENT_URI = Uri.parse(URL)
    }

    private var contentResolver: ContentResolver = mContext.contentResolver

    fun allItems() {

        /*val companyTMList: ArrayList<CompanyTMRecord> = ArrayList<CompanyTMRecord>()
        val projection = arrayOf<String>(KEY_ID, KEY_NAME, KEY_EMAIL)*/

        val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
        Log.v(">>>", "@# cursor : ${cursor?.count?: 0} / $cursor")
        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {

                val itemIdIndex = cursor.getColumnIndex("itemId")
                val titleIndex = cursor.getColumnIndex("title")
                val contentIndex = cursor.getColumnIndex("content")

                val id = cursor.getLong(itemIdIndex)
                val title = cursor.getString(titleIndex)
                val content = cursor.getString(contentIndex)

                Log.v(">>>", "@# id[$id] title[$title] content[$content]")
            }
        }
    }

    fun getItem(id: Long) {

        /*val companyTMRecord= CompanyTMRecord()
        val contentResolver = mContext.contentResolver
        val uri: Uri = CONTENT_URI
        val projection = arrayOf<String>(KEY_ID, KEY_NAME, KEY_EMAIL)
        val selection: String = KEY_ID.toString() + "=?"
        val selectionArgs = arrayOf(id.toString())
        val sortOrder: String? = null
        val cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                companyTMRecord.id = cursor.getLong(0)
                companyTMRecord.name = cursor.getString(1)
                companyTMRecord.email = cursor.getString(2)
            }
        }
        return companyTMRecord*/
    }

    fun insertCompanyTMRecord(title: String, content: String) {

        val contentValues = generateItem(title, content)
        val uri = contentResolver.insert(CONTENT_URI, contentValues)
    }

    fun deleteCompanyTMRecord(id: Long) {

        /*val contentResolver = mContext.contentResolver
        val where: String = KEY_ID + "=?"
        val selectionArgs = arrayOf(index.toString())
        contentResolver.delete(CONTENT_URI, where, selectionArgs)*/

        val url = "$URL/$id"
        contentResolver.delete(Uri.parse(url), null, null)
    }

    fun updateCompanyTMRecord(id: Long) {

        /*val contentValues = ContentValues()
        contentValues.put(KEY_ID, companyTMRecord.id)
        contentValues.put(KEY_NAME, companyTMRecord.name)
        contentValues.put(KEY_EMAIL, companyTMRecord.email)

        val where: String = KEY_ID.toString() + "=?"
        val selectionArgs = arrayOf(id.toString())
        val noOfRec = contentResolver.update(CONTENT_URI, contentValues, where, selectionArgs)
        Toast.makeText(mContext, "$noOfRec Record Delete successfully", Toast.LENGTH_SHORT).show()*/
    }

    private fun generateItem(title: String, content: String): ContentValues {

        val values = ContentValues()
        values.put("title", title)
        values.put("content", content)

        return values
    }
}