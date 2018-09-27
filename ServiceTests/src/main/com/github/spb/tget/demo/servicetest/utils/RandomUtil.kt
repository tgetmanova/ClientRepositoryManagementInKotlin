package com.github.spb.tget.demo.servicetest.utils

import java.util.Random

class RandomUtil {

    companion object {

        private const val ALPHABETIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvqxyz"
        private const val NUMERIC = "0123456789"
        private const val SPECIAL_CHARS = ",./?'|][{}()-_=+*&^:;#@$%\""
        private const val ALL_CHARS = ALPHABETIC + NUMERIC + SPECIAL_CHARS
        private const val TICKS_AT_EPOCH = 621355968000000000L

        private const val DEFAULT_CHARS_COUNT = 15

        val random = Random(System.currentTimeMillis() * 10000 + TICKS_AT_EPOCH)

        val randomInteger: Int
            get() = random.nextInt()

        fun generateRandomString(charactersCount: Int, charactersSet: String = ALL_CHARS): String {
            val chars = charactersSet.split("")
            val stringBuilder = StringBuilder()
            for (i in 1 until charactersCount) {
                stringBuilder.append(getRandomElement(chars))
            }
            return stringBuilder.toString()
        }

        fun getRandomAlpanumeric(charactersCount: Int = DEFAULT_CHARS_COUNT): String {
            return generateRandomString(charactersCount, ALPHABETIC + NUMERIC)
        }

        fun getRandomAlphabetic(charactersCount: Int = DEFAULT_CHARS_COUNT): String {
            return generateRandomString(charactersCount, ALPHABETIC)
        }

        fun getRandomNumeric(charactersCount: Int = DEFAULT_CHARS_COUNT): String {
            return generateRandomString(charactersCount, NUMERIC)
        }

        private inline fun <reified T> getRandomElement(collection: Collection<T>): T {
            val i = random.nextInt(collection.size)
            return collection.toTypedArray()[i]
        }
    }

}