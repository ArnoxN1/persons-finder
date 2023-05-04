package com.persons.finder.data

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class Location(
    //primary key for location table
    val id: Int? = null,

    // Tip: Person's id can be used for this field
    // Without @field, Kotlin will apply validation to the constructor parameters by default.
    @field:NotNull(message = "person's id can't be null")
    @field:Min(value = 1, message = "person's id can't be less than 1")
    val referenceId: Int?,

    @field:NotNull(message = "person's latitude can't be null")
    val latitude: Double? = null,
    @field:NotNull(message = "person's longitude can't be null")
    val longitude: Double? = null
)
