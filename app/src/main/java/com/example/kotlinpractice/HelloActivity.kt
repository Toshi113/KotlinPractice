package com.example.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_hello.*
import kotlinx.android.synthetic.main.activity_main.button

class HelloActivity  : AppCompatActivity() , View.OnClickListener {
    private var isHello: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
        button.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.button -> {
                    isHello = if(!isHello){
                        textView.setText(R.string.hello)
                        //textView.text = resources.getString(R.string.hello)
                        true
                    } else{
                        textView.setText(R.string.world)
                        //textView.text = resources.getString(R.string.world)
                        false
                    }
                }
            }
        }
    }
}
