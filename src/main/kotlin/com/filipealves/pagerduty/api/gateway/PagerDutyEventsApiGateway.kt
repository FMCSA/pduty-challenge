package com.filipealves.pagerduty.api.gateway

import com.filipealves.pagerduty.api.model.requests.Payload
import com.filipealves.pagerduty.api.model.requests.TriggerAlertRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class PagerDutyEventsApiGateway(
    @Qualifier("events-api") private val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendEvent() {

        val triggerAlertRequest: TriggerAlertRequest = TriggerAlertRequest(
            routingKey = "R021FMZ9JRGB4HWPCKPFKXXR71TS3IWS",
            eventAction = "trigger",
            payload = Payload(
                summary = "This is a summary",
                severity = "info",
                source = "some-source"
            )
        )

        val url = UriComponentsBuilder.fromPath("/enqueue").toUriString()

        val response: ResponseEntity<String> = restTemplate.exchange(
            url,
            HttpMethod.POST,
            HttpEntity(triggerAlertRequest),
            String::class.java
        )

        logger.info(response.body!!.toString())

//        return when (response.statusCode) {
//            HttpStatus.OK -> response.body!!
//            else -> throw RestClientException("GET $url returned unexpected status code ${response.statusCode}")
//        }
    }
}