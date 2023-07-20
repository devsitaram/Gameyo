package com.sitaram.gameyo.features.database.room

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: Dao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var usersDao: Dao? = null

    // register
    fun insertUser(newUser: List<UserPojo>) {
        coroutineScope.launch(Dispatchers.IO) {
            usersDao?.registerUser(newUser)
        }
    }

    // login
    fun getUserDetails(name: String, password: String){
        coroutineScope.launch(Dispatchers.IO) {
            usersDao?.getLoginUser(name, password)
        }
    }

    // update
    fun updateUserDetails(newUser: UserPojo) {
        coroutineScope.launch(Dispatchers.IO) {
            usersDao?.updateUser(newUser)
        }
    }

    // delete
    fun deleteCustomerById(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            usersDao?.deleteUserById(id)
        }
    }
}