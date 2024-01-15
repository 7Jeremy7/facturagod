package com.example.facturagod.model

import jakarta.persistence.*

@Entity
@Table(name = "detail")
class Detail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var quantity: Int? = null
    var price: Double? = null
    @Column (name="invoice_id")
    var invoiceid: Long? = null
    @Column (name="product_id")
    var productid: Long? = null
}
