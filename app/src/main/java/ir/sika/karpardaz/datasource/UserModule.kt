package ir.sika.karpardaz.datasource

import ir.sika.karpardaz.KarpardazApplication

object UserModule {

	@Volatile
	private var USER_DATASOURCE_INSTANCE: UserDataSource? = null

	fun provideUserDataSource(application: KarpardazApplication): UserDataSource {
		return USER_DATASOURCE_INSTANCE ?: synchronized(this) { UserRepository(application.database.userDao()) }
	}

}