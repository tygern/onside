package com.tygern.onside.testsupport.competitions

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class CompetitionsDispatcher : Dispatcher() {

    companion object {
        private val LIST_RESPONSE = listResponse()
        private val SINGLE_RESPONSE = singleResponse()
    }

    override fun dispatch(request: RecordedRequest?): MockResponse {

        when (request?.path) {
            "/competitions" -> return MockResponse().apply {
                setHeader("Content-Type", "application/json")
                setBody(LIST_RESPONSE)
            }

            "/competitions/424" -> return MockResponse().apply {
                setHeader("Content-Type", "application/json")
                setBody(SINGLE_RESPONSE)
            }
        }

        return MockResponse().apply { setResponseCode(404) }
    }
}
