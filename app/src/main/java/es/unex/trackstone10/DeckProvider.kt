package es.unex.trackstone10

class DeckProvider{
    companion object{
        val deckList = listOf<Deck>(
            Deck(
                "DH test",
                ClassProvider.classMap["DH"]!!,
                null
                ),
            Deck(
                "Druid test",
                ClassProvider.classMap["Druid"]!!,
                null
            ),
            Deck(
                "Mage test",
                ClassProvider.classMap["Mage"]!!,
                null
            )

        )
    }
}