package com.filipealves.pagerduty.api.gateway

import com.filipealves.pagerduty.api.model.requests.CreateIncidentRequest
import com.filipealves.pagerduty.api.model.requests.CreateServiceRequest
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

    fun getIncidents(): String {
        val url = UriComponentsBuilder.fromPath("/incidents").toUriString()

        val response: ResponseEntity<String> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            String::class.java
        )

        logger.info(response.body!!.toString())

        return when (response.statusCode) {
            HttpStatus.OK -> response.body!!
            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
        }
    }

    fun getIncidentsPage(): IncidentsPaginatedResponse {
        val url = UriComponentsBuilder.fromPath("/incidents").toUriString()

        val response: ResponseEntity<IncidentsPaginatedResponse> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            IncidentsPaginatedResponse::class.java
        )

        logger.info(response.body!!.toString())

        return when (response.statusCode) {
            HttpStatus.OK -> response.body!!
            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
        }
    }

    fun postIncident(createIncidentRequest: CreateIncidentRequest) {
        val url = UriComponentsBuilder.fromPath("/incidents").toUriString()

        val response: ResponseEntity<String> = restTemplate.exchange(
            url,
            HttpMethod.POST,
            HttpEntity(createIncidentRequest),
            String::class.java
        )

        logger.info(response.body!!.toString())
    }

    fun postService(createServiceRequest: CreateServiceRequest) {
        val url = UriComponentsBuilder.fromPath("/services").toUriString()

        val response: ResponseEntity<String> = restTemplate.exchange(
            url,
            HttpMethod.POST,
            HttpEntity(createServiceRequest),
            String::class.java
        )

        logger.info(response.body!!.toString())
    }

    fun getServicesPage(): ServicesPaginatedResponse {
        val url = UriComponentsBuilder.fromPath("/services").toUriString()

        val response: ResponseEntity<ServicesPaginatedResponse> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            ServicesPaginatedResponse::class.java
        )

        logger.info(response.body!!.toString())

        return when (response.statusCode) {
            HttpStatus.OK -> response.body!!
            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
        }
    }

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

    fun getLogEntries(isOverview: Boolean = false, limit: Int = 1, offset: Int = 0): LogEntriesResponse {
        val url = UriComponentsBuilder.fromPath("/log_entries")
            .queryParam("is_overview", "{isOverview}")
            .queryParam("limit", "{limit}")
            .queryParam("offset", "{offset}")
            .buildAndExpand(isOverview, limit, offset)
            .toUriString()

        val response: ResponseEntity<LogEntriesResponse> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            LogEntriesResponse::class.java,
        )

        logger.info(response.body!!.toString())

        return when (response.statusCode) {
            HttpStatus.OK -> response.body!!
            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
        }
    }

    fun getUsers(limit: Int, offset: Int, query: String? = null, total: Boolean = false, include: String? = null, teamIds: List<String>? = null): UsersResponse {
        val url = UriComponentsBuilder.fromPath("/users")
            .queryParam("limit", "{limit}")
            .queryParam("offset", "{offset}")
            .buildAndExpand(limit, offset)
            .toUriString()

        val response: ResponseEntity<UsersResponse> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            UsersResponse::class.java,
        )

        logger.info(response.body!!.toString())

        return when (response.statusCode) {
            HttpStatus.OK -> response.body!!
            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
        }
    }

    fun getUserContactMethod(userId: String): UserContactMethodResponse {
        val url = UriComponentsBuilder.fromPath("/users/{userId}/contact_methods")
            .buildAndExpand(userId)
            .toUriString()

        val response: ResponseEntity<UserContactMethodResponse> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            UserContactMethodResponse::class.java,
        )

        logger.info(response.body!!.toString())

        return when (response.statusCode) {
            HttpStatus.OK -> response.body!!
            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
        }
    }
}