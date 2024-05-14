package com.filipealves.pagerduty.api.command

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
class ExampleCommand {

    @ShellMethod(key = ["hello", "hey"], value = "I will say hello")
    fun hello(): String {
        return "Hello!"
    }
}