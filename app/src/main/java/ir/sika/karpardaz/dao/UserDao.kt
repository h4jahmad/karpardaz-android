package ir.sika.karpardaz.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.sika.karpardaz.model.User

@Dao
interface UserDao {
	@Query("select * from User where email = :email and password = :password")
	fun getUser(email: String, password: String): User

	@Insert
	fun insertUser(user: User): Long

}