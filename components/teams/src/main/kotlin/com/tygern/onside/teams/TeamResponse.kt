package com.tygern.onside.teams

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CompetitionTeamResponse(
        val teams: List<TeamResponse>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeamResponse(
        @JsonProperty("_links")
        val links: Links,
        val name: String,
        val code: String,
        val shortName: String,
        val crestUrl: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Links(
        val self: Link
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Link(
        val href: String
)
