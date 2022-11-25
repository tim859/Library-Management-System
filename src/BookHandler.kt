import BookDBHandler
class BookHandler {

    var bookDBHandler = BookDBHandler()
    fun searchForBook() {
        println("search")
    }

    fun addBook(book: Book) {
        if (bookDBHandler.addBookToDB(book)) {
            // pop up confirmation window in gui
        }
        else {
            // pop up failure window in gui
        }
    }

    fun deleteBook() {

    }

    fun editBook() {

    }
}