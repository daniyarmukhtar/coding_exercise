package com.example.coding_exercise.ui.controller

import com.example.coding_exercise.domain.contact.service.ContactService
import com.example.coding_exercise.domain.contact.model.Contact
import org.springframework.beans.factory.annotation.Autowired
import com.example.coding_exercise.ui.dto.ContactDtoMapper
import com.example.coding_exercise.ui.dto.ContactDto
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.apache.logging.log4j.LogManager
import org.springframework.http.MediaType
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI


@RestController
@RequestMapping("/contacts")
class ContactController(val contactService: ContactService) {

    private val logger = LogManager.getLogger(ContactController::class.java)

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getContacts(@RequestParam postalCode: String?): ResponseEntity<Any> {

        val contacts = contactService.findAllByPostalCode(postalCode)

        return ResponseEntity.ok(ContactDtoMapper.toContactDtoList(contacts))
    }

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getContact(@PathVariable("id") id: Long): ResponseEntity<Any> {

        val contact = contactService.findById(id)
            ?: return ResponseEntity.badRequest().body("There is no contact with id $id")

        return ResponseEntity.ok(ContactDtoMapper.toContactDto(contact))
    }

    @PostMapping()
    fun createContact(@RequestBody contactDto: ContactDto): ResponseEntity<Any> {
        val contact: Contact?
        try {
            contact = contactService.create(contactDto)
        } catch (e: Exception) {
            logger.warn("Contact does not created")
            return ResponseEntity.badRequest().build()
        }
        logger.info("Contact by id: ${contact.id} created")

        val location: URI = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(contact.id).toUri()

        return ResponseEntity.created(location).body("Contact is created successfully")
    }

    @PutMapping(value = ["/{id}"])
    fun updateContact(@PathVariable("id") id: Long,
                      @RequestBody contactDto: ContactDto): ResponseEntity<Any> {
        var contact: Contact?
        try {
            contact = contactService.update(id, contactDto)
            if (contact == null) {
                logger.warn("Contact by id: $id does not exist to update")
                return ResponseEntity.badRequest().build()
            }
        } catch (e: Exception) {
            logger.warn("Contact by id: $id does not updated")
            return ResponseEntity.badRequest().build()
        }
        logger.info("Contact by id: ${contact.id} updated")

        return ResponseEntity.noContent().build()
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteContact(@PathVariable("id") id: Long): ResponseEntity<Any> {
        try {
            contactService.deleteById(id)
        } catch (e: Exception) {
            logger.warn("Contact by id: $id does not deleted")
            return ResponseEntity.badRequest().build()
        }
        logger.info("Contact by id: $id deleted")

        return ResponseEntity.ok().body("Contact is deleted successfully")
    }
}
