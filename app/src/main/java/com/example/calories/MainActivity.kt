package com.example.calories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.calories.ui.components.*
import com.example.calories.ui.theme.CaloriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaloriesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalorieApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalorieApp(modifier: Modifier = Modifier) {
    var weightInput by remember { mutableStateOf("") }
    val weight = weightInput.toIntOrNull() ?: 0
    var male by remember { mutableStateOf(true) }
    var intensity by remember { mutableStateOf(1.3f) }
    var result by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.padding(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Heading("Calories")
        WeightField(weightInput = weightInput, onValueChange = { weightInput = it })
        GenderChoices(male, setGenderMale = { male = it })
        IntensityList(onClick = { intensity = it })
        Text(
            text = result.toString(),
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )
        Calculation(
            male = male,
            weight = weight,
            intensity = intensity,
            setResult = { result = it }
        )
    }
}