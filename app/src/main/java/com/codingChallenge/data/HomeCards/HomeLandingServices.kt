package com.codingChallenge.data.HomeCards

import com.codingChallenge.constants.AppConstants
import com.codingChallenge.models.HomeCardResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Handle the Home land service
 */
interface HomeLandingServices {
    @GET(AppConstants.NetworkConstants.Home_URL)
    fun getHomeResponse(): Call<HomeCardResponse>
}