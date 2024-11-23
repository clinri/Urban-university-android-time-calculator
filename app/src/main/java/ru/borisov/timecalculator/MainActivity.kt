package ru.borisov.timecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var firstTimeEditText: EditText
    lateinit var secondTimeEditText: EditText
    lateinit var plusButton: Button
    lateinit var minusButton: Button
    lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstTimeEditText = findViewById(R.id.firstTimeET)
        secondTimeEditText = findViewById(R.id.secondTimeET)

        plusButton = findViewById(R.id.plusBTN)
        minusButton = findViewById(R.id.minusBTN)

        resultTextView = findViewById(R.id.resultTV)

        plusButton.setOnClickListener(this)
        minusButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (firstTimeEditText.text.isEmpty() || secondTimeEditText.text.isEmpty()) return

        val timeOperation =
            TimeOperation(firstTimeEditText.text.toString(), secondTimeEditText.text.toString())

        val result = when (v?.id) {
            R.id.plusBTN -> timeOperation.plus()
            R.id.minusBTN -> timeOperation.minus()
            else -> ""
        }

        resultTextView.text = result
    }
}