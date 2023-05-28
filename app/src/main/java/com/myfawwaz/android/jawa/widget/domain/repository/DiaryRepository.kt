package com.myfawwaz.android.jawa.widget.domain.repository

import com.myfawwaz.android.jawa.widget.domain.model.DiaryEntry
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {

    fun getAllEntries(): Flow<List<DiaryEntry>>

    suspend fun getEntry(id: Int): DiaryEntry

    suspend fun searchEntries(title: String): List<DiaryEntry>

   // suspend fun searchDate(createdDate: Long): List<DiaryEntry>

    suspend fun addEntry(diary: DiaryEntry)

    suspend fun updateEntry(diary: DiaryEntry)

    suspend fun deleteEntry(diary: DiaryEntry)

}