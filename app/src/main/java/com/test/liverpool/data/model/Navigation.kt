package com.test.liverpool.data.model

data class Navigation(
    val ancester: List<Ancester>,
    val childs: List<Any>,
    val current: List<Current>
)