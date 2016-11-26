package com.tygern.onside

import com.tygern.onside.competitions.Competition
import io.damo.aspen.Test
import io.damo.aspen.spring.SpringTestTreeRunner
import io.damo.aspen.spring.injectValue
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod.GET
import org.springframework.web.client.RestTemplate


@RunWith(SpringTestTreeRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = arrayOf("football.url=http://localhost:8999"))
class CompetitionApiTests : Test({

    val port = injectValue("local.server.port", Int::class)
    val restTemplate = RestTemplate()

    val server = MockWebServer()

    before {
        server.setDispatcher(CompetitionDispatcher())
        server.start(8999)
    }

    after {
        server.shutdown()
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
})

class CompetitionDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest?): MockResponse {

        when (request?.path) {
            "/competitions" -> return MockResponse().apply {
                setHeader("Content-Type", "application/json")
                //language=JSON
                setBody("""
[
  {
    "_links": {
      "self": {
        "href": "http://api.football-data.org/v1/competitions/424"
      },
      "teams": {
        "href": "http://api.football-data.org/v1/competitions/424/teams"
      },
      "fixtures": {
        "href": "http://api.football-data.org/v1/competitions/424/fixtures"
      },
      "leagueTable": {
        "href": "http://api.football-data.org/v1/competitions/424/leagueTable"
      }
    },
    "id": 424,
    "caption": "European Championships France 2016",
    "league": "EC",
    "year": "2016",
    "currentMatchday": 7,
    "numberOfMatchdays": 7,
    "numberOfTeams": 24,
    "numberOfGames": 51,
    "lastUpdated": "2016-07-10T21:32:20Z"
  },
  {
    "_links": {
      "self": {
        "href": "http://api.football-data.org/v1/competitions/426"
      },
      "teams": {
        "href": "http://api.football-data.org/v1/competitions/426/teams"
      },
      "fixtures": {
        "href": "http://api.football-data.org/v1/competitions/426/fixtures"
      },
      "leagueTable": {
        "href": "http://api.football-data.org/v1/competitions/426/leagueTable"
      }
    },
    "id": 426,
    "caption": "Premier League 2016/17",
    "league": "PL",
    "year": "2016",
    "currentMatchday": 13,
    "numberOfMatchdays": 38,
    "numberOfTeams": 20,
    "numberOfGames": 380,
    "lastUpdated": "2016-11-24T11:00:01Z"
  }
]
"""
                )
            }
        }

        return MockResponse().apply { setResponseCode(404) }
    }

}