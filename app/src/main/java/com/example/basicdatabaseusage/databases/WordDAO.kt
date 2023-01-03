package com.example.basicdatabaseusage.databases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WordDAO {
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getSortedWords(): LiveData<List<WordEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: WordEntity)

    @Query("DELETE FROM word_table")
    fun deleteAll()
}