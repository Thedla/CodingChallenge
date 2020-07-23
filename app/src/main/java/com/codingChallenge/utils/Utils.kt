package com.codingChallenge.utils

import com.codingChallenge.models.CardType

class Utils {

    companion object{

        /** Get the Card type based on ID **/
        fun getCardType(type:Int):CardType{
            return when(type) {
                1 -> CardType.TEXT

                2 -> CardType.TITLE_DESCRIPTION

                3 -> CardType.IMAGE_TITLE_DESCRIPTION

                else -> CardType.TEXT
            }
        }

        /** Get the Card Type ID based on  type **/
        fun getCardType(type: String):Int{
            return when {
                CardType.TEXT.toString().equals(type,true) -> 1

                CardType.TITLE_DESCRIPTION.toString().equals(type,true) -> 2

                CardType.IMAGE_TITLE_DESCRIPTION.toString().equals(type,true) -> 3

                else -> 1
            }
        }
    }
}