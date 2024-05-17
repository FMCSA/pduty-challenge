package com.filipealves.pagerduty.api.gateway

import com.filipealves.pagerduty.api.model.responses.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class PagerDutyApiGateway(
    @Qualifier("rest-api") private val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getAbilities(): AbilitiesResponse {
        val url = UriComponentsBuilder.fromPath("/abilities").toUriString()

        val response: ResponseEntity<AbilitiesResponse> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            AbilitiesResponse::class.java
        )

        logger.info(response.body!!.toString())

        return when (response.statusCode) {
            HttpStatus.OK -> response.body!!
            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
        }
    }

    fun getUsers(limit: Int, offset: Int): UsersResponse {
        val url = UriComponentsBuilder.fromPath("/users")
            .queryParam("limit", "{limit}")
            .queryParam("offset", "{offset}")
            .buildAndExpand(limit, offset)
            .toUriString()

        val response: ResponseEntity<UsersResponse> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            UsersResponse::class.java
        )

        logger.info(response.body!!.toString())

        return when (response.statusCode) {
            HttpStatus.OK -> response.body!!
            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
        }
    }

    fun getUserContactMethod(userId: String, contactMethodId: String) : ContactMethodResponse {
        val url = UriComponentsBuilder.fromPath("/users/{userId}/contact_methods/{contactMethodId}")
            .buildAndExpand(userId, contactMethodId)
            .toUriString()

        val response: ResponseEntity<ContactMethodResponse> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            ContactMethodResponse::class.java
        )

        logger.info(response.body!!.toString())

        return when (response.statusCode) {
            HttpStatus.OK -> response.body!!
            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
        }
    }
}