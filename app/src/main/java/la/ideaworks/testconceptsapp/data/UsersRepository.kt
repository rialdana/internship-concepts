package la.ideaworks.testconceptsapp.data

import android.util.Log
import la.ideaworks.testconceptsapp.data.network.RemoteDataSource
import la.ideaworks.testconceptsapp.models.User

class UsersRepository (private val remoteDataSource: RemoteDataSource) {

    suspend fun getUsersList() : List<User> {
        return remoteDataSource.getUsersList()
    }

    fun sayMyName(name: String) : String {
        Log.i("Users", "Started call from repository")
        return remoteDataSource.sayMyName(name)
    }
}