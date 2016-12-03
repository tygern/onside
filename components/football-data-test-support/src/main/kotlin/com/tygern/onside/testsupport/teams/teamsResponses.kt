package com.tygern.onside.testsupport.teams

import org.springframework.core.io.ClassPathResource

fun listResponse() = ClassPathResource("teams/listResponse.json").inputStream.bufferedReader().use { it.readText() }
