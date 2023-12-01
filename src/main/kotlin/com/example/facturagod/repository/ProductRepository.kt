package com.example.facturagod.repository


import com.example.facturagod.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository : JpaRepository<Product, Long?> {

    fun findById (id: Long?): Product?

    @Query(nativeQuery = true)
    fun filterTotal(@Param("value") value: Int?):List<Product>?


}