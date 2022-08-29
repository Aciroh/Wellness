package com.learning.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    WaterCounter(modifier)
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    var count: MutableState<Int> = rememberSaveable{mutableStateOf(0)}
    Column {
        if(count.value > 0){
            var showTask: MutableState<Boolean> = rememberSaveable{mutableStateOf(true)}
            Text(
                text = "You've had ${count.value} glasses of water.",
                modifier = modifier.padding(16.dp)
            )

            if(showTask.value) {
                WellnessTaskItem("Task1", onClose = { showTask.value = false } )
            }
        }
        Row(Modifier.padding(top = 8.dp)){
            Button(onClick = { count.value++ } , enabled = count.value < 10) {
                Text("Add one.")
            }
            Button(onClick = { count.value = 0 }, Modifier.padding(start = 8.dp)) {
                Text("Clear water count")
            }
        }
    }
}

@Composable
fun StatelessCounter(count: Int, showTask: Boolean,  onIncrement: () -> Unit, onChange: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text(text = "You've had $count glasses of water.")
            if(showTask) {
                WellnessTaskItem(taskName = "Run 1 kilometers.", onClose = onChange)
            }
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one.")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count: MutableState<Int> = rememberSaveable{mutableStateOf(0)}

}

@Composable
fun WellnessTaskItem(
    taskName: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
   Row(
       modifier = modifier,
       verticalAlignment = Alignment.CenterVertically
   )  {
       Text(
           modifier = Modifier
               .weight(1f)
               .padding(start = 16.dp),
           text = taskName
       )
       IconButton(onClick = onClose) {
           Icon(Icons.Filled.Close, contentDescription = "Close")
       }
   }
}