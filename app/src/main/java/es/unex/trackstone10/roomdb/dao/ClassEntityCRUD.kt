package es.unex.trackstone10.roomdb.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import es.unex.trackstone10.roomdb.Entity.ClassEntity
import es.unex.trackstone10.roomdb.database.ClassManagerDBHelper
import es.unex.trackstone10.roomdb.database.DBContract

class ClassEntityCRUD private constructor(context: Context){
    private val mDbHelper : ClassManagerDBHelper?

    init{
        mDbHelper = ClassManagerDBHelper(context)
    }

    fun getAll(): List<ClassEntity>{
        val db = mDbHelper?.readableDatabase
        val projection = arrayOf(
            DBContract.ClassEntity.CLASS_ID,
            DBContract.ClassEntity.HERO_ID,
            DBContract.ClassEntity.CLASS_URL
        )

        val selection: String? = null
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null
        val cursor = db?.query(
            DBContract.ClassEntity.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        val classesE: ArrayList<ClassEntity> = ArrayList()
        if(cursor != null){
            if(cursor.count > 0){
                cursor.moveToFirst()
                do{
                    classesE.add((getClassFromCursor(cursor)))
                } while(cursor.moveToNext())
            }
            cursor.close()
        }
        return classesE
    }

    fun insert(classE: ClassEntity) : Long{
        val db = mDbHelper?.writableDatabase

        val values = ContentValues()
        values.put(DBContract.ClassEntity.CLASS_ID,classE.id)
        values.put(DBContract.ClassEntity.HERO_ID,classE.idhero)
        values.put(DBContract.ClassEntity.CLASS_URL,classE.url)

        return db?.insert(DBContract.ClassEntity.TABLE_NAME,null,values) ?: -1
    }

    fun deleteAll(){
        val db = mDbHelper?.writableDatabase

        val selection: String? = null
        val selectionArgs: Array<String> ? = null
        db?.delete(DBContract.ClassEntity.TABLE_NAME,selection,selectionArgs)
    }

    fun close(){
        mDbHelper?.close()
    }

    companion object{
        private var mInstance: ClassEntityCRUD? = null
        fun getInstance(context: Context): ClassEntityCRUD? {
            if(mInstance == null) mInstance = ClassEntityCRUD(context)
            return mInstance
        }

        @SuppressLint("Range")
        fun getClassFromCursor(cursor: Cursor): ClassEntity{
            val id = cursor.getInt(cursor.getColumnIndex(DBContract.ClassEntity.CLASS_ID))
            val idhero = cursor.getInt(cursor.getColumnIndex(DBContract.ClassEntity.HERO_ID))
            val url = cursor.getInt(cursor.getColumnIndex(DBContract.ClassEntity.CLASS_URL))
            val classE = ClassEntity(id,idhero,url)
            Log.d("ClassEntityCRUD", classE.toLog())
            return classE
        }
    }
}







