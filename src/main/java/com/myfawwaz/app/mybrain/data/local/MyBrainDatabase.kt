package com.myfawwaz.app.mybrain.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myfawwaz.app.mybrain.data.local.converters.DBConverters
import com.myfawwaz.app.mybrain.data.local.dao.*
import com.myfawwaz.app.mybrain.domain.model.*

@Database(
    entities = [Note::class, Task::class, DiaryEntry::class, Bookmark::class, Alarm::class, NoteFolder::class],
    version = 2
)
@TypeConverters(DBConverters::class)
abstract class MyBrainDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun taskDao(): TaskDao
    abstract fun diaryDao(): DiaryDao
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun alarmDao(): AlarmDao

    companion object {
        const val DATABASE_NAME = "by_myfawwaz_db"
    }
}