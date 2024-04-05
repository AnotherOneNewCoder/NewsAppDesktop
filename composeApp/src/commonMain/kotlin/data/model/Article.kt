package data.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import utils.DateSerializer
import java.util.Date

@Serializable
data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    @Serializable(with = DateSerializer::class)
    val publishedAt: Date?,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
)