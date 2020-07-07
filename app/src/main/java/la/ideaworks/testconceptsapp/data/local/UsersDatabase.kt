package la.ideaworks.testconceptsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {

        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getDatabase(context: Context): UsersDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "users_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }
}