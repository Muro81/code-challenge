package com.lukamurisic.lumiform_code_challenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.lukamurisic.lumiform_code_challenge.data.local.entity.PageEntity
import com.lukamurisic.lumiform_code_challenge.data.local.entity.QuestionEntity
import com.lukamurisic.lumiform_code_challenge.data.local.entity.SectionEntity
import com.lukamurisic.lumiform_code_challenge.data.local.relation.PageWithContents

@Dao
interface ContentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPage(page: PageEntity): Long

    @Insert
    suspend fun insertSection(section: SectionEntity): Long

    @Insert
    suspend fun insertQuestion(question: QuestionEntity): Long

    @Transaction
    @Query("SELECT * FROM pages")
    suspend fun getAllPagesWithContents(): List<PageWithContents>

    @Query("SELECT * FROM sections WHERE parentPageId = :pageId AND parentSectionId IS :parentSectionId")
    suspend fun getSectionsByPageAndParent(pageId: Int, parentSectionId: Int?): List<SectionEntity>

    @Query("SELECT * FROM questions WHERE parentPageId = :pageId AND parentSectionId IS :parentSectionId")
    suspend fun getQuestionsByPageAndParent(pageId: Int, parentSectionId: Int?): List<QuestionEntity>

    @Query("SELECT * FROM pages WHERE `order` = 0 LIMIT 1")
    suspend fun getFirstPage(): PageEntity?
}