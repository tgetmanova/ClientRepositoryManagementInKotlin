package com.github.spb.tget.demo.test

import com.github.spb.tget.demo.util.getSubstringBetweenOrToEnd
import org.junit.Assert
import org.junit.Test

class UtilTest {

    @Test
    fun getSubstringBetweenOrToEnd_beginMatch() {
        val testString = "Great Britain".getSubstringBetweenOrToEnd("at", "23")
        Assert.assertEquals(" Britain", testString)
    }

    @Test
    fun getSubstringBetweenOrToEnd_beginAndEndMatch() {
        val testString = "Great Britain".getSubstringBetweenOrToEnd("rea", "ai")
        Assert.assertEquals("t Brit", testString)
    }

    @Test
    fun getSubstringBetweenOrToEnd_NoMatch() {
        val testString = "Great Britain".getSubstringBetweenOrToEnd("fe", "ai")
        Assert.assertEquals("", testString)
    }
}