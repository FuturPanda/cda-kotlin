package com.example.notificationbackend.web.rest

import com.example.notificationbackend.config.AppProperties
import com.example.notificationbackend.domain.AppUser
import com.example.notificationbackend.repository.AppUserRepository
import com.example.notificationbackend.service.TestService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@ExtendWith(SpringExtension::class)
@WebAppConfiguration
class TestControllerTest {
    @MockkBean
    private lateinit var appUserRepository : AppUserRepository

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun service() = mockk<TestService>()
    }

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        println("")
    }

    @AfterEach
    fun tearDown() {
        println("")
    }

    @Test
    fun `should return true if the two parameters are equals`() {
        val result = testController.isPositive(6)
        mockMvc.post()
        assertTrue(result)
    }

    object TestWithSpeck: Spek({
        describe("A calculator") {
            val num1 = 1
            val num2 = 2
            context("Adding 3 and 5") {
                val result = num1 + num2
                it("Produces 3") {
                    assertEquals(3, result)
                }
            }
        }
    })


    @Test
    fun testAppUserRepository(){
            val testService = TestService(appUserRepository)
            every {appUserRepository.findAppUserById(2)} returns AppUser(2, "immoaugmente+agency@gmail.com")
            val result = testService.testGetAppUserEmail(2)
            assertEquals(result?.email, "immoaugmente+agency@gmail.com")
    }
}