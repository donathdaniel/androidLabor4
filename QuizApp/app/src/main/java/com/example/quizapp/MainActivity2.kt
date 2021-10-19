package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val msg = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById<TextView>(R.id.textView).apply {
            text = msg
        }


    }
}