package com.sitaram.gameyo.features.database.room

import android.content.Context
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

abstract class DatabaseHelper : RoomDatabase() {

    // initialize the final variable
    private val DB_NAME: String = "UserDatabase"

    @Volatile
    private var instance: DatabaseHelper? = null

    // this methods are FIFO data
    @Synchronized
    open fun getDatabaseInstance(context: Context?): DatabaseHelper? {
        // check the instance is null object
        if (instance == null) {
            instance = databaseBuilder(context!!, DatabaseHelper::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
        return instance
    }

    // create the abstract methods
    abstract fun userDao(): Dao
}