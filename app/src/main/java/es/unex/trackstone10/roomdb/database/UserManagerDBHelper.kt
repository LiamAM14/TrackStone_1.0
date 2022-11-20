package es.unex.trackstone10.roomdb.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserManagerDBHelper (context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_USERS)
    }

    override fun onUpgrade (db: SQLiteDatabase,oldVersion: Int,newVersion : Int){
        db.execSQL(SQL_DELETE_USERS)
        onCreate(db)
    }

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user_table.db"
        private const val TEXT_TYPE = " TEXT"
        private const val COMMA_SEP = ","
        private const val SQL_CREATE_USERS = "CREATE TABLE " + DBContract.UserEntity.TABLE_NAME + " (" +
                DBContract.UserEntity.USER_ID + " INTEGER PRIMARY KEY," +
                DBContract.UserEntity.USERNAME + TEXT_TYPE + COMMA_SEP +
                DBContract.UserEntity.PASSWORD + TEXT_TYPE + COMMA_SEP +
                DBContract.UserEntity.MAIL + TEXT_TYPE +
                " )"
        private const val SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + DBContract.UserEntity.TABLE_NAME
    }
}