package com.tygern.onside.competitions

import com.tygern.onside.competitions.testsupport.CompetitionDispatcher
import com.tygern.onside.competitions.testsupport.configuredRestTempleate
import io.damo.aspen.Test
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions

class CompetitionClientTest : Test({

    val restTemplate = configuredRestTempleate()
    val server = MockWebServer()

    val client = CompetitionClient(
            footballUrl = "http://localhost:8999",
            restOperations = restTemplate
    )

    before {
        server.setDispatcher(CompetitionDispatcher())
        server.start(8999)
    }

    after {
        server.shutdown()
    }

    test("list") {
        val result = client.list()

        Assertions.assertThat(result).isEqualTo(arrayOf(
                Competition(
                        id = 424L,
                        name = "EC",
                        year = "2016",
                        description = "European Championships France 2016",
                        currentMatchday = 7
                ),
                Competition(
                        id = 426L,
                        name = "PL",
                        year = "2016",
                        description = "Premier League 2016/17",
                        currentMatchday = 13
                )
        ).asList())
    }

})
