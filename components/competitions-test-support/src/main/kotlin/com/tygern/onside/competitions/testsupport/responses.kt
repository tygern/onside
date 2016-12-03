package com.tygern.onside.competitions.testsupport

import org.springframework.core.io.ClassPathResource

fun listResponse() = ClassPathResource("listResponse.json").inputStream.bufferedReader().use { it.readText() }
fun singleResponse() = ClassPathResource("singleResponse.json").inputStream.bufferedReader().use { it.readText() }
