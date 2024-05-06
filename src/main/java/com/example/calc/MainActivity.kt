package com.example.calc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.mariuszgromada.math.mxparser.Expression
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        textViewResult = findViewById(R.id.textViewResult)

        // Находим все кнопки калькулятора
        val buttons = arrayOf(
            findViewById<Button>(R.id.button0),
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9),
            findViewById<Button>(R.id.buttonPlus),
            findViewById<Button>(R.id.buttonMinus),
            findViewById<Button>(R.id.buttonMultiply),
            findViewById<Button>(R.id.buttonDivide),
            findViewById<Button>(R.id.buttonEquals),
            findViewById<Button>(R.id.buttonClear),
            findViewById<Button>(R.id.buttonDot)
        )

        // Устанавливаем обработчик нажатия на каждую кнопку
        buttons.forEach { button ->
            button.setOnClickListener {
                var currentText = textViewResult.text.toString()
                val buttonText = button.text.toString()
                if (buttonText == "C") {
                    // Если кнопка "C" нажата, очищаем TextView
                    textViewResult.text = ""
                } else if (buttonText == "=") {
                    // Если кнопка "=" нажата, вычисляем выражение
                    try {
                        val result = Expression(textViewResult.text.toString()).calculate()
                        textViewResult.text = result.toString()

                        // Запускаем новую активити с результатом
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("result", result.toString())
                        startActivity(intent)

                    } catch (e: Exception) {
                        textViewResult.text = "Error"
                    }

                } else {
                    if (currentText == "0") {
                        currentText = ""
                    }
                    textViewResult.text = currentText + buttonText
                }
            }
        }

    }

}
