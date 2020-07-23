package com.codingChallenge.data.HomeCards

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.codingChallenge.data.AppRetrofit
import com.codingChallenge.database.AppDataBase
import com.codingChallenge.database.CardItemEntity
import com.codingChallenge.models.CardsItem
import com.codingChallenge.models.HomeCardResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


/**
 * use the CardsHandler to request the card netwrok calls
 */
class CardsHandler(private val context: Context) {

    val TAG:String = CardsHandler::class.java.simpleName
    var appDataBase :AppDataBase ?= null

    // Update the data using the cardsList
    val cardsList = MutableLiveData<List<CardsItem>>()

    init {
        appDataBase = AppDataBase.getAppDataBase(context = context)

    }


    /**
     * Request for HomeCards
     */
    fun getHomeCards() {
        // update local cards
        getCardsFromLocalData()

        //Get the HomeLandingServices
        var service: HomeLandingServices? =
            AppRetrofit.getAppRetrofit()?.create(HomeLandingServices::class.java)

        //Initiate the network call
        service?.getHomeResponse()?.enqueue(object : Callback<HomeCardResponse> {
            // Handle the Network failure
            override fun onFailure(call: Call<HomeCardResponse>, t: Throwable) {
               Toast.makeText(context,"something whent wrong",Toast.LENGTH_LONG).show()
            }

            //Handle the Response
            override fun onResponse(call: Call<HomeCardResponse>, response: Response<HomeCardResponse>) {
                cardsList.value = response.body()?.page?.cards ?: emptyList()
                //insert local cards to local db
                insertToLocalData(cardsList.value ?: emptyList())
            }
        })
    }


    /** data save into local data base**/
    private fun insertToLocalData(items:List<CardsItem>){
        GlobalScope.launch {
            val cardData =  Gson().toJson(items)
            appDataBase?.cardItemDao()?.insertData(CardItemEntity(null,cardData))
        }
    }

    /** fetch the data from local data base**/
    private fun getCardsFromLocalData(){
        appDataBase?.cardItemDao()?.getAll()?.observeForever(Observer {
            it?.let {
                val listType: Type = object : TypeToken<List<CardsItem>>() {}.type
                cardsList.value = Gson().fromJson(it,listType)
            }
        })
    }

}
