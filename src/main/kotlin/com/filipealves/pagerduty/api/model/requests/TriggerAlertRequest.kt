package com.filipealves.pagerduty.api.model.requests

data class TriggerAlertRequest(
    val routingKey: String,
    val eventAction: String,
    val payload: Payload
)

data class Payload(
    val summary: String,
    val severity: String,
    val source: String
)
