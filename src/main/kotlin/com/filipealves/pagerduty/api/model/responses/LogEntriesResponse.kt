package com.filipealves.pagerduty.api.model.responses

data class LogEntriesResponse(
    val offset: Int,
    val limit: Int,
    val more: Boolean,
    val total: Int?,
    val logEntries: List<LogEntry>
)

data class LogEntry(
    val id: String,
    val summary: String,
    val type: String
)
