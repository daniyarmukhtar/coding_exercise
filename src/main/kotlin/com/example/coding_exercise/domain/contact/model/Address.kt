package com.example.coding_exercise.domain.contact.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Address(@Column(name = "postal_code") var postalCode: String? = null,
              @Column(name = "city") var city: String? = null) {
}
