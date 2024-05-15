package com.filipealves.pagerduty.api.gateway

import com.filipealves.pagerduty.api.model.requests.Service
import com.filipealves.pagerduty.api.model.responses.AbilitiesResponse
import com.filipealves.pagerduty.api.model.responses.ServicesPaginatedResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

class PagerDutyApiGatewayTest {
    private val restTemplate = mock<RestTemplate>()

    private val subject = PagerDutyApiGateway(restTemplate)

    @Test
    fun `getServices success`() {
        val url = "/services"
        val expectedServices = listOf(Service("id-1", "service", "service name"))
        val expectedResponse = ServicesPaginatedResponse(
            services = expectedServices,
            limit = 0,
            offset = 0,
            total = null,
            more = false
        )

        whenever(
            restTemplate.exchange(
                eq(url),
                eq(HttpMethod.GET),
                eq(HttpEntity.EMPTY),
                eq(ServicesPaginatedResponse::class.java)
            )
        ).thenReturn(ResponseEntity.ok(expectedResponse))

        val result = subject.getServicesPage()

        assertEquals(expectedResponse, result)
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(ServicesPaginatedResponse::class.java))
        verifyNoMoreInteractions(restTemplate)
    }

    @Test
    fun `it should return abilities`() {
        val url = "/abilities"
        val expectedAbilities = AbilitiesResponse(
            abilities = listOf("ab1", "ab2")
        )

        whenever(
            restTemplate.exchange(
                eq(url),
                eq(HttpMethod.GET),
                eq(HttpEntity.EMPTY),
                eq(AbilitiesResponse::class.java)
            )
        ).thenReturn(ResponseEntity.ok(expectedAbilities))

        val result = subject.getAbilities()

        assertEquals(expectedAbilities, result)
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(AbilitiesResponse::class.java))
        verifyNoMoreInteractions(restTemplate)
    }



    @Test
    fun `when an unexpected status code is returned it should throw a RestClientException`() {
        val url = "/abilities"
        val abilitiesResponse = listOf("ab1", "ab2")

        whenever(
            restTemplate.exchange(
                eq(url),
                eq(HttpMethod.GET),
                eq(HttpEntity.EMPTY),
                eq(AbilitiesResponse::class.java)
            )
        ).thenThrow(RestClientException("Rest client exception"))

        assertThrows<RestClientException> { val result = subject.getAbilities() }

        verify(restTemplate).exchange(eq(url), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(AbilitiesResponse::class.java))
        verifyNoMoreInteractions(restTemplate)
    }
}