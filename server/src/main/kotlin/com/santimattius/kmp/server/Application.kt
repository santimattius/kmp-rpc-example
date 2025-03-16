package com.santimattius.kmp.server

import com.santimattius.kmp.server.bus.EventBus
import com.santimattius.kmp.server.bus.LayoutChanged
import com.santimattius.kmp.server.json.configureSerialization
import com.santimattius.kmp.shared.Layout
import com.santimattius.kmp.shared.LayoutServices
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import kotlinx.rpc.krpc.ktor.server.Krpc
import kotlinx.rpc.krpc.ktor.server.rpc
import kotlinx.rpc.krpc.serialization.json.json

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    install(Krpc)

    installCORS()

    routing {
        get("/ping") {
            call.respond(HttpStatusCode.OK, "pong")
        }

        post("/layout") {
            val bus = EventBus.get()
            val layout = call.receive<Layout>()
            bus.emit(event = LayoutChanged(layout = layout))
            call.respond(HttpStatusCode.OK, "Layout Changed")
        }

        rpc("/api") {
            rpcConfig {
                serialization {
                    json()
                }
            }

            registerService<LayoutServices> { ctx -> ServerLayoutService(ctx) }
        }
    }
}

fun Application.installCORS() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.Upgrade)
        allowNonSimpleContentTypes = true
        allowCredentials = true
        allowSameOrigin = true

        // webpack-dev-server and local development
        val allowedHosts =
            listOf("localhost:3000", "localhost:8080", "localhost:8081", "127.0.0.1:8080")
        allowedHosts.forEach { host ->
            allowHost(host, listOf("http", "https", "ws", "wss"))
        }
    }
}