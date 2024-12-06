package ru.borisov.timecalculator

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var firstTimeEditText: EditText
    lateinit var secondTimeEditText: EditText
    lateinit var plusButton: Button
    lateinit var minusButton: Button
    lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar)
        toolbar.subtitle = "Версия 1"
        toolbar.setLogo(R.drawable.ic_time_calculator)

        firstTimeEditText = findViewById(R.id.firstTimeET)
        secondTimeEditText = findViewById(R.id.secondTimeET)

        plusButton = findViewById(R.id.plusBTN)
        minusButton = findViewById(R.id.minusBTN)

        resultTextView = findViewById(R.id.resultTV)

        plusButton.setOnClickListener(this)
        minusButton.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.resetMenuMain -> {
                firstTimeEditText.text.clear()
                secondTimeEditText.text.clear()
                resultTextView.apply {
                    text = "Результат"
                    setTextColor(getColor(R.color.black))
                }
                Toast.makeText(this, "Данные очищены", Toast.LENGTH_LONG).show()
            }

            R.id.exitMenuMain -> {
                Toast.makeText(this, "Приложение закрыто", Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
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

        resultTextView.apply {
            text = result
            setTextColor(getColor(R.color.red_dark))
        }
        Toast.makeText(this, "Результат: $result", Toast.LENGTH_LONG).show()
    }
}