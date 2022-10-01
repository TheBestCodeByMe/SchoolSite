package com.example.schoolsite.exception

import java.util.Date

class ErrorDetails(timestamp: Date, message: String, details: String) {
    private val timestamp: Date
    val message: String
    val details: String

    init {
        this.timestamp = timestamp
        this.message = message
        this.details = details
    }

    fun getTimestamp(): Date {
        return timestamp
    }
}