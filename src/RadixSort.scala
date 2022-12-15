//import scala.annotation.tailrec
//
//object RadixSort {
//  def sort(books: List[Book]): List[Book] = {
//    @tailrec
//    def sortRec(books: List[Book], digit: Int): List[Book] = {
//      if (digit > books.head.author.length && digit > books.head.title.length) {
//        // sorting is complete
//        books
//      } else {
//        val (sortedBooks, buckets) = books.foldLeft((List[Book](), Array.fill(26)(List[Book]()))) {
//          case ((sorted, buckets), book) =>
//            val bucketIndex = if (digit <= book.author.length) {
//              // sort by author
//              book.author(digit - 1) - 'A'
//            } else {
//              // sort by title
//              book.title(digit - 1) - 'A'
//            }
//            (sorted, buckets.updated(bucketIndex, book :: buckets(bucketIndex)))
//        }
//        sortRec(buckets.flatten.toList.reverse, digit + 1)
//      }
//    }
//
//    sortRec(books, 1)
//  }
//}