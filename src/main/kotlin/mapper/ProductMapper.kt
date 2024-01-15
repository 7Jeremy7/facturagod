package mapper

import com.example.facturagod.model.Product
import dto.ProductDto

object ProductMapper {

    fun mapToDto(product: Product): ProductDto{

        return ProductDto(
            product.id,
            "${product.description} ${product.brand}"
        )
    }
}