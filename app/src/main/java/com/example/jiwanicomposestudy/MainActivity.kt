package com.example.jiwanicomposestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiwanicomposestudy.ui.theme.JiwaniComposeStudyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // composable 함수를 호출하여 레이아웃을 표현
        setContent {
            JiwaniComposeStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

//region Text

@Composable
fun MessageCard(name: String) {
    Text(name)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JiwaniComposeStudyTheme {
        Greeting("Android")
    }
}
//endregion

@Composable
@Preview(showBackground = true) //preview는 매개변수가 없는 함수에서만 사용이 가능하다.
fun MessageCardPreviewed() {
    //MessageCard("Jiwan's study")
    MessageCard(Message("jiwan", "study"))
}

//region Layout + Image

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    // Row {} 수평으로 정렬
    // Box {} 같은 위치에 쌓임
    // Column {} 수직으로 정렬

    // Modifier : 각 요소들의 상태를 수정할 수 있다. 크기나 간격 등...
    Row(Modifier.padding (all = 8.dp)) {
        Image(
            painter = painterResource(
            id = R.drawable.linus),
            contentDescription = "profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.author)
            Spacer(modifier = Modifier.height(4.dp))
            Text(msg.body)
        }
    }
}

//endregion
