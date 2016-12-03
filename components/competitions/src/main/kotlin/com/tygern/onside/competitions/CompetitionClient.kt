package com.tygern.onside.competitions

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestOperations

class CompetitionClient(
        private val footballUrl: String,
        private val restOperations: RestOperations
) {
    companion object {
        val LIST_TYPE = object : ParameterizedTypeReference<List<CompetitionResponse>>() {}
    }

    fun list(): List<Competition>
            = restOperations.exchange("$footballUrl/competitions", HttpMethod.GET, null, LIST_TYPE)
            .body.map {
        Competition(
                id = it.id,
                name = it.league,
                year = it.year,
                description = it.caption,
                currentMatchday = it.currentMatchday
        )
    }
}
