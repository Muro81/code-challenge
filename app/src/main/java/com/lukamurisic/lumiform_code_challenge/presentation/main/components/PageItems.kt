package com.lukamurisic.lumiform_code_challenge.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lukamurisic.lumiform_code_challenge.core.utils.noRippleClickable
import com.lukamurisic.lumiform_code_challenge.domain.model.PageItem

@Composable
fun PageItems(
    items: List<PageItem>,
    currentFontSize : Int,
    onQuestionClick : (PageItem.Question) -> Unit
) {
    Column {
        items.forEach { pageItem ->
            when (pageItem) {
                is PageItem.Question -> {
                    if (pageItem.src != null)
                        AsyncImage(
                            model = pageItem.src,
                            contentDescription = pageItem.title,
                            modifier = Modifier
                                .size(64.dp)
                                .noRippleClickable{ onQuestionClick(pageItem) }
                        )
                    Text(
                        text = pageItem.title,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                is PageItem.Section -> {
                    Text(
                        text = pageItem.title,
                        fontSize = currentFontSize.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    PageItems(
                        items = pageItem.items,
                        currentFontSize = currentFontSize - 2,
                        onQuestionClick = onQuestionClick
                        )
                }
            }
        }
    }
}