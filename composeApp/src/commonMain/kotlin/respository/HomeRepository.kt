package respository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class HomeRepository {
    private val client = HttpClient()
    suspend fun fetchApiResponse(url: String): String {
        val response = client.get(url)
        return response.bodyAsText()
    }
}
