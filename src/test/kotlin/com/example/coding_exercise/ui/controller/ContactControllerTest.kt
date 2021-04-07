package com.example.coding_exercise.ui.controller

import com.example.coding_exercise.AbstractTest
import com.example.coding_exercise.ui.dto.ContactDto
import org.apache.logging.log4j.LogManager
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.util.*

internal class ContactControllerTest: AbstractTest() {

    @BeforeEach
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun getContacts() {
        val uri = "/contacts"
        val mvcResult = mvc!!.perform(
            MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        val status = mvcResult.response.status
        assertEquals(200, status)
        val content = mvcResult.response.contentAsString
        val contacts: Array<ContactDto>? = super.mapFromJson(content, Array<ContactDto>::class.java)
        assertTrue(contacts!!.isNotEmpty())
    }

    @Test
    fun getContact() {
        val uri = "/contacts/1"
        val mvcResult = mvc!!.perform(
            MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        val status = mvcResult.response.status
        assertEquals(200, status)
        val content = mvcResult.response.contentAsString
        val contact: ContactDto? = super.mapFromJson(content, ContactDto::class.java)
        assertTrue(null != contact)
    }

    @Test
    fun createContact() {
        val uri = "/contacts"
        val contactDto = ContactDto()
        contactDto.fullName = "Test Name"
        contactDto.birthDate = Date()
        contactDto.city = "New York"
        contactDto.postalCode = "5468565"
        val inputJson = super.mapToJson(contactDto)
        val mvcResult = mvc!!.perform(
            MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
        ).andReturn()

        val status = mvcResult.response.status
        assertEquals(201, status)
        val content = mvcResult.response.contentAsString
        assertEquals(content, "Contact is created successfully")
    }

    @Test
    fun updateContact() {
        val uri = "/contacts/2"
        val contactDto = ContactDto()
        contactDto.fullName = "Michael Jackson"
        contactDto.birthDate = Date()
        contactDto.city = "San Francisco"
        contactDto.postalCode = "9845652645"
        val inputJson = super.mapToJson(contactDto)
        val mvcResult = mvc!!.perform(
            MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
        ).andReturn()

        val status = mvcResult.response.status
        assertEquals(204, status)
    }

    @Test
    fun deleteContact() {
        val uri = "/contacts/2"
        val mvcResult = mvc!!.perform(MockMvcRequestBuilders.delete(uri)).andReturn()
        val status = mvcResult.response.status
        assertEquals(200, status)
        val content = mvcResult.response.contentAsString
        assertEquals(content, "Contact is deleted successfully")
    }
}
