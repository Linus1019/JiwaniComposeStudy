package com.example.jiwanicomposestudy.steps.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiwanicomposestudy.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


enum class TabPage {
    Home, Work
}

@Composable
fun animateAsState() {
    var tabPage by remember { mutableStateOf(TabPage.Home) }
    val lazyListState = rememberLazyListState()

    var weatherLoading by remember { mutableStateOf(TabPage.Home) }
    var editMessageShown by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    suspend fun showEditMessage() {
        if (editMessageShown.not()) {
            editMessageShown = true
            delay(3000L)
            editMessageShown = false
        }
    }

    //val backgroundColor = if (tabPage == TabPage.Home) Purple100 else Green300
    val backgroundColor by animateColorAsState(if (tabPage == TabPage.Home) Purple100 else Green300)

    Scaffold(
        topBar = {
            HomeTabBar(
                backgroundColor = backgroundColor,
                tabPage = tabPage,
                onTabSelected = { tabPage = it }
            )
        },
        backgroundColor = backgroundColor,
        floatingActionButton = {
            HomeFloatingActionButton(
                extended = lazyListState.isScrollingUp(),
                onClick = {
                    coroutineScope.launch {
                        showEditMessage()
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            state = lazyListState,
            modifier = Modifier.padding(padding)
        ) {
            /*item { Header(title = stringResource(R.string.weather)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 2.dp
                ) {

                }
            }*/
        }

        EditMessage(shown = editMessageShown)
    }
}

@Composable
private fun EditMessage(shown: Boolean) {
    AnimatedVisibility(
        visible = shown,
        /*enter = slideInVertically(),
        exit = slideOutVertically()*/
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary,
            elevation = 4.dp
        ) {
            Text(
                text = stringResource(id = R.string.edit_message),
                modifier = Modifier.padding(16.dp)
            ) 
            
            
        }
    }
}

@Composable
fun Header(
    title: String
) {
    Text(text = title,
        modifier = Modifier.semantics { heading() },
        style = MaterialTheme.typography.h5
    )
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }

    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }.value
    }
}

@Composable
fun HomeTabBar(
    backgroundColor: Color,
    tabPage: TabPage,
    onTabSelected: (tabPage: TabPage) -> Unit
) {
    TabRow(
        selectedTabIndex = tabPage.ordinal,
        backgroundColor = backgroundColor,
        indicator = { tabPositions ->
            HomeTabIndicator(tabPositions, tabPage)
        }
    ) {
        HomeTab(
            icon = Icons.Default.Home,
            title = stringResource(id = R.string.home),
            onClick = { onTabSelected(TabPage.Home) }
        )

        HomeTab(
            icon = Icons.Default.AccountBox,
            title = stringResource(id = R.string.work),
            onClick = { onTabSelected(TabPage.Work) })
    }
}

@Composable
fun HomeTabIndicator(
    tabPositions: List<TabPosition>,
    tabPage: TabPage
) {
    val indicatorLeft = tabPositions[tabPage.ordinal].left
    val indicatorRight = tabPositions[tabPage.ordinal].right
    val backgroundColor = if (tabPage == TabPage.Home) Purple700 else Green800

    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(4.dp)
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, backgroundColor),
                RoundedCornerShape(4.dp)
            )
    )
}

@Composable
fun HomeTab(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title)
    }
}

@Preview
@Composable
fun PreviewAnimateAsState() {
    animateAsState()
}

@Composable
fun HomeFloatingActionButton(
    extended: Boolean,
    onClick: () -> Unit
) {
    FloatingActionButton(onClick = onClick) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )

            AnimatedVisibility(extended) {
                Text(
                    text = stringResource(id = R.string.edit),
                    modifier = Modifier.padding(start = 8.dp, top = 3.dp)
                )
            }
        }
    }
}

@Preview 
@Composable
private fun PreviewHomeFloatingActionButton() {
    HomeFloatingActionButton(extended = true) {
        
    }
}

@Preview
@Composable
private fun PreviewHomeTabBar() {
    HomeTabBar(
        backgroundColor = Purple100,
        tabPage = TabPage.Home,
        onTabSelected = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeTab() {
    Surface(
        color = Green800
    ) {
        HomeTab(
            icon = Icons.Default.Home,
            title = stringResource(id = R.string.home),
            onClick = {},
        )
    }
}
