package com.example.coding_exercise

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.context.WebApplicationContext
import org.springframework.boot.test.context.SpringBootTest
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.core.JsonProcessingException
import org.springframework.boot.json.JsonParseException
import org.springframework.test.web.servlet.MockMvc
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [CodingExerciseApplication::class])
@WebAppConfiguration
abstract class AbstractTest {
    protected var mvc: MockMvc? = null

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    protected fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Throws(JsonProcessingException::class)
    protected fun mapToJson(obj: Any?): String {
        val objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(obj)
    }

    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    protected fun <T> mapFromJson(json: String?, clazz: Class<T>?): T? {
        val objectMapper = ObjectMapper() .registerModule(KotlinModule()).registerModule(JavaTimeModule())

        return objectMapper.readValue(json, clazz)
    }
}
