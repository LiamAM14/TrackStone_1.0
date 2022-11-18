package es.unex.trackstone10.roomdb.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import es.unex.trackstone10.roomdb.Entity.UserEntity
import es.unex.trackstone10.roomdb.database.DBContract
import es.unex.trackstone10.roomdb.database.UserManagerDBHelper

class UserEntityCRUD private constructor(context: Context){
    private val mDbHelper : UserManagerDBHelper?

    init {
        mDbHelper = UserManagerDBHelper(context)
    }

    fun getAll(): List<UserEntity>{
        val db =  mDbHelper?.readableDatabase
        val projection = arrayOf(
            DBContract.UserEntity.USER_ID,
            DBContract.UserEntity.USERNAME,
            DBContract.UserEntity.PASSWORD,
            DBContract.UserEntity.MAIL
        )

        val selection: String? = null
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null
        val cursor = db?.query(
            DBContract.UserEntity.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        val users: ArrayList<UserEntity> = ArrayList()
        if(cursor != null){
            if(cursor.count > 0){
                cursor.moveToFirst()
                do {
                    users.add(getUserFromCursor(cursor))
                } while(cursor.moveToNext())
            }
        cursor.close()
        }
        return users
    }

    fun insert(user: UserEntity) : Long{
        val db = mDbHelper?.writableDatabase

        val values = ContentValues()
        values.put(DBContract.UserEntity.USER_ID, user.id)
        values.put(DBContract.UserEntity.USERNAME, user.username)
        values.put(DBContract.UserEntity.PASSWORD, user.password)
        values.put(DBContract.UserEntity.MAIL, user.mail)

        return db?.insert(DBContract.UserEntity.TABLE_NAME,null,values) ?: -1
    }

    fun deleteAll(){
        val db = mDbHelper?.writableDatabase

        val selection: String? = null
        val selectionArgs: Array<String> ? = null
        db?.delete(DBContract.UserEntity.TABLE_NAME,selection,selectionArgs)
    }

    fun updateUsername(ID: Int, username : String) : Int? {
        val db = mDbHelper?.readableDatabase
        Log.d("UserEntityCRUD","User ID: $ID")

        val values = ContentValues()
        values.put(DBContract.UserEntity.USERNAME,username)

        val selection: String = DBContract.UserEntity.USERNAME + " = ?"
        val selectionArgs = arrayOf(ID.toString())
        return db?.update(
            DBContract.UserEntity.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
            ?: -1
    }

    fun updatePassword(ID: Int, password : String) : Int? {
        val db = mDbHelper?.readableDatabase
        Log.d("UserEntityCRUD","User ID: $ID")

        val values = ContentValues()
        values.put(DBContract.UserEntity.PASSWORD,password)

        val selection: String = DBContract.UserEntity.PASSWORD + " = ?"
        val selectionArgs = arrayOf(ID.toString())
        return db?.update(
            DBContract.UserEntity.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
            ?: -1
    }

    fun updateMail(ID: Int, mail : String) : Int? {
        val db = mDbHelper?.readableDatabase
        Log.d("UserEntityCRUD","User ID: $ID")

        val values = ContentValues()
        values.put(DBContract.UserEntity.MAIL,mail)

        val selection: String = DBContract.UserEntity.MAIL + " = ?"
        val selectionArgs = arrayOf(ID.toString())
        return db?.update(
            DBContract.UserEntity.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
            ?: -1
    }


    fun close(){
        mDbHelper?.close()
    }

    companion object {
        private var mInstance: UserEntityCRUD? = null
        fun getInstance(context: Context): UserEntityCRUD? {
            if(mInstance == null) mInstance = UserEntityCRUD(context)
            return mInstance
        }

        @SuppressLint("Range")
        fun getUserFromCursor(cursor: Cursor): UserEntity{
            val id = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntity.USER_ID))
            val username = cursor.getString(cursor.getColumnIndex(DBContract.UserEntity.USERNAME))
            val password = cursor.getString(cursor.getColumnIndex(DBContract.UserEntity.PASSWORD))
            val mail = cursor.getString(cursor.getColumnIndex(DBContract.UserEntity.MAIL))
            val user = UserEntity(id,username,password,mail)
            Log.d("UserEntityCRUD",user.toLog())
            return user
        }
    }
}