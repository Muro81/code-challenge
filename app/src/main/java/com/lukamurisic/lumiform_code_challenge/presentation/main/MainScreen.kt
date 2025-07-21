package com.lukamurisic.lumiform_code_challenge.presentation.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    showSnackBar : (String) -> Unit
) {
    Text("MAIN")
}