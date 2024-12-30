package com.basma.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basma.common.utils.Resource
import com.basma.domain.entity.products.ProductDataModel
import com.basma.domain.usecase.categories.GetCategoriesUseCase
import com.basma.domain.usecase.products.GetProductsUseCase
import com.basma.presentation.contract.TablesScreenContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TablesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TablesScreenContract.TablesState(isLoading = true))
    val uiState: StateFlow<TablesScreenContract.TablesState> get() = _uiState.asStateFlow()

    init {
        handleIntent(TablesScreenContract.TablesIntent.LoadData)
    }

    fun handleIntent(intent: TablesScreenContract.TablesIntent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (intent) {
                is TablesScreenContract.TablesIntent.LoadData -> loadData()
                is TablesScreenContract.TablesIntent.SelectCategory -> loadProductsForSelectedCategory(
                    intent.categoryName
                )

                is TablesScreenContract.TablesIntent.SearchProducts -> searchProducts(intent.query)
                is TablesScreenContract.TablesIntent.AddProductToCart -> addProductToCart(intent.product)
                is TablesScreenContract.TablesIntent.ClearOrder -> clearOrder()
            }
        }
    }


    private fun loadData() {
        viewModelScope.launch {
            val categoriesFlow = getCategoriesUseCase.invoke()
                .onStart { emit(Resource.Loading) }
                .catch { exception ->
                    _uiState.update { it.copy(errorMessage = exception.message ?: "Unknown error") }
                }

            val productsFlow = getProductsUseCase.invoke()
                .onStart { emit(Resource.Loading) }
                .catch { exception ->
                    _uiState.update { it.copy(errorMessage = exception.message ?: "Unknown error") }
                }

            combine(categoriesFlow, productsFlow) { categoriesResource, productsResource ->
                Pair(categoriesResource, productsResource)
            }.collectLatest { (categoriesResource, productsResource) ->
                when (categoriesResource) {
                    is Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        val categories = categoriesResource.data.categoriesList
                        _uiState.update {
                            it.copy(
                                categories = categories,
                                selectedCategory = categories.firstOrNull()?.name ?: "",
                                isLoading = false
                            )
                        }

                        if (categories.isNotEmpty()) {
                            val selectedCategory = categories.first()
                            if (productsResource is Resource.Success) {
                                val products = productsResource.data.productsList
                                val filteredProducts = products.filter {
                                    it.categoryDataModel.name == selectedCategory.name
                                }
                                _uiState.update {
                                    it.copy(
                                        products = products,
                                        filteredProducts = filteredProducts,
                                        isLoading = false
                                    )
                                }
                            }
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(errorMessage = categoriesResource.message, isLoading = false)
                        }
                    }
                }

                if (productsResource is Resource.Error) {
                    _uiState.update {
                        it.copy(errorMessage = productsResource.message, isLoading = false)
                    }
                }
            }
        }
    }


    private fun loadProductsForSelectedCategory(categoryName: String) {
        _uiState.update { currentState ->
            val filteredProducts =
                currentState.products.filter { it.categoryDataModel.name == categoryName }
            currentState.copy(
                selectedCategory = categoryName,
                filteredProducts = filteredProducts
            )
        }
    }

    private fun searchProducts(query: String) {
        _uiState.update { currentState ->
            val filteredProducts = if (query.isBlank()) {
                // If the query is blank, reset to filtered products based on the category
                currentState.products.filter { product ->
                    product.categoryDataModel.name == currentState.selectedCategory
                }
            } else {
                // When a search query is provided, filter product based on its category group
                currentState.products.filter { product ->
                    product.categoryDataModel.name == currentState.selectedCategory &&
                            product.name.contains(query, ignoreCase = true)
                }
            }
            currentState.copy(searchQuery = query, filteredProducts = filteredProducts)
        }
    }

    fun addProductToCart(product: ProductDataModel) {
        _uiState.update {
            val updatedCart = it.cart.toMutableList()
            val existingItem = updatedCart.find { item -> item.product.id == product.id }
            if (existingItem != null) {
                updatedCart.remove(existingItem)
                updatedCart.add(existingItem.copy(quantity = existingItem.quantity + 1))
            } else {
                updatedCart.add(TablesScreenContract.CartItem(product, 1))
            }
            val newTotalPrice = updatedCart.sumOf { item -> item.product.price * item.quantity }
            val formattedPrice = String.format("%.2f", newTotalPrice).toDouble()
            val totalCartSize = updatedCart.sumOf { item -> item.quantity }
            it.copy(cart = updatedCart, totalPrice = formattedPrice, cartSize = totalCartSize)
        }
    }

    private fun clearOrder() {
        _uiState.update { it.copy(cart = emptyList(), totalPrice = 0.0) }
    }
}