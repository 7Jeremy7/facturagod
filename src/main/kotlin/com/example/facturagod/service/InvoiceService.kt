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
    lateinit var invoiceRpository: InvoiceRepository

    fun list ():List<Invoice>{
        return invoiceRpository.findAll()
    }
    fun save(model: Invoice): Invoice {
        try{
            return invoiceRpository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(modelo: Invoice): Invoice{
        try {
            invoiceRpository.findById(modelo.id)
                    ?: throw Exception("ID no existe")
            return invoiceRpository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Invoice): Invoice{
        try{
            val response = invoiceRpository.findById(modelo.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                code=modelo.code //un atributo del modelo
            }
            return invoiceRpository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Invoice?{
        return invoiceRpository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = invoiceRpository.findById(id)
                    ?: throw Exception("ID no existe")
            invoiceRpository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}