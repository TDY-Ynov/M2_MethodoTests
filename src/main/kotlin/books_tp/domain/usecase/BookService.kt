package books_tp.domain.usecase

import books_tp.domain.model.Book
import books_tp.domain.port.IBookDb

class BookService(private val bookRepository: IBookDb) {

    fun addBook(title: String, author: String) {
        require(title.isNotBlank()) { "Book name must not be blank." }
        require(author.isNotBlank()) { "Author name must not be blank." }

        val book = Book(title = title.trim(), author = author.trim())

        bookRepository.save(book)
    }

    fun getAllBooksSortedByTitle(): List<Book> {
        return bookRepository.findAll().sortedBy { it.title }
    }
}
