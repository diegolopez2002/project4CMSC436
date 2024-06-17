package com.example.project4

import android.content.Context
import android.content.SharedPreferences
import kotlin.random.Random

class Game(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("game_prefs", Context.MODE_PRIVATE)

    private var bestLevel: Int = prefs.getInt("best_level", 0)


    private var currentLevel: Int = bestLevel + 1

    var targetSequence: MutableList<String> = mutableListOf()


    var currentSequence: MutableList<String> = mutableListOf()


    private val colors = listOf("red", "green", "yellow", "blue")

    init {
        generateNewSequence()
    }

    private fun generateNewSequence() {
        currentSequence.clear()
        targetSequence.add(colors[Random.nextInt(colors.size)])
    }

    fun checkSequence(): Boolean {
        if (currentSequence == targetSequence) {
            currentLevel++
            targetSequence.add(colors[Random.nextInt(colors.size)])
            currentSequence.clear()
            if (currentLevel > bestLevel) {
                bestLevel = currentLevel
                saveBestLevel()
            }
            return true
        }
        return false
    }

    fun resetGame() {
        bestLevel = 0
        currentLevel = 1
        targetSequence.clear()
        currentSequence.clear()
        generateNewSequence()
        saveBestLevel()
    }

    private fun saveBestLevel() {
        with(prefs.edit()) {
            putInt("best_level", bestLevel)
            apply()
        }
    }
}
