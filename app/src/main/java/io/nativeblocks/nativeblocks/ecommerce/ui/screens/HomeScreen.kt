package io.nativeblocks.nativeblocks.ecommerce.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.nativeblocks.nativeblocks.ecommerce.data.ProductRepository
import io.nativeblocks.nativeblocks.ecommerce.ui.components.ProductCard
import io.nativeblocks.nativeblocks.ecommerce.ui.components.AppHeader
import io.nativeblocks.nativeblocks.ecommerce.ui.components.HotDealsBanner
import io.nativeblocks.nativeblocks.ecommerce.ui.components.SectionBanner
import io.nativeblocks.nativeblocks.ecommerce.ui.components.PromoBanner

@Composable
fun HomeScreen(
    onProductClick: (String) -> Unit,
    onSeeAllClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            AppHeader()
        }

        item {
            HotDealsBanner(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            SectionBanner(
                title = "Featured Products",
                subtitle = "Hand-picked items just for you",
                icon = Icons.Default.Star,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(ProductRepository.getFeaturedProducts()) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }

        item {
            PromoBanner(
                title = "🚀 Free Shipping",
                description = "Get free shipping on orders over $50. Limited time offer!",
                buttonText = "Shop Now",
                onButtonClick = onSeeAllClick,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            SectionBanner(
                title = "Trending Now",
                subtitle = "What's popular this week",
                icon = Icons.Default.Favorite,
                backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(ProductRepository.getTrendingProducts()) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }

        item {
            SectionBanner(
                title = "Best Sellers",
                subtitle = "Customer favorites and top rated",
                icon = Icons.Default.Star,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(ProductRepository.getBestSellers()) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }

        item {
            SectionBanner(
                title = "New Arrivals",
                subtitle = "Fresh products just in",
                icon = Icons.Default.Add,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(ProductRepository.getNewArrivals()) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "All Products",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                TextButton(onClick = onSeeAllClick) {
                    Text(
                        text = "View All",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        items(ProductRepository.getAllProducts().chunked(2)) { productPair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                productPair.forEach { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                        modifier = Modifier.weight(1f)
                    )
                }
                if (productPair.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}