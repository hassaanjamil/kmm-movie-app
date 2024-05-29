package views.components.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import font

val montMedium: FontFamily
    @Composable
    get() {
        return FontFamily(
            font(
                name = "MontserratMedium",
                res = "MontserratAlternates-Medium",
                FontWeight.Normal,
                FontStyle.Normal,
            ),
        )
    }
