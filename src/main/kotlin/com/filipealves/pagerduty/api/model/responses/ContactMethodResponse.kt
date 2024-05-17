package com.filipealves.pagerduty.api.model.responses

data class ContactMethodResponse(
    val contactMethod: ContactMethod
)

data class ContactMethod(
    val id: String,
    val address: String
)
