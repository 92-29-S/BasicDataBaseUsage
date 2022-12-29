package com.example.basicdatabaseusage.databases

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class WordEntity (var info_word: String = "") {
    @PrimaryKey(autoGenerate = true)
    private var id: Int? = null

    @NonNull
    @ColumnInfo(name = "word")

    private var value = info_word

    fun setValue(info_word: String) {
        this.value = info_word
    }
    fun getValue(): String {
        return this.value
    }
}