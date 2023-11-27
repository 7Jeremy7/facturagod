package com.example.facturagod.service

import com.example.facturagod.model.Detail
import com.example.facturagod.repository.DeatailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class DetailService {
    @Autowired
    lateinit var DeatailRepository: DeatailRepository

    fun list ():List<Detail>{
        return DeatailRepository.findAll()
    }
    fun save(model: Detail): Detail {
        try{
            return DeatailRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(modelo: Detail): Detail{
        try {
            DeatailRepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")

            return DeatailRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Detail): Detail{
        try{
            val response = DeatailRepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                quantity=modelo.quantity //un atributo del modelo
            }
            return DeatailRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Detail?{
        return DeatailRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = DeatailRepository.findById(id)
                    ?: throw Exception("ID no existe")
            DeatailRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}