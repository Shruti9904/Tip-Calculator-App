package com.example.tipcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculatorapp.ui.theme.TipCalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                TipCalculator()
            }
        }
    }
}

@Composable
fun TipCalculator(){
    var inputAmt by remember{ mutableStateOf("")}
    var outputAmt by remember{ mutableStateOf("0.0")}
    var splitNumber by remember { mutableStateOf(1) }
    var tipPercentage by remember { mutableStateOf(0f) }

    Column(modifier= Modifier
        .padding(12.dp)
        .fillMaxWidth())
    {
        DisplayOutput(inputAmt,tipPercentage,splitNumber)

        Spacer(modifier = Modifier.height(12.dp))

        UserInput(inputAmt,splitNumber,tipPercentage,
            splitChange = {
               if(it){
                   splitNumber+=1
               }else{
                   if(splitNumber!=1){
                       splitNumber-=1
                   }else{
                       splitNumber=1
                   }
               }
            },

            amountChange = {
                inputAmt=it
            },

            sliderChange = {
                tipPercentage=it
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview(){
    TipCalculator()
}