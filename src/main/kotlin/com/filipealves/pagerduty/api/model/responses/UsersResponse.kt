package com.filipealves.pagerduty.api.model.responses

data class UsersResponse(
    val users: List<User>
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val contactMethods: List<ContactMethods>
)

data class ContactMethods(
    val id: String,
    val type: String,
    val summary: String
)
