package com.example.smartparking.data.api

import com.example.smartparking.data.model.LoginResponse
import com.example.smartparking.data.model.UserLogin
import com.example.smartparking.data.model.UserRegister
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

class ApiServiceImpl(
    private val client: HttpClient
): ApiService {
    override suspend fun register(userRegister: UserRegister): LoginResponse {
        try {
            val response: HttpResponse = client.post {
                url(ApiRoutes.BASE_URL + ApiRoutes.REGISTRATION)
                setBody(userRegister)
            }

            return if (response.status.isSuccess()) {
                response.body()
            } else {
                LoginResponse("")
            }

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun auth(userLogin: UserLogin): LoginResponse {
        try {
            val response: HttpResponse = client.post {
                url(ApiRoutes.BASE_URL + ApiRoutes.AUTHENTICATE)
                setBody(userLogin)
            }

            return if (response.status.isSuccess()) {
                response.body()
            } else {
                LoginResponse("")
            }

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }
}