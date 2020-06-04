package com.example.mentalmathpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val modeList = arrayListOf("plusBtn", "plusMinusBtn", "multiBtn", "divBtn")
    lateinit var seekBarProgress1: SeekBar
    lateinit var diffSelected1: TextView //to update UI
    lateinit var seekBarProgress2: SeekBar
    lateinit var diffSelected2: TextView //to update UI
    var modeSelected: String = ""
    var modeSelected2: String = ""
    var diff1: Int = 3
    var diff2: Int = 3

    //declare string building variables
    lateinit var a: String
    lateinit var b: String
    lateinit var c: String

    //Mode index
    //Addition = 0
    //AdditionSubtraction = 0
    //Multiplication = 1
    //Division = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusBtn.setOnClickListener {
            //Toast.makeText(applicationContext, "Test",Toast.LENGTH_SHORT).show()
            turnOffAllOther(
                "plusBtn", modeList) //the string passed onto the function needs to be dynamic
                modeSelected = "0"
                modeSelected2 = "plus"
                seekBar2.min= 2;seekBar2.max = 10
                updateDiff(modeSelected)
        }
        plusMinusBtn.setOnClickListener {
            turnOffAllOther("plusMinusBtn", modeList)
            modeSelected = "0"
            modeSelected2 = "plusMinus"
            seekBar2.min= 2;seekBar2.max = 10
            updateDiff(modeSelected)
        }
        multiBtn.setOnClickListener {
            turnOffAllOther("multiBtn", modeList)
            modeSelected = "1"
            modeSelected2 = "multi"
            seekBar2.min= 1;seekBar2.max = 9
            updateDiff(modeSelected)
        }
        divBtn.setOnClickListener {
            turnOffAllOther("divBtn", modeList)
            modeSelected = "1"
            modeSelected2 = "div"
            seekBar2.min= 1;seekBar2.max = 9
            updateDiff(modeSelected)
        }
        startBtn.setOnClickListener {

            val intent = Intent(this, Practice::class.java)
            intent.putExtra("diff1", diff1)
            intent.putExtra("diff2", diff2)
            intent.putExtra("modeSelected",modeSelected)
            intent.putExtra("modeSelected2",modeSelected2)
            startActivity(intent);
        }
        seekBarProgress1 = findViewById<SeekBar>(R.id.seekBar1)
        diffSelected1 = findViewById<TextView>(R.id.diff1)

        seekBarProgress1.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //diffSelected1.text = progress.toString()
                diff1 = progress
                if (modeSelected !="") {
                    updateDiff(modeSelected)
                } else{}
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        seekBarProgress2 = findViewById<SeekBar>(R.id.seekBar2)
        diffSelected2 = findViewById<TextView>(R.id.diff2)

        seekBarProgress2.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //diffSelected2.text = progress.toString()
                diff2 = progress
                if (modeSelected !="") {
                    updateDiff(modeSelected)
                } else{}
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
}

    fun turnOffAllOther(btn: String, inputArray: ArrayList<String>) {
        for (item in inputArray)
            if (item == btn) {
                var btnTarget = findViewById<ToggleButton>(resources.getIdentifier(item,"id", packageName))
                btnTarget.isChecked = true
            } else {
                    var btnTarget = findViewById<ToggleButton>(resources.getIdentifier(item,"id", packageName))
                    //println(btnTarget)
                    btnTarget.isChecked = false
            }
    }

        fun updateDiff(modeSelected: String) {
            //Toast.makeText(applicationContext, modeSelected,Toast.LENGTH_SHORT).show()
            startBtn.isEnabled = true
            when
                {
                    modeSelected == "0" -> {
                    a = seekBar1.progress.toString()
                    b = "figure numbers"
                    c = "$a $b"
                    diffSelected1.text = c
                    a = seekBar2.progress.toString()
                    b = "numbers per question"
                    c = "$a $b"
                    diffSelected2.text = c
                }
                modeSelected == "1" -> {
                    a = seekBar1.progress.toString()
                    b = "figure numbers"
                    c = "$a $b"
                    diffSelected1.text = c
                    a = seekBar2.progress.toString()
                    b = "figure numbers"
                    c = "by $a $b"
                    diffSelected2.text = c
                }
            }
        }
}


