package ir.sika.karpardaz.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.sika.karpardaz.datasource.UserDataSource
import ir.sika.karpardaz.model.User

class AuthViewModel(private val userDataSource: UserDataSource) : ViewModel() {

	fun register(user: User) {
		userDataSource.register()
	}

	fun login(user: User){
		userDataSource.login()
	}

}

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val userDataSource: UserDataSource):ViewModelProvider.Factory{
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(AuthViewModel::class.java)){
			return AuthViewModel(userDataSource) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}

}