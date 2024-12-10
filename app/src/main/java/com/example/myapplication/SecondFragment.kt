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

class SecondFragment : Fragment() {

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
        var currentQuestion = (Math.random() * 6).toInt()
        questionID = currentQuestion
        Log.i("SecondFragment", currentQuestion.toString())
        question.text = allQuestions[currentQuestion]
        //Change the answer:


        answers[0] = questionID.toInt()
        answers[1] = (Math.random() * 6).toInt()
        answers[2] = (Math.random() * 6).toInt()
        while (answers[0] == answers[1] || answers[0] == answers[2] || answers[1] == answers[2]){
            answers[1] = (Math.random() * 6).toInt()
            answers[2] = (Math.random() * 6).toInt()
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
        var answerImages: Array<ImageView> = arrayOf(AD1,AD2,AD3)
        questionImage.setImageResource(R.drawable.lego_computer)//Test

        if (questionID == 0){ questionImage.setImageResource(R.drawable.q1) }
        if (questionID == 1){ questionImage.setImageResource(R.drawable.q2) }
        if (questionID == 2){ questionImage.setImageResource(R.drawable.q3) }
        if (questionID == 3){ questionImage.setImageResource(R.drawable.q4) }
        if (questionID == 4){ questionImage.setImageResource(R.drawable.q5) }
        if (questionID == 5){ questionImage.setImageResource(R.drawable.q6) }
        

        for(nums in 0..2){
            answerImages[nums].setImageResource(R.drawable.lego_computer)//Test

            if (answers[nums] == 0){ answerImages[nums].setImageResource(R.drawable.a1) }
            if (answers[nums] == 1){ answerImages[nums].setImageResource(R.drawable.a2) }
            if (answers[nums] == 2){ answerImages[nums].setImageResource(R.drawable.a3) }
            if (answers[nums] == 3){ answerImages[nums].setImageResource(R.drawable.a4) }
            if (answers[nums] == 4){ answerImages[nums].setImageResource(R.drawable.a5) }
            if (answers[nums] == 5){ answerImages[nums].setImageResource(R.drawable.a6) }
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
            activity.setHighScore2(currentScore)

        }
        score.text = currentScore.toString()


    }



    override fun onResume() {
        super.onResume()
        (activity as MainActivity).assignShareMessage(textView.text.toString())
    }
}