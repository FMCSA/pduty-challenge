package com.filipealves.pagerduty.api.model.responses

data class IncidentsPaginated(
    val incidents: List<String>,
    val limit: Int,
    val offset: Int,
    val total: Int = 0,
    val more: Boolean
)
