import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.checkingchallenges.data.database.entities.User
import com.example.checkingchallenges.repository.RepositoryUser

class RegisterViewModel(private val repository: RepositoryUser) : ViewModel() {


    private val _userAdded = MutableLiveData<Boolean>()
    val userAdded: LiveData<Boolean> get() = _userAdded


    fun addUser(user: User) {
        viewModelScope.launch {
            try {
                repository.addUser(user)
                _userAdded.postValue(true)
            } catch (e: Exception) {
                _userAdded.postValue(false)
            }
        }
    }
}



