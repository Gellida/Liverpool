package com.test.liverpool.data

import com.test.liverpool.data.model.ProductsResponse
import com.test.liverpool.data.model.SortOption
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("appclienteservices/services/v3/plp")
    suspend fun getProducts(
        @Query("search-string") searchString: String,
        @Query("page-number") pageNumber: Int
    ): Response<ProductsResponse>

    @GET("appclienteservices/services/v3/plp")
    suspend fun getProductsSortedBy(
        @Query("search-string") searchString: String,
        @Query("page-number") pageNumber: Int,
        @Query("st") sortOption: String
    ): Response<ProductsResponse>
}