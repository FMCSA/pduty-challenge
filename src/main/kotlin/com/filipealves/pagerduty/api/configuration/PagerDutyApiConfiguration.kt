package com.filipealves.pagerduty.api.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

@Configuration
class PagerDutyApiConfiguration(
    private val restTemplateBuilder: RestTemplateBuilder,
    @Value("\${pagerduty.url}") val url: String,
    @Value("\${pagerduty.apiKey}") val apiKey: String
) {
    @Bean("rest-api")
    fun restTemplate() = restTemplateBuilder
        .rootUri(url)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("Authorization", "Token token = $apiKey")
        .build()
}