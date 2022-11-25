class BookHandler {

    private var bookDBHandler = BookDBHandler()
    fun searchForBook(bookTitle: String, bookAuthor: String, bookYear: Int, bookPublisher: String, bookSubject: String): List<Book> {

        return BookListHandler.searchBookList(bookTitle, bookAuthor, bookYear, bookPublisher, bookSubject)
    }

    fun addBook(bookTitle: String, bookAuthor: String, bookYear: Int, bookPublisher: String, bookSubject: String): Boolean {

        // basic validation that just makes sure none of the fields are empty, can be greatly enhanced
        if (bookTitle.isEmpty() or bookAuthor.isEmpty() or (bookYear <= 0) or bookPublisher.isEmpty() or bookSubject.isEmpty()) {
            return false
        }

        // acquired bookID and added book to list
        val bookID = BookListHandler.addBookToList(bookTitle, bookAuthor, bookYear, bookPublisher, bookSubject)

        // created new Book object with newly acquired bookID and other arguments passed into class
        val newBook = Book(bookID, bookTitle, bookAuthor, bookYear, bookPublisher, bookSubject)

        // use addBookToDB method to not only add book to database but also to get a boolean back that confirms whether
        // the book was successfully added to the database. This information is then used to get MainGUI to
        // display a success message or a failure message
        return bookDBHandler.addBookToDB(newBook)
    }

    fun deleteBook(book: Book) {


        bookDBHandler.deleteBookFromDB(book)
    }

    fun editBook() {

    }
}