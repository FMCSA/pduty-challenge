package com.filipealves.pagerduty.api.model.responses

data class IncidentsPaginatedResponse(
    val incidents: List<String>,
    val limit: Int,
    val offset: Int,
    val total: Int?,
    val more: Boolean
)
