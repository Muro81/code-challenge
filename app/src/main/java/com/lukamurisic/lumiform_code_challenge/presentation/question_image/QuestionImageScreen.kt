package com.lukamurisic.lumiform_code_challenge.presentation.question_image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseContentView
import com.lukamurisic.lumiform_code_challenge.presentation.question_image.components.BackArrowTopBar

@Composable
fun QuestionImageScreen(
    viewModel: QuestionImageViewModel
) {
    val state by viewModel.viewStateFlow.collectAsState()

    BaseContentView(
        viewState = state,
        topBar = {
            BackArrowTopBar{
                viewModel.onEvent(QuestionImageEvent.OnBackClick)
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = state.imagePath,
                contentDescription = state.title,
                modifier = Modifier
                    .fillMaxHeight(0.8f),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = state.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}