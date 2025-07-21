package com.lukamurisic.lumiform_code_challenge.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen
import com.lukamurisic.lumiform_code_challenge.presentation.question_image.QuestionImageScreen
import com.lukamurisic.lumiform_code_challenge.presentation.question_image.QuestionImageViewModel

fun NavGraphBuilder.questionImageScreenComposable() {
    composable<Screen.QuestionImageScreen> {
        val viewModel = hiltViewModel<QuestionImageViewModel>()
        QuestionImageScreen(
            viewModel = viewModel
        )
    }
}