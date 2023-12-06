package com.infinum.academyproject.cardatabase.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "carinfo")
class CarDB(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carinfo_seq")
    @SequenceGenerator(name = "carinfo_seq", sequenceName = "carinfo_seq", allocationSize = 1)
    var id: Long = 0,

    @Column(name = "manufacturer")
    var manufacturer: String,

    @Column(name = "model")
    var model: String
) : Serializable






