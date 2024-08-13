package com.test.liverpool.ui

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.liverpool.data.model.Record
import com.test.liverpool.data.repositories.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<List<Record>>()
    val products: LiveData<List<Record>> = _products

    fun fetchProducts(searchString: String, pageNumber: Int) {
        viewModelScope.launch {
            val response = repository.getProducts(searchString, pageNumber)
            if (response.isSuccessful) {
                val plpResults = response.body()?.plpResults
                if (plpResults != null) {
                    val products = plpResults.records
                    Log.d("asd", products.toString())
                    _products.postValue(products)
                }

            }
        }
    }

    fun getProductSortedByLowestPrice(searchString: String, pageNumber: Int){
        viewModelScope.launch {
            val response = repository.getProductsSortedByLowestPrice(searchString, pageNumber)
            if (response.isSuccessful) {
                val plpResults = response.body()?.plpResults
                if (plpResults != null) {
                    val products = plpResults.records
                    val sortedProducts = sortProductsByPrice(products, ascending = true)
                    Log.d("sortedLowestPrice", sortedProducts.toString())
                    _products.postValue(sortedProducts)
                }

            }
        }
    }

    fun getProductSortedByHighestPrice(searchString: String, pageNumber: Int){
        viewModelScope.launch {
            val response = repository.getProductsSortedByHighestPrice(searchString, pageNumber)
            if (response.isSuccessful) {
                val plpResults = response.body()?.plpResults
                if (plpResults != null) {
                    val products = plpResults.records
                    val sortedProducts = sortProductsByPrice(products, ascending = false)
                    Log.d("sortedLowestPrice", sortedProducts.toString())
                    _products.postValue(sortedProducts)
                }

            }
        }
    }

    private fun sortProductsByPrice(products: List<Record>, ascending: Boolean): List<Record> {
        return if (ascending) {
            products.sortedBy { it.promoPrice }
        } else {
            products.sortedByDescending { it.promoPrice }
        }
    }
}

