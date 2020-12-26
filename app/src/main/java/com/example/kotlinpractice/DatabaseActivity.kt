package com.example.kotlinpractice

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.ByteArrayOutputStream
import java.lang.Exception

class DatabaseActivity : AppCompatActivity() {

    private val dbName : String = "SampleDB"
    private val tableName : String = "SampleTable"
    private val dbVersion : Int = 1

    private var arrayListId: ArrayList<String> = arrayListOf()
    private var arrayListName: ArrayList<String> = arrayListOf()
    private var arrayListType: ArrayList<Int> = arrayListOf()
    private var arrayListBitmap: ArrayList<Bitmap> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
    }

    private fun insertData(id: String, name :String, type: Int, bitmap: Bitmap) {
        try{
            val dbHelper = SampleDBHelper(applicationContext, dbName, null , dbVersion)
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("id",id)
            values.put("name",name)
            values.put("type",type)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , byteArrayOutputStream)
            val bytes = byteArrayOutputStream.toByteArray()
            values.put("image", bytes)

            database.insertOrThrow(tableName, null , values)
        }catch (exception: Exception) {
            Log.e("insertData",exception.toString())
        }
    }

    private fun updateData(whereId: String, newName: String,newType: Int,newBitmap: Bitmap) {
        try{
            val dbHelper = SampleDBHelper(applicationContext,dbName,null,dbVersion)
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("name",newName)
            values.put("type",newType)
            val byteArrayOutputStream = ByteArrayOutputStream()
            newBitmap?.compress(Bitmap.CompressFormat.PNG, 100 , byteArrayOutputStream)
            val bytes = byteArrayOutputStream.toByteArray()
            values.put("image", bytes)

            val whereClauses = "id = ?"
            val whereArgs = arrayOf(whereId)
            database.update(tableName,values,whereClauses,whereArgs)
        }catch(exception: Exception) {
            Log.e("updateData",exception.toString())
        }
    }

    private fun deleteData(whereId: String) {
        try{
            val dbHelper = SampleDBHelper(applicationContext, dbName,null,dbVersion)
            val database = dbHelper.writableDatabase

            val whereClauses = "id = ?"
            val whereArgs = arrayOf(whereId)
            database.delete(tableName,whereClauses,whereArgs)
        }catch (exception:Exception) {
            Log.e("deleteData", exception.toString())
        }
    }

    private fun selectData() {
        try{
            arrayListId.clear()
            arrayListBitmap.clear()
            arrayListName.clear()
            arrayListType.clear()

            val dbHelper = SampleDBHelper(applicationContext, dbName,null,dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "SELECT id, name, type, image FROM" + tableName + "WHERE type IN (1, 2) ORDER BY id"

            val cursor = database.rawQuery(sql, null)
            if(cursor.count > 0) {
                cursor.moveToFirst()
                while(!cursor.isAfterLast) {
                    arrayListId.add(cursor.getString(0))
                    arrayListName.add(cursor.getString(1))
                    arrayListType.add(cursor.getInt(2))
                    val blob: ByteArray = cursor.getBlob(3)
                    val bitmap = BitmapFactory.decodeByteArray(blob,0,blob.size)
                    arrayListBitmap.add(bitmap)
                    cursor.moveToNext()
                }
            }
        }catch (exception: Exception) {
            Log.e("selectData",exception.toString())
        }
    }


}
