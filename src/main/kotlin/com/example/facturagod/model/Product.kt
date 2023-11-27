package com.example.facturagod.model

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var description: String? = null
    var brand: String? = null
    var price: Double? = null
    var stock: Int? = null
}