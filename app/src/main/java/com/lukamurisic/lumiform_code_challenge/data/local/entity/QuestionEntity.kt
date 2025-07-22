package com.lukamurisic.lumiform_code_challenge.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "questions",
    foreignKeys = [
        ForeignKey(
            entity = PageEntity::class,
            parentColumns = ["id"],
            childColumns = ["parentPageId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["parentSectionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("parentPageId"), Index("parentSectionId")]
)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val src: String? = null,
    val order : Int = 0,
    val parentPageId: Int? = null,
    val parentSectionId: Int? = null
)
