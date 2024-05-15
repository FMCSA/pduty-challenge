package com.filipealves.pagerduty.api.model.responses

data class UsersResponse(
    val users: List<User>,
    val limit: Int,
    val offset: Int,
    val total: Int?,
    val more: Boolean
)

data class User(
    val id: String,
    val summary: String,
    val type: String,
    val name: String,
    val email: String,
    val contactMethods: List<ContactMethods>
)

data class ContactMethods(
    val id: String,
    val type: String,
    val summary: String
)