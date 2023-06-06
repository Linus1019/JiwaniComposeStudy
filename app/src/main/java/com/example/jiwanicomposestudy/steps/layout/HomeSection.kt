package com.example.jiwanicomposestudy.steps.layout

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiwanicomposestudy.R
import com.example.jiwanicomposestudy.ui.theme.JiwaniComposeStudyTheme

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        Text(stringResource(id = title).uppercase(),
            modifier = Modifier
                .paddingFromBaseline(40.dp, 8.dp)
                .padding(16.dp),
            style = MaterialTheme.typography.h4)
        content()
    }
}

@Preview
@Composable
fun HomeSectionPreview() {
    JiwaniComposeStudyTheme() {
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}