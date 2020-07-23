package com.codingChallenge.ui

import android.graphics.Color
import androidx.test.platform.app.InstrumentationRegistry
import com.codingChallenge.models.Attributes
import com.codingChallenge.models.Card
import com.codingChallenge.models.CardType
import com.codingChallenge.models.CardsItem
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * test cases for CardWrapperTest
 */
class CardWrapperTest {

    val context = InstrumentationRegistry.getInstrumentation().context


    @Test
    fun getOnlyText() {

        // Text is null
        var cardItem = CardsItem(CardType.TEXT.toString(),Card(value = null))
        var cardWrapper = CardWrapper(context,cardItem = cardItem)
        Assert.assertEquals("",cardWrapper.getOnlyText())

        // check with text
        cardItem = CardsItem(CardType.TEXT.toString(),Card(value = "sample"))
        cardWrapper = CardWrapper(context,cardItem = cardItem)
        Assert.assertEquals("sample",cardWrapper.getOnlyText())

    }

    @Test
    fun getOnlyTextColor() {

        // check with text color null
        var cardItem = CardsItem(CardType.TEXT.toString(),Card(attributes = Attributes(textColor = null,font = null)))
        var cardWrapper = CardWrapper(context,cardItem = cardItem)
        Assert.assertEquals(Color.BLACK,cardWrapper.getOnlyTextColor())

        // check with color
        cardItem = CardsItem(CardType.TEXT.toString(),Card(attributes = Attributes(textColor = "#000000",font = null)))
        cardWrapper = CardWrapper(context,cardItem = cardItem)
        Assert.assertEquals(Color.parseColor("#000000"),cardWrapper.getOnlyTextColor())

    }

    @Test
    fun getOnlyTextSize() {
    }

    @Test
    fun getImagePath() {
    }

    @Test
    fun getImageWidth() {
    }

    @Test
    fun getImageHeight() {
    }

    @Test
    fun getImageVisibility() {
    }

    @Test
    fun getTitle() {
    }

    @Test
    fun getTitleTextColor() {
    }

    @Test
    fun getTitleTextSize() {
    }

    @Test
    fun getDescription() {
    }

    @Test
    fun getDescriptionTitleColor() {
    }

    @Test
    fun getDescriptionTitleSize() {
    }

    @Test
    fun getDescriptionVisibility() {
    }

    @Test
    fun getCardType() {
    }

    @Test
    fun getScaleSize() {
    }

    @Test
    fun getDensityScaleSize() {
    }
}