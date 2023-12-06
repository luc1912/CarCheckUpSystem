package com.infinum.academyproject.car.repository

import com.infinum.academyproject.car.entity.Car
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.Repository

interface CarRepository: Repository<Car, Long>{
    fun save(car: Car): Car

    fun findAll(): List<Car>

    fun findAll(pageable: Pageable): Page<Car>

    fun findById(id: Long): Car?

    fun deleteAll()
}