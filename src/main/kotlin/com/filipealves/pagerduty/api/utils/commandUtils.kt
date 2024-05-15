package com.filipealves.pagerduty.api.utils

import org.springframework.shell.table.ArrayTableModel
import org.springframework.shell.table.BorderStyle
import org.springframework.shell.table.TableBuilder

fun <T> listToArrayTableModel(items: List<T>, rowMapper: (T) -> Array<String>): TableBuilder {
    val data = items.map(rowMapper).toTypedArray()
    val model = ArrayTableModel(data)
    val tableBuilder = TableBuilder(model)
    tableBuilder.addHeaderBorder(BorderStyle.fancy_light_double_dash)
    tableBuilder.addInnerBorder(BorderStyle.fancy_light_double_dash)
    return tableBuilder
}