import scala.collection.mutable.ListBuffer
import scala.language.postfixOps

// The Book class represents a book with an author and a title
class Book(val author: String, val title: String)

// The RadixSort class provides a method for sorting a list of books
// by their author and then by their title using a radix sort algorithm
class RadixSort {
  // This method sorts the given list of books by their author and then by their title
  // using a radix sort algorithm.
  def sortBooksByAuthorAndTitle(books: List[Book]): List[Book] = {
    // First, sort the books by their author using a radix sort
    val booksByAuthor = radixSort(books, (book: Book) => book.author)

    // Then, for each group of books with the same author, sort the books
    // by their title using a radix sort
    // CHANGE: update the type of the booksByAuthorAndTitle variable to match the output of the map function
    val booksByAuthorAndTitle: List[List[Book]] = booksByAuthor.map(books => radixSort(books, (book: Book) => book.title))

    // Flatten the list of books to return a single, sorted list of books
    booksByAuthorAndTitle.flatten
  }

  // This method performs a radix sort on the given list of items, using the
  // provided function to extract the key for each item that should be used
  // for sorting.
  def radixSort[A](items: List[A], key: A => String): List[A] = {
    // Create a list of buckets for each digit in the key
    val buckets = Array.fill(10)(ListBuffer[A]())

    // Split the items into the appropriate buckets based on the digits in their keys
    for (item <- items) {
      val digits = key(item).map(_.asDigit)

      // Add the item to the appropriate bucket for each digit in its key
      for (i <- digits) {
        buckets(i) += item
      }
    }

    // Concatenate the buckets back into a single list, in order from least-significant
    // digit to most-significant digit
    var sortedItems = List[A]()
    for (i <- 0 to 9) {
      sortedItems ++= buckets(i)
    }

    sortedItems
  }
}