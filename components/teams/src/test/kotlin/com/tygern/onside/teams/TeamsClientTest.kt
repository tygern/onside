package com.tygern.onside.teams

import com.tygern.onside.testsupport.configuredRestTempleate
import com.tygern.onside.testsupport.teams.TeamsDispatcher
import io.damo.aspen.Test
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions

class TeamsClientTest: Test({

    val restTemplate = configuredRestTempleate()
    var server: MockWebServer? = null

    val client = TeamsClient(
            footballUrl = "http://localhost:8999",
            restOperations = restTemplate
    )

    before {
        server = MockWebServer().apply {
            setDispatcher(TeamsDispatcher())
            start(8999)
        }
    }

    after {
        server?.shutdown()
    }

    test("listForCompetition") {
        val result = client.listForCompetition(424L)

        Assertions.assertThat(result).isEqualTo(arrayOf(
                Team(
                        id = 773L,
                        name = "France",
                        nickname = "Fra",
                        code = "FRA",
                        crestUrl = "https://upload.wikimedia.org/wikipedia/en/c/c3/Flag_of_France.svg"
                ),
                Team(
                        id = 811L,
                        name = "Romania",
                        nickname = "Rom",
                        code = "ROU",
                        crestUrl = "https://upload.wikimedia.org/wikipedia/commons/7/73/Flag_of_Romania.svg"
                )        ).asList())
    }
})
