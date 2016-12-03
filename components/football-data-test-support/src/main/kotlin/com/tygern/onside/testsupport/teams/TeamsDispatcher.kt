package com.tygern.onside.testsupport.teams

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class TeamsDispatcher : Dispatcher() {

    companion object {
        private val LIST_RESPONSE = listResponse()
    }

    override fun dispatch(request: RecordedRequest?): MockResponse {

        when (request?.path) {
            "/competitions/424/teams" -> return MockResponse().apply {
                setHeader("Content-Type", "application/json")
                setBody(LIST_RESPONSE)
            }
        }

        return MockResponse().apply { setResponseCode(404) }
    }
}
