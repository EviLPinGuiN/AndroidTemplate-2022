package com.itis.template.data

import android.content.Context
import androidx.room.Room
import com.itis.template.data.entity.Author

class AuthorRepository(context: Context) {

    private val db by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    private val authorDao by lazy {
        db.getAuthorDao()
    }

    private val bookDao by lazy {
        db.getBookDao()
    }

    fun saveAuthor(author: Author) {
        authorDao.save(author)
    }

    suspend fun getAllAuthors(): List<Author> = authorDao.getAll()

    companion object {
        private const val DATABASE_NAME = "itis.db"
    }
}