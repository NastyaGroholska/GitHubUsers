package com.ahrokholska.gitHubUsers.domain.utils

object Parser {
    fun zonedDateTimeToDate(date: String) = date.substring(0, date.indexOf("T"))
}