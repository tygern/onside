package com.tygern.onside.competitions

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestOperations

class CompetitionClient(
        private val footballUrl: String,
        private val restOperations: RestOperations
) {
    companion object {
        private val LIST_TYPE = object : ParameterizedTypeReference<List<CompetitionResponse>>() {}
    }

    fun list() = restOperations
            .exchange("$footballUrl/competitions", HttpMethod.GET, null, LIST_TYPE)
            .body
            .map(CompetitionResponse::toCompetition)

    fun get(competitionId: Long): Competition? {
        try {
            return restOperations
                    .getForEntity("$footballUrl/competitions/$competitionId", CompetitionResponse::class.java)
                    .body
                    .toCompetition()
        } catch (e: HttpClientErrorException) {
            return null
        }
    }

}

private fun CompetitionResponse.toCompetition() = Competition(
        id = this.id,
        name = this.league,
        year = this.year,
        description = this.caption,
        currentMatchday = this.currentMatchday
)
