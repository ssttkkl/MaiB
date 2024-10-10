package io.ssttkkl.maib

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MaiBestPicture",
    ) {
        App()
    }
}