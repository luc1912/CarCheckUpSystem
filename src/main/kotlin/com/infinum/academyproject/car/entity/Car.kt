package com.infinum.academyproject.car.entity

import com.infinum.academyproject.cardatabase.entity.CarDB
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "car")
class Car(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    @SequenceGenerator(name = "car_seq", sequenceName = "car_seq", allocationSize = 1)
    var id: Long = 0,

    @Column(name = "datewhenadded")
    var dateWhenAdded: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carinfoid")
    var cardb: CarDB,

    @Column(name = "productionyear")
    var productionYear: Int,

    @Column(name = "vin")
    var vin: String,
)
