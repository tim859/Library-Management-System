class PublisherHandler {

    private val publisherDBHandler = PublisherDBHandler()

    fun getAllPublishers(): List<Publisher> {
        return publisherDBHandler.allPublishersInDB
    }

    fun searchForPublisher(publisherName: String): List<Publisher> {

        return publisherDBHandler.searchDBForPublisherByName(publisherName)
    }

    fun addPublisher(publisherName: String): Boolean {

        // basic validation that just makes sure none of the fields are empty, can be greatly enhanced
        if (publisherName.isEmpty()) {
            return false
        }

        return publisherDBHandler.addPublisherToDB(publisherName)
    }

    fun deletePublisher(deletedPublisher: Publisher): Boolean {

        return publisherDBHandler.deletePublisherFromDB(deletedPublisher)
    }

    fun editPublisher(editedPublisher: Publisher): Boolean {

        if (editedPublisher.name.isEmpty()) {
            return false
        }

        return publisherDBHandler.editPublisherInDB(editedPublisher)
    }
}