package es.unex.trackstone10.roomdb.database

import android.provider.BaseColumns

class DBContract private constructor() {
    object UserEntity : BaseColumns {
        const val TABLE_NAME = "user_table"
        const val USER_ID = "id"
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val MAIL = "mail"
    }

    object CardBackEntity : BaseColumns{
        const val TABLE_NAME = "card_back_table"
        const val CARDBACK_NAME = "name"
        const val CARDBACK_ID = "id"
        const val CARDBACK_URL = "url"
        const val USER_ID = "id"
    }

    object CardEntity : BaseColumns{
        const val TABLE_NAME = "card_table"
        const val CARD_ID = "id"
        const val CARD_NAME = "name"
        const val CARD_RARITY = "rarity"
        const val CARD_CLASS = "cardclass"
        const val CARD_MANACOST = "manacost"
        const val CARD_INFO = "info"
        const val CARD_TYPE = "type"
        const val CARD_RACE = "race"
        const val USER_ID = "id"
    }

    object ClassEntity : BaseColumns{
        const val TABLE_NAME = "class_table"
        const val CLASS_ID = "id"
        const val CLASS_NAME = "name"
        const val HERO_ID = "id_hero"
        const val CLASS_URL = "url"
        const val USER_ID = "id"
    }

    object DeckEntity: BaseColumns{
        const val TABLE_NAME = "deck_table"
        const val DECK_ID = "id"
        const val NAME_ID = "name"
        const val USER_ID = "userid"
        const val CLASS_ID = "classid"
        const val COUNT = "count"
    }

    object DeckListCards: BaseColumns{
        const val TABLE_NAME = "deck_list"
        const val DECK_ID = "deckid"
        const val USER_ID = "userid"
        const val COPIES = "copies"
        const val CARD_NAME = "name"
        const val CARD_RARITY = "rarity"
        const val CARD_CLASS = "cardclass"
        const val CARD_MANACOST = "manacost"
        const val IMAGE = "image"
    }




}