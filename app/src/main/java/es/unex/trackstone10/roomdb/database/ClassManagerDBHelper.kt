package es.unex.trackstone10.roomdb.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ClassManagerDBHelper (context: Context?) :
        SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_CLASSES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_CLASSES)
        onCreate(db)
    }


    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "class_table.db"
        private const val TEXT_TYPE = " TEXT"
        private const val COMMA_SEP = ","
        private const val SQL_CREATE_CLASSES = "CREATE TABLE " + DBContract.ClassEntity.TABLE_NAME + " (" +
                DBContract.ClassEntity.CLASS_ID + " INTEGER PRIMARY KEY," +
                DBContract.ClassEntity.CLASS_NAME + TEXT_TYPE + COMMA_SEP +
                DBContract.ClassEntity.HERO_ID + TEXT_TYPE + COMMA_SEP +
                DBContract.ClassEntity.CLASS_URL + TEXT_TYPE + COMMA_SEP +
                DBContract.ClassEntity.USER_ID + TEXT_TYPE + " )"
        private const val SQL_DELETE_CLASSES =
            "DROP TABLE IF EXISTS " + DBContract.ClassEntity.TABLE_NAME
    }
}