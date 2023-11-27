package com.example.facturagod.controller

import com.example.facturagod.model.Detail
import com.example.facturagod.service.DetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/detail")
class DetailController {
    @Autowired
    lateinit var detailService: DetailService

    @GetMapping
    fun list ():List <Detail>{
        return detailService.list()
    }
    @PostMapping
    fun save (@RequestBody modelo: Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.save(modelo), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody modelo:Detail):ResponseEntity<Detail>{
        return ResponseEntity(detailService.update(modelo), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody modelo:Detail):ResponseEntity<Detail>{
        return ResponseEntity(detailService.updateName(modelo), HttpStatus.OK)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(detailService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return detailService.delete(id)
    }
}