package com.basma.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.basma.presentation.contract.TablesScreenContract

@Composable
fun SearchBar(
    uiState: TablesScreenContract.TablesState,
    onSearchQueryChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = uiState.searchQuery,
        onValueChange = { onSearchQueryChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Search for products") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )
}