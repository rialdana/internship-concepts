package la.ideaworks.testconceptsapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import la.ideaworks.testconceptsapp.data.UsersRepository
import la.ideaworks.testconceptsapp.data.local.LocalDataSource
import la.ideaworks.testconceptsapp.data.local.UserEntity
import la.ideaworks.testconceptsapp.data.local.UsersDatabase
import la.ideaworks.testconceptsapp.data.network.RemoteDataSource
import la.ideaworks.testconceptsapp.models.User
import la.ideaworks.testconceptsapp.utils.ERROR
import la.ideaworks.testconceptsapp.utils.LOADING
import la.ideaworks.testconceptsapp.utils.SUCCESS

class UserListViewModel(private val applicationContext: Context) : ViewModel() {

    private val dao = UsersDatabase.getDatabase(applicationContext).usersDao()

    private val repository = UsersRepository(RemoteDataSource(), LocalDataSource(dao))

    val localUsersList: LiveData<List<UserEntity>> = repository.getLocalUsers()

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

    fun sendMessageFromViewModel(name: String) {
        Log.i("Users", "Started call from view Model")
        _messageFromRepository.value = repository.sayMyName(name)
    }

    fun saveLocalUser(userId: Int, name: String) {
        viewModelScope.launch {
            repository.saveLocalUser(UserEntity(userId, name))
        }
    }

    class UsersListViewModelFactory(private val app: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(UserListViewModel::class.java)){
                return UserListViewModel(app) as T
            }
            throw IllegalArgumentException("Invalid Viewmodel")
        }

    }

}