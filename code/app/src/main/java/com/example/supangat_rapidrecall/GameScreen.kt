package com.example.supangat_rapidrecall

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

/**
 * The GameScreen comes after the SelectDigitsScreen. This composable function generates the number
 * given the selected digits, and shows each digit one by one. At the end, the user will input their
 * guess on this screen.
 */
@Composable
fun GameScreen(
    digitsSelected: Int,
    onSubmitClick: (Attempt) -> Unit,
    onExitButton: (Attempt) -> Unit
) {
    val numberString = remember { generateNumber(digitsSelected) }

    var digitShown by remember { mutableStateOf("") } // how many digits

    var rememberingStage by remember { mutableStateOf(true) } // true when app is showing numbers, false when user is guessing
    var playerGuess by remember { mutableStateOf("") } // player's guess string

    LaunchedEffect(numberString) {
        for (digit in numberString) {
            digitShown = digit.toString()
            delay(500.milliseconds)
            digitShown = ""
            delay(200.milliseconds)
        }
        rememberingStage = false
    }

    Box(modifier = Modifier.padding(24.dp, top = 36.dp)) {
        Button(
            onClick = {
                onExitButton(
                    Attempt(
                        attemptFinished = false,
                        targetSequence = numberString
                    )
                )
            }
        ) {
            Text("Exit Attempt")
        }
    }

    if (rememberingStage) { // remembering digits stage
        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 170.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Remember\nthese digits!",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 38.sp
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = digitShown,
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 38.sp
            )
        }
    } else { // guessing stage
        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 170.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "What is the\nnumber?",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 38.sp
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(top = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = playerGuess,
                    onValueChange = { playerGuess = it },
                    label = { Text("Guess") }
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = {
                        onSubmitClick(
                            Attempt(
                                attemptFinished = true,
                                userInput = playerGuess,
                                targetSequence = numberString
                            )
                        )
                    },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(
                        text = "Submit",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

fun generateNumber(digitsSelected: Int): String {
    val numArray = ArrayList<String>()
    for (i in 0 until digitsSelected) {
        val digit = Random.nextInt(0, 10) // generate random digit from 0 to 9
        numArray.add(digit.toString())
    }
    return numArray.joinToString("")
}

