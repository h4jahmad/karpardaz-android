package ir.sika.karpardaz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<V : ViewBinding, VM : ViewModel>() : Fragment() {

	val navController: NavController by lazy {
		NavHostFragment.findNavController(this)
	}

	private var _binding: V? = null
	protected val binding: V
		get() = _binding ?: throw NullPointerException("ViewBinding is not initialized")

	abstract val bindingInitializer: BindingInitializer
	abstract val viewModel: VM

	@Suppress("UNCHECKED_CAST")
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = bindingInitializer(layoutInflater).also { _binding = it as? V }.root

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initViews()
		initCollectors()
	}

	protected inline fun <reified T> collectWithLifecycle(
		flow: Flow<T>,
		lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
		crossinline collector: suspend (T) -> Unit,
	) {
		viewLifecycleOwner.lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
				flow.collectLatest { collector(it) }
			}
		}
	}

	open fun initViews() = Unit

	open fun initCollectors() = Unit

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}