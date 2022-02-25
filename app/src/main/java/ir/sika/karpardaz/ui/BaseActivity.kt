package ir.sika.karpardaz.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V: ViewBinding> : AppCompatActivity(){

    protected var _binding: V? = null

    protected val binding get() = _binding!!

    override fun onDestroy() {
        clearReferences()
        super.onDestroy()
    }

    private fun clearReferences() {
        _binding = null
    }

}