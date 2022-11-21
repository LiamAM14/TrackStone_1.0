package es.unex.trackstone10.roomdb.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DeckListCardManagerDBHelper (context: Context?) :
        SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_DESK_LIST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_DESK_LIST)
        onCreate(db)
    }


    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "deck_list.db"
        private const val TEXT_TYPE = " TEXT"
        private const val COMMA_SEP = ","
        private const val SQL_CREATE_DESK_LIST = "CREATE TABLE " + DBContract.DeckListCards.TABLE_NAME + " (" +
                DBContract.DeckListCards.DECK_ID + " INTEGER PRIMARY KEY," +
                DBContract.DeckListCards.USER_ID + TEXT_TYPE + COMMA_SEP +
                DBContract.DeckListCards.CARD_NAME + TEXT_TYPE + COMMA_SEP +
                DBContract.DeckListCards.COPIES + TEXT_TYPE + COMMA_SEP +
                DBContract.DeckListCards.CARD_RARITY + TEXT_TYPE + COMMA_SEP +
                DBContract.DeckListCards.CARD_CLASS + TEXT_TYPE + COMMA_SEP +
                DBContract.DeckListCards.CARD_MANACOST + TEXT_TYPE + COMMA_SEP +
                DBContract.DeckListCards.IMAGE + TEXT_TYPE + " )"

        private const val SQL_DELETE_DESK_LIST =
            "DROP TABLE IF EXISTS" + DBContract.DeckListCards.TABLE_NAME
    }
        }