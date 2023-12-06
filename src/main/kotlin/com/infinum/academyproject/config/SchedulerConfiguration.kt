package com.infinum.academyproject.config

import com.infinum.academyproject.cardatabase.service.CarDatabaseService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class SchedulerConfiguration(private val carDatabaseService: CarDatabaseService) {
    @Scheduled(fixedRate = 86400000) //one day in miliseconds
    @CacheEvict(allEntries = true, value = ["manufacturer_model"])
    fun notifyUsers(){
        carDatabaseService.getManufacturersAndModels()
    }
}