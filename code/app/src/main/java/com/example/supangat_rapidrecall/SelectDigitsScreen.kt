package com.example.supangat_rapidrecall

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * The SelectNumberOfDigitsScreen allows the user to select the amount of digits they want to play
 * using +/- buttons. The range of allowed digits are 1-10 inclusive.
 */
@Composable
fun SelectNumberOfDigitsScreen(onBackButton: () -> Unit, onPlayClick: (Int) -> Unit) {
    var digits by remember { mutableIntStateOf(1) }

    Box(modifier = Modifier.padding(24.dp, top = 36.dp)) {
        Button(onClick = { onBackButton() }) {
            Text("Back")
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Select the number\nof digits to recall:",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 38.sp
        )
        Spacer(modifier = Modifier.height(128.dp))
        Text(
            text = digits.toString(),
            fontSize = 48.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (digits > 1) {
                        digits--
                    }
                },
                modifier = Modifier.width(100.dp)
            ) {
                Text(
                    text = "–",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = {
                    if (digits < 10) {
                        digits++
                    }
                },
                modifier = Modifier.width(100.dp)
            ) {
                Text(
                    text = "+",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(64.dp))
        Button(
            onClick = { onPlayClick(digits) },
            modifier = Modifier.width(128.dp).height(50.dp)
        ) {
            Text(
                text = "Play",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}