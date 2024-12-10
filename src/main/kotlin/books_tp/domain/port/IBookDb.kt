package books_tp.domain.port

import books_tp.domain.model.Book

interface IBookDb {
    fun save(book: Book): Book
    fun findAll(): List<Book>
}
