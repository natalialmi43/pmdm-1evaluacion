package edu.iesam.teslaapp.feature.carslist.domain

interface CarRepository {
    fun findAll(): List<Car>
    fun getById (id: String) : Car?

}