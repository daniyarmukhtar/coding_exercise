package com.example.coding_exercise.domain.contact.repository

import com.example.coding_exercise.domain.contact.model.Contact
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ContactRepository : CrudRepository<Contact, Long> {
    fun findByFullName(fullName: String): Optional<Contact>?
    fun findAllByAddress_PostalCode(address_postalCode: String): MutableList<Contact>
}
