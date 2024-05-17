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

    @ShellMethod(key = ["get-abilities"], value = "Get all abilities")
    fun getAbilities() {
        val abilitiesResponse = pagerDutyApiGateway.getAbilities()

        for(ability in abilitiesResponse.abilities) {
            println(ability)
        }
    }

    @ShellMethod(key = ["get-users", "gu"], value = "Get all abilities")
    fun getUsers(offset: Int, limit: Int) {
        val usersResponse = pagerDutyApiGateway.getUsers(offset, limit)

        val tableBuilder: TableBuilder = listToArrayTableModel(usersResponse.users) {
            user -> arrayOf(user.id, user.name, user.email, user.contactMethods.toString())
        }

        println(tableBuilder.build().render(120))

//        for(user in usersResponse.users) {
//            println(user)
//        }
    }

    @ShellMethod(key = ["get-user-contact", "gc"], value = "Get user contact")
    fun getUserContact(userId: String, contactMethodId: String) {

        val contactMethodResponse = pagerDutyApiGateway.getUserContactMethod(userId, contactMethodId)

//        val tableBuilder: TableBuilder = listToArrayTableModel(listOf(contactMethodResponse)) {
//                contact -> arrayOf(contact.id, contact.address)
//        }
//
//        println(tableBuilder.build().render(120))

        println(contactMethodResponse.contactMethod)

//        for(contact in contactMethodResponse.users) {
//            println(user)
//        }
    }
}
