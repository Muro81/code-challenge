package com.lukamurisic.lumiform_code_challenge.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lukamurisic.lumiform_code_challenge.R

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.no_items),
            contentDescription = stringResource(R.string.no_items),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.no_items),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}