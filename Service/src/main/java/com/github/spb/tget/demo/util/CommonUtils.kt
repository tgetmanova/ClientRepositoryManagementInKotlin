package com.github.spb.tget.demo.util

import org.apache.commons.lang3.StringUtils

fun String.getSubstringBetweenOrToEnd(from: String, to: String): String {
    if (!this.contains(from)) {
        return ""
    }

    val substringFrom = StringUtils.substringAfter(this, from)
    return if (substringFrom.contains(to)) {
        StringUtils.substringBefore(substringFrom, to)
    } else substringFrom
}