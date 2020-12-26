package com.example.kotlinpractice

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val KEY_TEXT = "text"
class TestFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var fragText: String? = "default"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ここで引数を受け取る
            fragText = it.getString(KEY_TEXT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //ここでfragmentを適切に作成する
        var view: View = inflater.inflate(R.layout.fragment_test, container, false)
        var tv: TextView = view.findViewById(R.id.fragment_textView)
        tv.setText(fragText)
        if(fragText == "フラグメント1") {
            view.setBackgroundColor(Color.RED)
        }else{
            view.setBackgroundColor(Color.BLUE)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(text: String) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    //ここで引数を与える
                    putString(KEY_TEXT, text)
                }
            }
    }

    override fun onPause() {
        super.onPause()
        Log.i(fragText,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(fragText,"onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(fragText,"onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(fragText,"onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(fragText,"onDetach")
    }
}
