package la.ideaworks.testconceptsapp.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource (private val dao: UsersDao) {

    fun getLocalUsers() = dao.getUsersList()

    suspend fun saveLocalUser(user: UserEntity) = withContext(Dispatchers.IO) {
        dao.insertUser(user)
    }

}