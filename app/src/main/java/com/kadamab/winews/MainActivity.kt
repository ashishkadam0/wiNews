package com.kadamab.winews

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kadamab.winews.adapter.MainAdapter
import com.kadamab.winews.metwork.ApiHelper
import com.kadamab.winews.metwork.RetrofitBuilder
import com.kadamab.winews.model.NewsResponse
import com.kadamab.winews.utility.Status
import com.kadamab.winews.utility.Status.SUCCESS
import com.kadamab.winews.viewModel.MainViewModel
import com.kadamab.winews.viewModel.ViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var progressBar : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        /*
        * initialse the viewMODEL
        *
        * */
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(
            MainViewModel::class.java)
    }

    private fun setupUI() {
        /*
       * setupUI - get all requied references
       *
       * */
        recyclerView = findViewById(R.id.recyclerView)
        progressBar  = findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        //set adapter
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        // set up the observer for news and observe and set news to adapter
        viewModel.getNews().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {  // handle response statuses
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users ->
                           retrieveList(users)
                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: NewsResponse) {
        // set adapter the list and notifi adapter
        adapter.apply {
            addUsers(users.rows)
            notifyDataSetChanged()
        }
    }

}