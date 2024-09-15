package com.example.checkingchallenges.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkingchallenges.data.database.entities.User
import com.example.checkingchallenges.repository.RepositoryUser
import kotlinx.coroutines.launch

class DateViewModel(private val repository: RepositoryUser) : ViewModel() {


    private val _eventAdded = MutableLiveData<Boolean>()
    val DateAdded: LiveData<Boolean> get() = _eventAdded


    fun addDate(user: User) {
        viewModelScope.launch {
            try {
                repository.addUser(user)
                _eventAdded.postValue(true)
            } catch (e: Exception) {
                _eventAdded.postValue(false)
            }
        }
    }
}



