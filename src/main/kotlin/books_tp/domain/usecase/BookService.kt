package books_tp.domain.usecase

import books_tp.domain.model.Book
import books_tp.domain.port.IBookDb

class BookService(private val bookRepository: IBookDb) {

    fun addBook(title: String, author: String) {
        val book = validateBookInputs(title, author)

        bookRepository.save(book)
    }

    private fun validateBookInputs(title: String, author: String): Book {
        require(title.isNotBlank()) { "Book name must not be blank." }
        require(author.isNotBlank()) { "Author name must not be blank." }

        val book = Book(title = title.trim(), author = author.trim())
        if (book.title.length < 5) {
            throw IllegalArgumentException("Title is too short")
        }
        return book
    }

    fun getAllBooksSortedByTitle(): List<Book> {
        return bookRepository.findAll().sortedBy { it.title }
    }
}
