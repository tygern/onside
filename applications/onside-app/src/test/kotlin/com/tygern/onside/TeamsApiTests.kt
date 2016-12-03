package com.tygern.onside

import com.tygern.onside.teams.Team
import com.tygern.onside.testsupport.teams.TeamsDispatcher
import io.damo.aspen.Test
import io.damo.aspen.spring.SpringTestTreeRunner
import io.damo.aspen.spring.injectValue
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod.GET
import org.springframework.web.client.RestTemplate


@RunWith(SpringTestTreeRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = arrayOf("football.url=http://localhost:8999"))
class TeamsApiTests : Test({

    val port = injectValue("local.server.port", Int::class)
    val restTemplate = RestTemplate()
    var server: MockWebServer? = null

    before {
        server = MockWebServer().apply {
            setDispatcher(TeamsDispatcher())
            start(8999)
        }
    }

    after {
        server?.shutdown()
    }

    test("GET /competitions/:competitionId/teams") {
        val teamListType = object : ParameterizedTypeReference<List<Team>>() {}

        val response = restTemplate.exchange("http://localhost:$port/competitions/424/teams", GET, null, teamListType)

        assertThat(response.body).isEqualTo(arrayOf(
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
                )        ).asList()
        )
    }
})
