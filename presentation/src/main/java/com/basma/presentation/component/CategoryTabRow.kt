package com.basma.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.basma.domain.entity.categories.CategoryDataModel
import com.basma.presentation.contract.TablesScreenContract

@Composable
fun CategoryTabRow(
    uiState: TablesScreenContract.TablesState,
    onCategorySelected: (CategoryDataModel) -> Unit,
) {
    val categories = uiState.categories
    val selectedCategory = uiState.selectedCategory

    val selectedTabIndex = categories.indexOfFirst { it.name == selectedCategory }
        .takeIf { it >= 0 } ?: 0

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp
    ) {
        categories.forEach { category ->
            Tab(
                selected = category.name == selectedCategory,
                onClick = { onCategorySelected(category) }
            ) {
                Text(
                    text = category.name,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    style = if (category.name == selectedCategory) {
                        MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                    } else {
                        MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface)
                    }
                )
            }
        }
    }
}