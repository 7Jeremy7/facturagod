package com.example.facturagod.controller

import com.example.facturagod.model.Product
import com.example.facturagod.service.ProductService
import dto.ProductDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class ProductController {
    @Autowired
    lateinit var productService: ProductService
    @GetMapping
    fun list ():List <Product>{
        return productService.list()
    }
    @GetMapping("/listDto")
    fun listDto ():List<ProductDto>{
        return productService.listDto()
    }
    @GetMapping("/filter-total/{value}")
    fun listTotals (@PathVariable("value") value: Int ):ResponseEntity<*>{
        return ResponseEntity(productService.filterTotal(value), HttpStatus.OK)
    }
    @PostMapping
    fun save (@RequestBody modelo: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.save(modelo), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody modelo:Product):ResponseEntity<Product>{
        return ResponseEntity(productService.update(modelo), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody modelo:Product):ResponseEntity<Product>{
        return ResponseEntity(productService.updateName(modelo), HttpStatus.OK)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(productService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return productService.delete(id)
    }
}