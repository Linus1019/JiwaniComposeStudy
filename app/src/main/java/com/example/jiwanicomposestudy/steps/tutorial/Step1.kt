package com.example.jiwanicomposestudy.steps.tutorial

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiwanicomposestudy.R
import com.example.jiwanicomposestudy.smaples.MessageData
import com.example.jiwanicomposestudy.ui.theme.JiwaniComposeStudyTheme

class Step1 {
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
            Conversation(messages = MessageData.conversationSample)
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
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // remember로 현재 상태를 저장하고 mutableStateOf로 변경된 상태를 추적
            // 하위 요소중에서 remember의 상태를 사용하는 요소가 변경됨을 감지하면 자동으로 업데이트됨
            // 이를 재구성(recomposition)이라고 함
            var isExpanded by remember { mutableStateOf(false) }
            // 요소의 상태 (지금은 isExpanded)가 변경되면 색상이 변경되는 animation을 생성
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )

            // 클릭을 하면 isExpanded 값을 변경
            Column(modifier =  Modifier.clickable { isExpanded = isExpanded.not() }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(4.dp))

                // 선언한 애니메이션은 Surface에서 사용
                // 크기가 변경되는 애니메이션은 modifier를 사용... 뭔가 일관성이 없는거 같음...
                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp, color = surfaceColor, modifier = Modifier.animateContentSize().padding(4.dp)) {
                    Text(
                        text = msg.body,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(all = 4.dp),
                        // 클릭으로 isExpanded가 변경되고 mutableStateOf에서 변경된 상태를 감지하고 재구성함
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1
                    )
                }
            }
        }
    }

//endregion
}