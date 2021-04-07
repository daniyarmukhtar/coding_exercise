package com.example.coding_exercise.domain.contact.service

import com.example.coding_exercise.domain.contact.model.Address
import com.example.coding_exercise.domain.contact.model.Contact
import com.example.coding_exercise.ui.dto.ContactDto
import java.util.*

interface ContactService {
    fun create(contact: Contact): Contact

    fun create(fullName: String, birthDate: Date, address: Address)

    fun create(contactDto: ContactDto): Contact

    fun findById(id: Long): Contact?

    fun findByFullName(name: String): Optional<Contact>?

    fun findAll(): List<Contact>

    fun findAllByPostalCode(postalCode: String?): List<Contact>

    fun update(contact: Contact): Contact

    fun update(id: Long, contactDto: ContactDto): Contact?

    fun delete(contact: Contact)

    fun deleteById(id: Long)
}
