package io.nativeblocks.nativeblocks.ecommerce.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : Screen("home")

    data object ProductList : Screen("product_list")

    data object ProductDetail : Screen(
        route = "product_detail/{productId}",
        arguments = listOf(
            navArgument("productId") {
                type = NavType.StringType
            }
        )
    ) {
        fun createRoute(productId: String) = "product_detail/$productId"
    }
}