package com.filipealves.pagerduty.api.controller

import com.filipealves.pagerduty.api.gateway.PagerDutyApiGateway
import com.filipealves.pagerduty.api.model.responses.IncidentsPaginated
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/incidents")
class PagerDutyController(
    private val pagerDutyApiGateway: PagerDutyApiGateway
) {
    @GetMapping("/")
    fun getIncidents(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body(pagerDutyApiGateway.getIncidents())
    }

    @GetMapping("/page")
    fun getIncidentsPage(): ResponseEntity<IncidentsPaginated> {
        return ResponseEntity.status(HttpStatus.OK).body(pagerDutyApiGateway.getIncidentsPage())
    }
}