package com.example.supangat_rapidrecall

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * ResultScreen shows the result of the last completed attempt (when the user clicks the submit
 * button on the GameScreen). It displays information of correctness, the number generated, and
 * their guessed number.
 */
@Composable
fun ResultScreen(attempt: Attempt, onMainMenuClick: () -> Unit) {
    val actualNumber = attempt.targetSequence
    val guessedNumber = attempt.userInput

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 180.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "You are",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 38.sp
            )
            if (actualNumber == guessedNumber) {
                Text(
                    text = "RIGHT!",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 38.sp,
                    color = Color(0xFF32CD32)
                )
            } else {
                Text(
                    text = "WRONG!",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 38.sp,
                    color = Color(0xFFDC143C)
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Given Number",
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = actualNumber,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )

            HorizontalDivider(modifier = Modifier.padding(horizontal = 100.dp, vertical = 10.dp))

            Text(
                text = guessedNumber,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Your Guess",
                modifier = Modifier,
                textAlign = TextAlign.Center,
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 190.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Button(
            onClick = { onMainMenuClick() }
        ) {
            Text("Go to Main Menu")
        }

    }
}