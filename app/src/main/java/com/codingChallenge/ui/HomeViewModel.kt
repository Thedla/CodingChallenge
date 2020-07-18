package com.codingChallenge.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codingChallenge.data.HomeCards.CardsHandler
import com.codingChallenge.models.CardsItem

/**
 * Viewmodel used in HomeActivity
 */
class HomeViewModel(application: Application) : AndroidViewModel(application)  {

    private var cardsList = MutableLiveData<List<CardsItem>>()

    //fetch the cards
    fun fetchCardsData(){
        CardsHandler.getHomeCards()
    }

    //get cards list
    fun getCardsList(): MutableLiveData<List<CardsItem>> {
        return CardsHandler.cardsList
    }
}