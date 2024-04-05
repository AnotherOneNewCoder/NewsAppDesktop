package utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.input.pointer.PointerIcon

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.skia.Image
import org.jetbrains.skiko.Cursor
import java.awt.Desktop
import java.io.FileNotFoundException
import java.net.URI
import java.net.URL

fun handCursor() = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))

fun openUrl(uri: URI) {
    val desktop = Desktop.getDesktop()
    desktop.browse(uri)
}
@OptIn(ExperimentalResourceApi::class)
fun loadImage(url: String): ImageBitmap {
    return try {
        Image.makeFromEncoded(URL(url).readBytes()).toComposeImageBitmap()
    } catch (e: FileNotFoundException) {
        println(e.message)
        Image.makeFromEncoded(URL("https://www.sevcanpeker.com/images/ilanlar/dae075b2dade_tb.jpg").readBytes()).toComposeImageBitmap()

    }
}