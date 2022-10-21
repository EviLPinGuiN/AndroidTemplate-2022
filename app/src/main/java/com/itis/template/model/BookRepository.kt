package com.itis.template.model

import com.itis.template.R
import com.itis.template.adapter.TitleItem

object BookRepository {

    val books = arrayListOf(
        Book(
            id = 0,
            name = "Naruto",
            author = "Pushkin",
            cover = "https://img1.ak.crunchyroll.com/i/spire4/5568ffb263f6bcba85a639980b80dd9a1612993223_full.jpg"
        ),
        Book(
            id = 1,
            name = "One piece",
            author = "Oda",
            cover = "https://static.wikia.nocookie.net/6c34f302-89dd-4e35-85e8-ce3e89f67d20"
        ),
        Book(
            id = 2,
            name = "Berserk",
            author = "Pushkin",
            cover = "https://i.pinimg.com/originals/24/f1/f5/24f1f54f147a70157d56180b5192f6df.jpg"
        ),
        Book(
            id = 3,
            name = "Pokemon",
            author = "Pushkin",
            cover = "https://img1.ak.crunchyroll.com/i/spire4/5568ffb263f6bcba85a639980b80dd9a1612993223_full.jpg"
        ),
        Book(
            id = 5,
            name = "JoJo",
            author = "Jotaro",
            cover = "https://upload.wikimedia.org/wikipedia/ru/c/cd/JoJos_Bizarre_Adventure.jpg"
        ),
        Book(
            id = 6,
            name = "One Punch Man",
            author = "Murata",
            cover = "https://www.mirf.ru/wp-content/uploads/2016/09/Poster.jpg"
        ),
        Book(
            id = 7,
            name = "Great Teacher Onidzuka",
            author = "GTO",
            cover = "https://upload.wikimedia.org/wikipedia/ru/thumb/d/d3/GTO.jpg/230px-GTO.jpg"
        ),
        Book(
            id = 8,
            name = "Samurai Champloo",
            author = "Lev",
            cover = "https://static.wikia.nocookie.net/dubbing9585/images/2/24/Samurai_Champloo.jpg/revision/latest?cb=20180415094726"
        ),
        Book(
            id = 9,
            name = "Suzume no Tojimari",
            author = "Makota Senkai",
            cover = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCoygbuZfqluhuYRAlyhPbuLHwuRn9OSXAbfG19FfC6jn5vYM1oOIJ_R9_Fj_6RsYIvEs&usqp=CAU"
        ),
        Book(
            id = 10,
            name = "Trigun",
            author = "Vern",
            cover = "https://m.media-amazon.com/images/M/MV5BNjMyYzg4NDMtYmE1OC00YzAxLTlhZGMtNjIyMWZkZjQyOWZhXkEyXkFqcGdeQXVyNjc3OTE4Nzk@._V1_FMjpg_UX1000_.jpg"
        ),

        )


    val booksUI: MutableList<MainItem.BookUiModel>
        get() = books.map {
            val titleColor = if (it.name == "Naruto")
                R.color.purple_700
            else
                R.color.black

            val font = if (it.name.length > 5) {
                R.font.alkalami
            } else {
                R.font.silkscreen
            }

            MainItem.BookUiModel(
                id = it.id.toLong(),
                name = it.name,
                author = it.author,
                cover = it.cover,
                isFavorite = it.isFavorite,
                titleColor = titleColor,
                font = font
            )
        }.toMutableList()

    val mainItems = arrayListOf<MainItem>().apply {
        add(MainItem.Title("Most popular"))
        addAll(booksUI.take(4))
        add(MainItem.Title("Most viewed"))
        addAll(booksUI.subList(4, booksUI.size))
    }
}