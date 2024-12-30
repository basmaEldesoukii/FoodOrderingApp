package com.basma.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.basma.presentation.contract.TablesScreenContract

@Composable
fun OrderBar(
    uiState: TablesScreenContract.TablesState,
    onViewOrderClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onViewOrderClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "${uiState.cartSize.toString().padStart(2, '0')}  View order",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "SAR ${uiState.totalPrice}",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Go to order",
            modifier = Modifier.padding(16.dp)
        )
    }
}
