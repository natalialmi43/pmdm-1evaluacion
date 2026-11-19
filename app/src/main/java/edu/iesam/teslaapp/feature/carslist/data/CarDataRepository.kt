package edu.iesam.teslaapp.feature.carslist.data

import edu.iesam.teslaapp.feature.carslist.data.local.CarXmlLocalDataSource
import edu.iesam.teslaapp.feature.carslist.data.remote.CarApiRemoteDataSource
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.domain.CarRepository

class CarDataRepository (private val  carApiRemoteDataSource: CarApiRemoteDataSource) : CarRepository {


    override fun findAll(): List<Car> {
       return carApiRemoteDataSource.getAll()
    }

    override fun getById(id: String): Car? {
        return carApiRemoteDataSource.findById(id)
    }

}