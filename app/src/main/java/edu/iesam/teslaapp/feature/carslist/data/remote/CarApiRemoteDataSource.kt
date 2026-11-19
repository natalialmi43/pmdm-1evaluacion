package edu.iesam.teslaapp.feature.carslist.data.remote

import edu.iesam.teslaapp.feature.carslist.domain.Car

class CarApiRemoteDataSource {


    fun getAll(): List<Car> {
        return listOf(
            Car("1", "Mercedes", "GT 63 S 4 - Door Coupe", "11111", "4.0L V8 Biturbo", "€198.500"),
            Car("2", "Mercedes 2", "GT 63 S 4 - Door Coupe", "22222", "4.0L V8 Biturbo", "€198.500"),
            Car("3", "Mercedes 3", "GT 63 S 4 - Door Coupe", "33333", "4.0L V8 Biturbo", "€198.500"),
            Car("4", "Mercedes 4", "GT 63 S 4 - Door Coupe", "44444", "4.0L V8 Biturbo", "€198.500"),
            Car("5", "Mercedes 5", "GT 63 S 4 - Door Coupe", "55555", "4.0L V8 Biturbo", "€198.500"),
            Car("6", "Mercedes 6", "GT 63 S 4 - Door Coupe", "6666", "4.0L V8 Biturbo", "€198.500"),
        )
    }

    fun findById(id: String): Car? {
        return getAll().find { it.id == id }
    }


}