package com.example.facturagod.service

import com.example.facturagod.model.Client
import com.example.facturagod.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ClientService {
    @Autowired
    lateinit var clientepository: ClientRepository

    fun list ():List<Client>{
        return clientepository.findAll()
    }
    fun save(model: Client): Client {
        try{
            return clientepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(modelo: Client): Client{
        try {
            clientepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")

            return clientepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Client): Client{
        try{
            val response = clientepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                fullname=modelo.fullname //un atributo del modelo
            }
            return clientepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Client?{
        return clientepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = clientepository.findById(id)
                    ?: throw Exception("ID no existe")
            clientepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}