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
 * SummaryScreen shows all the statistics for the user's attempts, that is
 * total attempts, correct attempts, and their accuracy (correct / total).
 */
@Composable
fun SummaryScreen(
    attemptHistory: AttemptHistory,
    onBackButton: () -> Unit
) {
    Box(modifier = Modifier.padding(24.dp, top = 36.dp)) {
        Button(onClick = { onBackButton() }) {
            Text("Back")
        }
    }
    Column(
        modifier = Modifier
            .padding(top = 110.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Summary Stats",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Total Attempts",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
        )
        Text(
            text = attemptHistory.getTotalAttempts().toString()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Correct Attempts",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
        )
        Text(
            text = attemptHistory.getCorrectAttempts().toString()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Accuracy",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
        )
        Text(
            text = "%.2f%%".format(attemptHistory.getAccuracy())
        )
    }

}