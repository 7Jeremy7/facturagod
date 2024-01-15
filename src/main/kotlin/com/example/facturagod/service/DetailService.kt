package com.example.facturagod.service

import com.example.facturagod.model.Detail
import com.example.facturagod.model.Product
import com.example.facturagod.repository.DetailRepository
import com.example.facturagod.repository.InvoiceRepository
import com.example.facturagod.repository.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    fun list ():List<Detail>{
        return detailRepository.findAll()
    }
    @Transactional
    fun save(model: Detail): Detail{
        fun calculateAndUpdateTotal(detail: Detail){
            val totalCalculated: Double = detailRepository.sumTotal(detail.invoiceid)
            val invoiceResponse = invoiceRepository.findById(detail.invoiceid)
            invoiceResponse.apply {
                total += totalCalculated
            }
            invoiceRepository.save(invoiceResponse)

        }
        try{
            val response = detailRepository.save(model)
            val responseProduct: Product = productRepository.findById(response.productid)
            responseProduct.apply {
                stock = stock?.minus(model.quantity!!)
            }
            productRepository.save(responseProduct)
            calculateAndUpdateTotal(response)


            var listDetail: List<Detail> = detailRepository.totalInvoice(model.invoiceid)
            var sum = 0.0
            listDetail.map{
                element: Detail ->
                sum += element.price!!*element.quantity!!
            }
            val invoicetoUp = invoiceRepository.findById(model.invoiceid)

            invoicetoUp.apply {
                total = sum
            }

            return response
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
@Transactional
    fun update(model:Detail):Detail{
        try {
            detailRepository.findById(model.id)
                ?: throw Exception("ID no existe")
            val response = detailRepository.save(model)
            val responseProduct: Product = productRepository.findById(response.productid)
            responseProduct.apply {
                stock = stock?.plus(model.quantity ?: 0)
            }
            productRepository.save(responseProduct)

            return response

        }

        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo: Detail): Detail {
        try{
            val response = detailRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                price=modelo.price //un atributo del modelo
            }
            return detailRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?): Detail?{
        return detailRepository.findById(id)
    }
    @Transactional
    fun delete (id: Long?):Boolean?{
        try{
            val response = detailRepository.findById(id)
                ?: throw Exception("ID no existe")
            detailRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
//git commit -n