package com.filipealves.pagerduty.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.shell.command.annotation.CommandScan

@SpringBootApplication
@CommandScan
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
