package com.example.tipcalculatorapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserInput(amount:String,splitNumber:Int,tipPercentage:Float,
              splitChange:(Boolean)->Unit,amountChange:(String)->Unit,
              sliderChange:(Float)->Unit){

    val tipPercentageRound=Math.round(tipPercentage*100.0)/100.0
    val keyboardController= LocalSoftwareKeyboardController.current
    Surface(
        modifier= Modifier.wrapContentHeight(),
        tonalElevation = 12.dp,
        shadowElevation = 12.dp,
        color = Color.White,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            OutlinedTextField(value = amount, onValueChange = {
                amountChange(it)
            },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                placeholder = {
                    Text(text = "Enter Value", fontSize = 22.sp)
                },
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

//            Spacer(modifier = Modifier.height(8.dp))
            if(amount.isNotBlank()){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "Split", fontSize = 22.sp,modifier=Modifier.padding(start = 6.dp))
                    Spacer(modifier = Modifier.fillMaxWidth(0.5f))
                    CustomButton(splitChange,splitNumber)
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    Text(text = "Tip",fontSize = 22.sp,modifier=Modifier.padding(start = 6.dp))
                    Spacer(modifier=Modifier.fillMaxWidth(0.65f))
                    Text(text = "$${getTipAmount(amount,tipPercentage)}",fontSize = 22.sp)
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "${tipPercentageRound}%",fontSize = 22.sp)
                }

                Slider(value =tipPercentage , onValueChange = {
                    sliderChange(it)
                },
                    modifier=Modifier.padding(8.dp),
                    valueRange = 0f..100f,
                    steps = 5)

            }

        }
    }
}

fun getTipAmount(userAmount:String,userTipPercentage: Float):String{
    return when{
        userAmount.isEmpty()-> {
            "0"
        }
        else->{
            val amount=userAmount.toFloat()
            val tipAmt=(amount*userTipPercentage/100)
            (Math.round(tipAmt*100.0)/100.0).toString()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomButton(splitChange:(Boolean)->Unit,splitNumber: Int){
    val isIncrement by remember {
        mutableStateOf(false)
    }
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Card(onClick = {
                       splitChange(isIncrement)
        }, shape = CircleShape) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription =null,
                modifier=Modifier.background(Color.White)
            )
        }
        Text(text = "$splitNumber", modifier = Modifier.padding(horizontal = 6.dp), fontSize = 22.sp)
        Card(onClick = {
                       splitChange(!isIncrement)
                       }, shape = CircleShape) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription =null,
                modifier=Modifier.background(Color.White)
            )
        }
    }

}
