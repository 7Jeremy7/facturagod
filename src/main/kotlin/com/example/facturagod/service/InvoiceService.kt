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
    lateinit var InvoiceRepository: InvoiceRepository

    fun list ():List<Invoice>{
        return InvoiceRepository.findAll()
    }
    fun save(model: Invoice): Invoice {
        try{
            return InvoiceRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(modelo: Invoice): Invoice {
        try {
            InvoiceRepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")

            return InvoiceRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Invoice): Invoice {
        try{
            val response = InvoiceRepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                code=modelo.code //un atributo del modelo
            }
            return InvoiceRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Invoice?{
        return InvoiceRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = InvoiceRepository.findById(id)
                    ?: throw Exception("ID no existe")
            InvoiceRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}