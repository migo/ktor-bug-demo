package com.github.migo

import com.github.migo.plugins.Transformer
import com.github.migo.plugins.configureRouting
import com.github.migo.plugins.configureSecurity
import io.ktor.client.call.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.*

class SessionTransportTransformerTest {

    @Test
    fun test() = testApplication {
        application {
            configureRouting()
            configureSecurity()
        }
        val client = createClient {
            install(HttpCookies)
        }

        val response = client.get("/set-session")
        assertEquals(response.status, HttpStatusCode.OK)
        val response2 = client.get("/get-session")
        assertEquals(response2.status, HttpStatusCode.OK)
        assertEquals(response2.body<String>(), "A text")

        // Here we can see the difference: transformRead receives the space as-is,
        // but transformWrite receives a "+" instead:
        assertEquals(Transformer.readValue, "text=%23sA text")
        assertEquals(Transformer.writeValue, "text=%23sA+text")
    }
}