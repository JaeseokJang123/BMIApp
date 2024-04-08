package com.example.bmiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmiapp.R.drawable.background_image
import com.example.bmiapp.ui.theme.BMIAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMIAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMIApp()
                }
            }
        }
    }
}

@Composable
fun BMIApp() {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf("") }
    var bodyType by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Text(
            "BMI Calculator",
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        )

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val heightValue = height.toFloatOrNull()?.div(100) ?: 0f
            val weightValue = weight.toFloatOrNull() ?: 0f

            if (heightValue > 0 && weightValue > 0) {
                val bmiValue = weightValue / (heightValue * heightValue)
                bmi = String.format("%.2f", bmiValue)


                bodyType = when {
                    bmiValue < 18.5 -> "저체중"
                    bmiValue < 23 -> "정상"
                    bmiValue < 25 -> "과체중"
                    bmiValue < 30 -> "비만"
                    else -> "고도비만"
                }
            } else {
                bmi = ""
                bodyType = ""
            }
        },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.darkgreen))
        ) {
            Text(text="Calculate BMI",
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                ))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (bmi.isNotEmpty()) {
            Text(
                text = "BMI: $bmi\n체형: $bodyType",
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BMIAppPreview() {
    BMIApp()
}