package com.stalkerfish.thecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null   // variable to handle the top TextView, which shows the input of the user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     tvInput = findViewById(R.id.tvInput)   // connects the variable to the actual UI element

    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)  // append the text in the button pressed to tvInput
    }

    fun onClear(view: View){    // This give functionality to the CLR button, inserting an empty string to tvInput
        tvInput?.text = ""
    }
}