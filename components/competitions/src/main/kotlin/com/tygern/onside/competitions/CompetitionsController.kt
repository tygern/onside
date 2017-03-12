package com.tygern.onside.competitions

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/competitions")
class CompetitionsController(val competitionsClient: CompetitionsClient) {
    @GetMapping
    fun list() = competitionsClient.list()

    @GetMapping(path = arrayOf("/{competitionId}"))
    fun get(@PathVariable competitionId: Long): ResponseEntity<Competition?> {
        val competition = competitionsClient.get(competitionId)
        val status = if (competition == null) NOT_FOUND else OK

        return ResponseEntity(competition, status)
    }
}
