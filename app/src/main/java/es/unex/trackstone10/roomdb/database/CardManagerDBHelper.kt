package es.unex.trackstone10.roomdb.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CardManagerDBHelper (context: Context?) :
        SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_CARDS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_CARDS)
        onCreate(db)
    }


    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "card_table.db"
        private const val TEXT_TYPE = " TEXT"
        private const val COMMA_SEP = ","
        private const val SQL_CREATE_CARDS = "CREATE TABLE " + DBContract.CardEntity.TABLE_NAME + " (" +
                DBContract.CardEntity.CARD_ID + " INTEGER PRIMARY KEY," +
                DBContract.CardEntity.CARD_NAME + TEXT_TYPE + COMMA_SEP +
                DBContract.CardEntity.CARD_RARITY + TEXT_TYPE + COMMA_SEP +
                DBContract.CardEntity.CARD_CLASS + TEXT_TYPE + COMMA_SEP +
                DBContract.CardEntity.CARD_MANACOST + TEXT_TYPE + COMMA_SEP +
                DBContract.CardEntity.CARD_INFO + TEXT_TYPE + COMMA_SEP +
                DBContract.CardEntity.CARD_TYPE + TEXT_TYPE + COMMA_SEP +
                DBContract.CardEntity.CARD_RACE + TEXT_TYPE + COMMA_SEP +
                DBContract.CardEntity.USER_ID + TEXT_TYPE + " )"

        private const val SQL_DELETE_CARDS =
            "DROP TABLE IF EXISTS" + DBContract.CardEntity.TABLE_NAME


    }
}