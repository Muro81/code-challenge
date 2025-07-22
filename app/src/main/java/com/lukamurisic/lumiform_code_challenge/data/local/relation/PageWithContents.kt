package com.lukamurisic.lumiform_code_challenge.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.lukamurisic.lumiform_code_challenge.data.local.entity.PageEntity
import com.lukamurisic.lumiform_code_challenge.data.local.entity.QuestionEntity
import com.lukamurisic.lumiform_code_challenge.data.local.entity.SectionEntity

data class PageWithContents(

    @Embedded val page: PageEntity,

    @Relation(
        entity = SectionEntity::class,
        parentColumn = "id",
        entityColumn = "parentPageId"
    )
    val sections: List<SectionWithChildren> = emptyList(),

    @Relation(
        parentColumn = "id",
        entityColumn = "parentPageId"
    )
    val questions: List<QuestionEntity> = emptyList()
)
