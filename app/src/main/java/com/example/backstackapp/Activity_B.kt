package com.example.backstackapp

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi

class Activity_B: BaseActivity() {


    private lateinit var buttonA: Button
    private lateinit var buttonB: Button
    private lateinit var buttonC: Button
    private lateinit var buttonD: Button
    private lateinit var textViewInstanceValue: TextView
    private lateinit var textViewTaskInfo: TextView

    companion object {
        private var instanceCounter = 0
    }

    init {
        instanceCounter++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        buttonA = findViewById(R.id.buttonStartActivityA)
        buttonB = findViewById(R.id.buttonStartActivityB)
        buttonC = findViewById(R.id.buttonStartActivityC)
        buttonD = findViewById(R.id.buttonStartActivityD)
        textViewTaskInfo = findViewById(R.id.textViewTaskInfo)
        textViewInstanceValue = findViewById(R.id.textViewInstanceValue)
        textViewInstanceValue.append(" , Current instance: $instanceCounter")

        buttonA.setOnClickListener {
            startActivity(this, Activity_A::class.java)
        }

        buttonB.setOnClickListener {
            startActivity(this, Activity_B::class.java)
        }

        buttonC.setOnClickListener {
            startActivity(this, Activity_C::class.java)
        }

        buttonD.setOnClickListener {
            startActivity(this, Activity_D::class.java)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onResume() {
        super.onResume()
        textViewTaskInfo.text = getAppTaskState()
    }

}