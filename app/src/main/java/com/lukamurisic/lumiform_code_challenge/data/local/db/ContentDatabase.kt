package com.lukamurisic.lumiform_code_challenge.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lukamurisic.lumiform_code_challenge.data.local.dao.ContentDao
import com.lukamurisic.lumiform_code_challenge.data.local.entity.PageEntity
import com.lukamurisic.lumiform_code_challenge.data.local.entity.QuestionEntity
import com.lukamurisic.lumiform_code_challenge.data.local.entity.SectionEntity


@Database(
    version = 2,
    exportSchema = false,
    entities = [
        PageEntity::class,
        SectionEntity::class,
        QuestionEntity::class
    ]
)
abstract class ContentDatabase : RoomDatabase(){
    abstract fun contentDao(): ContentDao
}