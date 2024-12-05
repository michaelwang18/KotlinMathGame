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
    private var allQuestions: Array<String> = arrayOf("1","2","3","4","5","6")
    private var allAnswers: Array<String> = arrayOf("1","2","3","4","5","6")
    private var currentScore = 0
    private var highScore = 0
    private var questionID = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_second, container, false)
        textView = view.findViewById(R.id.textview_fragment2)
        question = view.findViewById(R.id.question)
        answer1 = view.findViewById(R.id.answer1)
        answer2 = view.findViewById(R.id.answer2)
        answer3 = view.findViewById(R.id.answer3)
        score = view.findViewById(R.id.score)
        maxScore = view.findViewById(R.id.maxScore)
        val activity: MainActivity = context as MainActivity
        highScore = activity.getHighScore2()
        return view



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: Bundle? = arguments // synthetic property from getArguments/setArguments
        if (args != null) {
            val strArg: String? = args.getString("name")
            val intArg: Int = args.getInt("faveNumber") // returns 0 rather than null
            val textView: TextView = view.findViewById(R.id.textview_fragment2) // textView2 is ID for the textView in the third fragment (yikes forgot to change this ID)
            textView.text = "$strArg $intArg"
        }





        val secondToThirdButton: Button = view.findViewById(R.id.button_two_to_three)
        val navController: NavController = view.findNavController()


        changeQuestion()


        question.setOnClickListener{
            changeQuestion()
            chooseAnswer(questionID)
        }












        secondToThirdButton.setOnClickListener {  //Needs to adjust
            val data: Bundle = Bundle()
            data.putString("name", "Screen")
            data.putInt("faveNumber", 3)
            navController.navigate(R.id.navigate_second_to_third, data)
        }



    }

    fun changeQuestion(){
        var currentQuestion = (Math.random() * 6).toInt()
        questionID = currentQuestion
        Log.i("SecondFragment", currentQuestion.toString())

        question.text = allQuestions[currentQuestion]
    }

    fun chooseAnswer(answerIndex: Int){
        if (answerIndex == questionID){
            addScore()
        } else
        {
            currentScore = 0;
        }


    }

    fun addScore(){
        currentScore += 1
        if (currentScore > highScore){
            highScore = currentScore
            maxScore.text = highScore.toString()
        }
        score.text = currentScore.toString()


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