package com.example.p7c

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleItem() {
    var counter by remember { mutableStateOf(0) }
    var colorIndex by remember { mutableStateOf(0) }
    val colors = listOf(
        Color.Red, Color.Magenta, Color.Yellow, Color.Green,
        Color.Blue, Color.Cyan, Color.Gray
    )

    var scale by remember { mutableStateOf(1f) }

    val transition = updateTransition(targetState = scale, label = "ScaleTransition")
    val scaleAnim by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 300)
        }
    ) { targetValue ->
        targetValue
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color = colors[colorIndex % colors.size], shape = CircleShape)
            .clickable {
                scale = 1.5f
                colorIndex++
                if (colorIndex >= colors.size) {
                    colorIndex %= colors.size
                }
                counter++
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = counter.toString(),
            style = TextStyle(color = Color.White, fontSize = 20.sp),
            modifier = Modifier.scale(scaleAnim)
        )
    }
}