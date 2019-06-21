package com.markymark.architecture.rest


import com.markymark.architecture.rest.backendModel.request.LoginRequest
import com.markymark.architecture.rest.backendModel.response.LoginResponse
import com.markymark.architecture.rest.backendModel.response.ResourceListResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyService {

    @POST("login")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>

    @GET("unknown")
    fun getColors(@Query("per_page") perPage: Int): Observable<ResourceListResponse>
}
