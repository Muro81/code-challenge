package com.lukamurisic.lumiform_code_challenge.presentation.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BaseContentView(
    modifier: Modifier = Modifier,
    viewState: BaseViewState,
    applySystemBarsPadding: Boolean = true,
    topBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    content: @Composable (BaseViewState) -> Unit
) {

    val customModifier = remember {
        if (applySystemBarsPadding) {
            modifier
                .systemBarsPadding()
                .imePadding()
                .fillMaxSize()
        } else {
            modifier
                .imePadding()
                .fillMaxSize()
        }
    }

    Scaffold(
        modifier = customModifier,
        topBar = topBar,
        floatingActionButton = fab
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content(viewState)




            if (viewState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        alignment = Alignment.Center
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}