package com.example.sevenproject.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.sevenproject.presentation.notes.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(private val bindingInf: (layoutInflater: LayoutInflater) -> VB) : Fragment() {

    protected abstract val viewModel: VM

    protected val binding get() = _binding!!
    private  var _binding: VB? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        listeners()
        setupRequests()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInf.invoke(inflater)
        return binding.root
    }

    protected open fun initialize() {

    }

    protected open fun listeners() {}
    protected open fun setupRequests() {}


    protected fun <T> StateFlow<UiState<T>>.collectState(
        onLoading: () -> Unit,
        onError: (String) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectState.collect{
                    when (it) {
                        is UiState.Empty -> {}
                        is UiState.Error -> { onError(it.msg) }
                        is UiState.Loding -> { onLoading() }
                        is UiState.Success -> {
                            it.data?.let { it1 -> onSuccess(it1) }
                        }
                    }
                }
            }
        }

    }
}
