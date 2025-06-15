package com.lebaillyapp.kartliner.composition

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


/**
 * Composant Compose affichant un canvas animé en temps réel,
 * où chaque point est calculé par une fonction mathématique (i, t, size) -> Offset.
 *
 * @param modifier Modifier pour la taille, position, etc.
 * @param pointCount Nombre de points à dessiner par frame (3k-5k max conseillé)
 * @param pointFunction Fonction de génération de points (i, t, size) -> Offset
 *                      i : index du point
 *                      t : temps en secondes (float)
 *                      size : taille du canvas (IntSize)
 */
@Composable
fun AnimatedMathCanvas(
    modifier: Modifier = Modifier,
    pointCount: Int = 3000,
    scale: Float = 1f,
    offset: Offset = Offset.Zero,
    pointFunction: (Int, Float, IntSize) -> Offset
) {
    var t by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            t += 0.03f
            if (t > (2 * Math.PI).toFloat()) t = 0f
            delay(16L)
        }
    }

    Canvas(modifier = modifier) {
        val canvasSize = IntSize(size.width.toInt(), size.height.toInt())

        val points = List(pointCount) { i -> pointFunction(i, t, canvasSize) }
        val center = points.fold(Offset.Zero) { acc, p -> acc + p } / pointCount.toFloat()
        val targetCenter = Offset(size.width / 2f, size.height / 2f)

        for (p in points) {
            val translated = (p - center) * scale + targetCenter + offset
            drawCircle(
                color = Color.White,
                radius = 1.5f,
                center = translated
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AnimatedMathCanvasPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        AnimatedMathCanvas(pointCount = 2000) { i, t, size ->
            val x = size.width / 2f + 100 * kotlin.math.sin(i * 0.01f + t)
            val y = size.height / 2f + 100 * kotlin.math.cos(i * 0.01f + t)
            Offset(x, y)
        }
    }
}

