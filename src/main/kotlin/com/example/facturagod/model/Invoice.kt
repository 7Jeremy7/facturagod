package com.example.facturagod.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "invoice")
class Invoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var code: String? = null
    @Column (name="create_at")
    var createAt: Date? = null
    var total: Double = 0.0
    @Column (name="client_id")
    var clientid: Long? = null
}
