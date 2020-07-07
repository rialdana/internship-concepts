package la.ideaworks.testconceptsapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val userId: Int,

    val name: String
)