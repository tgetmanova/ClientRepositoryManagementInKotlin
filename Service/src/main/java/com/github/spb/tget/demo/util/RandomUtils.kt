package com.github.spb.tget.demo.util

import java.time.LocalDateTime
import java.util.Random

class RandomUtils {

    companion object {
        private val ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvqxyz" + ",./?'|][{}()-_=+*&^:;#@$%\"0123456789"
        private val ALPHABETIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvqxyz"
        private val NUMERIC = "0123456789"
        private val SPECIAL_CHARS = ",./?'|][{}()-_=+*&^:;#@$%\""

        private val TICKS_AT_EPOCH = 621355968000000000L

        private val random = Random(System.currentTimeMillis() * 10000 + TICKS_AT_EPOCH)

        val randomBoolean: Boolean
            get() = random.nextBoolean()

        val randomInteger: Int
            get() = random.nextInt()

        val randomEmailAddress: String
            get() = String.format("%s@%s.test",
                    getRandomAlpanumeric(15), getRandomAlpanumeric(10))

        private fun generateRandomString(charactersCount: Int, charactersSet: String = ALL_CHARS): String {
            val chars = charactersSet.split("")
            val stringBuilder = StringBuilder()
            for (i in 1 until charactersCount) {
                stringBuilder.append(getRandomElement(chars))
            }
            return stringBuilder.toString()
        }

        fun getRandomString(charactersCount: Int): String {
            return generateRandomString(charactersCount, ALL_CHARS)
        }

        fun getRandomAlpanumeric(charactersCount: Int): String {
            return generateRandomString(charactersCount, ALPHABETIC + NUMERIC)
        }

        fun getRandomAlphabetic(charactersCount: Int): String {
            return generateRandomString(charactersCount, ALPHABETIC)
        }

        fun getRandomNumeric(charactersCount: Int): String {
            return generateRandomString(charactersCount, NUMERIC)
        }

        private inline fun <reified T> getRandomElement(collection: Collection<T>): T {
            val i = random.nextInt(collection.size)
            return collection.toTypedArray()[i]
        }

        fun randomDateOfBirthAsAdult(): LocalDateTime {
            return LocalDateTime.now().minusYears(18)
        }
    }
}