package com.example.facturagod.controller

import com.example.facturagod.model.Invoice
import com.example.facturagod.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/invoice")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class InvoiceController {
    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping
    fun list ():List <Invoice>{
        return invoiceService.list()
    }
    @GetMapping("/filter-total/{value}")
    fun listTotals (@PathVariable("value") value: Double ):ResponseEntity<*>{
        return ResponseEntity(invoiceService.filterTotal(value), HttpStatus.OK)
    }
    @PostMapping
    fun save (@RequestBody modelo: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.save(modelo), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody modelo:Invoice):ResponseEntity<Invoice>{
        return ResponseEntity(invoiceService.update(modelo), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody modelo:Invoice):ResponseEntity<Invoice>{
        return ResponseEntity(invoiceService.updateName(modelo), HttpStatus.OK)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(invoiceService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return invoiceService.delete(id)
    }
}