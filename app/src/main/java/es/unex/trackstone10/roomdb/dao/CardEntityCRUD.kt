package es.unex.trackstone10.roomdb.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import es.unex.trackstone10.roomdb.Entity.CardEntity
import es.unex.trackstone10.roomdb.database.DBContract
import es.unex.trackstone10.roomdb.database.UserManagerDBHelper

class CardEntityCRUD private constructor(context: Context){
    private val mDBHelper : UserManagerDBHelper?

    init{
        mDBHelper = UserManagerDBHelper(context)
    }

    fun getAll(): List<CardEntity> {
        val db = mDBHelper?.readableDatabase
        val projection = arrayOf(
            DBContract.CardEntity.CARD_ID,
            DBContract.CardEntity.CARD_NAME,
            DBContract.CardEntity.CARD_RARITY,
            DBContract.CardEntity.CARD_CLASS,
            DBContract.CardEntity.CARD_MANACOST,
            DBContract.CardEntity.CARD_INFO,
            DBContract.CardEntity.CARD_TYPE,
            DBContract.CardEntity.CARD_RACE
        )

        val selection: String? = null
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null
        val cursor = db?.query(
            DBContract.CardEntity.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        val cards: ArrayList<CardEntity> = ArrayList()
        if (cursor != null){
            if(cursor.count > 0){
            if (cursor.moveToFirst())
                do {
                    cards.add(getCardFromCursor(cursor))
                } while (cursor.moveToNext())
            }
        cursor.close()
        }
        return cards
    }

    fun insert(card: CardEntity) : Long{
        val db = mDBHelper?.writableDatabase

        val values = ContentValues()
        values.put(DBContract.CardEntity.CARD_ID,card.id)
        values.put(DBContract.CardEntity.CARD_NAME,card.name)
        values.put(DBContract.CardEntity.CARD_RARITY,card.rarity)
        values.put(DBContract.CardEntity.CARD_CLASS,card.cardclass)
        values.put(DBContract.CardEntity.CARD_MANACOST,card.manacost)
        values.put(DBContract.CardEntity.CARD_INFO,card.info)
        values.put(DBContract.CardEntity.CARD_TYPE,card.type)
        values.put(DBContract.CardEntity.CARD_RACE,card.race)

        return db?.insert(DBContract.CardEntity.TABLE_NAME,null,values) ?: -1
    }

    fun deleteAll(){
        val db = mDBHelper?.writableDatabase

        val selection: String? = null
        val selectionArgs:  Array<String> ? = null
        db?.delete(DBContract.CardEntity.TABLE_NAME,selection,selectionArgs)
    }


    fun close(){
        mDBHelper?.close()
    }


    companion object{
        private var mInstance: CardEntityCRUD? = null
        fun getInstance(context: Context): CardEntityCRUD? {
            if(mInstance == null) mInstance = CardEntityCRUD(context)
            return mInstance
        }

        @SuppressLint("Range")
        fun getCardFromCursor(cursor: Cursor): CardEntity{
            val id = cursor.getInt(cursor.getColumnIndex(DBContract.CardEntity.CARD_ID))
            val name = cursor.getString(cursor.getColumnIndex(DBContract.CardEntity.CARD_NAME))
            val rarity = cursor.getInt(cursor.getColumnIndex(DBContract.CardEntity.CARD_RARITY))
            val cardclass = cursor.getInt(cursor.getColumnIndex(DBContract.CardEntity.CARD_CLASS))
            val manacost = cursor.getInt(cursor.getColumnIndex(DBContract.CardEntity.CARD_MANACOST))
            val info = cursor.getString(cursor.getColumnIndex(DBContract.CardEntity.CARD_INFO))
            val type = cursor.getString(cursor.getColumnIndex(DBContract.CardEntity.CARD_TYPE))
            val race = cursor.getString(cursor.getColumnIndex(DBContract.CardEntity.CARD_RACE))
            val card = CardEntity(id,name,rarity,cardclass,manacost,info,type,race)
            Log.d("CardEntityCRUD",card.toLog())
            return card
        }
    }
}