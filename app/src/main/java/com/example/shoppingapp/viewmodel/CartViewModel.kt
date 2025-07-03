package com.example.shoppingapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.model.CartItem
import com.example.shoppingapp.model.Product

class CartViewModel : ViewModel() {

    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addToCart(product: Product) {
        val index = _cartItems.indexOfFirst { it.product.id == product.id }
        if (index >= 0) {
            val existing = _cartItems[index]
            _cartItems[index] = existing.copy(quantity = existing.quantity + 1)
        } else {
            _cartItems.add(CartItem(product, 1))
        }
    }

    fun removeFromCart(product: Product) {
        val index = _cartItems.indexOfFirst { it.product.id == product.id }
        if (index >= 0) {
            val existing = _cartItems[index]
            if (existing.quantity > 1) {
                _cartItems[index] = existing.copy(quantity = existing.quantity - 1)
            } else {
                _cartItems.removeAt(index)
            }
        }
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun getTotalPrice(): Double {
        return _cartItems.sumOf { it.product.price * it.quantity }
    }

    fun getItemCount(): Int {
        return _cartItems.sumOf { it.quantity }
    }
}
