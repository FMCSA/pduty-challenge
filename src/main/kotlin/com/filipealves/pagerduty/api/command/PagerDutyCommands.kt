package com.filipealves.pagerduty.api.command

import com.filipealves.pagerduty.api.gateway.PagerDutyApiGateway
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
class PagerDutyCommands(
    private val pagerDutyApiGateway: PagerDutyApiGateway
) {

    @ShellMethod(key = ["hello", "hey"], value = "I will say hello")
    fun hello(): String {
        return "Hello!"
    }

    @ShellMethod(key = ["get-abilities"], value = "Get all abilities")
    fun getAbilities() {
        val abilitiesResponse = pagerDutyApiGateway.getAbilities()

        for(ability in abilitiesResponse.abilities) {
            println(ability)
        }
    }
}
