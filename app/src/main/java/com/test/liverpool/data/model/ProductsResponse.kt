package com.test.liverpool.data.model

data class ProductsResponse(
    val status: Status,
    val statusCode: Int,
    val pageType: String,
    val plpResults: PlpResults
)