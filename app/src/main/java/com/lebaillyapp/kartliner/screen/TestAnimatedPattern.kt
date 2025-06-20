package com.lebaillyapp.kartliner.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lebaillyapp.kartliner.composition.AnimatedMathCanvas
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


@Composable
fun TestAnimatedPattern() {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var scale by remember { mutableStateOf(1f) }
    val insets = WindowInsets.statusBars.asPaddingValues()
    val doubleTapModifier = Modifier.pointerInput(Unit) {
        detectTapGestures(
            onDoubleTap = {
                offset = Offset.Zero
                scale = 1f
            }
        )
    }

    val pointNumberResolution by remember { mutableStateOf(5000) }


    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(doubleTapModifier)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoomChange, _ ->
                        scale = (scale * zoomChange).coerceIn(0.05f, 50f)
                        offset += pan
                    }
                }
        ) {
            // UI minimaliste en haut sous la status bar
            Column(
                modifier = Modifier
                    .padding(insets)
                    .background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                    .align(Alignment.TopStart)
            ) {
                Text(
                    "Offset X/Y: %.1f / %.1f".format(offset.x, offset.y),
                    color = Color.LightGray,
                    fontSize = 11.sp
                )
                Text(
                    "Zoom: %.2f".format(scale),
                    color = Color.LightGray,
                    fontSize = 11.sp
                )
                Text(
                    "Points: $pointNumberResolution",
                    color = Color.LightGray,
                    fontSize = 9.sp
                )
            }

            AnimatedMathCanvas(
                modifier = Modifier.fillMaxSize(),
                pointCount = pointNumberResolution,
                scale = scale,
                offset = offset
            ) { i, t, _ ->

                //todo @@@@@@@ Whaouu!

                  generateInsectoidWhaoou(i=i,t=t)

                //todo @@@@@@ Spiral Galaxy Tentacle

                //  generateGalaxySpiral(i,t)


                //todo @@@@@@ Spiral Core with Orbiting Pulse

                // generateCorePulse(i, t)

                //todo @@@@@@ Web Fractal Pulse
                //generateFractalWeb(i, t)

                //todo @@@@@@  Chaotic Bloom (flowerburst)
               // generateChaoticBloom(i,t)

                //todo @@@@@@@ Alien Insect
            //       generateInsectAlienPoint(i, t)


            }
        }
    }
}





//Some design pattern ...

fun generateInsectoidWhaoou(i: Int,t: Float): Offset {
    val x = i.toFloat()
    val y = i.toFloat() / 235f
    val k = (4 + sin(y * 2 - t) * 3) * cos(x / 29f)
    val e = y / 8f - 13f
    val d = sqrt(k * k + e * e)
    val q = 3 * sin(k * 2) + 0.3f / k +  sin(y / 25f) * k * (9 + 4 * sin(e * 9 - d * 3 + t * 2))
    val c = d - t
    val px = q + 30 * cos(c)
    val py = q * sin(c) + d * 39
    return Offset(px, py)
}




fun generateGalaxySpiral(i: Int, t: Float): Offset {
    val theta = i * 0.15f + t * 0.5f
    val r = 20f + 0.5f * i + 5f * sin(t + i * 0.05f)
    val x = r * cos(theta) + 20f * sin(i * 0.02f + t)
    val y = r * sin(theta) + 10f * cos(i * 0.01f - t * 0.3f)
    return Offset(x, y)
}

fun generateCorePulse(i: Int, t: Float): Offset {
    val angle = i * 0.2f + t
    val radius = 30f + 10f * sin(i * 0.05f + t)
    val x = radius * cos(angle) + sin(t + i * 0.1f) * 5f
    val y = radius * sin(angle) + cos(t - i * 0.1f) * 5f
    return Offset(x, y)
}


fun generateFractalWeb(i: Int, t: Float): Offset {
    val x = i.toFloat()
    val wave = sin(x / 15f + t) + sin(x / 7f - t * 1.5f)
    val y = wave * x * 0.2f + cos(x * 0.05f + t) * 20f
    return Offset(x, y)
}


fun generateChaoticBloom(i: Int, t: Float): Offset {
    val a = i * 0.2f
    val r = 20f + 10f * sin(a * 3 + t) + 5f * cos(a * 5 - t * 0.7f)
    val x = r * cos(a)
    val y = r * sin(a)
    return Offset(x, y)
}







////////////////Manual training


fun generateInsectAlienPoint(
    i: Int,
    t: Float,
    scale: Float = 1f,
    wingPointCount: Int = 100,
    wingLength: Float = 100f,
    beatAmplitudeDegrees: Float = 60f,
    wingBeat: Float = 1f,
    bodyPointCount: Int = 100,
    bodyWidth: Float = 10f,
    bodyHeight: Float = 100f
): Offset {
    return when {
        i < wingPointCount * 4 -> { // 4 groupes d’ailes (2 spirales, 2 droites)
            generateAllWingTypesPoint(
                i = i,
                t = t,
                scale = scale,
                pointCount = wingPointCount,
                wingLength = wingLength,
                beatAmplitudeDegrees = beatAmplitudeDegrees,
                wingBeat = wingBeat
            )
        }



        i < wingPointCount * 4 + bodyPointCount -> {
            val bodyIndex = i - wingPointCount * 4
            generateSpinePoint(
                i = bodyIndex,
                t = t,
                pointCount = bodyPointCount,
                height = 120f * scale,
                width = 10f * scale,
                breathingAmplitude = 4f * scale,
                breathingSpeed = 2f,
                waveFrequency = 1f
            )
        }



        else -> Offset.Zero
    }
}


fun generateAllWingTypesPoint(
    i: Int,
    t: Float,
    scale: Float,
    pointCount: Int,
    wingLength: Float,
    beatAmplitudeDegrees: Float,
    wingBeat: Float = 8f,
    spiralTurns: Float = 5f,
    spiralRadiusMax: Float = 20f,
    spiralSpinSpeed: Float = 4f
): Offset {
    val groupSize = pointCount
    val totalPoints = groupSize * 4 // 2 spirales + 2 droites
    val localIndex = i % groupSize
    val progress = localIndex / (groupSize - 1).toFloat()

    val group = i / groupSize // 0: spiralG, 1: spiralD, 2: droitG, 3: droitD
    val side = if (group % 2 == 0) -1 else 1 // pair = gauche, impair = droite
    val isSpiral = group < 2

    val phaseOffset = if (side == -1) 0f else Math.PI.toFloat() / 12f
    val baseBeat = sin(t * wingBeat + phaseOffset) * beatAmplitudeDegrees
    val beatAngleDeg = baseBeat * progress * side
    val totalAngleRad = Math.toRadians(beatAngleDeg.toDouble()).toFloat()

    val xBase = side * progress * wingLength * scale

    return if (isSpiral) {
        // SPIRALE (copié-collé sans rien changer)
        val spiralAngle = progress * spiralTurns * 2f * Math.PI.toFloat() + t * spiralSpinSpeed
        val radius = spiralRadiusMax * progress
        val ySpiral = radius * cos(spiralAngle)
        val zSpiral = radius * sin(spiralAngle)

        val wingZBeat = sin(t * wingBeat * 2f + phaseOffset) * 0.3f
        val cosX = cos(wingZBeat)
        val sinX = sin(wingZBeat)

        val yRot = ySpiral * cosX - zSpiral * sinX
        val zRot = ySpiral * sinX + zSpiral * cosX

        val cosA = cos(totalAngleRad)
        val sinA = sin(totalAngleRad)

        val rotatedX = xBase * cosA - yRot * sinA
        val rotatedY = xBase * sinA + yRot * cosA + zRot * 0.5f

        Offset(rotatedX, rotatedY)
    } else {
        // DROITE (copié-collé sans rien changer)
        val yBase = 0f
        val cosA = cos(totalAngleRad)
        val sinA = sin(totalAngleRad)

        val rotatedX = xBase * cosA - yBase * sinA
        val rotatedY = xBase * sinA + yBase * cosA

        Offset(rotatedX, rotatedY)
    }
}


fun generateSpinePoint(
    i: Int,
    t: Float,
    pointCount: Int,
    height: Float,
    width: Float = 10f,
    breathingAmplitude: Float = 1f,
    breathingSpeed: Float = 1f,
    waveFrequency: Float = 3f
): Offset {
    val progress = i / (pointCount - 1f)

    // Position verticale centrée (symétrie verticale)
    val y = (progress - 0.5f) * height

    // Oscillation horizontale + respiration
    val baseWave = sin(progress * waveFrequency * 2f * PI).toFloat()
    val breathing = sin(t * breathingSpeed) * breathingAmplitude

    val x = baseWave * (width + breathing)

    return Offset(x, y)
}
