package com.hussein.ktor_rabbits_client

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hussein.ktor_rabbits_client.data.Rabbit
import com.hussein.ktor_rabbits_client.data.RabbitsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val rabbitsApi: RabbitsApi
) :ViewModel() {


    private val _state = mutableStateOf(RabbitState())
    val state : State<RabbitState> = _state


    init {
        getRandomRabbit()
    }
    fun getRandomRabbit(){
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                _state.value = _state.value.copy(
                    rabbit = rabbitsApi.randomRabbits(),
                    isLoading = false
                )
            }
            catch (e:Exception){
                e.printStackTrace()
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
    data class RabbitState(
        val rabbit: Rabbit?= null,
        val isLoading:Boolean= false
    )
}