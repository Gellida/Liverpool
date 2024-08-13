package com.test.liverpool.data.model

data class PlpState(
    val categoryId: String,
    val currentFilters: String,
    val currentSortOption: String,
    val firstRecNum: Int,
    val lastRecNum: Int,
    val plpSellerName: String,
    val recsPerPage: Int,
    val totalNumRecs: Int
)