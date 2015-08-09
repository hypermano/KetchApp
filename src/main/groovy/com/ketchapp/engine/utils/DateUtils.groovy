package com.ketchapp.engine.utils

import java.util.regex.Pattern

/**
 * Created by emmanuel on 8/8/2015.
 */
class DateUtils {

    private static final Pattern DASHED_YEAR_MONTH_DAY_REX = ~/\d{4}-\d{2}-\d{2}/
    private static final Pattern DASHED_YEAR_MONTH_REX = ~/\d{4}-\d{2}/

    static Date parse(String date) {
        switch (date) {
            case DASHED_YEAR_MONTH_DAY_REX:
                return Date.parse('yyyy-MM-dd', date)
            case DASHED_YEAR_MONTH_REX:
                return Date.parse('yyyy-MM', date)
        }

        return null
    }

    public static void main(String[] args) {
        println parse("2012-10-11")
        println parse("2012/10/11")
    }
}
