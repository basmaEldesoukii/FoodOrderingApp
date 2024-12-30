package com.basma.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.basma.common.ui.ErrorComponent
import com.basma.common.ui.ProgressComponent
import com.basma.domain.entity.categories.CategoryDataModel
import com.basma.domain.entity.products.ProductDataModel
import com.basma.presentation.component.CategoryTabRow
import com.basma.presentation.component.OrderBar
import com.basma.presentation.component.ProductGrid
import com.basma.presentation.component.SearchBar
import com.basma.presentation.contract.TablesScreenContract

@Composable
fun TablesScreen(
    uiState: TablesScreenContract.TablesState,
    onSearchQueryChanged: (String) -> Unit,
    onCategorySelected: (CategoryDataModel) -> Unit,
    onAddProductToCart: (ProductDataModel) -> Unit,
    onViewOrderClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        when {
            uiState.isLoading -> ProgressComponent()
            uiState.categories.isEmpty() -> ErrorComponent(message = "No products available.")
            else -> {
                // Search Bar
                SearchBar(uiState, onSearchQueryChanged)
                Spacer(modifier = Modifier.height(8.dp))

                // Categories Tab Row
                CategoryTabRow(uiState, onCategorySelected)
                Spacer(modifier = Modifier.height(8.dp))

                // Product Grid
                ProductGrid(uiState, onAddProductToCart)
                Spacer(modifier = Modifier.weight(1f))

                // Bottom Order Bar
                OrderBar(uiState, onViewOrderClick)
            }
        }
    }
}
