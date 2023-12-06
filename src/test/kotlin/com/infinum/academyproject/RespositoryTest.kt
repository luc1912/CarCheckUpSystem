package com.infinum.academyproject

import com.infinum.academyproject.car.entity.Car
import com.infinum.academyproject.car.repository.CarRepository
import com.infinum.academyproject.carcheckup.entity.CarCheckUp
import com.infinum.academyproject.carcheckup.repository.CheckUpRepository
import com.infinum.academyproject.cardatabase.entity.CarDB
import com.infinum.academyproject.cardatabase.repository.CarDataBaseRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.time.LocalDate
import java.time.LocalDateTime

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryTest @Autowired constructor(
    val carRepository: CarRepository,
    val checkUpRepository: CheckUpRepository,
    val carDataBaseRepository: CarDataBaseRepository
) {

    @BeforeEach
    fun setUp() {
        carRepository.deleteAll()
        checkUpRepository.deleteAll()
        carDataBaseRepository.deleteAll()

        val cardb1 = CarDB(
            manufacturer = "Toyota",
            model = "Carmy"
        )

        carDataBaseRepository.save(cardb1)

        val cardb2 = CarDB(
            manufacturer = "Porsche",
            model = "Panamera"
        )

        carDataBaseRepository.save(cardb2)

        val car1 = carDataBaseRepository.findById(cardb1.id)?.let {
            Car(
                dateWhenAdded = LocalDate.of(2023, 7, 1),
                cardb = it,
                productionYear = 2022,
                vin = "JT2BF22K310123456"
            )
        }

        if(car1 != null) carRepository.save(car1)

        val car2 = carDataBaseRepository.findById(cardb2.id)?.let {
            Car(
                dateWhenAdded = LocalDate.of(2023, 7, 2),
                cardb = it,
                productionYear = 2023,
                vin = "1HGCM82633A001234"
            )
        }

       if(car2 != null) carRepository.save(car2)

        val checkup1 = car1?.let {
            carRepository.findById(it.id)?.let {
                CarCheckUp(
                    performedAt = LocalDateTime.of(2023, 7, 1, 10, 30),
                    worker = "John Doe",
                    price = 100.50f,
                    car = it
                )
            }
        }

        if (checkup1 != null) {
            checkUpRepository.save(checkup1)
        }

        val checkup2 = car2?.let {
            carRepository.findById(it.id)?.let {
                CarCheckUp(
                    performedAt = LocalDateTime.of(2023, 7, 2, 11, 15),
                    worker = "Jane Smith",
                    price = 80.25f,
                    car = it
                )
            }
        }

        if (checkup2 != null) {
            checkUpRepository.save(checkup2)
        }


    }

    @Test
    fun `can find all cars`() {
        val allCars = carRepository.findAll()
        assertThat(allCars).hasSize(2)
    }

    @Test
    fun `can find all cars paged`() {
        val pageable = PageRequest.of(0, 5, Sort.by(Car::productionYear.name).descending())
        val allCars = carRepository.findAll(pageable)
        assertThat(allCars.totalPages).isEqualTo(1)
        assertThat(allCars.content[0].productionYear).isEqualTo(2023)
    }

    @Test
    fun `can find all check-ups`() {
        val allCheckUps = checkUpRepository.findAll()
        assertThat(allCheckUps).hasSize(2)
    }

    @Test
    fun `can find all check-ups paged`() {
        val pageable = PageRequest.of(0, 5, Sort.by(CarCheckUp::worker.name).descending())
        val allCheckUpsPage1 = checkUpRepository.findAll(pageable)
        assertThat(allCheckUpsPage1.totalPages).isEqualTo(1)
        assertThat(allCheckUpsPage1.numberOfElements).isEqualTo(2)
        assertThat(allCheckUpsPage1.content[0].worker).isEqualTo("John Doe")
    }

    @Test
    fun `can find by id`() {

        val car1 = Car(
            dateWhenAdded = LocalDate.of(2023, 7, 1),
            cardb = CarDB(
                manufacturer = "Toyota",
                model = "Carmy"
            ),
            productionYear = 2022,
            vin = "JT2BF22K310123456"
        )

        carRepository.save(car1)

        val car2 = Car(
            dateWhenAdded = LocalDate.of(2023, 7, 2),
            cardb = CarDB(
                manufacturer = "Honda",
                model = "Civic"
            ),
            productionYear = 2023,
            vin = "1HGCM82633A001234"
        )

        carRepository.save(car2)

        val foundCar1 = carRepository.findById(car1.id)
        val foundCar2 = carRepository.findById(car2.id)

        assertThat(foundCar1?.productionYear).isEqualTo(2022)

        assertThat(foundCar2?.productionYear).isEqualTo(2023)
    }



}
