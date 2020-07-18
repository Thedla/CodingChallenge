package com.codingChallenge.models

/**
 * Card types to use the enum
 */
enum class CardType {
    TEXT{
        override fun toString(): String {
            return "text"
        }
    },
    TITLE_DESCRIPTION{
        override fun toString(): String {
            return "title_description"
        }
    },
    IMAGE_TITLE_DESCRIPTION{
        override fun toString(): String {
            return "image_title_description"
        }
    }
}