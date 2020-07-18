package com.codingChallenge.data.HomeCards

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codingChallenge.data.AppRetrofit
import com.codingChallenge.models.CardsItem
import com.codingChallenge.models.HomeCardResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * use the CardsHandler to request the card netwrok calls
 */
class CardsHandler {

    companion object{
        val TAG:String = CardsHandler::class.java.simpleName
        // Update the data using the cardsList
        val cardsList = MutableLiveData<List<CardsItem>>()

        /**
         * Request for HomeCards
         */
        fun getHomeCards(){

            //Get the HomeLandingServices
            var service: HomeLandingServices? =AppRetrofit.getAppRetrofit()?.create(HomeLandingServices::class.java)

            //Initiate the network call
            service?.getHomeResponse()?.enqueue(object :Callback<HomeCardResponse>{
                // Handle the Network failure
                override fun onFailure(call: Call<HomeCardResponse>, t: Throwable) {
                    Log.i(TAG,t.message)
                }

                //Handle the Response
                override fun onResponse(call: Call<HomeCardResponse>, response: Response<HomeCardResponse>) {
                    cardsList.value = response.body()?.page?.cards ?: emptyList()
                }
            })
        }
    }
}