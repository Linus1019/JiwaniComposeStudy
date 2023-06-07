package com.example.jiwanicomposestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.primarySurface
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.jiwanicomposestudy.steps.layout.MySoothepp
import com.example.jiwanicomposestudy.steps.theme.JetnewsTheme
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // composable 함수를 호출하여 레이아웃을 표현
        setContent {
            JetnewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primarySurface
                ) {
                    //MessageCard(msg = Message("jiwan's", "study"))
                    MySoothepp()
                }
            }
        }
    }
}
