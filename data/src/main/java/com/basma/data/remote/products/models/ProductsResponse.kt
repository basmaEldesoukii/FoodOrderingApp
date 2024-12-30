package com.basma.data.remote.products.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(val productsList: List<ProductResponse>)