package com.learning.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()                ) {
    Column(modifier = modifier) {
        StatefulCounter()

        WellnessTasksList(
            list = wellnessViewModel.tasks,

            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task,checked)
            },
            onCloseTask = { task -> wellnessViewModel.remove(task) })
    }
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text(text = "You've had $count glasses of water.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one.")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count: MutableState<Int> = rememberSaveable{mutableStateOf(0)}
    StatelessCounter(count.value, { count.value++ }, modifier)
}



