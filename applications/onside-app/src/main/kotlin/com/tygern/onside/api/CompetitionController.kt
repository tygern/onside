package com.tygern.onside.api

import com.tygern.onside.competitions.Competition
import com.tygern.onside.competitions.CompetitionClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/competitions")
class CompetitionController @Autowired constructor(val competitionClient: CompetitionClient) {
    @GetMapping
    fun list() = competitionClient.list()

    @GetMapping(path = arrayOf("/{competitionId}"))
    fun get(@PathVariable competitionId: Long): ResponseEntity<Competition?> {
        val competition = competitionClient.get(competitionId)
        val status = if (competition == null) NOT_FOUND else OK

        return ResponseEntity(competition, status)
    }
}
