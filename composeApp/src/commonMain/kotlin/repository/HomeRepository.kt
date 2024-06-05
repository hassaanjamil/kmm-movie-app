package repository

import data.response.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.request
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import kotlinx.atomicfu.TraceBase.None.append
import kotlinx.serialization.json.Json

class HomeRepository {
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    private val accessToken =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1YjE2MTAxZDQ2NmNkYzBiM2QwMzE0YzI4ZGZiNDIwYiIsInN1YiI6IjYwNzJhNDg5OGRkYzM0MDA3OThhODYyZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vZMpCuN5kxlw4636OuXc5NKZ8S5oMkcWva8fpxRZ5nE"

    suspend fun fetchApiResponse(
        url: String,
        params: HashMap<String, String> = hashMapOf(),
    ): MovieResponse {
        val response = client.request(url) {
            url {
                params.forEach { (key, value) ->
                    parameters.append(key, value)
                    println(key)
                }
            }
            method = HttpMethod.Get
            append(HttpHeaders.Accept, "application/json")
            append(HttpHeaders.Authorization, "Bearer $accessToken")
        }
        if (response.status.value in 200..299) {
            return response.body() as MovieResponse
        }
        return MovieResponse()
    }
}
