package com.example.jiwanicomposestudy.steps.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jiwanicomposestudy.ui.theme.JiwaniComposeStudyTheme

@Composable
fun MySoothepp() {
    JiwaniComposeStudyTheme {
        Scaffold(
            bottomBar = { BottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}