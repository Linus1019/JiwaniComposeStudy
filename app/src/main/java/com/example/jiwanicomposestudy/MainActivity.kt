package com.example.jiwanicomposestudy

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiwanicomposestudy.smaples.SampleData
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
                    //MessageCard(msg = Message("jiwan's", "study"))
                    PreviewConversation()
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
//preview는 매개변수가 없는 함수에서만 사용이 가능하다.
@Preview(name = "Light Mode")
// @Preview 주석을 추가해서 테마를 다르게 표현도 할 수 있음
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
fun MessageCardPreviewed() {
    //MessageCard("Jiwan's study")
    JiwaniComposeStudyTheme {
        Surface {
            MessageCard(msg = Message("jiwan", "study"))
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
@Preview
fun PreviewConversation() {
    JiwaniComposeStudyTheme {
        Conversation(messages = SampleData.conversationSample)
    }
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
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp){
                Text(text = msg.body, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(all = 4.dp))
            }
        }
    }
}

//endregion
