class BookHandler {

    private var bookDBHandler = BookDBHandler()
    fun searchForBook(bookTitle: String, bookAuthor: String, bookYear: Int, bookPublisher: String, bookSubject: String): List<Book> {

        // searchedBookList can be stored for later manipulation
        // val searchedBookList = bookDBHandler.searchDBForBook(bookTitle, bookAuthor, bookYear, bookPublisher, bookSubject)

        return bookDBHandler.searchDBForBook(bookTitle, bookAuthor, bookYear, bookPublisher, bookSubject)
    }

    fun addBook(bookTitle: String, bookAuthor: String, bookYear: Int, bookPublisher: String, bookSubject: String): Boolean {

        // basic validation that just makes sure none of the fields are empty, can be greatly enhanced
        if (bookTitle.isEmpty() or bookAuthor.isEmpty() or (bookYear <= 0) or bookPublisher.isEmpty() or bookSubject.isEmpty()) {
            return false
        }

        // use addBookToDB method to not only add book to database but also to get a boolean back that confirms whether
        // the book was successfully added to the database. This information is then used to get MainGUI to
        // display a success message or a failure message
        return bookDBHandler.addBookToDB(bookTitle, bookAuthor, bookYear, bookPublisher, bookSubject)
    }

    fun deleteBook(deletedBook: Book): Boolean {
        return bookDBHandler.deleteBookFromDB(deletedBook)
    }

    fun editBook(editedBook: Book): Boolean {
        // basic validation that just makes sure none of the fields are empty, can be greatly enhanced
        if (editedBook.title.isEmpty() or editedBook.author.isEmpty() or (editedBook.yearOfPublication <= 0) or editedBook.publisher.isEmpty() or editedBook.subject.isEmpty()) {
            return false
        }

        return bookDBHandler.editBookInDB(editedBook)
    }

    fun getAllBooks(): List<Book> {
        return bookDBHandler.allBooksInDB
    }
}