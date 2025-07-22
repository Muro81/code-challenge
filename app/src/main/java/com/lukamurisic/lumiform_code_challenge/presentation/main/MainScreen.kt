package com.lukamurisic.lumiform_code_challenge.presentation.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lukamurisic.lumiform_code_challenge.core.utils.observeWithLifecycle
import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseContentView
import com.lukamurisic.lumiform_code_challenge.presentation.main.components.EmptyContent
import com.lukamurisic.lumiform_code_challenge.presentation.main.components.PageItems

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    showSnackBar: (String) -> Unit
) {
    val state by viewModel.viewStateFlow.collectAsState()

    viewModel.snackBarChannel.observeWithLifecycle {
        showSnackBar(it)
    }

    BaseContentView(
        viewState = state
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if(state.pages.isEmpty()){
                item {
                    EmptyContent()
                }
            }
            items(state.pages) { page ->
                Text(
                    text = page.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                PageItems(
                    items = page.items,
                    currentFontSize = 18,
                    onQuestionClick = { imagePath ->
                        viewModel.onEvent(MainEvent.OnImageClick(imagePath))
                    }
                )
            }
        }
    }
}