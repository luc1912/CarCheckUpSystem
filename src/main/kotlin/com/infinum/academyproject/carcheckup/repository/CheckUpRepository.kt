package com.infinum.academyproject.carcheckup.repository

import com.infinum.academyproject.car.entity.Car
import com.infinum.academyproject.carcheckup.entity.CarCheckUp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CheckUpRepository: JpaRepository<CarCheckUp, Long> , JpaSpecificationExecutor<CarCheckUp>{
    fun save(checkUp: CarCheckUp): CarCheckUp

    fun findByCarOrderByPerformedAtDesc(car: Car): List<CarCheckUp>

}