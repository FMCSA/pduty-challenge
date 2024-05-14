package com.filipealves.pagerduty.api.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/example")
class ExampleController {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/")
    fun getExample(): ResponseEntity<String> {
        logger.info("Get request example log")
        return ResponseEntity.status(HttpStatus.OK).body("Hello Boilerplate World")
    }
}