package ir.sika.karpardaz.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.sika.karpardaz.dao.UserDao
import ir.sika.karpardaz.model.Cost
import ir.sika.karpardaz.model.Income
import ir.sika.karpardaz.model.Stock
import ir.sika.karpardaz.model.User

@Database(
	entities = [
		User::class,
		Stock::class,
		Income::class,
		Cost::class,
	],
	version = 1
)
abstract class AppDatabase : RoomDatabase() {

	abstract fun userDao(): UserDao

	companion object {
		@Volatile
		private var INSTANCE: AppDatabase? = null

		fun getDatabase(context: Context): AppDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context,
					AppDatabase::class.java,
					"karpardazdb"
				).build()
				instance
			}
		}
	}
}