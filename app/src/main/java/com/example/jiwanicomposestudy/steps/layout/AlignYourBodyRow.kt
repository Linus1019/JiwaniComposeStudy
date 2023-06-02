package com.example.jiwanicomposestudy.steps.layout

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiwanicomposestudy.smaples.SampleData

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(SampleData.alignYourBodyData) {
            AlignYourBodyElement(image = it.drawable, text = it.text)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAlignYourBodyRow() {
    AlignYourBodyRow()
}
