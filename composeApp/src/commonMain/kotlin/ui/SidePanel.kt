package ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import newsappdesktop.composeapp.generated.resources.Res
import newsappdesktop.composeapp.generated.resources.logo_image
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import utils.handCursor

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SidePanel(onMenuSelected: (header: String) -> Unit, onNewsSearched: (header: String, searchedText: String) -> Unit) {
    var searchedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(0.15f).fillMaxHeight().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image( painter = painterResource(Res.drawable.logo_image), "Logo", modifier = Modifier.width(100.dp))
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            singleLine = true,
            placeholder = {
                Text("Search")
            },
            value = searchedText,
            onValueChange = {
                searchedText = it
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onNewsSearched("The reuslt of $searchedText is: ", searchedText)
                    },
                    modifier = Modifier.size(40.dp).pointerHoverIcon(handCursor()),
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search button",
                        tint = Color.Black,
                    )
                }
            }
        )
        TextButton(
            onClick = {
                searchedText = ""
                onMenuSelected("Headlines")
            }
        ) {
            Text(
                text = "Headlines",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.pointerHoverIcon(handCursor())
            )
        }
    }
}