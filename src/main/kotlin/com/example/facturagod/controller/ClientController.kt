package com.example.facturagod.controller

import com.example.facturagod.model.Client
import com.example.facturagod.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
class ClientController {
    @Autowired
    lateinit var clientService: ClientService
    @GetMapping
    fun list ():List <Client>{
        return clientService.list()
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
    fun updateName (@RequestBody modelo:Client):ResponseEntity<Client>{
        return ResponseEntity(clientService.updateName(modelo), HttpStatus.OK)
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