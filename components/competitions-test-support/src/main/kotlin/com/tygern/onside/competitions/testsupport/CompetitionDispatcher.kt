package com.tygern.onside.competitions.testsupport

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class CompetitionDispatcher : Dispatcher() {
    private val LIST_RESPONSE = listResponse()

    override fun dispatch(request: RecordedRequest?): MockResponse {

        when (request?.path) {
            "/competitions" -> return MockResponse().apply {
                setHeader("Content-Type", "application/json")
                setBody(LIST_RESPONSE)
            }
        }

        return MockResponse().apply { setResponseCode(404) }
    }
}
