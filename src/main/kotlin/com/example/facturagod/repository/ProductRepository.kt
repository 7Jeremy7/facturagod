package com.example.facturagod.repository



import com.example.facturagod.model.Client
import com.example.facturagod.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository : JpaRepository<Product, Long?> {

    fun findById (id: Long?): Product?


}