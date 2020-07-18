package com.codingChallenge.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codingChallenge.R
import com.codingChallenge.databinding.AdapterCardBinding
import com.codingChallenge.models.CardsItem
import com.squareup.picasso.Picasso

/**
 * Home Adapter to show the cars
 * @param cardsList list of card items
 */
class HomeAdapter(private var cardsList: List<CardsItem>): RecyclerView.Adapter<CardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
       return CardsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.adapter_card,parent,false))
    }

    override fun getItemCount(): Int { return cardsList.size }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(cardsList[position])
    }
}

/**
 * CardsViewHolder used in HomeAdapter adapter viewholder
 */
class CardsViewHolder(val biniding:AdapterCardBinding): RecyclerView.ViewHolder(biniding.root) {

    fun bind(cardItem: CardsItem){
        // bind the CardWrapper to layout
        biniding.cardWrapper = CardWrapper(cardItem)
    }

    companion object {
        /**
         * display the image for recyclerview
         */
        @BindingAdapter(value = ["setImageUrl"])
        @JvmStatic
        fun ImageView.bindImageUrl(url: String?) {
            if (url != null && url.isNotBlank()) {
                Picasso.get()
                    .load(url)
                    .into(this)
            }
        }
    }
}

/**
 * @param cardItem display the card item in item
 * to use to set the adapter item
 */
class CardWrapper(var cardItem:CardsItem){

    //Only Title type data
    var getOnlyText :String = cardItem.card?.value ?: ""
    var getOnlyTextColor:String = cardItem.card?.attributes?.textColor ?: ""
    var getOnlyTextSize:Int = cardItem.card?.attributes?.font?.size ?: 18

    // Image data
    var getImagePath : String = cardItem.card?.image?.url ?: ""
    var getImageWidth :Int = cardItem.card?.image?.size?.width ?: 150
    var getImageHeight :Int = cardItem.card?.image?.size?.height ?: 150

    //Title data
    var getTitle :String = cardItem.card?.title?.value ?: ""
    var getTitleTextColor:Int = cardItem.card?.title?.attributes?.textColor?.let { Color.parseColor(it) } ?: Color.WHITE
    var getTitleTextSize:Int = cardItem.card?.title?.attributes?.font?.size ?: 18

    //Description data
    var getDescription :String = cardItem.card?.description?.value ?: ""
    var getDescriptionTitleColor :Int = cardItem.card?.description?.attributes?.textColor?.let { Color.parseColor(it) } ?: Color.WHITE
    var getDescriptionTitleSize :Int = cardItem.card?.description?.attributes?.font?.size ?: 12

    var getCardType:String = cardItem.cardType ?: ""
}

