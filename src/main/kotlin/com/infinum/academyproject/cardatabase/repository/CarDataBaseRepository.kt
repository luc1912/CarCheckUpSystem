package com.infinum.academyproject.cardatabase.repository

import com.infinum.academyproject.cardatabase.entity.CarDB
import org.springframework.data.repository.Repository

interface CarDataBaseRepository : Repository<CarDB, Long>{
    fun save(carDB: CarDB): CarDB

    fun deleteAll()

    fun findById(id: Long): CarDB?

    fun findByManufacturerAndModel(manufacturer: String, model: String): CarDB?
}