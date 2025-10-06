package io.nativeblocks.nativeblocks.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.nativeblocks.nativeblocks.ecommerce.ui.screens.HomeScreen
import io.nativeblocks.nativeblocks.ecommerce.ui.screens.ProductDetailScreen
import io.nativeblocks.nativeblocks.ecommerce.ui.screens.ProductListScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            arguments = Screen.Home.arguments
        ) { backStackEntry ->
            val campaignId = backStackEntry.arguments?.getString("campaignId")
            HomeScreen(
                campaignId = campaignId,
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
                onSeeAllClick = {
                    navController.navigate(Screen.ProductList.route)
                }
            )
        }

        composable(Screen.ProductList.route) {
            ProductListScreen(
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = Screen.ProductDetail.arguments
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(
                productId = productId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}