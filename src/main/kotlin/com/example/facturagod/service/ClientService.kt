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
    lateinit var clientRepository: ClientRepository

    fun list ():List<Client>{
        return clientRepository.findAll()
    }
    fun save(model: Client): Client {
        try{
            return clientRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(modelo: Client): Client{
        try {
            clientRepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")

            return clientRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Client): Client{
        try{
            val response = clientRepository.findById(modelo.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                fullname=modelo.fullname //un atributo del modelo
            }
            return clientRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Client?{
        return clientRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = clientRepository.findById(id)
                    ?: throw Exception("ID no existe")
            clientRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}