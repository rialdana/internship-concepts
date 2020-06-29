package la.ideaworks.testconceptsapp.viewmodels

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import la.ideaworks.testconceptsapp.models.User
import la.ideaworks.testconceptsapp.utils.ERROR
import la.ideaworks.testconceptsapp.utils.LOADING
import la.ideaworks.testconceptsapp.utils.SUCCESS
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.Exception

class UserListViewModel : ViewModel() {

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>>
        get() = _usersList

    private val _apiStatus = MutableLiveData<String>()
    val apiStatus: LiveData<String>
        get() = _apiStatus

    private val _usersListErrorMessage = MutableLiveData<String>()
    val usersListErrorMessage: LiveData<String>
        get() = _usersListErrorMessage

    init {
        loadUsers()
    }

    private fun loadUsers() {
        _apiStatus.value = LOADING
        /**
         * Este bloque handler nos ayuda a simular una llamada a un endpoint de retrofit que
         * demora 3000 milisegundos (3 segundos)
         */
        Handler().postDelayed({

            /**
             * Si esta fuera una llamada a retrofit utilizando suspend functions, deberia
             * estar envuelta en un bloque try catch como se muestra aqui
             */
            try {
                throw Exception("No pudimos cargar los usuarios")

                _usersList.value = listOf(
                    User(0, "Otto", 23, "Jefe"),
                    User(1, "Ricardo", 23, "Developer"),
                    User(2, "Gaby", 23, "UI/UX"),
                    User(3, "Zayda", 23, "PM"),
                    User(4, "Oscar", 23, "Developer"),
                    User(5, "Enrique", 23, "Developer")
                )

                _apiStatus.value = SUCCESS

            } catch (e: Exception) {
                _apiStatus.value = ERROR
                _usersListErrorMessage.value = e.message
            }
        }, 3000)
    }

}