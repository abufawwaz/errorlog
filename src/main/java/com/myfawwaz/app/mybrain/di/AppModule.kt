package com.myfawwaz.app.mybrain.di

import android.content.Context
import androidx.room.Room
import com.myfawwaz.app.mybrain.app.dataStore
import com.myfawwaz.app.mybrain.data.local.MyBrainDatabase
import com.myfawwaz.app.mybrain.data.local.dao.*
import com.myfawwaz.app.mybrain.data.local.migrations.MIGRATION_1_2
import com.myfawwaz.app.mybrain.data.repository.*
import com.myfawwaz.app.mybrain.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMyBrainDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MyBrainDatabase::class.java,
        MyBrainDatabase.DATABASE_NAME
    ).addMigrations(MIGRATION_1_2)
        .build()

    @Singleton
    @Provides
    fun provideNoteDao(myBrainDatabase: MyBrainDatabase) = myBrainDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepositoryImpl(noteDao)

    @Singleton
    @Provides
    fun provideTaskDao(myBrainDatabase: MyBrainDatabase) = myBrainDatabase.taskDao()

    @Singleton
    @Provides
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository = TaskRepositoryImpl(taskDao)

    @Singleton
    @Provides
    fun provideBookmarkDao(myBrainDatabase: MyBrainDatabase) = myBrainDatabase.bookmarkDao()

    @Singleton
    @Provides
    fun provideBookmarkRepository(bookmarkDao: BookmarkDao): BookmarkRepository = BookmarkRepositoryImpl(bookmarkDao)

    @Singleton
    @Provides
    fun provideDiaryDao(myBrainDatabase: MyBrainDatabase) = myBrainDatabase.diaryDao()

    @Singleton
    @Provides
    fun provideDiaryRepository(diaryDao: DiaryDao): DiaryRepository = DiaryRepositoryImpl(diaryDao)

    @Singleton
    @Provides
    fun provideCalendarRepository(@ApplicationContext context: Context): CalendarRepository = CalendarRepositoryImpl(context)

    @Singleton
    @Provides
    fun provideAlarmDao(myBrainDatabase: MyBrainDatabase) = myBrainDatabase.alarmDao()

    @Singleton
    @Provides
    fun provideAlarmRepository(alarmDao: AlarmDao): AlarmRepository = AlarmRepositoryImpl(alarmDao)

    @Singleton
    @Provides
    fun provideSettingsRepository(@ApplicationContext context: Context): SettingsRepository = SettingsRepositoryImpl(context.dataStore)

    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext context: Context) = context
    }