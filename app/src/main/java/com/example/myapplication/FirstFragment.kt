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
    private lateinit var trigInverseHigh: TextView
    private lateinit var ruleHigh: TextView
    private lateinit var lab2: TextView
    private lateinit var lab3: TextView
    private lateinit var lab4: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_first, container, false)
        textView = view.findViewById(R.id.textview_fragment1)
        trigHigh = view.findViewById(R.id.trigScore)
        ruleHigh = view.findViewById(R.id.ruleScore)
        lab2 = view.findViewById(R.id.label2)
        lab3 = view.findViewById(R.id.label3)
        lab4 = view.findViewById(R.id.label4)
        trigInverseHigh = view.findViewById(R.id.trigInverseScore)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController: NavController = view.findNavController()
        val activity: MainActivity = context as MainActivity
        trigHigh.text = activity.getHighScore2().toString()
        trigInverseHigh.text = activity.getHighScore3().toString()
        ruleHigh.text = activity.getHighScore4().toString()


        trigHigh.setOnClickListener{ navController.navigate(R.id.secondFragment)};  lab2.setOnClickListener{ navController.navigate(R.id.secondFragment)}
        trigInverseHigh.setOnClickListener{ navController.navigate(R.id.thirdFragment)};  lab3.setOnClickListener{ navController.navigate(R.id.thirdFragment)}
        ruleHigh.setOnClickListener{ navController.navigate(R.id.fourthFragment)};  lab4.setOnClickListener{ navController.navigate(R.id.fourthFragment)}



    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).assignShareMessage(textView.text.toString())
    }
}