package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.findNavController

class FirstFragment : Fragment() {

    private lateinit var textView: TextView
    private lateinit var trigHigh: TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_first, container, false)
        textView = view.findViewById(R.id.textview_fragment1)
        trigHigh = view.findViewById(R.id.trigScore)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstToSecondButton: Button = view.findViewById(R.id.button)
        val firstToThirdButton: Button = view.findViewById(R.id.button_one_to_three)
        val navController: NavController = view.findNavController()
        firstToSecondButton.setOnClickListener { navController.navigate(R.id.navigate_first_to_second) }
        firstToThirdButton.setOnClickListener { navController.navigate(R.id.navigate_first_to_third) }
        val activity: MainActivity = context as MainActivity
        trigHigh.text = activity.getHighScore2().toString()

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).assignShareMessage(textView.text.toString())
    }
}