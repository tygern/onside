package com.tygern.onside.competitions

data class Competition(
        val id: Long,
        val name: String,
        val year: String,
        val description: String,
        val currentMatchday: Int
)