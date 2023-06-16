package com.example.jiwanicomposestudy.steps.composestates

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()

        /*val tasks = getWellnessTasks().toTypedArray()*/
        /*val list = remember { mutableStateListOf(*tasks) }*/

        WellnessTasksList(list = viewModel.tasks, onCheckedTask = { task, checked -> viewModel.changeTaskChecked(task, checked) },onCloseTask = { task -> viewModel.remove(task) }, )
    }
}

fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
