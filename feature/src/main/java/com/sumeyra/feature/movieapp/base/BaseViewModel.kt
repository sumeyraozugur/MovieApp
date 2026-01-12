package com.sumeyra.feature.movieapp.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E, Ef> : ViewModel() {

    private val _uiState = MutableStateFlow(createInitialState())
    val uiState = _uiState.asStateFlow()

    abstract fun createInitialState(): S

    protected val currentState: S get() = uiState.value

    protected fun setState(reduce: S.() -> S) {
        _uiState.value = _uiState.value.reduce()
    }
    private val _effect = Channel<Ef>()
    val effect = _effect.receiveAsFlow()

    protected fun setEffect(builder: () -> Ef) {
        viewModelScope.launch {
            _effect.send(builder())
        }
    }

    abstract fun handleEvent(event: E)

    protected fun launchSafe(
        loadingState: (S.() -> S)? = null,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch {
            loadingState?.let { setState(it) }
            
            runCatching {
                block()
            }.onFailure { t ->
                Log.e("BaseViewModel", "Hata: ${t.message}")
            }
        }
    }
}