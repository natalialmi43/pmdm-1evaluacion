package edu.iesam.teslaapp.feature.carslist.domain

class GetCarByIdUseCase (private val carRepository: CarRepository){

    operator fun invoke (id : String) : Car ? {
        return carRepository.getById(id)
    }

}