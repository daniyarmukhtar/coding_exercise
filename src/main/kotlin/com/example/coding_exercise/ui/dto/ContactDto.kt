package com.example.coding_exercise.ui.dto

import com.example.coding_exercise.domain.contact.model.Address
import java.util.*

class ContactDto(var id: Long?,
                 var fullName: String?,
                 var birthDate: Date?,
                 var postalCode: String?,
                 var city: String?) {
    constructor(): this(null, null, null, null, null)
}
