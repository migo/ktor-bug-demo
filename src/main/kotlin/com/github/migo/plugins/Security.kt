package com.github.migo.plugins

import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

data class MySession(val text: String)

fun Application.configureSecurity() {

    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            transform(Transformer)
        }
    }
}

object Transformer : SessionTransportTransformer {
    var readValue: String = ""
    var writeValue: String = ""

    override fun transformRead(transportValue: String): String? {
        readValue = transportValue
        return transportValue
    }

    override fun transformWrite(transportValue: String): String {
        writeValue = transportValue
        return transportValue
    }

}