package com.filipealves.pagerduty.api.model.responses

data class UserContactMethodResponse(
    val contactMethods: List<Contacts>
)

data class Contacts(
    val id: String,
    val address: String
)
