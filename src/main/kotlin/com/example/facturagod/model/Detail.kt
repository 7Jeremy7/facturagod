package com.example.facturagod.model

import jakarta.persistence.*

@Entity
@Table(name = "detail")
class Detail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var quantity: String? = null
    var price: Double? = null
    @Column (name="invoice_id")
    var invoiceid: String? = null
    @Column (name="product_id")
    var productid: Int? = null
}
