package com.test.liverpool.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.liverpool.data.source.Api
import com.test.liverpool.data.model.Record

class ProductPagingSource(
    private val api: Api,
    private val query: String
) : PagingSource<Int, Record>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Record> {
        val page = params.key ?: 1
        return try {
            val response = api.getProducts(query, page)
            val products = response.body()?.plpResults?.records ?: emptyList()
            LoadResult.Page(
                data = products,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (products.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Record>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
