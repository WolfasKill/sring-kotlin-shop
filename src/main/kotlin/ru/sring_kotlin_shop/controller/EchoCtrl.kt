package ru.sring_kotlin_shop.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/echo")
@Tag(name = "Echo for test")
class EchoCtrl {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private var counter = 0L
    private val MaxValueCounter = 100000L // for yandex_tank test

    @GetMapping("/{mes}")
    @Operation(method = "echo message")
    fun echoStr(
        @PathVariable("mes")
        @Parameter(
            name = "mes",
            description = "any string"
        ) mes: String
    ): String {
        if (counter + 1 > MaxValueCounter) {
            counter = 0L
        } else {
            counter += 1L
        }
        logger.info("$counter GET $mes")
        return mes
    }
}