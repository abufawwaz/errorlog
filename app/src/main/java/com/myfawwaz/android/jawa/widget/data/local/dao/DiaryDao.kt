package com.myfawwaz.android.jawa.widget.data.local.dao

import androidx.room.*
import com.myfawwaz.android.jawa.widget.domain.model.DiaryEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary")
    fun getAllEntries(): Flow<List<DiaryEntry>>

    @Query("SELECT * FROM diary WHERE id = :id")
    suspend fun getEntry(id: Int): DiaryEntry
/*
    @Query("SELECT * FROM diary WHERE created_date LIKE '%' || :date || '%' OR content LIKE '%' || :date || '%'")
    suspend fun getEntriesByDate(date: Long): List<DiaryEntry> //cari tanggal
 */
    @Query("SELECT * FROM diary WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    suspend fun getEntriesByTitle(query: String): List<DiaryEntry>

    @Insert
    suspend fun insertEntry(diary: DiaryEntry)

    @Insert
    suspend fun insertEntries(diary: List<DiaryEntry>)

    @Update
    suspend fun updateEntry(diary: DiaryEntry)

    @Delete
    suspend fun deleteEntry(diary: DiaryEntry)

}