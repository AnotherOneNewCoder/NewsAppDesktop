package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.Article
import newsappdesktop.composeapp.generated.resources.Res
import newsappdesktop.composeapp.generated.resources.no_image
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import utils.handCursor
import utils.loadImage
import utils.openUrl
import java.net.URI

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MainContent(headerTitle: String, articles: List<Article>) {

    if (articles.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White).padding(8.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = headerTitle, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(4.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(articles) {
                    Card(
                        modifier = Modifier.width(400.dp).height(400.dp).padding(4.dp).pointerHoverIcon(
                            handCursor()
                        ).clickable {
                            if (!it.url.isNullOrEmpty()) {
                                openUrl(URI(it.url))
                            }
                        }
                    ) {
                        Box {
                            if (it.urlToImage.isNullOrEmpty()) {
                                Image(painterResource(Res.drawable.no_image), "No image",
                                    modifier = Modifier.size(100.dp).align(Alignment.TopCenter), contentScale = ContentScale.Crop)
                            } else {

                                Image(
                                    loadImage(it.urlToImage), "News Image", contentScale = ContentScale.Crop
                                )
                            }
                            Column(
                                modifier = Modifier.align(Alignment.BottomStart).background(Color.White).padding(4.dp)
                            ) {
                                Text(
                                    it.title ?: "",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    it.content ?: "",
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraLight,
                                    maxLines = 5,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    it.publishedAt.toString() ?: "",
                                    color = Color.Blue,
                                    fontWeight = FontWeight.ExtraLight,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Loading...")
        }
    }
}