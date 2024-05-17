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
}