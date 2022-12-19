package com.itis.template.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Relation
import com.itis.template.data.entity.Author
import com.itis.template.data.entity.Book

@Dao
interface AuthorDao {

    @Insert(onConflict = REPLACE)
    fun save(author: Author)

    @Insert(onConflict = REPLACE)
    fun save(author: List<Author>)

    @Delete
    fun delete(author: Author)

    @Query("SELECT * FROM author ORDER BY name DESC")
    suspend fun getAll(): List<Author>

    @Query("SELECT * FROM author")
    suspend fun getAuthorWithBook(): AuthorWithBookResult

    @Query("SELECT * FROM author JOIN book ON author.id = book.author_id")
    suspend fun getMapAuthorWithBook(): Map<Author, List<Book>>
}

data class AuthorWithBookResult(
    @Embedded
    val author: Author,
    @Relation(
        parentColumn = "id",
        entityColumn = "author_id",
        entity = Book::class
    )
    val books: List<Book>,
)