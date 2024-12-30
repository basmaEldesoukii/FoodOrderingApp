package com.basma.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basma.common.ui.ErrorComponent
import com.basma.common.ui.ProgressComponent
import com.basma.presentation.contract.TablesScreenContract
import com.basma.presentation.screen.MenuScreen
import com.basma.presentation.screen.OrdersScreen
import com.basma.presentation.screen.SettingsScreen
import com.basma.presentation.screen.TablesScreen
import com.basma.presentation.viewmodel.TablesViewModel

@Composable
fun AppNavigation(tablesViewModel: TablesViewModel) {

    val navController = rememberNavController()
    val tablesState by tablesViewModel.uiState.collectAsState()

    when {
        tablesState.isLoading -> ShowLoadingState()
        tablesState.errorMessage != null -> ShowErrorState(message = tablesState.errorMessage!!)
        else -> ShowNavigation(navController, tablesState, tablesViewModel)
    }
}

@Composable
fun ShowLoadingState() {
    ProgressComponent()
}

@Composable
fun ShowErrorState(message: String) {
    ErrorComponent(message = message)
}

@Composable
fun ShowNavigation(
    navController: NavHostController,
    uiState: TablesScreenContract.TablesState,
    tablesViewModel: TablesViewModel
) {
    Scaffold(
        bottomBar = { AppNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Tables.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Tables.route) {
                TablesScreen(
                    uiState = uiState,
                    onSearchQueryChanged = { query ->
                        tablesViewModel.handleIntent(
                            TablesScreenContract.TablesIntent.SearchProducts(query)
                        )
                    },
                    onCategorySelected = { category ->
                        tablesViewModel.handleIntent(
                            TablesScreenContract.TablesIntent.SelectCategory(category.name)
                        )
                    },
                    onAddProductToCart = { product ->
                        tablesViewModel.handleIntent(
                            TablesScreenContract.TablesIntent.AddProductToCart(product)
                        )
                    },
                    onViewOrderClick = {
                        tablesViewModel.handleIntent(TablesScreenContract.TablesIntent.ClearOrder)
                    }
                )
            }
            composable(Screen.Orders.route) { OrdersScreen() }
            composable(Screen.Menu.route) { MenuScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}
