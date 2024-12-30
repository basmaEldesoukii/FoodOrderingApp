package com.basma.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.basma.domain.entity.products.ProductDataModel
import com.basma.presentation.contract.TablesScreenContract

@Composable
fun ProductGrid(
    uiState: TablesScreenContract.TablesState,
    onAddProductToCart: (ProductDataModel) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(uiState.filteredProducts) { product ->
            ProductItem(
                product = product,
                quantity = uiState.cart.find { it.product.id == product.id }?.quantity ?: 0,
                onAddToCart = { onAddProductToCart(product) }
            )
        }
    }
}