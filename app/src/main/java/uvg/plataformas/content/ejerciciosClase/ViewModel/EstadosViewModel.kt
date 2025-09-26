package uvg.plataformas.content.ejerciciosClase.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EstadosViewModel: ViewModel() {
    private val _state = MutableStateFlow(EstadosState())
    val state = _state.asStateFlow()


    init {
        cambiarEstado()
    }

    fun cambiarEstado() {
        _state.update {
            it.copy(
                isLoading = true,
                isSuccess = false,
                isError = false,
                winnerNumber = 0
            )
        }
        viewModelScope.launch {
            delay(3000L)
            val randomNumber = (0..10).random()
            println("Plataformas numero: $randomNumber")
            if (randomNumber % 2 == 0) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        winnerNumber = randomNumber
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }
        }
    }
}