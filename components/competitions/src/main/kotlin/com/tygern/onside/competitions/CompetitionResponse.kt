package com.tygern.onside.competitions

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CompetitionResponse(
        val id: Long,
        val caption: String,
        val league: String,
        val year: String,
        val currentMatchday: Int
)
