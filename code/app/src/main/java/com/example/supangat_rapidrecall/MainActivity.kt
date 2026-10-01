package com.example.supangat_rapidrecall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supangat_rapidrecall.ui.theme.SupangatRapidRecallTheme

/**
 * MainActivity handles all the screen changes when buttons are clicked
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SupangatRapidRecallTheme {
                var currentScreen by remember { mutableStateOf("Main") } // variable to determine which screen to show

                // game variables
                var digitsSelected by remember { mutableIntStateOf(1) } // default value
                var currentAttempt by remember { mutableStateOf(Attempt(false)) } // default value

                // logging variables
                val attemptsHistory = remember { AttemptHistory() }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        "Main" -> MainScreen (
                            onPlayGameClick = {
                                currentScreen = "SelectDigits"
                            },
                            onSummaryClick = {
                                currentScreen = "Summary"
                            },
                            onAttemptLogClick = {
                                currentScreen = "AttemptLog"
                            }
                        )
                        "SelectDigits" -> SelectNumberOfDigitsScreen (
                            onBackButton = {
                                currentScreen = "Main"
                            },
                            onPlayClick = {digitsReturned ->
                                digitsSelected = digitsReturned
                                currentScreen = "Game"
                            }
                        )
                        "Game" -> GameScreen (
                            digitsSelected = digitsSelected,
                            onSubmitClick = { attempt ->
                                currentAttempt = attempt
                                attemptsHistory.add(attempt)
                                currentScreen = "Result"
                            },
                            onExitButton = { attempt ->
                                currentAttempt = attempt
                                attemptsHistory.add(attempt)
                                currentScreen = "Main"
                            }
                        )
                        "Result" -> ResultScreen (
                            attempt = currentAttempt,
                            onMainMenuClick = { currentScreen = "Main" }
                        )
                        "Summary" -> SummaryScreen (
                            attemptHistory = attemptsHistory,
                            onBackButton = { currentScreen = "Main" }
                        )
                        "AttemptLog" -> AttemptLogScreen(
                            attemptHistory = attemptsHistory,
                            onBackButton = { currentScreen = "Main" }
                        )
                        else ->  MainScreen (
                            onPlayGameClick = {
                                currentScreen = "SelectDigits"
                            },
                            onSummaryClick = {
                                currentScreen = "Summary"
                            },
                            onAttemptLogClick = {
                                currentScreen = "AttemptLog"
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    onPlayGameClick: () -> Unit,
    onSummaryClick: () -> Unit,
    onAttemptLogClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Rapid Recall",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "CCID: supangat",
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.height(128.dp))

        Button(
            modifier = Modifier.width(200.dp),
            onClick = { onPlayGameClick() }
        ) {
            Text(text = "Play Game")
        }
        Button(
            modifier = Modifier.width(200.dp),
            onClick = { onSummaryClick() }
        ) {
            Text(text = "View Session Summary")
        }
        Button(
            modifier = Modifier.width(200.dp),
            onClick = { onAttemptLogClick() }
        ) {
            Text(text = "View Attempt Logs")
        }
    }
}