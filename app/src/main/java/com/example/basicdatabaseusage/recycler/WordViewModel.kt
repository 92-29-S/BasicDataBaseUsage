package com.example.basicdatabaseusage.recycler

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.basicdatabaseusage.databases.WordEntity
import com.example.basicdatabaseusage.databases.WordRepository

internal class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: WordRepository
    private val mAllWords: LiveData<List<WordEntity>>

    init {
        mRepository = WordRepository(application)
        mAllWords = mRepository.getAllWords()
    }

    fun getAllWords(): LiveData<List<WordEntity>> {
        return mAllWords
    }

    fun insert(word: WordEntity) {
        mRepository.insert(word)
    }
}