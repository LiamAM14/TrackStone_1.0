package es.unex.trackstone10.roomdb.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CardBackManagerDBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_CARDBACKS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DETELE_CARDBACKS)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "card_back_table_db"
        private const val TEXT_TYPE = " TEXT"
        private const val COMMA_SEP = ","
        private const val SQL_CREATE_CARDBACKS =
            "CREATE TABLE " + DBContract.CardBackEntity.TABLE_NAME + " (" +
                    DBContract.CardBackEntity.CARDBACK_ID + " INTEGER PRIMARY KEY," +
                    DBContract.CardBackEntity.CARDBACK_NAME + TEXT_TYPE + COMMA_SEP +
                    DBContract.CardBackEntity.CARDBACK_URL + TEXT_TYPE + COMMA_SEP +
                    DBContract.CardBackEntity.USER_ID + TEXT_TYPE + " )"
        private const val SQL_DETELE_CARDBACKS =
            "DROP TABLE IF EXISTS " + DBContract.CardBackEntity.TABLE_NAME
    }
}