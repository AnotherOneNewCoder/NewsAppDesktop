package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import data.NewsApiClient
import data.model.Article
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    var articles by remember { mutableStateOf(emptyList<Article>()) }
    var headerTitle by remember { mutableStateOf("Headlines") }
    var searchedText by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    LaunchedEffect(searchedText) {
        scope.launch {
            try {
                val newsData = if (searchedText.isNotEmpty()) {
                    NewsApiClient.getSearchNews(searchedText)
                } else {
                    NewsApiClient.getAllHeadlines()
                }
                articles = newsData.articles
            } catch (e: ClientRequestException) {
                println("Error fetching data: ${e.message}")
            }
        }
    }

    Row {
        SidePanel(
            onMenuSelected = {
                headerTitle = it
                searchedText = ""
                articles = emptyList()
            },
            onNewsSearched = { header, search ->
                searchedText = search
                headerTitle = header
                articles = emptyList()
            },
        )
        MainContent(headerTitle, articles.sortedBy { it.publishedAt }.asReversed())
    }
}