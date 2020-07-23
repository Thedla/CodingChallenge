package com.codingChallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingChallenge.R
import com.codingChallenge.databinding.ActivityHomeBinding
import com.codingChallenge.models.CardType

/**
 * Home Activity
 */
class HomeActivity : AppCompatActivity() {

    var viewModel : HomeViewModel ?=null
    lateinit var binding: ActivityHomeBinding
    var adapter: HomeAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(HomeViewModel::class.java)

        //fetch the cards data
        viewModel?.fetchCardsData()

        init()
        observeldata()

    }

    private fun init() {
        binding.cardsRecyclerViewHome.layoutManager = LinearLayoutManager(this)

        adapter = HomeAdapter(viewModel?.getCardsList()?.value ?: emptyList())
        binding.cardsRecyclerViewHome.adapter = adapter
    }

    private fun observeldata() {
        // Observe the cards list
        viewModel?.getCardsList()?.observe(this, Observer {
            it?.let {it ->
                // update the adapter
                adapter = HomeAdapter(it)
                binding.cardsRecyclerViewHome.adapter = adapter
            }
        })
    }
}
