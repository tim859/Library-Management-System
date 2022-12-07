class AuthorHandler {

    private val authorDBHandler = AuthorDBHandler()

    fun getAllAuthors(): List<Author> {
        return authorDBHandler.allAuthorsInDB
    }

    fun searchForAuthor(firstName: String, surname: String): List<Author> {

        return authorDBHandler.searchDBForAuthorByName(firstName, surname)
    }

    fun addAuthor(firstName: String, surname: String): Boolean {

        if (firstName.isEmpty() or surname.isEmpty()) {
            return false
        }

        return authorDBHandler.addAuthorToDB(firstName, surname)
    }

    fun deleteAuthor(deletedAuthor: Author): Boolean {

        return authorDBHandler.deleteAuthorFromDB(deletedAuthor)
    }

    fun editAuthor(editedAuthor: Author): Boolean {

        if (editedAuthor.firstName.isEmpty() or editedAuthor.surname.isEmpty()) {
            return false
        }

        return authorDBHandler.editAuthorInDB(editedAuthor)
    }
}