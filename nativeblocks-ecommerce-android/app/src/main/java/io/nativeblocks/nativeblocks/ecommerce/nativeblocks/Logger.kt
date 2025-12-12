package io.nativeblocks.nativeblocks.ecommerce.nativeblocks

import io.nativeblocks.core.api.provider.logger.INativeLogger
import io.nativeblocks.core.api.provider.logger.LoggerEventLevel

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
