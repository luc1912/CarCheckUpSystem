package com.infinum.academyproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching


@SpringBootApplication
@EnableCaching
class AcademyProjectApplication



fun main(args: Array<String>) {
    runApplication<AcademyProjectApplication>(*args)
}
