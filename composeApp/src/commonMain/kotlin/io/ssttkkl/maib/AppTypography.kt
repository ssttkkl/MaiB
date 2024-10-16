package io.ssttkkl.maib

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.platform.Font
import maibestpicture.composeapp.generated.resources.Res

suspend fun loadJaFont(): FontFamily {
    return FontFamily(
        Font(
            identity = "NotoSansJP-Regular",
            data = Res.readBytes("font/NotoSansJP-Regular.ttf"),
            weight = FontWeight.Normal
        ),
        Font(
            identity = "NotoSansJP-Bold",
            data = Res.readBytes("font/NotoSansJP-Bold.ttf"),
            weight = FontWeight.Bold
        ),
    )
}

@Composable
fun getAppTypography(): Typography {
    val locale = Locale.current

    var typography by remember { mutableStateOf(Typography()) }
    LaunchedEffect(locale.language) {
        val fontFamily = loadJaFont()

        typography = androidx.compose.material3.Typography(
            displayLarge = typography.displayLarge.copy(fontFamily = fontFamily),
            displayMedium = typography.displayMedium.copy(fontFamily = fontFamily),
            displaySmall = typography.displaySmall.copy(fontFamily = fontFamily),

            headlineLarge = typography.headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = typography.headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = typography.headlineSmall.copy(fontFamily = fontFamily),

            titleLarge = typography.titleLarge.copy(fontFamily = fontFamily),
            titleMedium = typography.titleMedium.copy(fontFamily = fontFamily),
            titleSmall = typography.titleSmall.copy(fontFamily = fontFamily),

            bodyLarge = typography.bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = typography.bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = typography.bodySmall.copy(fontFamily = fontFamily),

            labelLarge = typography.labelLarge.copy(fontFamily = fontFamily),
            labelMedium = typography.labelMedium.copy(fontFamily = fontFamily),
            labelSmall = typography.labelSmall.copy(fontFamily = fontFamily)
        )
    }
    return typography
}

