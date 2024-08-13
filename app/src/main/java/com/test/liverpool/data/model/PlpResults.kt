package com.test.liverpool.data.model

data class PlpResults(
    val customUrlParam: CustomUrlParam,
    val label: String,
    val metaData: MetaData,
    val navigation: Navigation,
    val plpState: PlpState,
    val records: List<Record>,
    val refinementGroups: List<RefinementGroup>,
    val sortOptions: List<SortOption>
)