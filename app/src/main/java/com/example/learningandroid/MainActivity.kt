package com.example.learningandroid

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningandroid.ui.theme.GreetingsTheme
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingsTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        GreetingText(
                            name = "Hazel",
                            //modifier = Modifier.padding(innerPadding)
                            modifier = Modifier.padding(8.dp)
                        )
                    }
//                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GreetingText(name: String, modifier: Modifier = Modifier) {
    val greeting = determineDaytime()
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // Delay to simulate loading or some condition
        delay(1000) // Adjust delay as needed
        visible = true // Set visibility to true after delay
    }

    Box(
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Good $greeting, $name!",
            fontSize = 90.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(20.dp)
                .padding(bottom = 30.dp)
                .align(Alignment.Center)
        )

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 3000)),
            modifier = modifier
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = "It's a beautiful day...",
                fontSize = 25.sp,
                fontStyle = FontStyle.Italic,
                modifier = modifier
                    .padding(bottom = 130.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun determineDaytime() : String {
    val currentHour =
        Integer.parseInt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")))
    val greeting = if (currentHour in 4..10) {
        "morning"
    } else if (currentHour in 11..13) {
        "day"
    } else if (currentHour in 14..17) {
        "afternoon"
    } else {
        "evening"
    }

    return greeting
}

//Preview Only
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, name = "GreetingsPreviewScreen")
@Composable
fun GreetingCardPreview() {
    GreetingsTheme {
        GreetingText(name = "Hazel")
    }
}