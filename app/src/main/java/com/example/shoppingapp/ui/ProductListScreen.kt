package com.example.shoppingapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.shoppingapp.data.ProductRepository
import com.example.shoppingapp.viewmodel.CartViewModel

@Composable
fun ProductListScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val allProducts = ProductRepository.allProducts
    val categories = allProducts.map { it.name }.distinct()

    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val filteredProducts = allProducts.filter {
        (selectedCategory == "All" || it.name == selectedCategory) &&
                (it.name.contains(searchQuery.text, ignoreCase = true) ||
                        it.description.contains(searchQuery.text, ignoreCase = true))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search products...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Filter Chips + Clear Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                item {
                    CategoryChip("All", selectedCategory == "All") {
                        selectedCategory = "All"
                    }
                }
                items(categories.size) { index ->
                    val category = categories[index]
                    CategoryChip(category, selectedCategory == category) {
                        selectedCategory = category
                    }
                }
            }

            TextButton(
                onClick = {
                    selectedCategory = "All"
                    searchQuery = TextFieldValue("")
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Clear")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Product Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredProducts) { product ->
                ProductCard(
                    product = product,
                    onClick = { navController.navigate("productDetail/${product.id}") },
                    onAddToCart = { cartViewModel.addToCart(product) }
                )
            }
        }
    }
}

@Composable
fun CategoryChip(label: String, selected: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(label) },
        colors = FilterChipDefaults.filterChipColors()
    )
}
