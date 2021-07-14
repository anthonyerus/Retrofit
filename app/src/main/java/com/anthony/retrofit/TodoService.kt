package com.anthony.retrofit

import android.telecom.Call
import retrofit2.http.GET

interface TodoService {
    @GET("todos")
    fun getAllTodos(): retrofit2.Call<List<Todo>>
}