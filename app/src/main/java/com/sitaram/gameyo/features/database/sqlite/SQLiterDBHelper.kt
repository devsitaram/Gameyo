package com.sitaram.gameyo.features.database.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.sql.SQLException

class SQLiterDBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VIRION) {

    // create the companion object
    companion object {
        // initialize the variable
        private const val DATABASE_NAME = "UserDB"
        private const val DATABASE_VIRION = 1
        const val TABLE_NAME = "user"
        const val KEY_ID = "id"
        const val KEY_EMAIL = "email"
        const val KEY_NAME = "username"
        const val KEY_PASSWORD = "password"
        private var userId: String? = null
        private var oldPassword: String? = null
    }

    // create the database
    override fun onCreate(db: SQLiteDatabase) {
        // create the database table
        db.execSQL(" CREATE TABLE " + TABLE_NAME +
                    "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_EMAIL + " TEXT UNIQUE, " + KEY_NAME + " TEXT, " + KEY_PASSWORD + " TEXT " + ")"
        )
    }

    // CRUD statements
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // drop the database table
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // insert the user
    fun registerUser(email: String, username: String, userPassword: String): Boolean? {
        return try {
            val databaseWrite = this.writableDatabase // write only Insert, update, delete query
            val values = ContentValues()
            values.put(KEY_EMAIL, email)
            values.put(KEY_NAME, username)
            values.put(KEY_PASSWORD, userPassword)
            databaseWrite.insert(TABLE_NAME, null, values) // insert the user data in database
            true
        } catch (ex: SQLException) {
            false
        }
    }

    // get data from database
    @SuppressLint("Recycle")
    fun getLoginUsers(name: String, password: String): Boolean {
        // create the object of sqLiteDatabase and call the getReadableDatabase methods
        val sqLiteDatabaseRead = this.readableDatabase
        val cursor = sqLiteDatabaseRead.rawQuery("SELECT * FROM $TABLE_NAME", null)

        // use the while loop
        while (cursor.moveToNext()) {

            // store the data in variable
            val userName = cursor.getString(2) // username
            val userPassword = cursor.getString(3) // password

            // check the user login details are valid or not
            if (name == userName && password == userPassword) {
                cursor.close()
                return true
            }
        }
        cursor.close()
        return false
    }

    // get email
    fun getUserEmail(email: String): Boolean {
        // create the object of sqLiteDatabase and call the getReadableDatabase methods
        val sqLiteDatabaseRead = this.readableDatabase
        val cursor = sqLiteDatabaseRead.rawQuery("SELECT * FROM $TABLE_NAME", null)

        // use the while loop
        while (cursor.moveToNext()) {
            // check the user login details are valid or not
            if (email == cursor.getString(1)) {
                userId = cursor.getString(0)
                oldPassword = cursor.getString(3)
                cursor.close()
                return true
            }
        }
        cursor.close()
        return false
    }

    // update password
    @SuppressLint("Recycle")
    fun updatePassword(newPassword: String, context: Context): Boolean? {
        return try {
            val sqLiteDatabaseWrite = this.writableDatabase
            return if (oldPassword == newPassword) {
                Toast.makeText(context, "Enter the new password!", Toast.LENGTH_SHORT).show()
                false
            } else {
                val updateQuery = "UPDATE $TABLE_NAME SET $KEY_PASSWORD = ? WHERE $KEY_ID = ?"
                val args = arrayOf(newPassword, userId)
                sqLiteDatabaseWrite.execSQL(updateQuery, args)
                return true
            }
        } catch (ex: Exception) {
            Toast.makeText(context, "Enter the valid strong password!", Toast.LENGTH_SHORT).show()
            false
        }
    }
}