package com.tygern.onside.api

import com.tygern.onside.teams.TeamsClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CompetitionTeamsController @Autowired constructor(val teamsClient: TeamsClient) {
    @GetMapping(path = arrayOf("/competitions/{competitionId}/teams"))
    fun list(@PathVariable competitionId: Long) = teamsClient.listForCompetition(competitionId)
}
