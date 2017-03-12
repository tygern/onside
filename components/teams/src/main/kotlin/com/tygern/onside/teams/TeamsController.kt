package com.tygern.onside.teams

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TeamsController(val teamsClient: TeamsClient) {

    @GetMapping(path = arrayOf("/competitions/{competitionId}/teams"))
    fun list(@PathVariable competitionId: Long) = teamsClient.listForCompetition(competitionId)
}
