package com.example.basicdatabaseusage.databases

import android.app.Application
import androidx.lifecycle.LiveData

class WordRepository {
    private var mWordDAO: WordDAO
    private var mAllWords: LiveData<List<WordEntity>>

    constructor(application: Application) {
        val db: WordRoomDatabase? = WordRoomDatabase.getDatabase(application)
        mWordDAO = db?.wordDAO()!!
        mAllWords = mWordDAO.getSortedWords()
    }

    fun getAllWords(): LiveData<List<WordEntity>> {
        return mAllWords
    }

    fun insert(info_word: WordEntity) {
        WordRoomDatabase.databaseWriteExecutor.execute(Runnable { mWordDAO.insert(info_word) })
    }
}