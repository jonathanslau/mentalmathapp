package com.example.mentalmathpractice

import android.graphics.Color.red
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Gravity
import android.view.Gravity.CENTER
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_practice.*
import java.lang.Math.random
import java.util.*
import kotlin.math.abs

class Practice : AppCompatActivity() {

    var answer: String = ""
    var numGen: String = ""
    var question: String = ""
    var eval: Long = 0
    var temp: String = ""
    var ansSlice: String = ""
    var ansLen: Int = 0
    var diff1: Int = 0
    var diff2: Int = 0
    lateinit var modeSelected: String
    lateinit var modeSelected2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)
        //get variables from main activity
            diff1 = intent.getIntExtra("diff1", 0)
            diff2 = intent.getIntExtra("diff2", 0)
            modeSelected = intent.getStringExtra("modeSelected")
            modeSelected2 = intent.getStringExtra("modeSelected2")
            //println(modeSelected2)
        val response =
            Toast.makeText(this.applicationContext, "Placeholder", Toast.LENGTH_SHORT)
        //Generate question
        generateQuestion()

        //Implementing the answer pad

        button0.setOnClickListener {
            temp = "0"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        button1.setOnClickListener {
            temp = "1"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        button2.setOnClickListener {
            temp = "2"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        button3.setOnClickListener {
            temp = "3"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        button4.setOnClickListener {
            temp = "4"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        button5.setOnClickListener {
            temp = "5"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        button6.setOnClickListener {
            temp = "6"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        button7.setOnClickListener {
            temp = "7"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        button8.setOnClickListener {
            temp = "8"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        button9.setOnClickListener {
            temp = "9"
            answer = "$answer$temp"
            answerInput.setText(answer)
        }
        buttonX.setOnClickListener {
            ansLen = answer.length - 1
            if (answer.length == 1) {
                answer = ""
            } else {
                temp = ""
                ansSlice = ""
                for (item in answer.indices) {
                    if (item != ansLen) {
                        ansSlice = answer[item].toString()
                        temp = "$temp$ansSlice"
                    }
                }
                answer = temp
            }
            answerInput.setText(answer)
        }
        submit.setOnClickListener {
            //Toast.makeText(this.applicationContext, diff1.toString(), Toast.LENGTH_SHORT).show()
            //println(diff1)
            //(diff2)

            answer = answerInput.text.toString()
            ansLen = answer.length
            if (ansLen > 0) {
                var answerTemp = answer.toLong()

                //If answer is correct
                if (answerTemp == eval) {
                    response.setText("Correct!")
                    //    response.view.setBackgroundColor(android.graphics.Color.GREEN)
                    response.setGravity(Gravity.CENTER_VERTICAL, 0, 0);response.show()
                    next.isEnabled = true

                    //If answer is false
                } else {
                    response.setText("False!")
                    //    response.view.setBackgroundColor(android.graphics.Color.RED)
                    response.setGravity(Gravity.CENTER_VERTICAL, 0, 0);response.show()
                }
            }

        }
        next.setOnClickListener {
            next.isEnabled = false
            response.cancel()
            answer = ""
            answerInput.setText("")
            generateQuestion() //next question
        }
    }

    //Behind the scenes math generation
    fun generateQuestion() {
        //question = "test\ntest\ntest\ntest"
        question = ""
        eval = 0
        //sequence generation

        when {
            modeSelected2 == "plus" -> {
                var h = 1
                while (h <= diff2) {
                    numberGen(diff1)
                    //diff2 and operation setup
                    eval += numGen.toLong()
                    question = "$question\n$numGen"
                    h += 1
                }
            }
            modeSelected2 == "plusMinus" -> {
                var h = 1
                //This will be the first number, which I always want to be positive
                numberGen(diff1)
                eval += abs(numGen.toLong())
                numGen = abs(numGen.toLong()).toString()
                question = "$question\n$numGen"
                h += 1

                while (h <= diff2) {
                    numberGen(diff1)
                    // prevent answering going into negative
                    if (eval > 0) {
                        if (eval + numGen.toLong() <= 0) {
                            //repeat loop but do not increment h
                        } else {
                            eval += numGen.toLong()
                            question = "$question\n$numGen"
                            h += 1
                        }
                    }
                }
            }
            modeSelected2 == "multi" -> {
                numberGen(diff1)
                eval = numGen.toLong()
                question = "$numGen"
                numberGen(diff2)
                eval = eval*numGen.toLong()
                question = "$question x $numGen"
            }
            modeSelected2 == "div" -> {
                numberGen(diff1)
                eval = numGen.toLong()
                numberGen(diff2)
                eval = eval*numGen.toLong()
                question = "$eval / $numGen"
                eval = eval/numGen.toLong()
            }
        }
        questionDisplay.setText(question)
    }

    //Generate x figures
    fun numberGen(figure: Int){
        //random number generation (diff1)
        numGen = ""
        var i = 1
        var qTemp1 = Random()
        var qTemp2 = ""
        while (i <= figure) {

            if (i == 1) {
                qTemp2 = (qTemp1.nextInt(9) + 1).toString()
            } else {
                qTemp2 = qTemp1.nextInt(10).toString()
            }
            numGen = "$numGen$qTemp2"
            i += 1
            //return numGen (there's probably a better way to "return" the results of the function as opposed to using a variable right?
        }
        //For plusMinus mode, generate positive or negative
        if (modeSelected2 == "plusMinus") {
            qTemp2 = qTemp1.nextInt(2).toString()
            if (qTemp2 == "1") {
                numGen = "-$numGen"
            }
        }

    }
}




