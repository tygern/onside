package com.tygern.onside.competitions

import com.tygern.onside.testsupport.competitions.CompetitionsDispatcher
import com.tygern.onside.testsupport.configuredRestTempleate
import io.damo.aspen.Test
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions

class CompetitionsClientTest : Test({

    val restTemplate = configuredRestTempleate()
    var server: MockWebServer? = null

    val client = CompetitionsClient(
            footballUrl = "http://localhost:8999",
            restOperations = restTemplate
    )

    before {
        server = MockWebServer().apply {
            setDispatcher(CompetitionsDispatcher())
            start(8999)
        }
    }

    after {
        server?.shutdown()
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

    test("get") {
        val result = client.get(424L)

        Assertions.assertThat(result).isEqualTo(
                Competition(
                        id = 424L,
                        name = "EC",
                        year = "2016",
                        description = "European Championships France 2016",
                        currentMatchday = 7
                )
        )
    }

    test("get not found") {
        val result = client.get(999L)

        Assertions.assertThat(result).isNull()
    }
})
