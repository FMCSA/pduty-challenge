package com.filipealves.pagerduty.api.model.responses

import com.filipealves.pagerduty.api.model.requests.Service

data class ServicesPaginatedResponse(
    val services: List<Service>,
    val limit: Int,
    val offset: Int,
    val total: Int?,
    val more: Boolean
)
