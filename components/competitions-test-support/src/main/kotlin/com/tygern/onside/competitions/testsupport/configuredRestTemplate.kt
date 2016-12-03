package com.tygern.onside.competitions.testsupport

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

fun configuredRestTempleate() = RestTemplate().apply {
    messageConverters.add(0, MappingJackson2HttpMessageConverter().apply {
        objectMapper = ObjectMapper().registerKotlinModule()
    })
}
