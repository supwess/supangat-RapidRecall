package com.example.supangat_rapidrecall

import androidx.compose.runtime.mutableStateListOf
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * The Attempt class contains the information needed about an attempt for logging.
 * All parameters except `attemptFinished` are defaulted to empty string.
 *
 * @param attemptFinished true if user finishes attempt, false otherwise (e.g. user leaving mid-game)
 * @param userInput: the number guess the player made
 * @param targetSequence: the generated (target) number
 */
class Attempt(
    val attemptFinished: Boolean,
    val userInput: String = "",
    val targetSequence: String = "",
    val timeformatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy 'at' HH:mm"), // reference : https://www.baeldung.com/kotlin/current-date-time
    val currenttimestamp: String = LocalDateTime.now().format(timeformatter)
) {
    fun isAnswerCorrect(): Boolean {
        return userInput.isNotEmpty() && targetSequence.isNotEmpty() && userInput == targetSequence
    }

    fun getNumberOfDigitsSelected(): Int {
        return targetSequence.length
    }

    fun isFinishedAttempt(): Boolean {
        return attemptFinished
    }
}

/**
 * The AttemptHistory class is a representation of all the attempts (stored in an ArrayList).
 * Functions have been implemented to get the total and correct number of attempts as well as accuracy.
 */
class AttemptHistory() : Iterable<Attempt> {
    val attemptList = mutableStateListOf<Attempt>()

    fun add(attempt: Attempt) {
        attemptList.add(attempt)
    }

    fun getTotalAttempts(): Int {
        return attemptList.size
    }

    fun getCorrectAttempts(): Int {
        var count: Int = 0
        for (attempt in attemptList) {
            if (attempt.isAnswerCorrect()) {
                count++
            }
        }
        return count
    }

    fun getAccuracy(): Float {
        val totalAttempts = getTotalAttempts()
        val correctAttempts = getCorrectAttempts()
        val accuracy: Float = if (totalAttempts <= 0) {
            0F;
        } else {
            (correctAttempts.toFloat() / totalAttempts)*100
        }
        return accuracy
    }

    override fun iterator(): Iterator<Attempt> {
        return attemptList.iterator()
    }
}
