package com.example

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    val client = HttpClient {
        install(WebSockets)
    }

    runBlocking {
        client.webSocket(port = 8080, path = "/chat"){
            while (true){

                val outgoing = launch {
                    println("Enter string:")
                    val line = readln()
                    if(line.equals("exit")) return@launch
                    send(line)
                }

                val incoming = launch {
                    for (frame in incoming) {
                        frame as? Frame.Text ?: continue
                        println(String(frame.readBytes()))
                    }
                }

                outgoing.join()
                incoming.cancelAndJoin()
            }
        }
    }
}

