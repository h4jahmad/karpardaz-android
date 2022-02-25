package ir.sika.karpardaz.datasource

import ir.sika.karpardaz.dao.UserDao

class UserRepository(private val userDao: UserDao): UserDataSource {

	override fun register() {
		TODO("Not yet implemented")
	}

	override fun login() {
		TODO("Not yet implemented")
	}


}