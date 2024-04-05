package com.st10445996.history_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGen = findViewById<Button>(R.id.buttonGen)
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        val editAgeText = findViewById<EditText>(R.id.editAgeText)
        val textView = findViewById<TextView>(R.id.textView)

        buttonGen.setOnClickListener {
            val ageInput = editAgeText.text.toString()
            if (ageInput.isEmpty()) {
                textView.text = "Please enter your age"
                return@setOnClickListener
            }
            val age = ageInput.toIntOrNull()
            when {
                age == null -> textView.text = "Invalid age format. Please enter a whole number."
                age !in 20..100 -> textView.text = "Age must be between 20 and 100 years old."
                else -> {
                    val matchedFigures = getMatchedFigures(age)
                    if (matchedFigures.isNotEmpty()) {
                        textView.text = "Famous figures who died at  $age: ${matchedFigures.joinToString(", ")}"
                    } else {
                        textView.text = "No famous figures found at this age $age"
                    }
                }
            }
        }

        buttonClear.setOnClickListener {
            textView.text = ""
        }
    }

    private fun getMatchedFigures(age: Int): List<String> {
        val figures = mapOf(
            "Albert Einstein" to 76,
            "Napoleon Bonaparte" to 51,
            "Muhammad Ali" to 74,
            "Christopher Columbus" to 55,
            "Alexander the Great" to 32,
            "Ludwig van Beethoven" to 56,
            "George Washington" to 63,
            "Thomas Jefferson" to 87,
            "Pelé" to 82,
            "Diego Maradona" to 60

            // Add more figures and their ages
        )
        return figures.filterValues { it == age }.keys.toList()
    }
}