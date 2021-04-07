package com.example.coding_exercise.domain.contact.service

import com.example.coding_exercise.domain.contact.model.Address
import com.example.coding_exercise.domain.contact.model.Contact
import com.example.coding_exercise.domain.contact.repository.ContactRepository
import com.example.coding_exercise.ui.dto.ContactDto
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContactServiceImpl(val contactRepository: ContactRepository): ContactService {
    private val logger = LogManager.getLogger(ContactServiceImpl::class.java)

    override fun create(contact: Contact): Contact {
        return contactRepository.save(contact)
    }

    override fun create(fullName: String, birthDate: Date, address: Address) {
        val contact = Contact()
        contact.fullName = fullName
        contact.birthDate = birthDate
        contact.address = address

        create(contact)
    }

    override fun create(contactDto: ContactDto): Contact {
        val contact = Contact()
        contact.fullName = contactDto.fullName
        contact.birthDate = contactDto.birthDate
        contact.address = Address(contactDto.postalCode, contactDto.city)

        return create(contact)
    }

    override fun findById(id: Long): Contact? {
        val optionalContact = contactRepository.findById(id)
        return if (optionalContact.isPresent) {
            optionalContact.get()
        } else {
            null
        }
    }

    override fun findByFullName(name: String): Optional<Contact>? {
        return contactRepository.findByFullName(name)
    }

    override fun findAll(): List<Contact> {
        return contactRepository.findAll().toList()
    }

    override fun findAllByPostalCode(postalCode: String?): MutableList<Contact> {
        return when (postalCode) {
            null -> {
                contactRepository.findAll().toMutableList()
            }
            else -> {
                contactRepository.findAllByAddress_PostalCode(postalCode)
            }
        }
    }

    override fun update(contact: Contact): Contact {
        return contactRepository.save(contact)
    }

    override fun update(id: Long, contactDto: ContactDto): Contact? {
        val contact = findById(id) ?: return null
        contact.fullName = contactDto.fullName

        contact.address = Address(contactDto.postalCode, contactDto.city)

        return update(contact)
    }

    override fun delete(contact: Contact) {
        contactRepository.delete(contact)
    }

    override fun deleteById(id: Long) {
        contactRepository.deleteById(id)
    }
}
