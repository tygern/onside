package com.tygern.onside.competitions

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestOperations

class CompetitionClient(
        val footballUrl: String,
        val restOperations: RestOperations
) {
    fun list(): List<Competition> {
        val competitionListType = object : ParameterizedTypeReference<List<CompetitionResponse>>() {}

        val response = restOperations.exchange("$footballUrl/competitions", HttpMethod.GET, null, competitionListType)

        return response.body.map {
            Competition(
                    id = it.id,
                    name = it.league,
                    year = it.year,
                    description = it.caption,
                    currentMatchday = it.currentMatchday
            )
        }
    }
}
