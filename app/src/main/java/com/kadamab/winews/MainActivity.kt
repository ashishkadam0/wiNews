package com.kadamab.winews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadamab.winews.adapter.NewsListAdapter
import com.kadamab.winews.databinding.ActivityMainBinding
import com.kadamab.winews.network.ApiHelper
import com.kadamab.winews.network.RetrofitBuilder
import com.kadamab.winews.model.NewsResponse
import com.kadamab.winews.utility.Status
import com.kadamab.winews.utility.Status.SUCCESS
import com.kadamab.winews.utility.Utilities
import com.kadamab.winews.viewModel.MainViewModel
import com.kadamab.winews.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar





class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NewsListAdapter
    private lateinit var activityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        //check if active internet is available
        if (!Utilities().isNetworkAvailble(this)) {
            val snackbar = Snackbar.make(
                activityBinding.constraint,
                resources.getString(R.string.no_network),
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar.setAction(R.string.action_ok, View.OnClickListener {
                snackbar.dismiss()
            })
            snackbar.show()
        } else {
            setupViewModel()
            setupUI()
            setupObservers()
        }
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

        activityBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(arrayListOf())
        activityBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activityBinding.recyclerView.context,
                (activityBinding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        //set adapter
        activityBinding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        // set up the observer for news and observe and set news to adapter
        viewModel.getNews().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {  // handle response statuses
                    SUCCESS -> {
                        activityBinding.recyclerView.visibility = View.VISIBLE
                        activityBinding.progressBar.visibility = View.GONE
                        resource.data?.let { users ->
                           retrieveList(users)
                        }
                    }
                    Status.ERROR -> {
                        activityBinding.recyclerView.visibility = View.VISIBLE
                        activityBinding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        activityBinding.progressBar.visibility = View.VISIBLE
                        activityBinding.progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(newsResponse : NewsResponse) {
        // set adapter the list and notifi adapter
        adapter.apply {
            addNews(newsResponse.rows)
            notifyDataSetChanged()
        }
    }

}