package com.example.facturagod.controller

import com.example.facturagod.model.Client
import com.example.facturagod.model.Invoice
import com.example.facturagod.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
    @RequestMapping("/client")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class ClientController {
    @Autowired
    lateinit var clientService: ClientService
    @GetMapping
    fun list (model:Client, pageable: Pageable):ResponseEntity<*>{
        val response= clientService.list(pageable,model)
        return ResponseEntity(response, HttpStatus.OK)
    }
    @PostMapping
    fun save (@RequestBody modelo: Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.save(modelo), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody modelo:Client):ResponseEntity<Client>{
        return ResponseEntity(clientService.update(modelo), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody modelo: Client):ResponseEntity<Client>{
        return ResponseEntity(clientService.updateName(modelo), HttpStatus.OK)
    }
    @PatchMapping("/{id}")
    fun updateEmail (@PathVariable id: Long,@RequestBody model: Client):ResponseEntity<Client>{
        return ResponseEntity(clientService.updateEmail(id, model.email), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(clientService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return clientService.delete(id)
    }
}