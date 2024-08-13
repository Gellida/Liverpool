package com.test.liverpool.data.usecases

import com.test.liverpool.data.model.ProductsResponse
import com.test.liverpool.data.model.Record
import com.test.liverpool.data.repositories.ProductRepository
import retrofit2.Response
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(query: String, page: Int): Response<ProductsResponse> {
        return repository.getProducts(query, page)
    }
}