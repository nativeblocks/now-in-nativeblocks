package io.nativeblocks.nativeblocks.ecommerce.data

object ProductRepository {
    private val sampleProducts = listOf(
        Product(
            id = "1",
            name = "Wireless Headphones",
            description = "High-quality wireless headphones with noise cancellation. Perfect for music lovers and professionals who need crystal clear audio quality.",
            price = 199.99,
            imageUrl = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=300&h=300&fit=crop",
            category = "Electronics"
        ),
        Product(
            id = "2",
            name = "Smart Watch",
            description = "Feature-rich smartwatch with fitness tracking, heart rate monitoring, and smartphone connectivity. Stay connected and healthy.",
            price = 299.99,
            imageUrl = "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=300&h=300&fit=crop",
            category = "Electronics"
        ),
        Product(
            id = "3",
            name = "Laptop Backpack",
            description = "Durable and stylish laptop backpack with multiple compartments. Perfect for students and professionals on the go.",
            price = 79.99,
            imageUrl = "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=300&h=300&fit=crop",
            category = "Accessories"
        ),
        Product(
            id = "4",
            name = "Coffee Maker",
            description = "Premium coffee maker with programmable settings and thermal carafe. Brew perfect coffee every morning.",
            price = 159.99,
            imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=300&h=300&fit=crop",
            category = "Home & Kitchen"
        ),
        Product(
            id = "5",
            name = "Running Shoes",
            description = "Comfortable running shoes with advanced cushioning technology. Perfect for daily runs and workouts.",
            price = 129.99,
            imageUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=300&h=300&fit=crop",
            category = "Sports"
        ),
        Product(
            id = "6",
            name = "Bluetooth Speaker",
            description = "Portable Bluetooth speaker with powerful sound and long battery life. Take your music anywhere.",
            price = 89.99,
            imageUrl = "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=300&h=300&fit=crop",
            category = "Electronics"
        ),
        Product(
            id = "7",
            name = "Desk Lamp",
            description = "Modern LED desk lamp with adjustable brightness and color temperature. Perfect for work and study.",
            price = 49.99,
            imageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=300&h=300&fit=crop",
            category = "Home & Kitchen"
        ),
        Product(
            id = "8",
            name = "Phone Case",
            description = "Protective phone case with shock absorption and precise cutouts. Keep your phone safe and stylish.",
            price = 24.99,
            imageUrl = "https://images.unsplash.com/photo-1556656793-08538906a9f8?w=300&h=300&fit=crop",
            category = "Accessories"
        )
    )

    fun getAllProducts(): List<Product> {
        return sampleProducts
    }

    fun getProductById(id: String): Product? {
        return sampleProducts.find { it.id == id }
    }

    fun getProductsByCategory(category: String): List<Product> {
        return sampleProducts.filter { it.category == category }
    }

    fun getFeaturedProducts(): List<Product> {
        return sampleProducts.take(4)
    }
}