package com.tygern.onside.api

import com.tygern.onside.competitions.CompetitionClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/competitions")
class CompetitionController @Autowired constructor(val competitionClient: CompetitionClient) {
    @RequestMapping(method = arrayOf(GET))
    fun list() = competitionClient.list()
}
