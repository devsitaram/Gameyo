package com.sitaram.gameyo.features.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable

@SuppressWarnings("AndroidUnresolvedRoomSqlReference")
@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(userList: List<UserPojo>): Completable?

    @Query("SELECT EXISTS(SELECT * FROM user WHERE user_name =:userName AND user_password =:userPassword)")
    fun getLoginUser(userName: String, userPassword: String): Boolean?

    @Query("SELECT * FROM user WHERE user_name = :username")
    fun getAllUsers(username: String)

    @Update
    fun updateUser(user: UserPojo)

    @Query("DELETE FROM user where userId = :id")
    fun deleteUserById(id: Int)
}