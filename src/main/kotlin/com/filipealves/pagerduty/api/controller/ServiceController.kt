package com.filipealves.pagerduty.api.controller

import com.filipealves.pagerduty.api.configuration.PagerDutyEventsApiConfiguration
import com.filipealves.pagerduty.api.gateway.PagerDutyApiGateway
import com.filipealves.pagerduty.api.gateway.PagerDutyEventsApiGateway
import com.filipealves.pagerduty.api.model.responses.AbilitiesResponse
import com.filipealves.pagerduty.api.model.responses.LogEntriesResponse
import com.filipealves.pagerduty.api.model.responses.ServicesPaginatedResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
//@RequestMapping("/services")
class ServiceController(
    val pagerDutyApiGateway: PagerDutyApiGateway,
    val pagerDutyEventsApiGateway: PagerDutyEventsApiGateway
) {

    @GetMapping("/services")
    fun getService(): ResponseEntity<ServicesPaginatedResponse> {
        return ResponseEntity.ok(pagerDutyApiGateway.getServicesPage())
    }

    @GetMapping("/testSendEvent")
    fun testEvent(): ResponseEntity<String> {
        pagerDutyEventsApiGateway.sendEvent()
        return ResponseEntity.ok("Test")
    }

    @GetMapping("/abilities")
    fun getAbilities(): ResponseEntity<List<String>> {
        return ResponseEntity.ok(pagerDutyApiGateway.getAbilities().abilities)
    }

    @GetMapping("/log_entries")
    fun getLogEntries(@RequestParam("limit") limit: Int, @RequestParam("offset") offset: Int): ResponseEntity<LogEntriesResponse> {
        return ResponseEntity.ok(pagerDutyApiGateway.getLogEntries(limit = limit, offset = offset))
    }
}