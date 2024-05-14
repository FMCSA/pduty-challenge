package com.filipealves.pagerduty.api.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

class ExampleApiConfiguration(
    private val restTemplateBuilder: RestTemplateBuilder,
    @Value("\${example.url}") val url: String,
    @Value("\${example.apiKey}") val apiKey: String
) {
    @Bean
    fun restTemplate() = restTemplateBuilder
        .rootUri(url)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("Authorization", apiKey)
        .build()
}