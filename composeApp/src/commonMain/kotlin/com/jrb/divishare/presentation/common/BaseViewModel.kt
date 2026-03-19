package com.jrb.divishare.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseContract.UiState, UiIntent : BaseContract.UiIntent, UiEffect : BaseContract.UiEffect> : ViewModel() {

    protected abstract val initialState: UiState

    private val _stateStateFlow: MutableStateFlow<UiState> by lazy { MutableStateFlow(initialState) }
    val stateStateFlow: StateFlow<UiState> get() = _stateStateFlow

    protected val currentState: UiState get() = stateStateFlow.value

    private val intentsChannel = Channel<UiIntent>(Channel.UNLIMITED)
    private val effectsChannel = Channel<UiEffect>(Channel.BUFFERED)

    val effectFlow = effectsChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            intentsChannel.consumeAsFlow().collect { intent ->
                reduce(intent)
            }
        }
    }

    // Función para que la UI envíe acciones al ViewModel
    fun sendIntent(intent: UiIntent) {
        viewModelScope.launch {
            intentsChannel.send(intent)
        }
    }

    // Función interna para actualizar el estado
    protected fun updateState(reducer: UiState.() -> UiState) {
        _stateStateFlow.update(reducer)
    }

    // Función interna para enviar efectos de un solo uso a la UI (navegación, toasts)
    protected fun sendEffect(effect: UiEffect) {
        effectsChannel.trySend(effect)
    }

    // El procesador principal que cada ViewModel deberá implementar
    protected abstract fun reduce(intent: UiIntent)
}