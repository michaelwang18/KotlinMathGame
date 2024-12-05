package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FourthFragment : Fragment() {

    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_fourth, container, false)
        textView = view.findViewById(R.id.textview_fragment4)
        return view
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).assignShareMessage(textView.text.toString())
    }
}