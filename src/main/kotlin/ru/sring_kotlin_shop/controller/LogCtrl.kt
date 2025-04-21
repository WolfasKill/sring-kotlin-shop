package ru.sring_kotlin_shop.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.lang.String.format
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("/log")
@Tag(name="Log controller")
class LogCtrl {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    var basedir = Paths.get("").toAbsolutePath().toString()

    val path = "log/spring.log"

    @GetMapping("/")
    @Operation(method="Getting log")
    fun getLog(): String {
        val current = File(basedir + File.separator + path)
        val currentDir = current.getAbsolutePath()
        logger.info(format("Log file %s", currentDir))
        return String(Files.readAllBytes(current.toPath()))
    }

}