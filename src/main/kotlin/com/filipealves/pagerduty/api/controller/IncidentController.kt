package com.filipealves.pagerduty.api.controller

import com.filipealves.pagerduty.api.gateway.PagerDutyApiGateway
import com.filipealves.pagerduty.api.model.responses.IncidentsPaginatedResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IncidentController(
    private val pagerDutyApiGateway: PagerDutyApiGateway
) {
//    @GetMapping("/")
//    fun getIncidents(): ResponseEntity<String> {
//        return ResponseEntity.ok(pagerDutyApiGateway.getIncidents())
//    }

    @GetMapping("/incidents")
    fun getIncidentsPage(): ResponseEntity<IncidentsPaginatedResponse> {
        return ResponseEntity.ok(pagerDutyApiGateway.getIncidentsPage())
    }
}