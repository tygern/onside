package com.tygern.onside.competitions.testsupport

import org.springframework.core.io.ClassPathResource

fun listResponse() = ClassPathResource("listResponse.json").inputStream.bufferedReader().use { it.readText() }
