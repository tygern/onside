package com.tygern.onside

import com.tygern.onside.competitions.Competition
import com.tygern.onside.competitions.testsupport.CompetitionDispatcher
import io.damo.aspen.Test
import io.damo.aspen.spring.SpringTestTreeRunner
import io.damo.aspen.spring.injectValue
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod.GET
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate


@RunWith(SpringTestTreeRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = arrayOf("football.url=http://localhost:8999"))
class CompetitionApiTests : Test({

    val port = injectValue("local.server.port", Int::class)
    val restTemplate = RestTemplate()
    var server: MockWebServer? = null

    before {
        server = MockWebServer().apply {
            setDispatcher(CompetitionDispatcher())
            start(8999)
        }
    }

    after {
        server?.shutdown()
    }

    test("GET /competitions") {
        val competitionListType = object : ParameterizedTypeReference<List<Competition>>() {}

        val response = restTemplate.exchange("http://localhost:$port/competitions", GET, null, competitionListType)

        assertThat(response.body).isEqualTo(arrayOf(
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

    test("GET /competitions/:competitionId") {
        val response = restTemplate.exchange("http://localhost:$port/competitions/424", GET, null, Competition::class.java)

        assertThat(response.body).isEqualTo(
                Competition(
                        id = 424L,
                        name = "EC",
                        year = "2016",
                        description = "European Championships France 2016",
                        currentMatchday = 7
                )
        )
    }

    test("GET /competitions/:competitionId not found") {
        assertThatExceptionOfType(HttpClientErrorException::class.java)
                .isThrownBy({
                    restTemplate.getForEntity("http://localhost:$port/competitions/999", Competition::class.java)
                })
                .withMessageContaining("404 null")
    }
})
