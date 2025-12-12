package io.nativeblocks.nativeblocks.ecommerce.nativeblocks

import android.content.Context
import io.nativeblocks.core.api.NativeblocksEdition
import io.nativeblocks.core.api.NativeblocksManager
import io.nativeblocks.foundation.FoundationProvider

fun Context.initializeNativeblocks() {
    NativeblocksManager.initialize(
        applicationContext = this,
        edition = NativeblocksEdition.Cloud(
            endpoint = "https://api.nativeblocks.io/gateway/init",
            apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3OTUwNjk4ODAsImlkIjoiMDE5YTlhY2YtN2QwMi03ODA4LWIzYjAtMDZlODQ4ODM5NzZiIiwib3JnIjoiMDE5ODhkZWQtNjdhMC03ZGQyLWJkOWEtZDUwM2Q5NDQ3ZWI5In0.txZ4S9Khpvh9RWLIL2xZdi-Qs4W5ROJ7bW9c4F-UOoc", // Replace with your actual API key
            developmentMode = false
        )
    )

    FoundationProvider.provide()
    NativeblocksManager.getInstance().provideEventLogger("LOGGER", Logger())
}