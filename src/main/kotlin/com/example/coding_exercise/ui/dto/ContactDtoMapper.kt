package com.example.coding_exercise.ui.dto

import com.example.coding_exercise.domain.contact.model.Address
import com.example.coding_exercise.domain.contact.model.Contact

class ContactDtoMapper {
    companion object {
        fun toContactDto(contact: Contact): ContactDto {

            return ContactDto(contact.id, contact.fullName, contact.birthDate, contact.address?.postalCode, contact.address?.city)
        }

        fun toContactDtoList(contacts: List<Contact>): MutableList<ContactDto> {
            val contactDtoList = mutableListOf<ContactDto>()
            for (contact in contacts) {
                contactDtoList.add(ContactDto(contact.id, contact.fullName, contact.birthDate, contact.address?.postalCode, contact.address?.city))
            }
            return contactDtoList
        }
    }
}
