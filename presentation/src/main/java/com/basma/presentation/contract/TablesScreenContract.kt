package com.basma.presentation.contract

import com.basma.domain.entity.categories.CategoryDataModel
import com.basma.domain.entity.products.ProductDataModel

class TablesScreenContract {
    data class TablesState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val categories: List<CategoryDataModel> = emptyList(),
        val products: List<ProductDataModel> = emptyList(),
        val filteredProducts: List<ProductDataModel> = emptyList(),
        val cart: List<CartItem> = emptyList(),
        val cartSize: Int = 0,
        val totalPrice: Double = 0.0,
        val searchQuery: String = "",
        val selectedCategory: String = "",
    )

    sealed class TablesIntent {
        object LoadData : TablesIntent()
        data class SelectCategory(val categoryName: String) : TablesIntent()
        data class SearchProducts(val query: String) : TablesIntent()
        data class AddProductToCart(val product: ProductDataModel) : TablesIntent()
        object ClearOrder : TablesIntent()
    }

    data class CartItem(val product: ProductDataModel, val quantity: Int)

}