package com.example.recyclerviewer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val getAllUsers: ArrayList<UserModel>
        get() {
            val userModelArrayList = ArrayList<UserModel>()

            val selectQuery = "SELECT  * FROM $TABLE_USER"
            val db = this.readableDatabase
            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {
                    val userModel = UserModel()
                    userModel.setIds(c.getInt(c.getColumnIndex(KEY_ID)))
                    userModel.setNames(c.getString(c.getColumnIndex(KEY_FIRSTNAME)))
                    userModel.setHobbys(c.getString(c.getColumnIndex(KEY_HOBBY)))
                    userModelArrayList.add(userModel)
                } while (c.moveToNext())
            }
            return userModelArrayList
        }

    init {

        Log.d("table", CREATE_TABLE_STUDENTS)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_STUDENTS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_USER'")
        onCreate(db)
    }

    fun addUserDetail(name: String, hobby: String): Long {
        val db = this.writableDatabase
        // Creating content values
        val values = ContentValues()
        if(name=="")
        {
         return -1
           }
       else{
            values.put(KEY_FIRSTNAME,name)
            values.put(KEY_HOBBY, hobby)

        // insert row in students table
        return db.insert(TABLE_USER, null, values)}
    }

    fun updateUser(id: Int, name: String, hobby: String): Int {
        val db = this.writableDatabase

        // Creating content values
        val values = ContentValues()
        values.put(KEY_FIRSTNAME, name)
        values.put(KEY_HOBBY, hobby)
        // update row in students table base on students.is value
        return db.update(
            TABLE_USER, values, "$KEY_ID = ?",
            arrayOf(id.toString())
        )
    }

    fun deleteUSer(id: Int) {

        // delete row in students table based on id
        val db = this.writableDatabase
        db.delete(
            TABLE_USER, "$KEY_ID = ?",
            arrayOf(id.toString())
        )
    }

    companion object {

        var DATABASE_NAME = "user_database"
        private val DATABASE_VERSION = 1
        private val TABLE_USER = "users"
        private val KEY_ID = "id"
        private val KEY_FIRSTNAME = "name"
        private val KEY_HOBBY = "hobby"


        private val CREATE_TABLE_STUDENTS = ("CREATE TABLE "
                + TABLE_USER + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT, " + KEY_HOBBY + " TEXT );")
    }

}