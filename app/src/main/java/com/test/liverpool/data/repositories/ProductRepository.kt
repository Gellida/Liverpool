package com.test.liverpool.data.repositories

import com.test.liverpool.data.source.Api
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getProducts(searchString: String, pageNumber: Int) = api.getProducts(searchString, pageNumber)

    //suspend fun getProductsSortedByRelevance(searchString: String, pageNumber: Int) = api.getProducts(searchString, pageNumber)

    suspend fun getProductsSortedByLowestPrice(searchString: String, pageNumber: Int) = api.getProductsSortedBy(searchString, pageNumber,"sortPrice-0")

    suspend fun getProductsSortedByHighestPrice(searchString: String, pageNumber: Int) = api.getProductsSortedBy(searchString, pageNumber, "sortPrice-1")
}
