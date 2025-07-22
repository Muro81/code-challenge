package com.lukamurisic.lumiform_code_challenge.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.lukamurisic.lumiform_code_challenge.data.local.entity.QuestionEntity
import com.lukamurisic.lumiform_code_challenge.data.local.entity.SectionEntity

data class SectionWithChildren(
    @Embedded val section : SectionEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "parentSectionId"
    )
    val subsections : List<SectionEntity> = emptyList(),

    @Relation(
        parentColumn = "id",
        entityColumn = "parentSectionId"
    )
    val questions : List<QuestionEntity> = emptyList()
)
