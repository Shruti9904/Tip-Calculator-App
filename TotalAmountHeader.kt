package com.example.tipcalculatorapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DisplayOutput(userAmount:String,userTipPercentage: Float,personCounter:Int){
    Card (
        modifier= Modifier
            .fillMaxHeight(0.15f),
        shape = RoundedCornerShape(12.dp)
    ){
        Column (modifier= Modifier
            .fillMaxSize()
            .background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Text(text = "Total Per Person",
                fontWeight = FontWeight.Bold,
                style= MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "$${getTotalAmount(userAmount,userTipPercentage,personCounter)}",
                fontWeight = FontWeight.Bold,
                style= MaterialTheme.typography.headlineMedium)
        }
    }
}

fun getTotalAmount(userAmount:String,userTipPercentage: Float,personCounter:Int):String{
    return when{
        userAmount.isEmpty()->{
            "0"
        }
        else->{
            val tipAmount= (getTipAmount(userAmount,userTipPercentage)).toDouble()
            val result=(tipAmount+userAmount.toDouble())/personCounter
            val outputAmt=Math.round(result*100.0)/100.0
            outputAmt.toString()
        }
    }
}
