package com.example.testapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.ProductRepository
import com.example.testapplication.data.Result
import com.example.testapplication.data.model.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProductsViewModel(
    private var productRepository: ProductRepository
) : ViewModel() {
    private var _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            productRepository.getProductsList().collectLatest { result ->
                when (result) {
                    is Result.Error -> _showErrorToastChannel.send(true)
                    is Result.Success -> result.data?.let { products ->
                        _products.update { products }
                    }
                }
            }
        }
    }

}





























