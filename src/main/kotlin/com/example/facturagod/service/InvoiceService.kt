package com.example.facturagod.service

import com.example.facturagod.model.Invoice
import com.example.facturagod.repository.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class InvoiceService {
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    fun list ():List<Invoice>{
        return invoiceRepository.findAll()
    }
    fun filterTotal(value:Double?): List<Invoice>? {
        return invoiceRepository.filterTotal(value)
    }
    fun save(model: Invoice): Invoice {
        try{
            return invoiceRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(modelo: Invoice): Invoice{
        try {
            invoiceRepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")
            return invoiceRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Invoice): Invoice{
        try{
            val response = invoiceRepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                code=modelo.code //un atributo del modelo
            }
            return invoiceRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Invoice?{
        return invoiceRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = invoiceRepository.findById(id)
                    ?: throw Exception("ID no existe")
            invoiceRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}