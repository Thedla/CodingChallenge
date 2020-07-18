package com.codingChallenge.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class HomeCardResponse(@SerializedName("page")
                            val page: Page?)

data class Page(@SerializedName("cards")
                val cards: List<CardsItem>?)

@Entity
data class CardsItem(
                    @PrimaryKey(autoGenerate = true) val id:Int,
                    @SerializedName("card_type")
                     val cardType: String? = "",
                     @SerializedName("card")
                     val card: Card?)

data class Card(@SerializedName("attributes")
                var attributes: Attributes?=null,
                @SerializedName("value")
                var value: String?=null,
                @SerializedName("title")
                val title:Content?=null,
                @SerializedName("description")
                val description:Content?=null,
                @SerializedName("image")
                val image:Image?=null)



data class Content(@SerializedName("attributes")
                       open var attributes: Attributes?=null,
                       @SerializedName("value")
                       open var value: String? = "")

data class Image(@SerializedName("url")
                 var url: String?,
                 @SerializedName("size")
                 var size : ImageSize?)

data class ImageSize( @SerializedName("width")
                      var width: Int?,
                      @SerializedName("height")
                      var height:Int?)

data class Attributes(@SerializedName("text_color")
                      val textColor: String? = "",
                      @SerializedName("font")
                      val font: Font?)

data class Font(@SerializedName("size")
                val size: Int? = 0)




