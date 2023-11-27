package com.example.facturagod.repository



import com.example.facturagod.model.Detail
import com.example.facturagod.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface DeatailRepository : JpaRepository<Detail, Long?> {

    fun findById (id: Long?): Detail?


}