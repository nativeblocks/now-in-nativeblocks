package io.nativeblocks.nativeblocks.ecommerce.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Locale



@Composable
fun CountdownTimer(
    modifier: Modifier = Modifier,
    targetDate: String,
    style: CountdownStyle = CountdownStyle.PRIMARY,
    size: CountdownSize = CountdownSize.M,
) {
    var remainingSeconds by remember { mutableLongStateOf(0L) }

    LaunchedEffect(targetDate) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val targetDateMillis = try {
            dateFormat.parse(targetDate)?.time ?: 0L
        } catch (e: Exception) {
            0L
        }


        val maxSeconds = 48L * 60L * 60L
        while (true) {
            val currentTimeMillis = System.currentTimeMillis()
            val diff = (targetDateMillis - currentTimeMillis) / 1000

            remainingSeconds = when {
                diff <= 0 -> 0L
                diff > maxSeconds -> maxSeconds
                else -> diff
            }

            if (remainingSeconds <= 0) break

            delay(1000)
        }
    }

    val hours = remainingSeconds / 3600
    val minutes = (remainingSeconds % 3600) / 60
    val seconds = remainingSeconds % 60

    val (textSize, padding, spacing, boxSize) = when (size) {
        CountdownSize.S -> TimeDisplayConfig(
            textSize = 16.sp,
            padding = 6.dp,
            spacing = 4.dp,
            boxSize = 32.dp
        )
        CountdownSize.M -> TimeDisplayConfig(
            textSize = 24.sp,
            padding = 8.dp,
            spacing = 6.dp,
            boxSize = 48.dp
        )
        CountdownSize.L -> TimeDisplayConfig(
            textSize = 32.sp,
            padding = 12.dp,
            spacing = 8.dp,
            boxSize = 64.dp
        )
    }

    val (backgroundColor, textColor) = when (style) {
        CountdownStyle.PRIMARY -> Pair(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary
        )
        CountdownStyle.SECONDARY -> Pair(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.onSecondary
        )
        CountdownStyle.ACCENT -> Pair(
            MaterialTheme.colorScheme.tertiary,
            MaterialTheme.colorScheme.onTertiary
        )
        CountdownStyle.DANGER -> Pair(
            Color(0xFFE53935),
            Color.White
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TimeBox(
            value = hours,
            backgroundColor = backgroundColor,
            textColor = textColor,
            textSize = textSize,
            padding = padding,
            boxSize = boxSize
        )

        Text(
            text = ":",
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        TimeBox(
            value = minutes,
            backgroundColor = backgroundColor,
            textColor = textColor,
            textSize = textSize,
            padding = padding,
            boxSize = boxSize
        )

        Text(
            text = ":",
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        TimeBox(
            value = seconds,
            backgroundColor = backgroundColor,
            textColor = textColor,
            textSize = textSize,
            padding = padding,
            boxSize = boxSize
        )
    }
}

enum class CountdownStyle {
    PRIMARY,
    SECONDARY,
    ACCENT,
    DANGER
}

enum class CountdownSize {
    S,
    M,
    L
}

@Composable
private fun TimeBox(
    value: Long,
    backgroundColor: Color,
    textColor: Color,
    textSize: TextUnit,
    padding: Dp,
    boxSize: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(boxSize)
                .height(boxSize)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = String.format("%02d", value),
                fontSize = textSize,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}

private data class TimeDisplayConfig(
    val textSize: TextUnit,
    val padding: Dp,
    val spacing: Dp,
    val boxSize: Dp
)

@Preview
@Composable
private fun Preview1() {
    CountdownTimer(
        modifier = Modifier,
        targetDate = "2025-12-12 20:45:00",
        style = CountdownStyle.PRIMARY,
        size = CountdownSize.L
    )
}

@Preview
@Composable
private fun Preview2() {
    CountdownTimer(
        modifier = Modifier,
        targetDate = "2025-12-12 20:45:00",
        style = CountdownStyle.SECONDARY,
        size = CountdownSize.M
    )
}

@Preview
@Composable
private fun Preview3() {
    CountdownTimer(
        modifier = Modifier,
        targetDate = "2025-12-12 20:45:00",
        style = CountdownStyle.DANGER,
        size = CountdownSize.S
    )
}
