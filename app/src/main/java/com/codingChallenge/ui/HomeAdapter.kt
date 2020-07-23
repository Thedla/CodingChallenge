package com.codingChallenge.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codingChallenge.R
import com.codingChallenge.databinding.AdapterCardBinding
import com.codingChallenge.databinding.AdapterCardTypeTextBinding
import com.codingChallenge.models.CardType
import com.codingChallenge.models.CardsItem
import com.codingChallenge.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception


/**
 * Home Adapter to show the cars
 * @param cardsList list of card items
 */
class HomeAdapter(private var cardsList: List<CardsItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(Utils.getCardType(viewType)){
            CardType.TEXT -> CardsViewTextHolder(parent.context,DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.adapter_card_type_text,parent,false))
            else -> CardsViewHolder(parent.context,DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.adapter_card,parent,false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return Utils.getCardType(cardsList[position].cardType ?: "")
    }

    override fun getItemCount(): Int { return cardsList.size }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type = Utils.getCardType(holder.itemViewType)
        if(CardType.TEXT == type) {
            val cardHolder = holder as? CardsViewTextHolder
            cardHolder?.bind(cardsList[position])
        }else{
            val cardTextHolder = holder as? CardsViewHolder
            cardTextHolder?.bind(cardsList[position])
        }
    }
}

/**
 * CardsViewHolder used in HomeAdapter adapter viewholder
 */
class CardsViewHolder(val context: Context,val biniding:AdapterCardBinding): RecyclerView.ViewHolder(biniding.root) {

    fun bind(cardItem: CardsItem){
        // bind the CardWrapper to layout
        biniding.cardWrapper = CardWrapper(context,cardItem)
        val wrapper =CardWrapper(context,cardItem)
        Log.i("suji","titleColor -> ${cardItem.card?.title?.attributes?.textColor} desc color -> ${cardItem.card?.description?.attributes?.textColor}" )
    }

    companion object {
        /**
         * display the image for recyclerview
         */
        @BindingAdapter(value = ["setImageUrl","width","height"])
        @JvmStatic
        fun ImageView.bindImageUrl(url: String?,width:Int,height:Int) {
            if (url != null && url.isNotBlank()) {
                Picasso.get()
                    .load(url)
                    .error(R.drawable.ic_launcher_background)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .resize(width,height)
                    .into(this,object :Callback{
                        override fun onSuccess() {
                            //ignore it
                        }

                        override fun onError(e: Exception?) {

                            // first time image load fail then load from network
                            Picasso.get()
                                .load(url)
                                .error(R.drawable.ic_launcher_background)
                                .resize(width,height)
                                .into(this@bindImageUrl)
                        }
                    })
            }
        }
    }
}

/** card type text view holder **/
class CardsViewTextHolder(val context: Context,val biniding:AdapterCardTypeTextBinding): RecyclerView.ViewHolder(biniding.root) {

    fun bind(cardItem: CardsItem){
        // bind the CardWrapper to layout
        biniding.cardWrapper = CardWrapper(context,cardItem)
    }
}

/**
 * @param cardItem display the card item in item
 * to use to set the adapter item
 */
class CardWrapper(var context: Context,var cardItem:CardsItem){

    //Only Title type data
    fun getOnlyText():String = cardItem.card?.value ?: ""
    fun getOnlyTextColor():Int = cardItem.card?.title?.attributes?.textColor?.let {Color.parseColor(it)} ?: Color.BLACK
    fun getOnlyTextSize():Float = getScaleSize(cardItem.card?.attributes?.font?.size ?: 30)

    // Image data
    fun getImagePath(): String = cardItem.card?.image?.url ?: ""
    fun getImageWidth():Int = cardItem.card?.image?.size?.width ?: 150
    fun getImageHeight():Int = cardItem.card?.image?.size?.height ?: 150
    fun getImageVisibility():Int = if(CardType.IMAGE_TITLE_DESCRIPTION.toString().equals(getCardType(),true)) View.VISIBLE else View.GONE

    //Title data
    fun getTitle():String = cardItem.card?.title?.value ?: ""
    fun getTitleTextColor():Int = cardItem.card?.title?.attributes?.textColor?.let { Color.parseColor(it) } ?: Color.BLACK
    fun getTitleTextSize():Float = getScaleSize(cardItem.card?.title?.attributes?.font?.size ?: 18)

    //Description data
    fun getDescription():String = cardItem.card?.description?.value ?: ""
    fun getDescriptionTitleColor():Int = cardItem.card?.description?.attributes?.textColor?.let { Color.parseColor(it) } ?: Color.BLACK
    fun getDescriptionTitleSize():Float = getScaleSize(cardItem.card?.description?.attributes?.font?.size ?: 12)
    fun getDescriptionVisibility():Int = if(CardType.TEXT.toString().equals(getCardType(),true)) View.GONE else View.VISIBLE

    fun getCardType():String = cardItem.cardType ?: ""
    fun getScaleSize(size:Int) :Float = size * context.resources.displayMetrics.scaledDensity
    fun getDensityScaleSize(size:Int) :Int = size * context.resources.displayMetrics.densityDpi
}

