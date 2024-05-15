package com.filipealves.pagerduty.api.model.requests

data class CreateServiceRequest(
    val id: String,
    val summary: String,
    val type: String,
    val self: String,
    val html_url: String,
    val name: String,
    val description: String,
    val auto_resolve_timeout: Int,
    val acknowledgement_timeout: Int,
    val status: String,
    val escalation_policy: EscalationPolicyReference,
    val response_play: ResponsePlayReference,
    val incident_urgency_rule: IncidentUrgencyRule,
    val support_hours: SupportHours,
    val scheduled_actions: List<ScheduledAction>,
    val alert_creation: String,
    val alert_grouping_parameters: AlertGroupingParameters,
    val auto_pause_notifications_parameters: AutoPauseNotificationsParameters
)

data class EscalationPolicyReference(
    val id: String,
    val type: String
)

data class ResponsePlayReference(
    val id: String,
    val type: String
)

data class IncidentUrgencyRule(
    val type: String,
    val during_support_hours: UrgencyRule,
    val outside_support_hours: UrgencyRule
)

data class UrgencyRule(
    val type: String,
    val urgency: String
)

data class SupportHours(
    val type: String,
    val time_zone: String,
    val start_time: String,
    val end_time: String,
    val days_of_week: List<Int>
)

data class ScheduledAction(
    val type: String,
    val at: NamedTime,
    val to_urgency: String
)

data class NamedTime(
    val type: String,
    val name: String
)

data class AlertGroupingParameters(
    val type: String,
    val config: AlertGroupingConfig
)

data class AlertGroupingConfig(
    val timeout: Int
)

data class AutoPauseNotificationsParameters(
    val enabled: Boolean,
    val timeout: Int
)
