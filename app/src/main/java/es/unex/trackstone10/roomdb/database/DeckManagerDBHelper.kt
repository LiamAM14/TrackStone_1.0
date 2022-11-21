package es.unex.trackstone10.roomdb.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DeckManagerDBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_DECKS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_DECKS)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "deck_table_db"
        private const val TEXT_TYPE = " TEXT"
        private const val COMMA_SEP = ","
        private const val SQL_CREATE_DECKS =
            "CREATE TABLE " + DBContract.DeckEntity.TABLE_NAME + " (" +
                    DBContract.DeckEntity.DECK_ID + " INTEGER PRIMARY KEY," +
                    DBContract.DeckEntity.NAME_ID + TEXT_TYPE + COMMA_SEP +
                    DBContract.DeckEntity.USER_ID + TEXT_TYPE + COMMA_SEP +
                    DBContract.DeckEntity.CLASS_ID + TEXT_TYPE + COMMA_SEP +
                    DBContract.DeckEntity.COUNT + TEXT_TYPE + " )"
        private const val SQL_DELETE_DECKS =
            "DROP TABLE IF EXISTS " + DBContract.DeckEntity.TABLE_NAME
    }
}