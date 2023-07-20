package com.sitaram.gameyo.features.database.room

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserPojo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val id: Int? = null

    @ColumnInfo(name = "user_email")
    var userEmail: String? = null

    @ColumnInfo(name = "user_name")
    var userName: String? = null

    @ColumnInfo(name = "user_password")
    var userPassword: String? = null

    // constructor
    constructor(email: String, name: String, password: String){
        this.userEmail = email
        this.userName = name
        this.userPassword = password
    }
}