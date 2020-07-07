package la.ideaworks.testconceptsapp.data

import android.util.Log
import la.ideaworks.testconceptsapp.data.local.LocalDataSource
import la.ideaworks.testconceptsapp.data.local.UserEntity
import la.ideaworks.testconceptsapp.data.network.RemoteDataSource
import la.ideaworks.testconceptsapp.models.User

class UsersRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getUsersList(): List<User> {
        return remoteDataSource.getUsersList()
    }

    fun sayMyName(name: String): String {
        Log.i("Users", "Started call from repository")
        return remoteDataSource.sayMyName(name)
    }

    fun getLocalUsers() = localDataSource.getLocalUsers()

    suspend fun saveLocalUser(user: UserEntity) {
        localDataSource.saveLocalUser(user)
    }

    /*


    suspend fun getUsersFromGitHub() {
        try {
            // Esto retornaria una lista de GithubUsers
            val response = remoteDataSource.getGitHubUsers()

            // Esto guardria los usuarios desde github
            localDataSource.insertGitHubUsers(response)
        }catch (e: Exception){
            Log.e("", "", e)
        }
    }
    */
}