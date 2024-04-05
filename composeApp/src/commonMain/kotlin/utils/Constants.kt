package utils

object Constants {
    private const val API_KEY = "4956b1b46a754b279a9fed0fa4db9152"
    private const val HEADLINES_RU = "https://newsapi.org/v2/top-headlines?country=en&apiKey="
    const val HEADLINES_RU_WITH_API_KEY = HEADLINES_RU + API_KEY
    val apiKey
        get() = API_KEY

}