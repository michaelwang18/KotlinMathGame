package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ThirdFragment : Fragment() {

    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_third, container, false)
        textView = view.findViewById(R.id.textview_fragment3)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: Bundle? = arguments // synthetic property from getArguments/setArguments
        if (args != null) {
            val strArg: String? = args.getString("name")
            val intArg: Int = args.getInt("faveNumber") // returns 0 rather than null
            val textView: TextView = view.findViewById(R.id.textview_fragment3) // textView2 is ID for the textView in the third fragment (yikes forgot to change this ID)
            textView.text = "$strArg $intArg"
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).assignShareMessage(textView.text.toString())
    }
}