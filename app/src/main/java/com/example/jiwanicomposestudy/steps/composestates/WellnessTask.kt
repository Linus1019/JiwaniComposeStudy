package com.example.jiwanicomposestudy.steps.composestates

import androidx.compose.runtime.*

class WellnessTask(
    val id: Int,
    val label: String,
    initialChecked: Boolean = false
) {
    var checked: Boolean by mutableStateOf(initialChecked)
}