package edu.iesam.teslaapp.feature.carslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.domain.GetCarByIdUseCase

class CarDetailsViewModel (private val getCarByIdUseCase: GetCarByIdUseCase): ViewModel() {

    data class UiState (
        val isLoading : Boolean = false,
        val hasError : Boolean = false,
        val car : Car? = null
    )

    private val _UiState = MutableLiveData <UiState> ()
    val uiState : LiveData <UiState> = _UiState


    fun loadCarDetails (id: String){
        _UiState.value = UiState(isLoading = true)
        _UiState.value = UiState(car = getCarByIdUseCase.invoke(id) )
    }


    companion object{
        fun factory (getCarByIdUseCase: GetCarByIdUseCase) = viewModelFactory {
            initializer { CarDetailsViewModel(getCarByIdUseCase)}
        }
    }

}