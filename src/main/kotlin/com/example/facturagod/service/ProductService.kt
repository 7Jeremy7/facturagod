package com.example.facturagod.service

import com.example.facturagod.model.Product
import com.example.facturagod.repository.ProductRepository
import dto.ProductDto
import mapper.ProductMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list ():List<Product>{
        return productRepository.findAll()
    }
    fun listDto ():List<ProductDto>{
        val productList = productRepository.findAll()
        val mutableList = mutableListOf<ProductDto>()
        productList.map{
            product ->
            val productDto = ProductMapper.mapToDto(product)
            mutableList.add(productDto)
        }
        return mutableList
    }
    fun filterTotal(value:Int?): List<Product>? {
        return productRepository.filterTotal(value)
    }
    fun save(modelo: Product): Product{
        try{
            return productRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(modelo: Product): Product{
        try {
            productRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return productRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo: Product): Product {
        try{
            val response = productRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                description=modelo.description //un atributo del modelo
            }
            return productRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?): Product?{
        return productRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = productRepository.findById(id)
                ?: throw Exception("ID no existe")
            productRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}