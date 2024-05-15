package com.filipealves.pagerduty.api.model.requests

data class CreateIncidentRequest(
    val incident: Incident,
//    val type: String,
//    val title: String,
//    val service: Service,
    //val priority: Priority,
    //val urgency: String,
    //val incidentKey: String,
    //val body: Body,
    //val escalationPolicy: EscalationPolicy
)

data class Incident(
    val type: String,
    val title: String,
    val service: Service
)

data class Service(
    val id: String,
    val type: String,
    val name: String
)

data class Priority(
    val id: String,
    val type: String
)

data class Body(
    val id: String,
    val type: String
)

data class EscalationPolicy(
    val id: String,
    val type: String
)