package com.jrb.divishare.data.remote

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object SupabaseClient {
    private const val PROJECT_ID = "aipokcjzeehtprwouylk"
    private const val API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFpcG9rY2p6ZWVodHByd291eWxrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzMzMTU3ODgsImV4cCI6MjA4ODg5MTc4OH0.47PMAUl5XsUDdaRUmLrsHmRXojGa_yKeK9cksmSd74I"

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "$PROJECT_ID.supabase.co"
                path("rest/v1/")
            }
            header("apikey", API_KEY)
            header("Authorization", "Bearer $API_KEY")
            header("Prefer", "return=representation")
            contentType(ContentType.Application.Json)
        }
    }
}