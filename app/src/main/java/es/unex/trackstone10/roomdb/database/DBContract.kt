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
    }

    object ClassEntity : BaseColumns{
        const val TABLE_NAME = "class_table"
        const val CLASS_ID = "id"
        const val HERO_ID = "id_hero"
        const val CLASS_URL = "url"
    }




}