package com.example.shoppingapp.data

import com.example.shoppingapp.R
import com.example.shoppingapp.model.Product

object ProductRepository {
    val allProducts = listOf(
        Product("1", "Sneakers", "Comfortable running shoes", R.drawable.sneaker1, 59.99),
        Product("2", "Sneakers", "Comfortable running shoes", R.drawable.sneaker2, 54.99),
        Product("3", "Sneakers", "Comfortable running shoes", R.drawable.sneaker3, 69.99),
        Product("4", "Backpack", "Waterproof backpack", R.drawable.bagpack1, 39.99),
        Product("5", "Backpack", "Waterproof backpack", R.drawable.bagpack2, 41.99),
        Product("6", "Backpack", "Waterproof backpack", R.drawable.bagpack3, 35.00),
        Product("7", "Smart Watch", "Fitness tracking and notifications", R.drawable.smartwatch1, 120.00),
        Product("8", "Smart Watch", "Fitness tracking and notifications", R.drawable.smartwatch2, 135.50),
        Product("9", "Smart Watch", "Fitness tracking and notifications", R.drawable.smartwatch3, 115.75),
        Product("10", "Wireless Headphones", "Noise cancelling headphones", R.drawable.headphone1, 85.00),
        Product("11", "Wireless Headphones", "Noise cancelling headphones", R.drawable.headphone2, 90.00),
        Product("12", "Wireless Headphones", "Noise cancelling headphones", R.drawable.headphone3, 89.50),
        Product("13", "Laptop", "Next Gen Experience", R.drawable.laptop1, 950.00),
        Product("14", "Laptop", "Next Gen Experience", R.drawable.laptop2, 1120.99),
        Product("15", "Laptop", "Next Gen Experience", R.drawable.laptop3, 999.00),
        Product("16", "Coffee Mug", "Insulated travel mug", R.drawable.mug1, 15.49)
    )

    fun getProductById(id: String?): Product? {
        return allProducts.find { it.id == id }
    }
}
