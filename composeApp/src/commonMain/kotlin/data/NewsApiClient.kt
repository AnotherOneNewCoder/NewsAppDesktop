package data

import data.model.News
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.CIO

import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import utils.Constants


object NewsApiClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getAllHeadlines(): News {
        val url = Constants.HEADLINES_RU_WITH_API_KEY
        return client.get(url).body()
    }
    suspend fun getSearchNews(searchedText: String): News {
        val url = "https://newsapi.org/v2/everything?q=$searchedText&apiKey=${Constants.apiKey}"
        return client.get(url).body()
    }
}