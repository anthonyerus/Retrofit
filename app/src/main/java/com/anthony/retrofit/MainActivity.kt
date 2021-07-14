package com.anthony.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anthony.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(listOf())
        binding.recycView.adapter = todoAdapter



        val todos: Call<List<Todo>> = RetrofitProvider.service.getAllTodos()

        todos.enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                if (response.isSuccessful){
                    response.body()?.let { todos ->
                        todoAdapter.list = todos
                        todoAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
            }

        })
    }
}