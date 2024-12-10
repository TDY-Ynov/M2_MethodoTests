package books_tp.domain.usecase

import books_tp.domain.model.Book

fun createBookList(
    vararg books: Book = arrayOf(createBook())
) = books.toList()

fun createBook(
    title: String = "Titre",
    author: String = "Auteur"
) = Book(title, author)
