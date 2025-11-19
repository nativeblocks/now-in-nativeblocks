package io.nativeblocks.nativeblocks.ecommerce.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.nativeblocks.core.api.NativeblocksFrame
import io.nativeblocks.core.api.NativeblocksManager

/**
 * A dialog that shows server-driven UI from Nativeblocks
 *
 * This version does not use shouldShowDialog as a flag to avoid blank state from SDK multiple reloads.
 * It simply waits for the route, then shows the dialog directly.
 */
@Composable
fun NativeblocksPromotionDialog(
    campaignId: String?,
    onDismiss: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val dialogHeight = (configuration.screenHeightDp * 0.5f).dp

    var isLoading by remember { mutableStateOf(true) }
    var nativeblocksRoute by remember { mutableStateOf<String?>(null) }
    var hasFailed by remember { mutableStateOf(false) }

    LaunchedEffect(campaignId) {
        isLoading = true
        hasFailed = false
        nativeblocksRoute = NativeblocksManager.getInstance().getExperiment("welcome", "fallback")
        isLoading = false
        if (nativeblocksRoute == "fallback") {
            hasFailed = true
        }
    }

    if (hasFailed) {
        LaunchedEffect(Unit) {
            onDismiss()
        }
        return
    }

    if (isLoading) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dialogHeight),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dialogHeight),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        return
    }

    if (nativeblocksRoute != null) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dialogHeight),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dialogHeight)
                ) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        if (nativeblocksRoute.isNullOrEmpty()) {
                            onDismiss()
                        } else {
                            NativeblocksFrame(
                                route = nativeblocksRoute.orEmpty(),
                                routeArguments = mapOf(),
                                loading = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator(
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                },
                                error = {
                                    LaunchedEffect(Unit) {
                                        onDismiss()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}