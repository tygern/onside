package com.tygern.onside

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.tygern.onside.competitions.CompetitionsClient
import com.tygern.onside.teams.TeamsClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate

@Configuration
open class ClientConfiguration {
    @Value("\${football.url}")
    lateinit var footballUrl: String

    @Bean
    open fun objectMapper() = ObjectMapper().registerKotlinModule()

    @Bean
    open fun restOperations(): RestOperations = RestTemplate()

    @Bean
    open fun competitionsClient(restOperations: RestOperations)
            = CompetitionsClient(footballUrl, restOperations)

    @Bean
    open fun teamsClient(restOperations: RestOperations)
            = TeamsClient(footballUrl, restOperations)
}