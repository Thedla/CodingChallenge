package com.codingChallenge.data

import com.codingChallenge.constants.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * RetrofitBuilder createt the retrofit instance
 */
class AppRetrofit {

    companion object{
        var retrofit : Retrofit? =null
        // create and get the retrofit object
        fun getAppRetrofit() : Retrofit?{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(AppConstants.NetworkConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}