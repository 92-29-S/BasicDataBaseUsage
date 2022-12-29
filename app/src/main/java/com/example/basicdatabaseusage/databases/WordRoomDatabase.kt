package com.example.basicdatabaseusage.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors


@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase: RoomDatabase(), WordDAO {
    abstract fun wordDAO(): WordDAO?

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null
        private val NUMBER_OF_THREADS = 4

        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        val sRoomDatabaseCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                // If you want to keep data through app restarts,
                // comment out the following block
                databaseWriteExecutor.execute {

                    // Populate the database in the background.
                    // If you want to start with more words, just add them.
                    val dao: WordDAO = INSTANCE!!.wordDAO()!!
                    dao.deleteAll()
                    dao.insert(WordEntity("Hello"))
                    dao.insert(WordEntity("My"))
                    dao.insert(WordEntity("Friend"))
                }
            }
        }

        fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDatabase::class.java, "word_database"
                        ).addCallback(sRoomDatabaseCallback).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}