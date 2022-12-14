import kotlin.system.measureTimeMillis

class SortAlgo {

    private val authorDBHandler = AuthorDBHandler()

    private var mergeSortTime = 0.0f

    fun nestedBubbleSort(books: MutableList<Book>): List<Book> {

        val startTime = System.currentTimeMillis()

        val authorSurnameList = authorDBHandler.getAuthorsFromPK(books)

        for (i in 0 until books.size - 1) {
            for (j in 0 until books.size - i - 1) {
                if (authorSurnameList[j] > authorSurnameList[j + 1]) {
                    // Swap books if authors are out of order
                    val tmp = books[j]
                    books[j] = books[j + 1]
                    books[j + 1] = tmp

                    val tmp2 = authorSurnameList[j]
                    authorSurnameList[j] = authorSurnameList[j + 1]
                    authorSurnameList[j + 1] = tmp2

                } else if (authorSurnameList[j] == authorSurnameList[j + 1]) {
                    // If authors are the same, compare titles
                    if (books[j].title > books[j + 1].title) {
                        val tmp = books[j]
                        books[j] = books[j + 1]
                        books[j + 1] = tmp

                        val tmp2 = authorSurnameList[j]
                        authorSurnameList[j] = authorSurnameList[j + 1]
                        authorSurnameList[j + 1] = tmp2
                    }
                }
            }
        }
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        println("Elapsed time: $elapsedTime ms")
        return books
    }

//    fun mergeSort(books: List<Book>): List<Book> {
//
//        val startTime = System.currentTimeMillis()
//
//        val sortedBookList = mergeSorting(books)
//
//        val endTime = System.currentTimeMillis()
//        val elapsedTime = endTime - startTime
//        println("Elapsed time: $elapsedTime ms")
//
//        return sortedBookList
//    }

    fun mergeSort(books: List<Book>): List<Book> {

        if (books.size <= 1) return books

        val middle = books.size / 2
        val left = books.subList(0, middle)
        val right = books.subList(middle, books.size)

        return merge(mergeSort(left), mergeSort(right))
    }

    private fun merge(left: List<Book>, right: List<Book>): List<Book> {
        val result = mutableListOf<Book>()

        var leftIndex = 0
        var rightIndex = 0

        while (leftIndex < left.size && rightIndex < right.size) {
            val leftBook = left[leftIndex]
            val rightBook = right[rightIndex]

            // first sort by author, then by title
            if (leftBook.author < rightBook.author ||
                (leftBook.author == rightBook.author && leftBook.title < rightBook.title)) {
                result.add(leftBook)
                leftIndex++
            } else {
                result.add(rightBook)
                rightIndex++
            }
        }

        while (leftIndex < left.size) {
            result.add(left[leftIndex])
            leftIndex++
        }

        while (rightIndex < right.size) {
            result.add(right[rightIndex])
            rightIndex++
        }
        return result
    }
}