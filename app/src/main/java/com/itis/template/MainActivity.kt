package com.itis.template

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.itis.template.adapter.BookAdapter
import com.itis.template.adapter.BookListAdapter
import com.itis.template.adapter.SpaceItemDecorator
import com.itis.template.databinding.ActivityMainBinding
import com.itis.template.model.BookRepository
import com.itis.template.model.MainItem
import com.itis.template.utils.showSnackbar

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var adapter: BookAdapter? = null
    private var listAdapter: BookListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.run {
            val itemDecoration = SpaceItemDecorator(
                this@MainActivity,
                16.0f
            )

//            adapter = BookAdapter(
//                BookRepository.booksUI,
//                glide = Glide.with(this@MainActivity)
//            ) {
//                root.showSnackbar("You click: ${it.name}")
//            }

            listAdapter = BookListAdapter(glide = Glide.with(this@MainActivity)) {
                onBookClick(it)
            }

            rvBook.adapter = listAdapter
            rvBook.layoutManager = LinearLayoutManager(this@MainActivity)
            rvBook.addItemDecoration(itemDecoration)

            listAdapter?.submitList(BookRepository.booksUI) {
//                rvBook.scrollToPosition(0)
            }
        }
    }

    private fun onBookClick(book: MainItem.BookUiModel) {
        val index = BookRepository.books.indexOfFirst { it.id.toLong() == book.id }
        val originalBook = BookRepository.books[index]
        val newItem = originalBook.copy(
            isFavorite = !originalBook.isFavorite,
            name = "${originalBook.name} + ${originalBook.isFavorite}"
        )

        BookRepository.books.removeAt(index)
        BookRepository.books.add(index, newItem)

        listAdapter?.submitList(BookRepository.booksUI) {
            binding?.rvBook?.scrollToPosition(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_edit -> {
                Log.e("MENU", "action_edit")
                false
            }
            R.id.action_save -> {


                Log.e("MENU", "action_save")
                true
            }
            R.id.action_preferences -> {
                Log.e("MENU", "action_preferences")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}