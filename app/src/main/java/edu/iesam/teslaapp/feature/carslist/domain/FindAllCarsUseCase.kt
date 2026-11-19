package edu.iesam.teslaapp.feature.carslist.domain

class FindAllCarsUseCase (private val carRepository: CarRepository) {

    operator fun invoke (): List<Car> {
        return carRepository.findAll()
    }

}