package ir.sika.karpardaz.ui

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

typealias BindingInitializer = (LayoutInflater) -> ViewBinding

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

	private var _binding: V? = null

	protected val binding
		get() = _binding ?: throw NullPointerException("ViewBinding is not initialized")

	abstract val bindingInitializer: BindingInitializer

	override fun onDestroy() {
		clearReferences()
		super.onDestroy()
	}

	private fun clearReferences() {
		_binding = null
	}

}