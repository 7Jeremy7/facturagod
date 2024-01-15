package com.example.facturagod.repository



import com.example.facturagod.model.Detail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface DetailRepository : JpaRepository<Detail, Long?> {

    fun findById (id: Long?): Detail?

    @Query(nativeQuery = true)
    fun sumTotal(@Param("invoice_id") invoiceid: Long?): Double

    @Query(nativeQuery = true)
    fun totalInvoice(invoiceid: Long?): List <Detail>

}

