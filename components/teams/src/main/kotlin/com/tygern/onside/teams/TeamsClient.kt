package com.tygern.onside.teams

import org.springframework.http.HttpMethod
import org.springframework.web.client.RestOperations

class TeamsClient(
        private val footballUrl: String,
        private val restOperations: RestOperations) {

    fun listForCompetition(competitionId: Long) = restOperations
            .exchange("$footballUrl/competitions/$competitionId/teams", HttpMethod.GET, null, CompetitionTeamResponse::class.java)
            .body
            .teams
            .map(TeamResponse::toTeam)

}

private fun TeamResponse.toTeam(): Team {
    val id = this.links.self.href.split("teams/")[1].toLong()

    return Team(
            id = id,
            name = this.name,
            nickname = this.shortName,
            code = this.code,
            crestUrl = this.crestUrl
    )
}
