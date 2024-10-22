package com.cat.cattasticapp

import java.io.Serializable

data class Weight(
    val imperial: String,
    val metric: String
) : Serializable