package com.example.supangat_rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
 * AttemptLogScreen shows a log of all the attempts the user have made in the current session.
 * Each attempt contains information about the timestamp, target sequence, user guess,
 * digits selected, and whether the user guess correctly or not.
 */
@Composable
fun AttemptLogScreen(
    attemptHistory: AttemptHistory,
    onBackButton: () -> Unit
) {
    Box(modifier = Modifier.padding(24.dp, top = 36.dp)) {
        Button(onClick = { onBackButton() }) {
            Text("Back")
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Session Attempt Logs",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 15.dp)
            )
        }
        HorizontalDivider(modifier = Modifier.padding(20.dp))
        LazyColumn(
            modifier = Modifier
        ) {
            itemsIndexed(attemptHistory.attemptList) { index, attempt ->
                Column(
                    modifier = Modifier.padding(horizontal = 30.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Attempt ${index + 1}", fontWeight = FontWeight.Bold)
                        if (!attempt.isFinishedAttempt()) {
                            Text(
                                text = "Did Not Finish",
                                color = Color(0xFFFFAE25),
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            if (attempt.isAnswerCorrect()) {
                                Text(
                                    text = "Correct",
                                    color = Color(0xFF32CD32),
                                    fontWeight = FontWeight.Bold
                                )
                            } else {
                                Text(
                                    text = "Incorrect",
                                    color = Color(0xFFDC143C),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Text(
                        text = attempt.currenttimestamp,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Digits Selected:")
                        Text("${attempt.getNumberOfDigitsSelected()}")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Number Given:")
                        Text(attempt.targetSequence)
                    }
                    if (attempt.isFinishedAttempt()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Your Guess:")
                            Text(attempt.userInput)
                        }
                    }

                }
                HorizontalDivider(modifier = Modifier.padding(20.dp))
            }
        }
    }
}