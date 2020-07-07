package la.ideaworks.testconceptsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Query("SELECT * FROM users_table")
    fun getUsersList(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

}