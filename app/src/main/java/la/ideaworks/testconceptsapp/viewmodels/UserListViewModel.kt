package la.ideaworks.testconceptsapp.viewmodels

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import la.ideaworks.testconceptsapp.data.UsersRepository
import la.ideaworks.testconceptsapp.data.network.RemoteDataSource
import la.ideaworks.testconceptsapp.models.User
import la.ideaworks.testconceptsapp.utils.ERROR
import la.ideaworks.testconceptsapp.utils.LOADING
import la.ideaworks.testconceptsapp.utils.SUCCESS

class UserListViewModel : ViewModel() {

    private val repository = UsersRepository(RemoteDataSource())

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>>
        get() = _usersList

    private val _apiStatus = MutableLiveData<String>()
    val apiStatus: LiveData<String>
        get() = _apiStatus

    private val _usersListErrorMessage = MutableLiveData<String>()
    val usersListErrorMessage: LiveData<String>
        get() = _usersListErrorMessage

    private val _messageFromRepository = MutableLiveData<String>()
    val messageFromRepository: LiveData<String>
        get() = _messageFromRepository

    init {
        loadUsers()

    }

    private fun loadUsers() {

        viewModelScope.launch {

            _apiStatus.value = LOADING

            try {

                _usersList.value = repository.getUsersList()

                _apiStatus.value = SUCCESS

            } catch (e: Exception) {
                _apiStatus.value = ERROR
                _usersListErrorMessage.value = e.message
            }
        }
    }

    fun sendMessageFromViewModel(name: String){
        Log.i("Users", "Started call from view Model")
        _messageFromRepository.value = repository.sayMyName(name)
    }

}