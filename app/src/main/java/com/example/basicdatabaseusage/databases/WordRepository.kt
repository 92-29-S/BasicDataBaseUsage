package com.example.basicdatabaseusage.databases

import android.app.Application
import androidx.lifecycle.LiveData

class WordRepository {
    private var mWordDAO: WordDAO
    private var mAllWords: LiveData<List<WordEntity>>

    constructor(application: Application) {
        val db: WordRoomDatabase = WordRoomDatabase.getDatabase(application)!!
        mWordDAO = db.wordDAO()!!
        mAllWords = db.getSortedWords()
    }

    fun getAllWords(): LiveData<List<WordEntity>>? {
        return mAllWords
    }

    fun insert(word: WordEntity) {
        WordRoomDatabase.databaseWriteExecutor.execute(Runnable { mWordDAO!!.insert(word) })
    }
}