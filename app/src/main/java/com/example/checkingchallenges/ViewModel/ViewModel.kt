package com.example.checkingchallenges.ViewModel

import User
import com.example.checkingchallenges.repository.RepositoryUser
import androidx.lifecycle.ViewModel

class ViewModel(private val repository: RepositoryUser) : ViewModel() {

    fun addUser(user: User) {
        repository.addUser(user)
    }

    fun getUser(callback: (List<User>) -> Unit) {
        repository.getUsers(callback)
    }
}
