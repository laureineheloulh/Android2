package com.example.helou_ijreis_projet_crous

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Body
import retrofit2.http.Path

interface CrousService {

    @GET("crous")
    fun getAllCrous(): Call<List<Crous>>

    @GET("crous/{id}")
    fun getCrous(@Path("id") id: String): Call<Crous>

    @PUT("crous/{id}")
    fun setFavorite(@Path("id") id: String): Call<Crous>

}