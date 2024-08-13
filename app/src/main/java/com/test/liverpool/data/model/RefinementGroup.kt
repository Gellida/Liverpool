package com.test.liverpool.data.model

data class RefinementGroup(
    val dimensionName: String,
    val multiSelect: Boolean,
    val name: String,
    val refinement: List<Refinement>
)