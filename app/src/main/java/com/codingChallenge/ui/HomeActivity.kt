package com.codingChallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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
        binding.cardsRecyclerViewHome.layoutManager = GridLayoutManager(this,2)

        adapter = HomeAdapter(viewModel?.getCardsList()?.value ?: emptyList())
        binding.cardsRecyclerViewHome.adapter = adapter
    }

    private fun observeldata() {
        viewModel?.getCardsList()?.observe(this, Observer {
            it?.let {it ->
                 it.filter {
                     it.cardType?.equals(CardType.IMAGE_TITLE_DESCRIPTION.toString(),true) ?: false
                 }.let {
                     adapter = HomeAdapter(it)
                     binding.cardsRecyclerViewHome.adapter = adapter
                 }
            }
        })
    }
}
