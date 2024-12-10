package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController

class FourthFragment : Fragment() {

    private lateinit var textView: TextView
    private lateinit var question: TextView
    private lateinit var score: TextView
    private lateinit var maxScore: TextView
    private lateinit var answer1: TextView
    private lateinit var answer2: TextView
    private lateinit var answer3: TextView
    private lateinit var AD1: ImageView
    private lateinit var AD2: ImageView
    private lateinit var AD3: ImageView
    private lateinit var questionImage: ImageView

    private var allQuestions: Array<String> = arrayOf("1","2","3","4","5","6")

    private var allQImage: Array<String> = arrayOf("1","2","3","4","5","6")
    private var allAnswers: Array<String> = arrayOf("1","2","3","4","5","6")
    private var allAImage: Array<String> = arrayOf("1","2","3","4","5","6")
    private var answers: Array<Int> = arrayOf(1,2,3)
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
        questionImage = view.findViewById(R.id.QDis)

        answer1 = view.findViewById(R.id.answer1);  answer2 = view.findViewById(R.id.answer2);  answer3 = view.findViewById(R.id.answer3)
        AD1 = view.findViewById(R.id.ADis1);        AD2 = view.findViewById(R.id.ADis2);        AD3 = view.findViewById(R.id.ADis3)
        //AD1.scaleType = ImageView.ScaleType.CENTER; AD2.scaleType = ImageView.ScaleType.CENTER; AD3.scaleType = ImageView.ScaleType.CENTER

        score = view.findViewById(R.id.score)

        maxScore = view.findViewById(R.id.maxScore) //Takes from main activity, Single Source of Truth Thingy
        val activity: MainActivity = context as MainActivity
        highScore = activity.getHighScore4()
        return view



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val secondToThirdButton: Button = view.findViewById(R.id.button_two_to_three)
        val navController: NavController = view.findNavController()


        changeQuestion()


        question.setOnClickListener{
            changeQuestion()

            // chooseAnswer(questionID) //Temp to Test
        }

        AD1.setOnClickListener{ chooseAnswer(0) }
        AD2.setOnClickListener{ chooseAnswer(1) }
        AD3.setOnClickListener{ chooseAnswer(2) }
        // answer1.setOnClickListener{ chooseAnswer(1) }
        //answer2.setOnClickListener{ chooseAnswer(2) }
        //answer3.setOnClickListener{ chooseAnswer(3) }





        secondToThirdButton.setOnClickListener {  //Needs to adjust
            val data: Bundle = Bundle()
            data.putString("name", "Screen")
            data.putInt("faveNumber", 3)
            navController.navigate(R.id.navigate_second_to_third, data)
        }



    }




    fun changeQuestion(){
        var tempList = allQuestions
        var currentQuestion = (Math.random() * 5).toInt()
        questionID = currentQuestion
        Log.i("Fourth", currentQuestion.toString())
        question.text = allQuestions[currentQuestion]
        //Change the answer:


        answers[0] = questionID.toInt()
        answers[1] = (Math.random() * 5).toInt()
        answers[2] = (Math.random() * 5).toInt()
        while (answers[0] == answers[1] || answers[0] == answers[2] || answers[1] == answers[2]){
            answers[1] = (Math.random() * 5).toInt()
            answers[2] = (Math.random() * 5).toInt()
        }
        answers.shuffle()
        setImages()
        var displays: Array<TextView> = arrayOf(answer1,answer2,answer3)
        for (nums in 0..2){
            displays[nums].text = answers[nums].toString()
            //make Actual images

        }


    }


    fun setImages(){
        //Need to make question Image
        var flipped = (Math.random() * 2).toInt()

        var answerImages: Array<ImageView> = arrayOf(AD1,AD2,AD3)
        questionImage.setImageResource(R.drawable.lego_computer)//Test


        if (flipped == 0) {
            if (questionID == 0) {
                questionImage.setImageResource(R.drawable.p3a1)
            }
            if (questionID == 1) {
                questionImage.setImageResource(R.drawable.p3a2)
            }
            if (questionID == 2) {
                questionImage.setImageResource(R.drawable.p3a3)
            }
            if (questionID == 3) {
                questionImage.setImageResource(R.drawable.p3a4)
            }
            if (questionID == 4) {
                questionImage.setImageResource(R.drawable.p3a5)
            }

            for (nums in 0..2) {
                answerImages[nums].setImageResource(R.drawable.lego_computer)//Test

                if (answers[nums] == 0) {
                    answerImages[nums].setImageResource(R.drawable.p3q1)
                }
                if (answers[nums] == 1) {
                    answerImages[nums].setImageResource(R.drawable.p3q2)
                }
                if (answers[nums] == 2) {
                    answerImages[nums].setImageResource(R.drawable.p3q3)
                }
                if (answers[nums] == 3) {
                    answerImages[nums].setImageResource(R.drawable.p3q4)
                }
                if (answers[nums] == 4) {
                    answerImages[nums].setImageResource(R.drawable.p3q5)
                }

            }
        } else if (flipped == 1) {

            if (questionID == 0) {
                questionImage.setImageResource(R.drawable.p3q1)
            }
            if (questionID == 1) {
                questionImage.setImageResource(R.drawable.p3q2)
            }
            if (questionID == 2) {
                questionImage.setImageResource(R.drawable.p3q3)
            }
            if (questionID == 3) {
                questionImage.setImageResource(R.drawable.p3q4)
            }
            if (questionID == 4) {
                questionImage.setImageResource(R.drawable.p3q5)
            }

            for (nums in 0..2) {
                answerImages[nums].setImageResource(R.drawable.lego_computer)//Test

                if (answers[nums] == 0) {
                    answerImages[nums].setImageResource(R.drawable.p3a1)
                }
                if (answers[nums] == 1) {
                    answerImages[nums].setImageResource(R.drawable.p3a2)
                }
                if (answers[nums] == 2) {
                    answerImages[nums].setImageResource(R.drawable.p3a3)
                }
                if (answers[nums] == 3) {
                    answerImages[nums].setImageResource(R.drawable.p3a4)
                }
                if (answers[nums] == 4) {
                    answerImages[nums].setImageResource(R.drawable.p3a5)
                }


            }
        }




    }




    fun chooseAnswer(answerIndex: Int){
        if (answers[answerIndex] == questionID){
            addScore()
        } else
        {
            currentScore = 0;
            score.text = currentScore.toString()
        }
        changeQuestion()

    }

    fun addScore(){
        currentScore += 1
        if (currentScore > highScore){
            highScore = currentScore
            maxScore.text = highScore.toString()
            val activity: MainActivity = context as MainActivity
            activity.setHighScore4(currentScore)

        }
        score.text = currentScore.toString()


    }



    override fun onResume() {
        super.onResume()
        (activity as MainActivity).assignShareMessage(textView.text.toString())
    }
}