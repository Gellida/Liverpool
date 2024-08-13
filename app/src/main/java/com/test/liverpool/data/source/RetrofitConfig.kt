package com.test.liverpool.data.source

import com.test.liverpool.data.repositories.ProductRepository

class RetrofitConfig {
    val api: Api = RetrofitInstance.api
    val repository: ProductRepository = ProductRepository(api)
}
