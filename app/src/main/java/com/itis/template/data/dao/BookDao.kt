package com.itis.template.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itis.template.data.entity.Author
import com.itis.template.data.entity.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(book: List<Book>)

    @Delete
    fun delete(book: Book)

    @Query("SELECT * FROM book")
    fun getAll(): List<Book>
}