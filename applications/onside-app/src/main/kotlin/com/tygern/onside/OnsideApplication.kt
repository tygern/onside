package com.tygern.onside

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class OnsideApplication

fun main(args: Array<String>) {
    SpringApplication.run(OnsideApplication::class.java, *args)
}
