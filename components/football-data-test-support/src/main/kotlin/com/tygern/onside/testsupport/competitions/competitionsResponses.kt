package com.tygern.onside.testsupport.competitions

import org.springframework.core.io.ClassPathResource

fun listResponse() = ClassPathResource("competitions/listResponse.json").inputStream.bufferedReader().use { it.readText() }
fun singleResponse() = ClassPathResource("competitions/singleResponse.json").inputStream.bufferedReader().use { it.readText() }
