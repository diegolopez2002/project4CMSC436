package com.example.project4
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var game: Game

    private lateinit var buttonRed: Button
    private lateinit var buttonGreen: Button
    private lateinit var buttonYellow: Button
    private lateinit var buttonBlue: Button
    private lateinit var buttonReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = Game(this)

        buttonRed = findViewById(R.id.button_red)
        buttonGreen = findViewById(R.id.button_green)
        buttonYellow = findViewById(R.id.button_yellow)
        buttonBlue = findViewById(R.id.button_blue)
        buttonReset = findViewById(R.id.button_reset)

        showCurrentPattern()

        buttonRed.setOnClickListener { handleButtonClick("red") }
        buttonGreen.setOnClickListener { handleButtonClick("green") }
        buttonYellow.setOnClickListener { handleButtonClick("yellow") }
        buttonBlue.setOnClickListener { handleButtonClick("blue") }
        buttonReset.setOnClickListener { resetGame() }
    }

    private fun handleButtonClick(color: String) {
        game.currentSequence.add(color)
        if (!game.checkSequence()) {
            Toast.makeText(this, "You lost! Game Over.", Toast.LENGTH_LONG).show()
            finish()
        } else {
            if (game.currentSequence.size == game.targetSequence.size) {
                Toast.makeText(this, "Correct! Next level.", Toast.LENGTH_LONG).show()
                showCurrentPattern()
            }
        }
    }

    private fun showCurrentPattern() {
        Toast.makeText(this, game.targetSequence.joinToString("-"), Toast.LENGTH_LONG).show()
    }

    private fun resetGame() {
        game.resetGame()
        showCurrentPattern()
    }
}
