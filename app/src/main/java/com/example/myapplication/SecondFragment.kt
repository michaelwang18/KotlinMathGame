package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController

class SecondFragment : Fragment(), NavController.OnDestinationChangedListener  {

    private lateinit var textView: TextView
    private lateinit var question: TextView
    private lateinit var score: TextView
    private lateinit var maxScore: TextView
    private lateinit var answer1: TextView
    private lateinit var answer2: TextView
    private lateinit var answer3: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_second, container, false)
        textView = view.findViewById(R.id.textview_fragment2)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question = view.findViewById(R.id.question)
        answer1 = view.findViewById(R.id.answer1)
        answer2 = view.findViewById(R.id.answer2)
        answer3 = view.findViewById(R.id.answer3)
        score = view.findViewById(R.id.score)
        maxScore = view.findViewById(R.id.maxScore)

        val secondToThirdButton: Button = view.findViewById(R.id.button_two_to_three)
        val navController: NavController = view.findNavController()



        secondToThirdButton.setOnClickListener {  //Needs to adjust
            val data: Bundle = Bundle()
            data.putString("name", "Screen")
            data.putInt("faveNumber", 3)
            navController.navigate(R.id.navigate_second_to_third, data)
        }



    }










    override fun onDestinationChanged(   //dont think it works
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        Log.i("Second Frag","Hi")
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).assignShareMessage(textView.text.toString())
    }
}