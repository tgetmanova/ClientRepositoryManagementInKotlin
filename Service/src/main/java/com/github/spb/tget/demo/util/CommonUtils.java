package com.github.spb.tget.demo.util;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {

    public static String getSubstringBetweenOrToEnd(String source, String from, String to) {
        if (!source.contains(from)) {
            return "";
        }

        String substringFrom = StringUtils.substringAfter(source, from);
        if (substringFrom.contains(to)) {
            return StringUtils.substringBefore(substringFrom, to);
        }

        return substringFrom;
    }
}
