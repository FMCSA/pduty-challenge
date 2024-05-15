package com.filipealves.pagerduty.api.command

import com.filipealves.pagerduty.api.gateway.PagerDutyApiGateway
import com.filipealves.pagerduty.api.utils.listToArrayTableModel
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.table.TableBuilder

@ShellComponent
class PagerDutyCommands(
    private val pagerDutyApiGateway: PagerDutyApiGateway
) {

    @ShellMethod(key = ["hello", "hey"], value = "I will say hello")
    fun hello(): String {
        return "Hello!"
    }

    @ShellMethod(key = ["get-services"], value = "Get services by page")
    fun getServices(): String {
        return pagerDutyApiGateway.getServicesPage().toString()
    }

    @ShellMethod(key = ["get-abilities"], value = "Get all abilities")
    fun getAbilities() {
        val abilitiesResponse = pagerDutyApiGateway.getAbilities()

        for(ability in abilitiesResponse.abilities) {
            println(ability)
        }
    }

    @ShellMethod(key = ["get-log-entries"], value = "Get Log entries by page")
    fun getLogEntries(limit: Int, offset: Int, isOverview: Boolean) {
        val logEntriesResponse = pagerDutyApiGateway.getLogEntries(isOverview, limit, offset)
        for(entry in logEntriesResponse.logEntries) {
            println(entry)
        }
    }

    @ShellMethod(key = ["get-users"], value = "Get Users")
    fun getUsers(limit: Int, offset: Int) {
        val userResponse = pagerDutyApiGateway.getUsers(limit, offset)

        if(userResponse.users.isNotEmpty()) {
            val tableBuilder: TableBuilder = listToArrayTableModel(userResponse.users) {
                    user -> arrayOf(user.id, user.name, user.type, user.email, user.summary, user.contactMethods.toString())
            }

            println(tableBuilder.build().render(120))
        }

        //for(user in userResponse.users) {
        //    println(user)
       // }
    }

    @ShellMethod(key = ["get-user-contact"], value = "Get User Contact methods")
    fun getUserContactMethod(userId: String) {
        val userContactMethodResponse = pagerDutyApiGateway.getUserContactMethod(userId)

        if(userContactMethodResponse.contactMethods.isNotEmpty()) {
            val tableBuilder: TableBuilder = listToArrayTableModel(userContactMethodResponse.contactMethods) {
                    contact -> arrayOf(contact.id, contact.address)
            }

            println(tableBuilder.build().render(120))
        }
    }
}

//play with coroutines and tests and have a way to get the user by contact or id - play with it
//do this: Overall, this code fetches user data from the PagerDuty API, allows the user to select a user from the fetched data, and then displays the contact methods associated with the selected user.
//        also build it with graal vm?