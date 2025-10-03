package io.nativeblocks.nativeblocks.ecommerce

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import io.nativeblocks.core.api.NativeblocksEdition
import io.nativeblocks.core.api.NativeblocksManager
import io.nativeblocks.core.api.provider.logger.INativeLogger
import io.nativeblocks.core.api.provider.logger.LoggerEventLevel
import io.nativeblocks.foundation.FoundationProvider
import io.nativeblocks.nativeblocks.ecommerce.navigation.NavGraph
import io.nativeblocks.nativeblocks.ecommerce.navigation.Screen
import io.nativeblocks.nativeblocks.ecommerce.ui.theme.NativeblocksecommerceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        initializeNativeblocks()

        setContent {
            val navController = rememberNavController()

            LaunchedEffect(intent) {
                handleDeeplink(intent, navController)
            }

            NativeblocksecommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(navController)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        NativeblocksManager.getInstance().destroy()
    }

    private fun initializeNativeblocks() {
        NativeblocksManager.initialize(
            applicationContext = this,
            edition = NativeblocksEdition.Cloud(
                endpoint = "https://api.nativeblocks.io/gateway/init",
                apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3ODI3MzI5OTUsImlkIjoiMDE5N2JiNzktOTAzMC03Y2EzLWIyMjktNWU1NjIxNDQ0NWE3Iiwib3JnIjoiMDE5NmE5YWItM2NjMi03OGY3LTgxMzQtYmI5ZDU3MmUwNDIzIn0.Hiqf_9Zo4pBGrq8H4ndAd1Wj9D-3dw4PLZHGEj67WTI", // Replace with your actual API key
                developmentMode = false
            )
        )

        FoundationProvider.provide()
        NativeblocksManager.getInstance().provideEventLogger("LOGGER", Logger())
    }

    private fun handleDeeplink(intent: Intent, navController: androidx.navigation.NavController) {
        val data: Uri? = intent.data
        if (data != null) {
            val campaignId = when {
                // Handle ecommerce://campaign?id=flash_sale
                data.scheme == "ecommerce" && data.host == "campaign" -> {
                    data.getQueryParameter("id")
                }
                // Handle https://nativeblocks.ecommerce.app/campaign?id=flash_sale
                data.scheme == "https" && data.host == "nativeblocks.ecommerce.app" -> {
                    if (data.path?.startsWith("/campaign") == true) {
                        data.getQueryParameter("id")
                    } else null
                }
                else -> null
            }

            if (campaignId != null) {
                navController.navigate(Screen.Home.createRoute(campaignId)) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }
        }
    }
}

class Logger : INativeLogger {
    override fun log(
        level: LoggerEventLevel,
        event: String,
        message: String,
        parameters: Map<String, String>
    ) {
        val jsonLog = buildString {
            appendLine("{")
            appendLine("  \"level\": \"${level.name}\",")
            appendLine("  \"event\": \"$event\",")
            appendLine("  \"message\": \"$message\",")
            appendLine("  \"parameters\": {")
            parameters.entries.forEachIndexed { index, entry ->
                append("    \"${entry.key}\": \"${entry.value}\"")
                if (index != parameters.entries.size - 1) append(",")
                appendLine()
            }
            appendLine("  }")
            append("}")
        }
        android.util.Log.d("NativeblocksLogger", jsonLog)
    }
}
