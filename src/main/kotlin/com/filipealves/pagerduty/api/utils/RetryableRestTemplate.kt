package com.filipealves.pagerduty.api.utils

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.retry.support.RetryTemplate
import org.springframework.web.client.RestTemplate

class RetryableRestTemplate(
    private val retryTemplate: RetryTemplate,
    private val restTemplate: RestTemplate
) {
    // TODO: Log when theres a retry
    fun <T, R> exchange(
        path: String,
        method: HttpMethod,
        body: T?,
        responseType: Class<R>,
        uriVars: Map<String, Any>
    ): ResponseEntity<R> = retryTemplate.execute<ResponseEntity<R>, Exception> {
        val requestBody = body?.let { HttpEntity(body) } ?: HttpEntity.EMPTY
        restTemplate.exchange(path, method, requestBody, responseType, uriVars)
    }

    fun <T, R> exchange(
        path: String,
        method: HttpMethod,
        body: T?,
        responseType: Class<R>
    ): ResponseEntity<R> = retryTemplate.execute<ResponseEntity<R>, Exception> {
        val requestBody = body?.let { HttpEntity(body) } ?: HttpEntity.EMPTY
        restTemplate.exchange(path, method, requestBody, responseType)
    }
}
