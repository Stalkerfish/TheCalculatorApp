package com.stalkerfish.thecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null   // variable to handle the top TextView, which shows the input of the user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)   // connects the variable to the actual UI element

    }

    private var lastWasNumeric: Boolean = true
    private var lastWasDot: Boolean = false

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)  // append the text in the button pressed to tvInput
        lastWasNumeric = true
        lastWasDot = false
    }

    fun onClear(view: View) {    // This give functionality to the CLR button, inserting an empty string to tvInput
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if (lastWasNumeric && !lastWasDot) {        //  double checks if the last digit is a number and it is not a dot
            if ((tvInput?.text)?.contains(".") == false) {      //  triple check if in the whole string has a dot
                tvInput?.append(".")
                lastWasDot = true
                lastWasNumeric = false
            }
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let {    //  checks if tvInput is null

            if(lastWasNumeric && !isOperatorAdded(it.toString())){      //  only executes if the last digit is a number and
                tvInput?.append((view as Button).text)                  //  there is not a operator sign in the string
                lastWasNumeric = false
                lastWasDot = false
            }
        }
    }

    fun onEqual(view: View){
        if (lastWasNumeric){    // checks if the last digit is a number, so it can perform the operation

            var tvValue = tvInput?.text.toString()  //  convert the value of the TextView to an actual string,
                                                    // see getText() method from TextView class for further information
            try{
                val splitValue = tvValue.split("-") //  Split the string using the operator sign as a delimiter

                var one = splitValue[0] //  the split string comes in arrays
                var two = splitValue[1]

                tvInput?.text = (one.toDouble() - two.toDouble()).toString()    // convert the strings to Double, do the operation,
                                                                                // convert back to strings, and set to tvInput, all at the same time

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun isOperatorAdded(value: String) : Boolean {

        return if(value.startsWith("-")){   //  makes an exception for the minus sign,
            false                                 // allowing us to do math with negative numbers
        }

        else {
            value.contains("/")     //  return `true` if any of the following statements are satisfied
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}