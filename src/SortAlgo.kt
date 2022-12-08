class SortAlgo {

    public fun bubbleSort(array: Array<String>) {
        for (i in 0 until array.size - 1) {
            for (j in 0 until array.size - i - 1) {
                if (array[j] > array[j + 1]) {
                    // Swap the elements
                    val temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                }
            }
        }
    }

    public fun mergeSort(array: Array<String>): Array<String> {

        if (array.size <= 1) return array

        val middle = array.size / 2
        val left = array.copyOfRange(0, middle)
        val right = array.copyOfRange(middle, array.size)

        return merge(mergeSort(left), mergeSort(right))
    }

    private fun merge(left: Array<String>, right: Array<String>): Array<String> {
        val result = mutableListOf<String>()
        var leftIndex = 0
        var rightIndex = 0

        while (leftIndex < left.size && rightIndex < right.size) {
            if (left[leftIndex] < right[rightIndex]) {
                result.add(left[leftIndex])
                leftIndex++
            } else {
                result.add(right[rightIndex])
                rightIndex++
            }
        }

        result.addAll(left.toList().subList(leftIndex, left.size))
        result.addAll(right.toList().subList(rightIndex, right.size))

        return result.toTypedArray()
    }
}