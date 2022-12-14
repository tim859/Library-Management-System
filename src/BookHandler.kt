class BookHandler {

    private val bookDBHandler = BookDBHandler()
    private val sortAlgo = SortAlgo()

    fun getAllBooks(sortAlgoChoice: String): List<Book> {
        val bookList = bookDBHandler.allBooksInDB

        return if (sortAlgoChoice == "bubble sort") {
            sortAlgo.nestedBubbleSort(bookList)

        } else if (sortAlgoChoice == "merge sort") {
            sortAlgo.mergeSort(bookList)
        } else {
            bookList
        }
    }
    fun searchForBook(bookTitle: String, bookAuthor: Int, bookYear: Int, bookPublisher: Int, bookSubject: String, sortAlgoChoice: String): List<Book> {

        val searchedBookList = bookDBHandler.searchDBForBook(bookTitle, bookAuthor, bookYear, bookPublisher, bookSubject)

        return if (sortAlgoChoice == "bubble sort") {
            sortAlgo.nestedBubbleSort(searchedBookList)

        } else if (sortAlgoChoice == "merge sort") {
            sortAlgo.mergeSort(searchedBookList)
        } else {
            searchedBookList
        }
    }

    fun addBook(bookTitle: String, bookAuthor: Int, bookYear: Int, bookPublisher: Int, bookSubject: String): Boolean {

        // basic validation that just makes sure none of the fields are empty, can be greatly enhanced
        if (bookTitle.isEmpty() or (bookAuthor <= 0) or (bookYear <= 0) or (bookPublisher <= 0) or bookSubject.isEmpty()) {
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
        if (editedBook.title.isEmpty() or (editedBook.author <= 0) or (editedBook.yearOfPublication <= 0) or (editedBook.publisher <= 0) or editedBook.subject.isEmpty()) {
            return false
        }

        return bookDBHandler.editBookInDB(editedBook)
    }
}