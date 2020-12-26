package com.example.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val testFragment = TestFragment.newInstance("フラグメント1")
        val testFragment2 = TestFragment.newInstance("フラグメント2")

        supportFragmentManager.beginTransaction().add(R.id.fragment_container1,testFragment).commit()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container2,testFragment2).commit()

    }
}
