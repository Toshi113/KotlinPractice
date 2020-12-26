package com.example.kotlinpractice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recyclerview.*

class RecyclerviewActivity : AppCompatActivity() ,RecyclerViewHolder.ItemClickListener{
    private lateinit var recyclerview: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val hoges = resources.getStringArray(R.array.hoges).toMutableList()
        main_recyclerview.adapter = RecyclerAdapter(this, this, hoges)
        main_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onItemClick(view: View,position: Int) {
        val pos: Int = position + 1
        Toast.makeText(applicationContext,"position $pos was tapped",Toast.LENGTH_SHORT).show()
    }
}
