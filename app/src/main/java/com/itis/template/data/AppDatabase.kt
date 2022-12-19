package com.itis.template.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itis.template.data.dao.AuthorDao
import com.itis.template.data.dao.BookDao
import com.itis.template.data.entity.Author
import com.itis.template.data.entity.Book

@Database(entities = [Author::class, Book::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAuthorDao(): AuthorDao

    abstract fun getBookDao(): BookDao
}
