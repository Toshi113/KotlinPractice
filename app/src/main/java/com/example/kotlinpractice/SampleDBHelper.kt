package com.example.kotlinpractice

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SampleDBHelper(context: Context,databaseName: String,factory: SQLiteDatabase.CursorFactory?,version: Int) : SQLiteOpenHelper(context,databaseName,factory,version){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS SampleTable (id TEXT PRIMARY KEY, name TEXT,type INTEGER, image BLOB)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("ALTER TABLE SampleTable ADD COLUMN deleteFlag INTEGER DEFAULT 0")
    }
}