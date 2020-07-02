package la.ideaworks.testconceptsapp.data.network

import android.util.Log
import la.ideaworks.testconceptsapp.models.User

class RemoteDataSource {

    suspend fun getUsersList() : List<User> {

        // throw Exception("Unable to get users")

        return listOf(
            User(0, "Otto", 23, "Jefe"),
            User(1, "Ricardo", 23, "Developer"),
            User(2, "Gaby", 23, "UI/UX"),
            User(3, "Zayda", 23, "PM"),
            User(4, "Oscar", 23, "Developer"),
            User(5, "Enrique", 23, "Developer")
        )
    }

    fun sayMyName(name: String): String {
        Log.i("Users", "returned call from data source")
        return "Hi, this is the remote data source, your name is $name"
    }
}