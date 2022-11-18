package es.unex.trackstone10.roomdb.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import es.unex.trackstone10.roomdb.Entity.CardBackEntity
import es.unex.trackstone10.roomdb.database.CardBackManagerDBHelper
import es.unex.trackstone10.roomdb.database.DBContract

class CardBackEntityCRUD private constructor(context: Context){
    private val mDbHelper : CardBackManagerDBHelper?

    init{
        mDbHelper = CardBackManagerDBHelper(context)
    }

    fun getAll(): List <CardBackEntity>{
        val db = mDbHelper?.readableDatabase
        val projection = arrayOf(
            DBContract.CardBackEntity.CARDBACK_ID,
            DBContract.CardBackEntity.CARDBACK_URL
        )

        val selection: String? = null
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null
        val cursor = db?.query(
            DBContract.CardBackEntity.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        val cardbacks: ArrayList<CardBackEntity> = ArrayList()
        if(cursor != null){
            if(cursor.count > 0){
                cursor.moveToFirst()
                do{
                    cardbacks.add(getCardBackFromCursor(cursor))
                } while(cursor.moveToNext())
            }
            cursor.close()
        }
        return cardbacks
    }

    fun insert(cardback : CardBackEntity) : Long{
        val db = mDbHelper?.writableDatabase

        val values = ContentValues()
        values.put(DBContract.CardBackEntity.CARDBACK_ID,cardback.id)
        values.put(DBContract.CardBackEntity.CARDBACK_URL,cardback.url)

        return db?.insert(DBContract.CardBackEntity.TABLE_NAME,null,values) ?: -1
    }

    fun deleteAll(){
        val db = mDbHelper?.writableDatabase

        val selection: String? = null
        val selectionArgs: Array<String> ? = null
        db?.delete(DBContract.CardBackEntity.TABLE_NAME,selection,selectionArgs)
    }

    fun close(){
        mDbHelper?.close()
    }

    companion object{
        private var mInstance: CardBackEntityCRUD? = null
        fun getInstance(context: Context): CardBackEntityCRUD? {
            if(mInstance == null) mInstance = CardBackEntityCRUD(context)
            return mInstance
        }
        
        @SuppressLint("Range")
        fun getCardBackFromCursor(cursor: Cursor): CardBackEntity{
            val id = cursor.getInt(cursor.getColumnIndex(DBContract.CardBackEntity.CARDBACK_ID))
            val url = cursor.getString(cursor.getColumnIndex(DBContract.CardBackEntity.CARDBACK_URL))
            val cardback = CardBackEntity(id,url)
            Log.d("CardBackEntityCRUD",cardback.toLog())
            return cardback
        }
    }
}