package com.github.migo.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.sessions.*

fun Application.configureRouting() {
    routing {

        get("/set-session") {
            call.sessions.set(MySession("A text"))
            call.respond("Session set")
        }

        get("/get-session") {
            val session = call.sessions.get<MySession>()
            if (session == null) {
                call.respond("Session not set")
            } else {
                call.respond(session.text)
            }
        }

    }
}
