package com.infinum.academyproject.carcheckup.repository

import com.infinum.academyproject.car.entity.Car
import com.infinum.academyproject.carcheckup.controller.dto.CheckUpFilter
import com.infinum.academyproject.carcheckup.entity.CarCheckUp
import java.time.LocalDateTime
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Join

object CheckUpSpecifications {

    fun isDateTime(dateTime: LocalDateTime) = Specification<CarCheckUp> { root, query, cb ->
        cb.equal(root.get<LocalDateTime>(CarCheckUp::performedAt.name), dateTime)
    }

    fun isWorker(worker: String) = Specification<CarCheckUp> { root, query, cb ->
        cb.equal(root.get<String>(CarCheckUp::worker.name), worker)
    }

    fun isPrice(price: Int) = Specification<CarCheckUp> { root, query, cb ->
        cb.equal(root.get<Int>(CarCheckUp::price.name), price)
    }

    fun isCar(carId: Long) = Specification<CarCheckUp> { root, query, cb ->
        val carJoin: Join<CarCheckUp, Car> = root.join("car")
        cb.equal(carJoin.get<Long>(Car::id.name), carId)
    }

    fun toSpecification(filter: CheckUpFilter): Specification<CarCheckUp> {
        var spec = Specification<CarCheckUp> { root, query, cb ->
            cb.and()
        }
        if (filter.performedAt != null) {
            spec = spec.and(isDateTime(filter.performedAt))
        }

        if (filter.worker != null) {
            spec = spec.and(isWorker(filter.worker))
        }

        if (filter.price != null) {
            spec = spec.and(isPrice(filter.price))
        }

        if (filter.carId != null) {
            spec = spec.and(isCar(filter.carId))
        }

        return spec
    }

}