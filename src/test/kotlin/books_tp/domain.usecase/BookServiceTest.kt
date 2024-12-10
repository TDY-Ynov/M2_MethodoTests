package books_tp.domain.usecase

import books_tp.domain.port.IBookDb
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeSortedBy
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class BookServiceTest : FunSpec({
    val iBookDbMock = mockk<IBookDb>()
    val service = BookService(iBookDbMock)

    test("should call DB interface on book creation") {

        every { iBookDbMock.save(any()) } returns books_tp.domain.model.Book("Les misérables", "Victor Hugo")

        service.addBook(
            title = "Les misérables",
            author = "Victor Hugo"
        )

        verify(exactly = 1) { iBookDbMock.save(any()) }
    }

    test("should throw errors when title or author is empty") {
        shouldThrow<IllegalArgumentException> {
            service.addBook(
                title = "",
                author = "Victor Hugo"
            )
        }.message shouldBe "Book name must not be blank."

        shouldThrow<IllegalArgumentException> {
            service.addBook(
                title = "Les misérables",
                author = ""
            )
        }.message shouldBe "Author name must not be blank."
    }


    test("should return list of books sorted by title") {

        every { iBookDbMock.findAll() } returns
                createBookList(
                    createBook(title = "Z Book", author = "Author Z"),
                    createBook(title = "A Book", author = "Author A")
                )

        val result = service.getAllBooksSortedByTitle()

        verify(exactly = 1) { iBookDbMock.findAll() }
        result.size shouldBe 2
        result shouldBeSortedBy { it.author }
    }


})
