package com.example.facturagod.service

import com.example.facturagod.model.Client
import com.example.facturagod.model.Invoice
import com.example.facturagod.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    fun list (pageable: Pageable, model:Client): Page<Client> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return clientRepository.findAll(Example.of(model, matcher), pageable)
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



    fun updateName(modelo: Client): Client {
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
    fun updateEmail (id: Long?, newEmail:String?):Client{
        try {
            val client = clientRepository.findById(id)
                ?:throw ResponseStatusException(HttpStatus.NOT_FOUND)
            client.apply {
                email = newEmail ?: email
            }
            return clientRepository.save(client)
        }catch (ex: Exception){
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