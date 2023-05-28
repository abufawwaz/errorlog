package com.myfawwaz.android.jawa.widget.data.repository

import com.myfawwaz.android.jawa.widget.data.local.dao.DiaryDao
import com.myfawwaz.android.jawa.widget.domain.model.DiaryEntry
import com.myfawwaz.android.jawa.widget.domain.repository.DiaryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DiaryRepositoryImpl(
    private val diaryDao: DiaryDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DiaryRepository {

    override fun getAllEntries(): Flow<List<DiaryEntry>> {
        return diaryDao.getAllEntries()
    }

    override suspend fun getEntry(id: Int): DiaryEntry {
        return withContext(ioDispatcher) {
            diaryDao.getEntry(id)
        }
    }

    override suspend fun searchEntries(title: String): List<DiaryEntry> {
        return withContext(ioDispatcher) {
            diaryDao.getEntriesByTitle(title)
        }
    }
   /* override suspend fun searchDate(createdDate: Long): List<DiaryEntry> {
        return withContext(ioDispatcher) {
            diaryDao.getEntriesByDate(createdDate)
        }
    }

    */
    override suspend fun addEntry(diary: DiaryEntry) {
        withContext(ioDispatcher) {
            diaryDao.insertEntry(diary)
        }
    }

    override suspend fun updateEntry(diary: DiaryEntry) {
        withContext(ioDispatcher) {
            diaryDao.updateEntry(diary)
        }
    }

    override suspend fun deleteEntry(diary: DiaryEntry) {
        withContext(ioDispatcher) {
            diaryDao.deleteEntry(diary)
        }
    }
}